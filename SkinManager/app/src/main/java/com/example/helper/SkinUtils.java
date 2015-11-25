package com.example.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author EdisonChang
 */
public class SkinUtils {

    protected static final Map<String, Drawable> mapDynamicDrawable = new HashMap<>();

    public static Drawable getDrawable(Context context, String res, int width, int height) {
        if (TextUtils.isEmpty(res))
            return null;

        Drawable drawable = mapDynamicDrawable.get(res);
        String selectedSkinPath = getSkinZipPath();
        if (drawable == null) {
            if (!res.endsWith("xml")) {
                String path = selectedSkinPath + res;
                Bitmap bm = getBitmapFromFile(new File(path), width, height);
                if (bm != null) {
                    drawable = new BitmapDrawable(context.getResources(), bm);
                    mapDynamicDrawable.put(res, drawable);
                }
            } else {
                String res_schemas = res.split("\\.")[0];
                String res_normal = res_schemas + "_normal.png";
                String res_press = res_schemas + "_pressed.png";
                String res_normal_path = selectedSkinPath + res_normal;
                String res_press_path = selectedSkinPath + res_press;
                Bitmap bm_normal = getBitmapFromFile(new File(res_normal_path), width, height);
                Bitmap bm_press = getBitmapFromFile(new File(res_press_path), width, height);
                if (bm_normal != null && bm_press != null) {
                    Drawable dw_normal = new BitmapDrawable(context.getResources(), bm_normal);
                    Drawable dw_press = new BitmapDrawable(context.getResources(), bm_press);
                    drawable = new StateListDrawable();
                    ((StateListDrawable) drawable).addState(new int[]{android.R.attr.state_focused, android.R.attr.state_pressed}, dw_press);
                    ((StateListDrawable) drawable).addState(new int[]{}, dw_normal);
                    mapDynamicDrawable.put(res, drawable);
                }
            }
        }
        return drawable;
    }

    public static String getSkinZipPath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "skin_zip" + File.separator;
    }

    public static String getApkPluginPath() {
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "skin_apk" + File.separator;
    }

    private static Bitmap getBitmapFromFile(File dst, int width, int height) {
        if (null != dst && dst.exists()) {
            BitmapFactory.Options opts = null;
            if (width > 0 && height > 0) {
                opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(dst.getPath(), opts);
                final int minSideLength = Math.min(width, height);
                opts.inSampleSize = computeSampleSize(opts, minSideLength, width * height);
                opts.inJustDecodeBounds = false;
                opts.inInputShareable = true;
                opts.inPurgeable = true;
            }
            try {
                return BitmapFactory.decodeFile(dst.getPath(), opts);
            } catch (OutOfMemoryError e) {
                try {
                    return BitmapFactory.decodeFile(dst.getPath(), opts);
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }


    private static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = maxNumOfPixels == -1 ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = minSideLength == -1 ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if (maxNumOfPixels == -1 && minSideLength == -1) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
}
