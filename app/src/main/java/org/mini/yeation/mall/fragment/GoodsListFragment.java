package org.mini.yeation.mall.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.activity.base.BaseFragmentActivity;
import org.mini.yeation.mall.adapter.recyclerview.BaseRecyclerAdapter;
import org.mini.yeation.mall.adapter.recyclerview.GridDividerItemDecoration;
import org.mini.yeation.mall.adapter.recyclerview.ViewHolder;
import org.mini.yeation.mall.domain.Goods;
import org.mini.yeation.mall.domain.GoodsCategory;
import org.mini.yeation.mall.fragment.base.LazyFragment;
import org.mini.yeation.mall.fragment.GoodsDetailFragment;
import org.mini.yeation.mall.model.GoodsListModel;
import org.mini.yeation.mall.presenter.GoodsListPresenter;
import org.mini.yeation.mall.utils.app.DPUtils;
import org.mini.yeation.mall.R;
import org.mini.yeation.mall.utils.app.AppUtils;

import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.view.GoodsListView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class GoodsListFragment extends LazyFragment<GoodsListPresenter> implements GoodsListView {

    @BindView(R.id.refresh_layout)
    public  RefreshLayout mRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    Integer mPage = 1;
    GoodsCategory mGategory;

    //商品适配器（用于组装商品列表的单个商品）
    BaseRecyclerAdapter<Goods> mAdapter;

    public static GoodsListFragment newInstance(GoodsCategory category) {
        GoodsListFragment fragment = new GoodsListFragment();
        Bundle args = new Bundle();//设置分类信息
        args.putString(Constants.INTENT_KEY1, JsonUtils.toString(category));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getViewId() {
        return R.layout.fragment_goods_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        //商品概述适配器
        mAdapter = new BaseRecyclerAdapter<Goods>(R.layout.item_goods) {
            @Override
            protected void convert(ViewHolder viewHolder, Goods item, int position) {
                //加载单个商品信息
                ImageView image = viewHolder.findViewById(R.id.image);
                TextView caption = viewHolder.findViewById(R.id.caption);
                TextView name = viewHolder.findViewById(R.id.name);
                TextView price = viewHolder.findViewById(R.id.price);
                caption.setText(item.getGoodsCaption());
                name.setText(item.getGoodsName());
                price.setText(AppUtils.toRMBFormat(item.getGoodsPrice()));
                AppUtils.loadImage(item.getImage(), image);
            }

            @Override
            protected void onItemClick(Goods item, int position) {//点击商品进入商品详情
                Bundle args = new Bundle();
                args.putString(Constants.INTENT_KEY1, JsonUtils.toString(item));
                BaseFragmentActivity.createFragmentNewTask(Objects.requireNonNull(getActivity()), GoodsDetailFragment.class, args);
            }
        };
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(DPUtils.dp2px(getResources(), 10)));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        //获取分类信息
        mGategory = JsonUtils.toObject(getArguments().getString(Constants.INTENT_KEY1), GoodsCategory.class);

        //刷新商品列表
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPage = 1;
                getPresenter().queryGoodsByCategory(mGategory.getCategoryid(),  mPage, Constants.LIMIT);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                mPage++;
                getPresenter().queryGoodsByCategory(mGategory.getCategoryid(),  mPage, Constants.LIMIT);
            }
        });
        mRefreshLayout.autoRefresh();
    }

    @Override
    public GoodsListPresenter createPresenter() {
        return new GoodsListPresenter(new GoodsListModel(), this);
    }

    /**
     * 根据分页信息加载商品信息(用于获取商品信息回调)
     */
    @Override
    public void setGoodsList(long currPage, long totalPage, List<Goods> goodsList) {
        if (currPage == 1)
            mAdapter.replaceAll(goodsList);
        else
            mAdapter.addAll(goodsList);
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}
}
