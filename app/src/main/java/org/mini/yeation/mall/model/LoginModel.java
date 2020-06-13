package org.mini.yeation.mall.model;


import org.mini.yeation.mall.BaseApplication;
import org.mini.yeation.mall.dao.UserDao;
import org.mini.yeation.mall.domain.User;
import org.mini.yeation.mall.model.base.BaseModel;
import org.mini.yeation.mall.utils.AppDatabaseHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseModel {

    private final UserDao userDao = AppDatabaseHelper.getInstance(BaseApplication.getInstance()).getUserDao();

    /**
     * 获取对应用户
     */
    public void login(String mobile, String password, Consumer<User> consumer, Consumer<Throwable> error){
         userDao.findByName(mobile,password)
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer,error);
    }

}
