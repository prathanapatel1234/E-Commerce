package uk.actees.aad.w9521088.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import uk.actees.aad.w9521088.Fragments.AccountFragment;
import uk.actees.aad.w9521088.Fragments.HomeFragment;

import uk.actees.aad.w9521088.Fragments.WishListFragment;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Storage.LocalStorage;
import uk.actees.aad.w9521088.Utils.AppUtils;
import uk.actees.aad.w9521088.Utils.GpsUtils;
import uk.actees.aad.w9521088.Utils.NetworkCheck;
import uk.actees.aad.w9521088.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private boolean isFirstTime = true;
    private  boolean isGPS= false;
    protected LocationManager locationManager;
    private LocationListener mLocationListener;
    ProgressDialog progressDialog;
    public static final long LOCATION_REFRESH_TIME = 5000;
    public static final float LOCATION_REFRESH_DISTANCE = 5;
    public static String latitude;
    public static String longitude;

    public boolean isNeedToOpenHomeFragment = false;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.txtLocation.setText(LocalStorage.getDeliveryAddress(this));

        ChangeBottomBarColor(binding.imgHome, R.drawable.ic_home_clicked);
        AppUtils.goNextFragmentAdd(this, new HomeFragment());
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.imgHome:
                ChangeBottomBarColor(binding.imgHome,R.drawable.ic_home_clicked);
                AppUtils.goNextFragmentReplace(this, new HomeFragment());
                break;

            case R.id.imgAccount:
                ChangeBottomBarColor(binding.imgAccount,R.drawable.ic_user_clicked);
                AppUtils.goNextFragmentReplace(this, new AccountFragment());
                break;

            case R.id.imgWishList:
                ChangeBottomBarColor(binding.imgWishList,R.drawable.ic_wishlist_clicked);
                AppUtils.goNextFragmentReplace(this, new WishListFragment());
                break;

            default:
                break;
        }
    }

    public void ChangeBottomBarColor(ImageView imageView, int icon){

        binding.imgHome.setImageResource(R.drawable.ic_home);
        binding.imgAccount.setImageResource(R.drawable.ic_user);
        binding.imgWishList.setImageResource(R.drawable.ic_wishlist);

        imageView.setImageResource(icon);

    }

    @Override
    public void onBackPressed(){
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();

            if (backStackEntryCount > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            } else {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.FrameLayout);
                if (!(fragment instanceof HomeFragment)) {
                    isNeedToOpenHomeFragment = true;
                } else {
                    isNeedToOpenHomeFragment = false;
                }
                if (isNeedToOpenHomeFragment) {
                    isNeedToOpenHomeFragment = false;
                    ChangeBottomBarColor(binding.imgHome,R.drawable.ic_home_clicked);
                    AppUtils.goNextFragmentReplace(this,new HomeFragment());
                } else {
                    if (doubleBackToExitPressedOnce) {

                        new AlertDialog.Builder(this)
                                .setIcon(R.drawable.img_splash)
                                .setTitle("exit")
                                .setCancelable(false)
                                .setMessage("Are you sure want to exit")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //turnGPSOff();
                                        finishAffinity();
                                        MainActivity.this.finish();
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                    }
                }
            }

    }

    @Override
    public void onResume(){
        super.onResume();
        if (isFirstTime)
            fetchLocation();
    }

    private void fetchLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }
        else {
            CheckGPSStatus();
        }
    }

    private void CheckGPSStatus(){
        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                if (isGPSEnable){
                    isGPS = true;
                    if (NetworkCheck.isConnected(MainActivity.this)){
                        getCurrentLocation();
                    }else {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.please_enable_your_mobile_data), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    isGPS = false;
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.please_enable_your_gps), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getCurrentLocation() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching device location");
        progressDialog.setCancelable(false);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        progressDialog.show();
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                if (location != null) {
                    isFirstTime = false;
                    progressDialog.dismiss();
                    latitude = ""+location.getLatitude();
                    longitude = ""+location.getLongitude();
                    LocalStorage.setLatitude(MainActivity.this, latitude);
                    LocalStorage.setLongitude(MainActivity.this, longitude);
                    getCompleteAddressString(location.getLatitude(), location.getLongitude());
                    //goFurther();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            fetchLocation();
        }
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        String pinCode = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                pinCode = returnedAddress.getPostalCode();
                LocalStorage.setPincode(MainActivity.this,pinCode);
                LocalStorage.setDeliveryAddress(MainActivity.this,strAdd);
                Log.e("CurrentLocation","pinCode "+pinCode);
                Log.e("CurrentLocation","adrs "+ strReturnedAddress.toString());
            } else {
                Log.w("Myloctionaddress", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("Myloctionaddress", "Canont get Address!");
        }
        return strAdd;
    }
}