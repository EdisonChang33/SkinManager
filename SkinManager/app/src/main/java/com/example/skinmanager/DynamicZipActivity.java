package com.example.skinmanager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helper.SkinUtils;

/**
 * @EdisonChang
 * put one picture named "bg1.jpg" in /sdcard/skin_zip/
 * before you test
 */
public class DynamicZipActivity extends ActionBarActivity {

    private static final String BG_NAME = "bg1.jpg";

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
        //put bg1.jpg in /sdcard/skin_zip/ before you test this demo
        Drawable drawable = SkinUtils.getDrawable(getApplicationContext(), BG_NAME, 0, 0);
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
            textView.setText(R.string.skin_info2);
        }
    }
}
