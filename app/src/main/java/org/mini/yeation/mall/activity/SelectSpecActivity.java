package org.mini.yeation.mall.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.activity.base.BaseActivity;
import org.mini.yeation.mall.activity.base.ToolbarFragmentActivity;
import org.mini.yeation.mall.adapter.recyclerview.BaseVLayoutAdapter;
import org.mini.yeation.mall.adapter.recyclerview.ViewHolder;
import org.mini.yeation.mall.domain.Goods;
import org.mini.yeation.mall.domain.base.GoodsSpecification;
import org.mini.yeation.mall.domain.base.GoodsSpecificationValue;
import org.mini.yeation.mall.domain.base.GoodsTag;
import org.mini.yeation.mall.domain.base.GoodsProduct;
import org.mini.yeation.mall.fragment.LoginFragment;
import org.mini.yeation.mall.fragment.submit_order.SubmitOrderFragment;
import org.mini.yeation.mall.model.SelectSpecModel;
import org.mini.yeation.mall.presenter.SelectSpecPresenter;
import org.mini.yeation.mall.utils.UserSession;
import org.mini.yeation.mall.utils.app.DPUtils;
import org.mini.yeation.mall.utils.app.StatusBarUtils;

import org.mini.yeation.mall.R;
import org.mini.yeation.mall.utils.app.AppUtils;

import org.mini.yeation.mall.utils.JsonUtils;
import org.mini.yeation.mall.utils.app.ToastUtils;
import org.mini.yeation.mall.domain.CartGoods;
import org.mini.yeation.mall.view.NumberButton;
import org.mini.yeation.mall.view.SelectSpecView;
import org.mini.yeation.mall.view.tag.TagContainerLayout;
import org.mini.yeation.mall.view.tag.TagView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 规格选择Activity
 */
public class SelectSpecActivity extends BaseActivity<SelectSpecPresenter> implements SelectSpecView {

    @BindView(R.id.image)
    ImageView mImage;//商品主图

    @BindView(R.id.price)
    TextView mPrice;

    @BindView(R.id.select)
    TextView mSelect;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    Goods mGoods;

    /**
     * 根据商品信息，规格信息，购买数量组装成待下单商品信息
     */
    GoodsProduct mProduct;

    BaseVLayoutAdapter<GoodsSpecification> mSpecAdapter;


    BaseVLayoutAdapter<String> mBuyNumberAdapter;

    //用户选择的规格
    SparseArray<GoodsTag> selectedTagArr = new SparseArray<>();

