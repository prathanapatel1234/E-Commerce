package uk.actees.aad.w9521088.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import uk.actees.aad.w9521088.ApiCall.Model.LoginResBean;
import uk.actees.aad.w9521088.ApiCall.Model.UpdateProfileResBean;

public class SharedPreferenceData {
    SharedPreferences sharedPreferences, sharedPreferences1;
    Context context;
    private String MyLoginPREFERENCES = "MyLoginPrefs" ;
    private String SELECTED_DEPARTMENT_PREFERENCES = "SelectedDepartmentPrefs" ;
    private String DELIVERY_ADDRESS_PREFERENCES = "DeliveryAddressPrefs" ;
    private String USER_ID = "user_id" ;
    private String NAME = "name" ;
    private String LASTNAME = "lastname" ;
    private String PASSWORD = "password" ;
    private String FATHER_NAME = "father_name" ;
    private String MOBILE_NO = "mobile_no" ;
    private String EMAIL = "email" ;
    private String ACCESS_TOKEN = "access_token";
    private String REGISTRATION_NO = "registration_no" ;
    private String PROFILE_IMAGE = "profile_image" ;
    private String SELECTED_DEPART_ID = "selected_depart";
    private String DEFAULT_ADDRESS = "default_address";
    private String REFERRAL_CODE = "referral_code";
    private String PINCODE = "pincode";
    private String SECTION_TYPE = "section_type";
    private String IS_FIRST_TIME = "is_first_time";
    private String DAY = "day";
    private String MONTH = "month";
    private String YEAR = "year";

    public SharedPreferenceData(Context context){
        this.context = context;
    }

  public void SavedLoginData(LoginResBean loginResBean){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
      LoginResBean.Data data = loginResBean.getData();
        editor.putString(USER_ID, ""+data.getId());
        editor.putString(NAME, data.getFirstName());
        editor.putString(LASTNAME, data.getLastName());
        editor.putString(PROFILE_IMAGE,data.getProfilePic());
        editor.putString(ACCESS_TOKEN,loginResBean.getAccessToken());
        editor.putString(EMAIL, data.getEmail());
        editor.putString(MOBILE_NO, data.getMobile());
        editor.commit();
    }

    public void SavedUpdateProfileData(UpdateProfileResBean updateProfileResBean){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        UpdateProfileResBean.Data data = updateProfileResBean.getData();
        editor.putString(USER_ID,""+data.getId());
        editor.putString(NAME,data.getFirstName());
        editor.putString(LASTNAME,data.getLastName());
        editor.putString(EMAIL,data.getEmail());
        editor.putString(MOBILE_NO,data.getMobile());
        editor.putString(PROFILE_IMAGE,data.getProfilePic());
        editor.commit();

    }

    public void savePincode(String pincode){
        sharedPreferences = context.getSharedPreferences(DELIVERY_ADDRESS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PINCODE, pincode);
        editor.commit();
    }

    public void saveSectionType(String sectionType){
        sharedPreferences = context.getSharedPreferences(DELIVERY_ADDRESS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SECTION_TYPE, sectionType);
        editor.commit();
    }

    public void isAppInstalledFirstTime(boolean isFirstTime){
        sharedPreferences = context.getSharedPreferences(DELIVERY_ADDRESS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_FIRST_TIME, isFirstTime);
        editor.commit();
    }

    public void Logout(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences1 = context.getSharedPreferences(DELIVERY_ADDRESS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor.clear();
        editor1.clear();
        editor.commit();
        editor1.commit();
    }

    public boolean IsLogin(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.contains(USER_ID);
    }

    public String getUser_id(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_ID, "");
    }

    public String getUser_name(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(NAME, "");
    }

    public String getMobile_no(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MOBILE_NO, "");
    }

    public String getProfile_image(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PROFILE_IMAGE, "");
    }

    public String getDobDay(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DAY, "");
    }

    public String getDobMonth(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(MONTH, "");
    }

    public String getDobYear(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(YEAR, "");
    }

    public String getEmail(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(EMAIL, "");
    }

    public String getACCESS_TOKEN(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ACCESS_TOKEN, "");
    }

    public String getREFERRAL_CODE(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(REFERRAL_CODE, "");
    }

    public String getPINCODE(){
        sharedPreferences = context.getSharedPreferences(MyLoginPREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PINCODE, "");
    }

    public String getCurrentPINCODE(){
        sharedPreferences = context.getSharedPreferences(DELIVERY_ADDRESS_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(PINCODE, "");
    }

    public String getSectionType(){
        sharedPreferences = context.getSharedPreferences(DELIVERY_ADDRESS_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SECTION_TYPE, "");
    }

    public Boolean getIsAppInstalledFirstTime(){
        sharedPreferences = context.getSharedPreferences(DELIVERY_ADDRESS_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_FIRST_TIME, true);
    }
}
