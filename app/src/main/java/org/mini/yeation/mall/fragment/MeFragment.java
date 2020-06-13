package org.mini.yeation.mall.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.mini.yeation.mall.R;

import org.mini.yeation.mall.Constants;
import org.mini.yeation.mall.activity.base.ToolbarFragmentActivity;
import org.mini.yeation.mall.entity.Event;
import org.mini.yeation.mall.fragment.address.AddressFragment;
import org.mini.yeation.mall.fragment.base.BaseFragment;
import org.mini.yeation.mall.fragment.order.OrderFragment;
import org.mini.yeation.mall.fragment.personal_center.PersonalCenterFragment;
import org.mini.yeation.mall.fragment.setting.SettingFragment;
import org.mini.yeation.mall.presenter.base.IPresenter;
import org.mini.yeation.mall.utils.UserSession;
import org.mini.yeation.mall.utils.app.AppUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页｜我的
 *
 */
public class MeFragment extends BaseFragment {

    @BindView(R.id.photo)
    ImageView mPhoto;//头像

    @BindView(R.id.go_login)
    TextView mGoLogin;//用户名 或 登录提示

    @Override
    public int getViewId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        setSupportEventBus();
        onEvent(new Event.LoginEvent());
    }

    @Override
    public IPresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.photo, R.id.go_login, R.id.un_pay_order, R.id.un_send_order, R.id.un_receive_order, R.id.finished_order, R.id.address_mgr, R.id.help, R.id.setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo:
                if (UserSession.isLogin()) {
                    ToolbarFragmentActivity.createFragment(requireContext(), PersonalCenterFragment.class);
                } else {
                    ToolbarFragmentActivity.createFragment(requireContext(), LoginFragment.class);
                }
                break;
            case R.id.go_login:
                if (!UserSession.isLogin()) {
                    ToolbarFragmentActivity.createFragment(requireContext(), LoginFragment.class, null);
                }
                break;
            case R.id.un_pay_order:
                if (UserSession.isLogin()) {
                    Bundle args = new Bundle();
                    args.putInt(Constants.INTENT_KEY1, 0);
                    ToolbarFragmentActivity.createFragment(requireContext(), OrderFragment.class, args);
                } else {
                    ToolbarFragmentActivity.createFragment(requireContext(), LoginFragment.class);
                }
                break;
            case R.id.un_send_order:
                if (UserSession.isLogin()) {
                    Bundle args = new Bundle();
                    args.putInt(Constants.INTENT_KEY1, 1);
                    ToolbarFragmentActivity.createFragment(requireContext(), OrderFragment.class, args);
                } else {
                    ToolbarFragmentActivity.createFragment(requireContext(), LoginFragment.class);
                }
                break;
            case R.id.un_receive_order:
                if (UserSession.isLogin()) {
                    Bundle args = new Bundle();
                    args.putInt(Constants.INTENT_KEY1, 2);
                    ToolbarFragmentActivity.createFragment(requireContext(), OrderFragment.class, args);
                } else {
                    ToolbarFragmentActivity.createFragment(requireContext(), LoginFragment.class);
                }
                break;
            case R.id.finished_order:
                if (UserSession.isLogin()) {
                    Bundle args = new Bundle();
                    args.putInt(Constants.INTENT_KEY1, 3);
                    ToolbarFragmentActivity.createFragment(requireContext(), OrderFragment.class, args);
                } else {
                    ToolbarFragmentActivity.createFragment(requireContext(), LoginFragment.class);
                }
                break;
            case R.id.address_mgr:
                if (UserSession.isLogin()) {
                    ToolbarFragmentActivity.createFragment(requireContext(), AddressFragment.class);
                } else {
                    ToolbarFragmentActivity.createFragment(requireContext(), LoginFragment.class);
                }
                break;
            case R.id.help:
                break;
            case R.id.setting:
                ToolbarFragmentActivity.createFragment(requireContext(), SettingFragment.class);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event.LoginEvent event) {
        if (UserSession.isLogin()) {
            mGoLogin.setText(UserSession.getUser().getUsername());
        } else {
            mGoLogin.setText("登录/注册 >");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Event.CartEvent event) {

    }

}
