package org.mini.yeation.mall.presenter;

import org.mini.yeation.mall.model.HomeModel;
import org.mini.yeation.mall.presenter.base.BasePresenter;
import org.mini.yeation.mall.view.HomeView;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HomePresenter extends BasePresenter<HomeModel, HomeView> {

    public HomePresenter(HomeModel mModel, HomeView mView) {
        super(mModel, mView);
    }

    public void getBadgeCount() {
        getModel().getBadgeCount(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onSuccess(Integer count) {
                getView().setBadgeCount(count);
            }

            @Override
            public void onError(Throwable e) {
                getView().setBadgeCount(0);
            }
        });
    }

}
