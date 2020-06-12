package org.mini.yeation.mall.presenter.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;


import org.mini.yeation.mall.model.base.IModel;
import org.mini.yeation.mall.view.BaseView;

/**
 * 业务处理层基类
 * @param <M>
 * @param <V>
 * @author zm
 */
public class BasePresenter<M extends IModel, V extends BaseView> implements IPresenter {

    private M mModel;

    private V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
        addObserver();
    }

    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
        addObserver();
    }

    private void addObserver() {
        if (mView != null && mView instanceof LifecycleOwner) {
            ((LifecycleOwner) mView).getLifecycle().addObserver(this);
            if (mModel != null) {
                ((LifecycleOwner) mView).getLifecycle().addObserver(mModel);
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }


    public M getModel() {
        return mModel;
    }

    public V getView() {
        return mView;
    }

}
