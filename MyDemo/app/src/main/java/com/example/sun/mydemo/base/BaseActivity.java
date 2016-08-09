package com.example.sun.mydemo.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by sun on 16/8/9.
 */
public abstract class BaseActivity extends FragmentActivity implements ActivityPageSetting{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent();
        ButterKnife.bind(this);
        setupView();
        setModel();
    }
}
