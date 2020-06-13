package org.mini.yeation.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.activity.base.BaseActivity;
import org.mini.yeation.mall.adapter.viewpager.BaseFragmentPagerStateAdapter;
import org.mini.yeation.mall.domain.Area;
import org.mini.yeation.mall.utils.Event;
import org.mini.yeation.mall.fragment.area_list.AreaListFragment;
import org.mini.yeation.mall.model.SelectAreaModel;
import org.mini.yeation.mall.presenter.SelectAreaPresenter;
import org.mini.yeation.mall.utils.app.StatusBarUtils;

import org.mini.yeation.mall.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.view.SelectAreaView;
import org.mini.yeation.mall.view.pager.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 半透明效果，需要设置主题样式android:theme="@style/AppTheme.Black.Translucent"，所以才用Activity实现
 */
public class SelectAreaActivity extends BaseActivity<SelectAreaPresenter> implements SelectAreaView {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;

    @BindView(R.id.sure_text)
    TextView mSureText;

    List<String> mTitleList;
    List<Fragment> mFragmentList;

    BaseFragmentPagerStateAdapter mAdapter;

    @Override
    public int getViewId() {
        return R.layout.activity_select_area;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mAdapter = new BaseFragmentPagerStateAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    @Override
    public void initData() {
        setSupportEventBus();
        StatusBarUtils.setTranslucentStatus(this); //透明状态栏
        StatusBarUtils.setStatusBarDarkTheme(this, true); //状态栏文字黑色
        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        if (args != null && args.containsKey(Constants.INTENT_KEY1)) {
            mSureText.setEnabled(true);
            String text1 = args.getString(Constants.INTENT_KEY1);
            List<Area> areaList = JsonUtils.toList(text1, Area.class);
            mTitleList = new ArrayList<>();
            mFragmentList = new ArrayList<>();
            for (Area area : areaList) {
                mTitleList.add(area.getName());
                mFragmentList.add(AreaListFragment.newInstance(area.currentLevelList, area));
            }
            mAdapter.setFragment(mTitleList, mFragmentList);
            mViewPager.setOffscreenPageLimit(mFragmentList.size());
        } else {
            mSureText.setEnabled(false);
            getPresenter().queryAreaByLevel(0);
        }
    }

    @Override
    public SelectAreaPresenter createPresenter() {
        return new SelectAreaPresenter(new SelectAreaModel(), this);
    }

    @OnClick(R.id.sure_text)
    public void onClick() {
        Event.SelectAreaEvent event = new Event.SelectAreaEvent();
        List<Area> selectAreaList = new ArrayList<>();
        for (Fragment item : mFragmentList) {
            selectAreaList.add(((AreaListFragment) item).getArea());
        }
        event.areaList = selectAreaList;
        EventBus.getDefault().post(event);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setLevelAreaList(List<Area> areaList) {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("请选择");
        mFragmentList.add(AreaListFragment.newInstance(areaList));
        mAdapter.setFragment(mTitleList, mFragmentList);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubscribeEvent(Event.ClickAreaEvent event) {
        if (event.area.getLevel() + 1 < mTitleList.size()) {
            List<String> titleList = new ArrayList<>();
            List<Fragment> fragmentList = new ArrayList<>();
            for (int i = 0; i < mTitleList.size(); i++) {
                if (i <= event.area.getLevel()) {
                    titleList.add(mTitleList.get(i));
                    fragmentList.add(mFragmentList.get(i));
                }
            }
            mTitleList = titleList;
            mFragmentList = fragmentList;
        }
        mTitleList.set(event.area.getLevel(), event.area.getName());
        mAdapter.setFragment(mTitleList, mFragmentList);
        getPresenter().queryAreaByParentId(event.area.getId());
    }

    @Override
    public void setParentAreaList(List<Area> areaList) {
        if (areaList != null && areaList.size() > 0) {
            mAdapter.addFragment("请选择", AreaListFragment.newInstance(areaList));
            mViewPager.setCurrentItem(mAdapter.getCount(), false);
            mSureText.setEnabled(false);
        } else {
            mSureText.setEnabled(true);
        }
    }

}