    @Override
    public int getViewId() {
        return R.layout.activity_select_spec;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        int space = DPUtils.dp2px(getResources(), 10);
        linearLayoutHelper.setMarginTop(space);
        linearLayoutHelper.setDividerHeight(space);

        //规格属性适配器
        mSpecAdapter = new BaseVLayoutAdapter<GoodsSpecification>(linearLayoutHelper, R.layout.item_spec_lable) {
            @Override
            protected void convert(ViewHolder viewHolder, GoodsSpecification item, int position) {
                TextView name = viewHolder.findViewById(R.id.name);//属性名称
                TagContainerLayout tagLayout = viewHolder.findViewById(R.id.tag_layout);//属性值
                name.setText(item.getSpecificationName());
                List<String> mTagList = GoodsSpecification.getGoodsSpecificationTagList(item);
                tagLayout.setOnTagClickListener(new TagView.OnTagClickListener() {//属性值点击事件
                    @Override
                    public void onTagClick(int tagIdx, String text) {
                        GoodsTag tagBean = selectedTagArr.get(position);
                        //标签选中&取消选中
                        if (tagBean == null) {
                            selectedTagArr.put(position, new GoodsTag(tagIdx, text));
                            tagLayout.selectTagView(tagIdx);
                        } else if (tagIdx == tagBean.getId() && text.equals(tagBean.getName())) {
                            selectedTagArr.remove(position);
                            tagLayout.deselectTagView(tagIdx);
                        } else {
                            tagLayout.deselectTagView(tagBean.getId());
                            selectedTagArr.put(position, new GoodsTag(tagIdx, text));
                            tagLayout.selectTagView(tagIdx);
                        }

                        /*根据商品ID和规格获取对应具体产品*/
                        if (selectedTagArr.size() == mSpecAdapter.getItemCount()) {
                            List<GoodsSpecificationValue> specValueList = new ArrayList<>();
                            //按照顺序添加规格
                            for (GoodsSpecification specItem : mSpecAdapter.getData()) {
                                GoodsSpecificationValue specValue = getTagSpecificationValue(specItem);
                                if(specValue != null)specValueList.add(specValue);
                            }
                            setProduct(GoodsProduct.getGoodsProduct(mGoods,specValueList));
                            //getPresenter().getProductBySpec((long)mGoods.getGoodsId(), specValueList);
                        }
                    }

                    @Override
                    public void onTagLongClick(int tagIdx, String text) {}
                    @Override
                    public void onSelectedTagDrag(int tagIdx, String text) {}
                    @Override
                    public void onTagCrossClick(int tagIdx) {}
                });
                tagLayout.setTags(mTagList);
            }
        };

        SingleLayoutHelper buyNumberHelper = new SingleLayoutHelper();
        buyNumberHelper.setMarginTop(space);
        buyNumberHelper.setMarginBottom(space);
        mBuyNumberAdapter = new BaseVLayoutAdapter<String>(buyNumberHelper, R.layout.item_buy_number) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                NumberButton numberButton = viewHolder.findViewById(R.id.number_btn);
                numberButton.setNumber(item);
            }
        };

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        DelegateAdapter mAdapter = new DelegateAdapter(manager);
        mAdapter.addAdapter(mSpecAdapter);
        mAdapter.addAdapter(mBuyNumberAdapter);
        mRecyclerView.setAdapter(mAdapter);

        //默认数量为1
        mBuyNumberAdapter.add("1");
    }

    @Override
    public void initData() {
        StatusBarUtils.setTranslucentStatus(this); //透明状态栏
        StatusBarUtils.setStatusBarDarkTheme(this, true); //状态栏文字黑色
        Bundle args = getIntent().getExtras();
        if(null ==args || !args.containsKey(Constants.INTENT_KEY1))finish();

        mGoods = JsonUtils.toObject(args.getString(Constants.INTENT_KEY1), Goods.class);
        String selectSpecItem = String.format("请选择 %s", GoodsSpecification.getSelectSpecItem(mGoods.getGoodsSpecification()));

        List<GoodsSpecification> specValueList = new ArrayList<>();
        if(null == mGoods.getGoodsSpecification())//规格为空生成默认规格信息
            specValueList.add(GoodsSpecification.createDefaultGoodsSpecification());
        else
            specValueList = JsonUtils.toList(mGoods.getGoodsSpecification(), GoodsSpecification.class);
        mSpecAdapter.addAll(specValueList);
        AppUtils.loadImage(mGoods.getImage(), mImage);
        mPrice.setText(AppUtils.toRMBFormat(mGoods.getGoodsPrice()));
        mSelect.setText(selectSpecItem);

    }

    @OnClick({R.id.add_cart, R.id.buy, R.id.close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_cart:
                addGoodsToCart();
                break;
            case R.id.buy:
                goSubmitOrder();
                break;
            case R.id.close:
                finish();
                break;
        }
    }

    @Override
    public void setProduct(GoodsProduct product) {
        this.mProduct = product;
        mPrice.setText(AppUtils.toRMBFormat(product.getPrice()));
        mSelect.setText(GoodsSpecification.getSelectSpecValue(product.getSpecificationValues()));
    }

    /**
     * 用户下单
     */
    private void goSubmitOrder() {
        if (this.mProduct == null) {
            ToastUtils.showToast("请选择 " + GoodsSpecification.getSelectSpecItem(mGoods.getGoodsSpecification()));
            return;
        }
        if (UserSession.isLogin()) {
            List<CartGoods> cartList = new ArrayList<>();
            CartGoods cart = new CartGoods();
            cart.setProductId((long)mProduct.getId());
            cart.setQuantity(1);
            cart.setGoodsId((long)mGoods.getGoodsId());
            cart.setGoodsName(mGoods.getGoodsName());
            cart.setImage(mGoods.getImage());
            cart.setGoodsPrice(mProduct.getPrice());
            cart.setSpecificationValues(mProduct.getSpecificationValues());
            cartList.add(cart);
            Bundle args = new Bundle();
            args.putString(Constants.INTENT_KEY1, JsonUtils.toString(cartList));
            ToolbarFragmentActivity.createFragment(this, SubmitOrderFragment.class, args);
        } else {
            ToolbarFragmentActivity.createFragment(this, LoginFragment.class);
        }
    }

    /**
     * 添加商品到购物车
     */
    private void addGoodsToCart(){
        if (this.mProduct == null)
            ToastUtils.showToast("请选择 " + GoodsSpecification.getSelectSpecItem(mGoods.getGoodsSpecification()));
        else
            getPresenter().saveCartWithDB((long)mProduct.getId(), 1);
    }

    public GoodsSpecificationValue getTagSpecificationValue(GoodsSpecification item) {
        for (GoodsSpecificationValue specValue : item.getGoodsSpecificationValueList()) {
            for (int i = 0; i < selectedTagArr.size(); i++) {
                GoodsTag tagBean = selectedTagArr.valueAt(i);
                if (specValue.getSpecificationValueName().equals(tagBean.getName())) {
                    return specValue;
                }
            }
        }
        return null;
    }

    @Override
    public SelectSpecPresenter createPresenter() {
        return new SelectSpecPresenter(new SelectSpecModel(), this);
    }

    @Override
    public void goBack() {
        finish();
    }
    @Override
    public void showLoading() {}
    @Override
    public void hideLoading() {}

}
