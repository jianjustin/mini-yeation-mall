package org.mini.yeation.mall.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.activity.SelectSpecActivity;
import org.mini.yeation.mall.activity.base.BaseFragmentActivity;
import org.mini.yeation.mall.adapter.viewpager.BaseBannerPagerAdapter;
import org.mini.yeation.mall.domain.base.GoodsImage;
import org.mini.yeation.mall.domain.base.GoodsSpecification;
import org.mini.yeation.mall.utils.Event;
import org.mini.yeation.mall.domain.Goods;
import org.mini.yeation.mall.fragment.base.BaseFragment;
import org.mini.yeation.mall.fragment.cart.CartFragment;
import org.mini.yeation.mall.model.GoodsDetailModel;
import org.mini.yeation.mall.presenter.GoodsDetailPresenter;
import org.mini.yeation.mall.utils.app.StatusBarUtils;
import org.mini.yeation.mall.R;
import org.mini.yeation.mall.utils.app.AppUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.view.GoodsDetailView;
import org.mini.yeation.mall.view.pager.BannerViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class GoodsDetailFragment extends BaseFragment<GoodsDetailPresenter> implements GoodsDetailView {

    @BindView(R.id.banner_view_pager)
    BannerViewPager mBannerViewPager;

    @BindView(R.id.go_cart)
    TextView mGoCart;//加入购物车

    @BindView(R.id.indicator)
    TextView mIndicator;

    @BindView(R.id.price)
    TextView mPrice;

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.select_value)
    TextView mSelectValue;

    @BindView(R.id.address_value)
    TextView mAddressValue;

    @BindView(R.id.freight_value)
    TextView mFreightValue;

    Goods mGoods;

    Badge mBdage;

    @Override
    public int getViewId() {
        return R.layout.fragment_goods_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        assert getArguments() != null;
        String text1 = getArguments().getString(Constants.INTENT_KEY1);
        mGoods = JsonUtils.toObject(text1, Goods.class);
        //加载商品图片组
        List<GoodsImage> data = JsonUtils.toList(mGoods.getGoodsImages(), GoodsImage.class);
        mBannerViewPager.setAdapter(new BaseBannerPagerAdapter<GoodsImage>(data) {

            @Override
            public void convert(ImageView image, GoodsImage item, int position) {
                image.setScaleType(ImageView.ScaleType.MATRIX);
                AppUtils.loadImage(item.getUrl(), image);
            }

        });
        mIndicator.setText(String.format("%d/%d", mBannerViewPager.getCurrentItem() + 1, mBannerViewPager.getCount()));
        mBannerViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                mIndicator.setText(String.format("%d/%d", i + 1, mBannerViewPager.getCount()));
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });
    }

    @Override
    public void initData() {
        setSupportEventBus();
        StatusBarUtils.setTranslucentStatus(getActivity()); //透明状态栏
        StatusBarUtils.setStatusBarDarkTheme(getActivity(), true); //状态栏文字黑色
        mPrice.setText(AppUtils.toRMBFormat(mGoods.getGoodsPrice()));
        mName.setText(mGoods.getGoodsName());
        mSelectValue.setText(String.format("请选择 %s", GoodsSpecification.getSelectSpecItem(mGoods.getGoodsSpecification())));
        mAddressValue.setText("请选择地址");
        mFreightValue.setText("免运费");
        mBdage = new QBadgeView(getContext()).bindTarget(mGoCart);
        getPresenter().getBadgeCount();
    }

    @Override
    public GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter(new GoodsDetailModel(), this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.back, R.id.select_value, R.id.address_value, R.id.freight_value, R.id.contact, R.id.go_cart, R.id.add_cart, R.id.buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.select_value://点击规格选择
                Bundle args = new Bundle();
                args.putString(Constants.INTENT_KEY1, JsonUtils.toString(mGoods));
                goIntent(SelectSpecActivity.class, args);
                break;
            case R.id.address_value:
                break;
            case R.id.freight_value:
                break;
            case R.id.contact:
                break;
            case R.id.go_cart://进入我的购物车
                args = new Bundle();
                args.putBoolean(Constants.INTENT_KEY1, true);
                BaseFragmentActivity.createFragmentNewTask(requireContext(), CartFragment.class, args);
                break;
            case R.id.add_cart://加入购物车
                args = new Bundle();
                args.putString(Constants.INTENT_KEY1, JsonUtils.toString(mGoods));
                goIntent(SelectSpecActivity.class, args);
                break;
            case R.id.buy://直接进入下单
                args = new Bundle();
                args.putString(Constants.INTENT_KEY1, JsonUtils.toString(mGoods));
                goIntent(SelectSpecActivity.class, args);
                break;
        }
    }

    @Override
    public void setBadgeCount(int count) {
        mBdage.setBadgeNumber(count);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSubscribeEvent(Event.CartEvent event) {
        getPresenter().getBadgeCount();
    }
}
