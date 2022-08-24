package uk.actees.aad.w9521088.Activity;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.actees.aad.w9521088.ApiCall.Model.LoginResBean;
import uk.actees.aad.w9521088.ApiCall.Presenter.LoginPresenter;
import uk.actees.aad.w9521088.ApiCall.View.ILoginView;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.NetworkCheck;
import uk.actees.aad.w9521088.Utils.SharedPreferenceData;
import uk.actees.aad.w9521088.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    ActivityLoginBinding binding;
    LoginPresenter presenter;
    SharedPreferenceData ProfileData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);

        presenter = new LoginPresenter();
        presenter.setView(this);
        ProfileData = new SharedPreferenceData(this);

        binding.btnLogin.setOnClickListener(this);
        binding.txtForgotPassword.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);

    }

    public  void onClick(View view){
        switch (view.getId()){

            case R.id.btnLogin:
                if(binding.edtMobile.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtMobile.getText().toString().length()!=10){
                    Toast.makeText(this, "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
                else if(NetworkCheck.isConnected(this)){
                    presenter.LoginCall(this,ProfileData.getACCESS_TOKEN(),binding.edtMobile.getText().toString(),binding.edtPassword.getText().toString());
                }
                else{
                    NetworkCheck.showNetworkFailureAlert(this);
                }
                break;

            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;

            default:
                break;
        }
    }

    @Override
    public void onLoginSucess(LoginResBean item) {
        if(item.isStatus()){
            new SharedPreferenceData(this).SavedLoginData(item);
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        toast(item.getMessage());

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onError(String reason) {

    }
}