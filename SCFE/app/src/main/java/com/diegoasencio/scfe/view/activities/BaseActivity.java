package com.diegoasencio.scfe.view.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.diegoasencio.scfe.tools.Constant;

public abstract class BaseActivity extends AppCompatActivity {

    protected SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        preferences = getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    abstract public int getLayout();
}
