package uk.actees.aad.w9521088.ApiCall.Presenter;

import android.app.Activity;
import android.widget.Toast;

import uk.actees.aad.w9521088.ApiCall.Model.ProductListResBean;
import uk.actees.aad.w9521088.ApiCall.View.IProductListView;
import uk.actees.aad.w9521088.TopperApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListPresenter extends BasePresenter<IProductListView>{
    public void ProductListCall(final Activity context ,String accessToken){
        getView().enableLoadingBar(context,true);

        TopperApp.getInstance().getApiService().ProductList("Bearer "+accessToken).enqueue(new Callback<ProductListResBean>() {
            @Override
            public void onResponse(Call<ProductListResBean> call, Response<ProductListResBean> response) {
                getView().enableLoadingBar(context,false);

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onProductListSuccess(response.body());

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
            public void onFailure(Call<ProductListResBean> call, Throwable t) {
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
