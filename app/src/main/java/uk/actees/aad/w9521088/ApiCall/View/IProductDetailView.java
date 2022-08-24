package uk.actees.aad.w9521088.ApiCall.View;

import uk.actees.aad.w9521088.ApiCall.Model.ProductDetailResBean;

public interface IProductDetailView extends IUtopperView {
    void onProductDetailSuccess(ProductDetailResBean item);
}
