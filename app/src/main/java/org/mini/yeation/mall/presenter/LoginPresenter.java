package org.mini.yeation.mall.presenter;

import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.domain.User;
import org.mini.yeation.mall.utils.Event;
import org.mini.yeation.mall.model.LoginModel;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.utils.UserSession;


import org.greenrobot.eventbus.EventBus;
import org.mini.yeation.mall.domain.CartGoods;
import org.mini.yeation.mall.view.LoginView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录Presenter
 */
public class LoginPresenter extends BasePresenter<LoginModel, LoginView> {

    public LoginPresenter(LoginModel mModel, LoginView mView) {
        super(mModel, mView);
    }

    public void login(String mobile, String password) {
        getModel().login(mobile, password, new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                UserSession.saveSession(user);//生成会话
                EventBus.getDefault().post(new Event.LoginEvent());//发射登录事件
                //获取用户对应购物车，订单
                getView().goHome();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });


        /*
        getModel().login(params, new BaseResponse() {
            @Override
            public void onSuccess(ResultBean bean) {
                JSONObject data = bean.getJSONObject();
                String expire = data.optString("expire");
                String token = data.optString("token");
                JSONObject jsonMember = data.optJSONObject("member");
                String memberId = jsonMember.optString("id");
                String mobile = jsonMember.optString("mobile");
                String sn = jsonMember.optString("sn");
                Member member = new Member();
                member.expire = expire;
                member.token = token;
                member.memberId = memberId;
                member.mobile = mobile;
                member.sn = sn;
                AppUtils.saveMember(member);

                //发射登录事件
                EventBus.getDefault().post(new Event.LoginEvent());

                JSONObject jsonCart = jsonMember.optJSONObject("cart");
                if (jsonCart != null) {
                    String cartKey = jsonCart.optString("cartKey");
                    String jsonList = jsonCart.optString("list");
                    List<CartItemProduct> cartItemProductList = JsonUtils.toList(jsonList, CartItemProduct.class);
                    if (cartItemProductList != null) {
                        List<Cart> cartList = new ArrayList<>();
                        for (CartItemProduct item : cartItemProductList) {
                            Cart cart = new Cart();
                            cart.setCartItemId(item.id);
                            cart.setProductId(item.productId);
                            cart.setQuantity(item.quantity);
                            cart.setCartKey(cartKey);
                            cart.setName(item.name);
                            cart.setImage(item.image);
                            cart.setPrice(item.price);
                            cart.setSpecificationValues(item.specificationValues);
                            cartList.add(cart);
                        }
                        saveDB(cartList);
                    } else {
                        getView().goHome();
                    }
                } else {
                    getView().goHome();
                }
            }

            @Override
            public void onError(String errMsg) {

            }
        });

         */
    }

    private void saveDB(List<CartGoods> cartList) {
        Observable.empty()
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        BaseApplication.getInstance().getCartDao().addOrReplaceList(cartList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        EventBus.getDefault().post(new Event.CartEvent());
                        getView().goHome();
                    }
                })
                .subscribe();
    }

}
