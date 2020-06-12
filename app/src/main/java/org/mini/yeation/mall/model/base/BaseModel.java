package org.mini.yeation.mall.model.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * 数据层基类
 * @author zm
 */
public class BaseModel implements IModel {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }

}
