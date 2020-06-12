package org.mini.yeation.mall.fragment.goods;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import org.mini.yeation.mall.R;

import org.mini.yeation.mall.adapter.viewpager.BaseFragmentPagerAdapter;
import org.mini.yeation.mall.entity.GoodsCategory;
import org.mini.yeation.mall.fragment.base.BaseFragment;
import org.mini.yeation.mall.fragment.goods_list.GoodsListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商品列表
 */
public class GoodsFragment extends BaseFragment<GoodsPresenter> implements GoodsView {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    List<String> mTitleList;
    List<Fragment> mFragmentList;
    List<GoodsCategory> mCategoryList;

    BaseFragmentPagerAdapter mAdapter;

    @Override
    public int getViewId() {
        return R.layout.fragment_goods;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    @Override
    public void initData() {
        getPresenter().queryTreeCategory();
    }

    @Override
    public GoodsPresenter createPresenter() {
        return new GoodsPresenter(new GoodsModel(), this);
    }

    @Override
    public void setCategoryTitle(List<GoodsCategory> categoryList) {
        if (mViewPager != null && categoryList != null && categoryList.size() > 0) {
            this.mCategoryList = categoryList;
            this.mTitleList = new ArrayList<>();
            this.mFragmentList = new ArrayList<>();
            for (GoodsCategory item : this.mCategoryList) {
                mTitleList.add(item.name);
                mFragmentList.add(GoodsListFragment.newInstance(item));
            }
            mAdapter.setFragment(mTitleList, mFragmentList);
            mViewPager.setOffscreenPageLimit(mFragmentList.size());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
