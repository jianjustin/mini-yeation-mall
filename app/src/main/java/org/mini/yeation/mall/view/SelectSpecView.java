package org.mini.yeation.mall.view;

import org.mini.yeation.mall.entity.Product;
import org.mini.yeation.mall.view.BaseView;


public interface SelectSpecView extends BaseView {

    void setProduct(Product product);

    void goBack();

}
