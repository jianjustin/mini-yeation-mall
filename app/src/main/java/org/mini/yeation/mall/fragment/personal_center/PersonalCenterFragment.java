package org.mini.yeation.mall.fragment.personal_center;

import android.os.Bundle;

import org.mini.yeation.mall.entity.Member;
import org.mini.yeation.mall.fragment.base.BaseFragment;
import org.mini.yeation.mall.presenter.base.IPresenter;
import org.mini.yeation.mall.utils.app.DPUtils;
import org.mini.yeation.mall.R;
import org.mini.yeation.mall.utils.app.AppUtils;
import org.mini.yeation.mall.view.row.RowSettingText;

import butterknife.BindView;

public class PersonalCenterFragment extends BaseFragment {


    @BindView(R.id.user_id)
    RowSettingText mUserId;

    @BindView(R.id.phone)
    RowSettingText mPhone;

    @Override
    public int getViewId() {
        return R.layout.fragment_personal_center;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        getToolbar().setTitle("个人中心");
        Member member = AppUtils.getMember();

        mUserId.setStatus(member.sn);
        mUserId.setRightImage(false);
        int space = DPUtils.dp2px(getResources(), 16);
        mUserId.setStatusPadding(0, 0, space, 0);
        mPhone.setStatus(AppUtils.formatPhone(member.mobile));
        mPhone.setRightImage(false);
        mPhone.setStatusPadding(0, 0, space, 0);
    }

    @Override
    public IPresenter createPresenter() {
        return null;
    }

}
