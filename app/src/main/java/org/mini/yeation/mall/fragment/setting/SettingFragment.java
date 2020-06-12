package org.mini.yeation.mall.fragment.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.mini.yeation.mall.entity.Event;
import org.mini.yeation.mall.fragment.base.BaseFragment;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.utils.app.DPUtils;
import org.mini.yeation.mall.R;
import org.mini.yeation.mall.utils.app.AppUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mini.yeation.mall.utils.network.GlideCacheUtil;
import org.mini.yeation.mall.view.CommonDialog;
import org.mini.yeation.mall.view.row.RowSettingText;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingFragment extends BaseFragment {

    @BindView(R.id.exit_btn)
    Button mExitBtn;

    @BindView(R.id.clear_cache)
    RowSettingText mClearCache;

    @Override
    public int getViewId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mClearCache.setStatus(GlideCacheUtil.getInstance().getCacheSize(getContext()));
        mClearCache.setRightImage(false);
        int space = DPUtils.dp2px(getResources(), 16);
        mClearCache.setStatusPadding(0, 0, space, 0);
    }

    @Override
    public void initData() {
        setSupportEventBus();
        getToolbar().setTitle("设置");
        if (AppUtils.isLogin()) {
            mExitBtn.setVisibility(View.VISIBLE);
        } else {
            mExitBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.clear_cache, R.id.problem, R.id.feedback, R.id.about, R.id.exit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_cache:
                CommonDialog dialog = new CommonDialog(getContext());
                dialog.setTitle("清理缓存");
                dialog.setContent("你确认要清理应用缓存？");
                dialog.setOnClickCancelListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                dialog.setOnClickSureListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GlideCacheUtil.getInstance().clearCache(getContext());
                    }
                });
                dialog.show();
                break;
            case R.id.problem:
                break;
            case R.id.feedback:
                break;
            case R.id.about:
                break;
            case R.id.exit_btn:
                AppUtils.exitLogin();
                finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubscribeEvent(Event.ClearCacheEvent event) {
        mClearCache.setStatus(GlideCacheUtil.getInstance().getCacheSize(getContext()));
    }

}
