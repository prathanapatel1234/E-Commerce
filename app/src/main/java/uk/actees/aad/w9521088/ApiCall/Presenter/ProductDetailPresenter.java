package uk.actees.aad.w9521088.ApiCall.Presenter;

import android.app.Activity;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.actees.aad.w9521088.ApiCall.Model.ProductDetailResBean;
import uk.actees.aad.w9521088.ApiCall.View.IProductDetailView;
import uk.actees.aad.w9521088.TopperApp;

public class ProductDetailPresenter extends BasePresenter<IProductDetailView>{

    public void onProductDetailCall(final Activity context,String accessToken,String product_id){
        getView().enableLoadingBar(context,true);

        TopperApp.getInstance().getApiService().Detail("Bearer "+accessToken,product_id).enqueue(new Callback<ProductDetailResBean>() {
            @Override
            public void onResponse(Call<ProductDetailResBean> call, Response<ProductDetailResBean> response) {
                getView().enableLoadingBar(context,false);

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onProductDetailSuccess(response.body());

                        }else {
                            Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        getView().onError(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    getView().onError(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResBean> call, Throwable t) {
                try {
                    getView().enableLoadingBar(context, false);
                    t.printStackTrace();
                    getView().onError(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
