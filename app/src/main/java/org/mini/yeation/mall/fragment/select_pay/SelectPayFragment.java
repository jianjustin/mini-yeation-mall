package org.mini.yeation.mall.fragment.select_pay;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.adapter.recyclerview.BaseRecyclerAdapter;
import org.mini.yeation.mall.adapter.recyclerview.LinearDividerItemDecoration;
import org.mini.yeation.mall.adapter.recyclerview.ViewHolder;
import org.mini.yeation.mall.domain.Order;
import org.mini.yeation.mall.domain.base.PayType;
import org.mini.yeation.mall.fragment.base.BaseFragment;
import org.mini.yeation.mall.utils.app.DPUtils;
import org.mini.yeation.mall.R;

import org.mini.yeation.mall.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SelectPayFragment extends BaseFragment<SelectPayPresenter> implements SelectPayView {


    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    BaseRecyclerAdapter<PayType> mAdapter;

    Order mOrder;

    @Override
    public int getViewId() {
        return R.layout.fragment_select_pay;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mAdapter = new BaseRecyclerAdapter<PayType>(R.layout.item_pay) {
            @Override
            protected void convert(ViewHolder viewHolder, PayType item, int position) {
                ImageView select = viewHolder.findViewById(R.id.select);
                ImageView icon = viewHolder.findViewById(R.id.icon);
                TextView title = viewHolder.findViewById(R.id.title);
                if (item.isSelect) {
                    select.setImageResource(R.mipmap.check_pressed);
                } else {
                    select.setImageResource(R.mipmap.check_normal);

                }
                icon.setImageResource(item.icon);
                title.setText(item.title);
            }

            @Override
            protected void onItemClick(PayType item, int position) {
                super.onItemClick(item, position);
                onSingleSelect(item);
            }
        };
        mRecyclerView.addItemDecoration(new LinearDividerItemDecoration(LinearDividerItemDecoration.VERTICAL, DPUtils.dp2px(getResources(), 1)));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public SelectPayPresenter createPresenter() {
        return new SelectPayPresenter(new SelectPayModel(), this);
    }

    @Override
    public void initData() {

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        getToolbar().setTitle("选择支付方式");

        assert getArguments() != null;

        String text1 = getArguments().getString(Constants.INTENT_KEY1);
        mOrder = JsonUtils.toObject(text1, Order.class);

        PayType alipay = new PayType();
        alipay.isSelect = true;
        alipay.icon = R.mipmap.alipay;
        alipay.title = "支付宝";
        alipay.mode = 1;

        PayType wxpay = new PayType();
        wxpay.isSelect = false;
        wxpay.icon = R.mipmap.wxpay;
        wxpay.title = "微信";
        wxpay.mode = 2;

        List<PayType> payItemList = new ArrayList<>();
        payItemList.add(alipay);
        payItemList.add(wxpay);
        mAdapter.replaceAll(payItemList);


    }

    public void onSingleSelect(PayType selectItem) {
        for (PayType item : mAdapter.getData()) {
            if (item.equals(selectItem)) {
                item.isSelect = !item.isSelect;
            } else {
                item.isSelect = false;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.sure_btn)
    public void onClick() {
        getPresenter().alipay((long)mOrder.getId());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    ObservableEmitter mEmitter;

    @Override
    public void alipayResult(final String result) {
        PayTask alipay = new PayTask(getActivity());
        Observable.create(new ObservableOnSubscribe<Map<String, String>>() {
                    @Override
                    public void subscribe(ObservableEmitter<Map<String, String>> emitter) throws Exception {
                        Map<String, String> map = alipay.payV2(result, true);
                        emitter.onNext(map);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Map<String, String> map) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
