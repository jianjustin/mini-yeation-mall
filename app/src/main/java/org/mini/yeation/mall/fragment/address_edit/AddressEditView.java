package org.mini.yeation.mall.fragment.address_edit;

import org.mini.yeation.mall.entity.Area;
import org.mini.yeation.mall.view.BaseView;

import java.util.List;

public interface AddressEditView extends BaseView {

    void saveUpdateSuccess();

    void setSelectAreaList(List<Area> areaList);

}
