package uk.actees.aad.w9521088.Activity;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.actees.aad.w9521088.ApiCall.Model.RegisterResBean;
import uk.actees.aad.w9521088.ApiCall.Presenter.RegisterPresenter;
import uk.actees.aad.w9521088.ApiCall.View.IRegisterView;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.NetworkCheck;
import uk.actees.aad.w9521088.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    ActivityRegisterBinding binding;
    RegisterPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil. setContentView(this,R.layout.activity_register);

       presenter =  new RegisterPresenter();
       presenter.setView(this);

       binding.btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(binding.edtName.getText().toString().isEmpty()){
                   Toast.makeText(RegisterActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
               }
               else if(binding.edtLastName.getText().toString().isEmpty()){
                   Toast.makeText(RegisterActivity.this, "Please enter last name", Toast.LENGTH_SHORT).show();
               }
               else if(binding.edtMobile.getText().toString().isEmpty()){
                   Toast.makeText(RegisterActivity.this, "Please enetr mobile number", Toast.LENGTH_SHORT).show();
               }
               else if(binding.edtMobile.getText().toString().length()!=10){
                   Toast.makeText(RegisterActivity.this, "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
               }
               else if(binding.edtPassword.getText().toString().isEmpty()){
                   Toast.makeText(RegisterActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
               }
               else if(binding.edtConfirmPassword.getText().toString().isEmpty()){
                   Toast.makeText(RegisterActivity.this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
               }
               else if(NetworkCheck.isConnected(RegisterActivity.this)){
                   presenter.RegisterCall(RegisterActivity.this,binding.edtName.getText().toString(),binding.edtLastName.getText().toString(),
                   binding.edtMobile.getText().toString(),binding.edtPassword.getText().toString(),binding.edtConfirmPassword.getText().toString());
               }
               else{
                   NetworkCheck.showNetworkFailureAlert(RegisterActivity.this);
               }
           }
       });

       binding.imgBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

    }

    @Override
    public void onRegisterSuccess(RegisterResBean item) {
        if(item.isStatus()){
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
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