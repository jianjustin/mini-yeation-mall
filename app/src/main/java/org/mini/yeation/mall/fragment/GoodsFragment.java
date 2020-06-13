package org.mini.yeation.mall.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import org.mini.yeation.mall.R;

import org.mini.yeation.mall.adapter.viewpager.BaseFragmentPagerAdapter;
import org.mini.yeation.mall.domain.GoodsCategory;
import org.mini.yeation.mall.fragment.base.BaseFragment;
import org.mini.yeation.mall.fragment.GoodsListFragment;
import org.mini.yeation.mall.model.GoodsModel;
import org.mini.yeation.mall.presenter.GoodsPresenter;
import org.mini.yeation.mall.view.GoodsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 首页｜商品列表
 */
public class GoodsFragment extends BaseFragment<GoodsPresenter> implements GoodsView {

    @BindView(R.id.tab_layout)
    public TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    public ViewPager mViewPager;

    public List<String> mTitleList = new ArrayList<>();;
    public List<Fragment> mFragmentList = new ArrayList<>();;
    public List<GoodsCategory> mCategoryList;

    public BaseFragmentPagerAdapter mAdapter;//分类展示商品适配器

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
        getPresenter().queryRootCategory();//初始化分类信息
    }

    @Override
    public GoodsPresenter createPresenter() {
        return new GoodsPresenter(new GoodsModel(), this);
    }

    /**
     * 获取分类信息后生成分类展示界面
     * @param categoryList
     */
    @Override
    public void setCategoryTitle(List<GoodsCategory> categoryList) {
        if(null == categoryList || categoryList.size() == 0)return;
        this.mCategoryList = categoryList;//获取分类
        for (GoodsCategory item : this.mCategoryList) {
            mTitleList.add(item.getCategoryName());
            mFragmentList.add(GoodsListFragment.newInstance(item));//根据分类信息获取商品列表
        }
        mAdapter.setFragment(mTitleList, mFragmentList);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
    }

    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

}
