package org.mini.yeation.mall.activity;

import android.os.Bundle;

import org.mini.yeation.mall.R;

import org.mini.yeation.mall.activity.base.BaseActivity;
import org.mini.yeation.mall.presenter.base.IPresenter;

/**
 * 应用入口Activity
 */
public class SplashActivity extends BaseActivity {

    @Override
    public int getViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public IPresenter createPresenter() {
        return null;
    }

    @Override
    public void initData() {
        goIntent(HomeActivity.class);
        finish();
    }

}
