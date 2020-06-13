package org.mini.yeation.mall.view;

import org.mini.yeation.mall.domain.Area;

import java.util.List;

public interface SelectAreaView extends BaseView {

    void setLevelAreaList(List<Area> areaList);

    void setParentAreaList(List<Area> areaList);

}
