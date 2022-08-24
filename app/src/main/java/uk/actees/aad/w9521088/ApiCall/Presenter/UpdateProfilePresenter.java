package uk.actees.aad.w9521088.ApiCall.Presenter;

import android.app.Activity;
import android.widget.Toast;

import uk.actees.aad.w9521088.ApiCall.Model.UpdateProfileResBean;
import uk.actees.aad.w9521088.ApiCall.View.IUpdateProfileView;
import uk.actees.aad.w9521088.TopperApp;
import uk.actees.aad.w9521088.Utils.GoogleProgressDialog;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfilePresenter extends BasePresenter<IUpdateProfileView>{

    GoogleProgressDialog googleProgressDialog;
    public void UpdateProfile(final Activity context, String accessToken, String name, String mobile,
                                MultipartBody.Part captureMediaFileProfile){

        //getView().enableLoadingBar(context,true);
        googleProgressDialog = new GoogleProgressDialog(context);
        googleProgressDialog.showDialog();

        RequestBody reqUserName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody reqMobile = RequestBody.create(MediaType.parse("multipart/form-data"), mobile);

        TopperApp.getInstance().getApiService().UpdateProfile("Bearer "+accessToken,reqUserName,reqMobile,captureMediaFileProfile).enqueue(new Callback<UpdateProfileResBean>() {
            @Override
            public void onResponse(Call<UpdateProfileResBean> call, Response<UpdateProfileResBean> response) {
                //getView().enableLoadingBar(context,false);
                googleProgressDialog.dismiss();
                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onUpdateProfilesSuccess(response.body());

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
            public void onFailure(Call<UpdateProfileResBean> call, Throwable t) {
                try {

                    //getView().enableLoadingBar(context, false);
                    googleProgressDialog.dismiss();
                    t.printStackTrace();
                    getView().onError(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
