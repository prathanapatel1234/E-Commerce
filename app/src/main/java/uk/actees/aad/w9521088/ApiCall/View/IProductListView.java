package uk.actees.aad.w9521088.ApiCall.View;

import uk.actees.aad.w9521088.ApiCall.Model.ProductListResBean;

public interface IProductListView extends IUtopperView{
    void onProductListSuccess(ProductListResBean item);
}
