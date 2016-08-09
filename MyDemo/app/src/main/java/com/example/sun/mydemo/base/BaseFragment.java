package com.example.sun.mydemo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by sun on 16/8/9.
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    protected LayoutInflater mInflater;
    protected boolean isVisible;
    protected boolean isPrepare;
    protected boolean isLoaded;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            load();
        } else {
            isVisible = false;
        }
    }

    protected void load() {
        if (isVisible && isPrepare && !isLoaded) {
            isLoaded = true;
            setModel();
        }
    }

    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;

        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);
            setupView();

            isPrepare = true;
            load();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayoutId();

    protected abstract void setupView();

    protected abstract void setModel();
}
