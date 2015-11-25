package com.example.skinmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helper.Consts;
import com.example.helper.ResourceHelper;

/**
 * @EdisonChang
 * to test this change skin method, you may create a signed apk plugin
 * and the signature of plugin apk must be the same with host apk
 */
public class ApkShareIdActivity extends ActionBarActivity {

    private Button btnChange;
    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_share_id);

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

    private void changeSkin() {
        Context context = null;
        try {
            context = createPackageContext(Consts.SKIN_PLUGIN_PACKAGE_NAME, Context.CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //make sure id in the skin is same with host apk
        if (context != null) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.bg1);
            imageView.setImageDrawable(drawable);

            //string id in skin1.apk is not the same as host apk
            //so we getIdentifier id by resource name
            String desc = ResourceHelper.getString(context.getResources(), "skin_info");
            textView.setText(desc);
        }
    }
}
