package com.example.skinmanager;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helper.Consts;
import com.example.helper.ResourceHelper;
import com.example.helper.SkinUtils;

import java.lang.reflect.Method;

/**
 * @EdisonChang put the skin plugin apk (skin1 application) in /sdcard/skin_apk/
 * named "skin_plugin1.apk", before you test
 */
public class ApkPluginActivity extends ActionBarActivity {

    private Button btnChange;
    private TextView textView;
    private ImageView imageView;
    private Resources mResources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_share_id);

        initResource();
        btnChange = (Button) findViewById(R.id.button_change);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.image);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeSkin();
            }
        });
    }

    private void initResource() {
        AssetManager assetManager = null;
        try {
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod(
                    "addAssetPath", String.class);
            addAssetPath.invoke(assetManager, getApkFullPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (assetManager != null) {
            mResources = new Resources(assetManager, super.getResources().getDisplayMetrics(),
                    super.getResources().getConfiguration());
        }
    }

    private String getApkFullPath() {
        return SkinUtils.getApkPluginPath() + Consts.SKIN_PLUGIN_FILE_NAME;
    }

    private void changeSkin() {
        //in this case drawable id in plugin is same as host apk
        //so we can use id
        // cheers!
        Drawable drawable = mResources.getDrawable(R.drawable.bg1);
        String desc = ResourceHelper.getString(mResources, "skin_info");
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
            textView.setText(desc);
        }
    }
}
