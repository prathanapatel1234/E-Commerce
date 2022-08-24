package uk.actees.aad.w9521088.ApiCall.Presenter;

import android.app.Activity;
import android.widget.Toast;

import uk.actees.aad.w9521088.ApiCall.Model.LoginResBean;
import uk.actees.aad.w9521088.ApiCall.View.ILoginView;
import uk.actees.aad.w9521088.TopperApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter<ILoginView>{

    public void LoginCall (final Activity context,String accessToken,String mobile,String password){
        getView().enableLoadingBar(context,true);

        TopperApp.getInstance().getApiService().login("Bearer "+accessToken,mobile,password).enqueue(new Callback<LoginResBean>() {
            @Override
            public void onResponse(Call<LoginResBean> call, Response<LoginResBean> response) {
                getView().enableLoadingBar(context,false);

                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onLoginSucess(response.body());

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
            public void onFailure(Call<LoginResBean> call, Throwable t) {
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
