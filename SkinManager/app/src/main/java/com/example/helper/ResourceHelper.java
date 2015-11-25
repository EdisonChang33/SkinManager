package com.example.helper;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * @author EdisonChang
 */
public class ResourceHelper {

    private static int getIdentifier(Resources resource, String type, String name) {
        return resource.getIdentifier(name, type, Consts.SKIN_PLUGIN_PACKAGE_NAME);
    }

    public static int getId(Resources resource,String name) {
        return getIdentifier(resource, Consts.R.ID, name);
    }

    public static String getString(Resources resource, String name) {
        return resource.getString(getIdentifier(resource, Consts.R.STRING, name));
    }

    public static int getColor(Resources resource, String name) {
        return resource.getColor(getIdentifier(resource, Consts.R.COLOR, name));
    }

    public static Drawable getDrawable(Resources resource, String name) {
        return resource.getDrawable(getIdentifier(resource, Consts.R.DRAWABLE, name));
    }
}
