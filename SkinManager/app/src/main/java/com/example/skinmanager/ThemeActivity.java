package com.example.skinmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * @EdisonChang
 */
public class ThemeActivity extends ActionBarActivity {

    private static final String FILE_NAME = "theme";
    private static final String KEY_THEME = "appTheme";

    private Button btnChange;
    private int mTheme;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //use setTheme before super onCreate
        mTheme = getCustomTheme();
        setTheme(mTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        btnChange = (Button) findViewById(R.id.button_change);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reLoad();
            }
        });
    }

    private int getCustomTheme() {
        if (mSharedPreferences == null)
            mSharedPreferences = this.getApplicationContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(KEY_THEME, R.style.AppTheme1);
    }

    private void saveTheme() {
        if (mTheme == R.style.AppTheme1) {
            mTheme = R.style.AppTheme2;
        } else {
            mTheme = R.style.AppTheme1;
        }

        if (mSharedPreferences == null)
            mSharedPreferences = this.getApplicationContext().getSharedPreferences(FILE_NAME,
                    Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(KEY_THEME, mTheme);
        editor.apply();
    }

    private void reLoad() {
        saveTheme();
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
