package uk.actees.aad.w9521088.ApiCall.Presenter;

import android.app.Activity;
import android.widget.Toast;

import uk.actees.aad.w9521088.ApiCall.Model.RegisterResBean;
import uk.actees.aad.w9521088.ApiCall.View.IRegisterView;
import uk.actees.aad.w9521088.TopperApp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter extends BasePresenter<IRegisterView>{

    public void RegisterCall(final Activity context,String first_name,String last_name,String mobile,String password,String confirm_password){
        getView().enableLoadingBar(context,true);

        TopperApp.getInstance().getApiService().Register(first_name,last_name,mobile,password,confirm_password).enqueue(new Callback<RegisterResBean>() {
            @Override
            public void onResponse(Call<RegisterResBean> call, Response<RegisterResBean> response) {
                getView().enableLoadingBar(context,false);
                try {
                    if (!handleError(response, context)) {
                        if (response.isSuccessful() && response.code() == 200){
                            assert response.body() != null;
                            getView().onRegisterSuccess(response.body());

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
            public void onFailure(Call<RegisterResBean> call, Throwable t) {
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
