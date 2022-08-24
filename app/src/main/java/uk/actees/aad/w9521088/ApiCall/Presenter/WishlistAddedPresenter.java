package uk.actees.aad.w9521088.ApiCall.Presenter;

import android.app.Activity;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.actees.aad.w9521088.ApiCall.Model.WishlistAddedResBean;
import uk.actees.aad.w9521088.ApiCall.View.IWishlistAddedView;
import uk.actees.aad.w9521088.TopperApp;
import uk.actees.aad.w9521088.Utils.GoogleProgressDialog;

public class WishlistAddedPresenter extends BasePresenter<IWishlistAddedView>{
    GoogleProgressDialog googleProgressDialog;
    public void onWishlistAdded(final Activity context,String accessToken,String product_id,String vareint_id){
        //getView().enableLoadingBar(context,true);
        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        TopperApp.getInstance().getApiService().WishlistAdded("Bearer "+accessToken,product_id,vareint_id).enqueue(new Callback<WishlistAddedResBean>() {
            @Override
            public void onResponse(Call<WishlistAddedResBean> call, Response<WishlistAddedResBean> response) {
                //getView().enableLoadingBar(context,false);
                googleProgressDialog.dismiss();
                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onWishListAdded(response.body());

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
            public void onFailure(Call<WishlistAddedResBean> call, Throwable t) {

                try {
                    googleProgressDialog.dismiss();
                    //getView().enableLoadingBar(context, false);
                    t.printStackTrace();
                    getView().onError(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
