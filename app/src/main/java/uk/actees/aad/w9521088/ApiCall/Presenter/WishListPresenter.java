package uk.actees.aad.w9521088.ApiCall.Presenter;

import android.app.Activity;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.actees.aad.w9521088.ApiCall.Model.WishListResBean;
import uk.actees.aad.w9521088.ApiCall.View.IWishListView;
import uk.actees.aad.w9521088.TopperApp;
import uk.actees.aad.w9521088.Utils.GoogleProgressDialog;

public class WishListPresenter extends BasePresenter<IWishListView>{

    GoogleProgressDialog googleProgressDialog;
    public void onWishlistCall(final Activity context,String accessToken){
        //getView().enableLoadingBar(context,true);
        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        TopperApp.getInstance().getApiService().Wishlist("Bearer "+accessToken).enqueue(new Callback<WishListResBean>() {
            @Override
            public void onResponse(Call<WishListResBean> call, Response<WishListResBean> response) {
                //getView().enableLoadingBar(context,false);
                googleProgressDialog.dismiss();

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onWishlistSuccess(response.body());

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
            public void onFailure(Call<WishListResBean> call, Throwable t) {
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
