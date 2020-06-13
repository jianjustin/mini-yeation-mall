package org.mini.yeation.mall.fragment.address;

import org.mini.yeation.mall.domain.Address;
import org.mini.yeation.mall.view.BaseView;

import java.util.List;

public interface AddressView extends BaseView {

    void setAddressList(List<Address> addressList);
}
