package uk.actees.aad.w9521088.Fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import uk.actees.aad.w9521088.ApiCall.Model.UpdateProfileResBean;
import uk.actees.aad.w9521088.ApiCall.Presenter.UpdateProfilePresenter;
import uk.actees.aad.w9521088.ApiCall.View.IUpdateProfileView;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.ApiConstants;
import uk.actees.aad.w9521088.Utils.NetworkCheck;
import uk.actees.aad.w9521088.Utils.PermissionCaller;
import uk.actees.aad.w9521088.Utils.SharedPreferenceData;

import uk.actees.aad.w9521088.BuildConfig;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import uk.actees.aad.w9521088.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment implements IUpdateProfileView {

    FragmentAccountBinding binding;
    public Uri uriProfile = null;
    private boolean isimageFromGallery= false;
    public static final int REQUEST_CAPTURE = 1001;
    public Uri captureMediaFile = null;

    SharedPreferenceData ProfileData;
    UpdateProfilePresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil .inflate(inflater, R.layout.fragment_account, container, false);

        presenter = new UpdateProfilePresenter();
        presenter.setView(this);

        ProfileData = new SharedPreferenceData(getContext());
        binding.edtName.setText(ProfileData.getUser_name());
        binding.edtMobile.setText(ProfileData.getMobile_no());
        Picasso.get().load(ApiConstants.BASE_IMAGE_URL +"/"+ ProfileData.getProfile_image()).into(binding.imgProfile);

        binding.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showpictureDialog();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edtName.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter name", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtMobile.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(binding.edtMobile.getText().toString().length()!=10){
                    Toast.makeText(getActivity(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(NetworkCheck.isConnected(getActivity())){

                    MultipartBody.Part user_image = null;

                    if (uriProfile != null) {
                        String fileName = new File(uriProfile.getPath()).getName();
                        File actualfile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                                new File(uriProfile.getPath()).getName());
                        user_image = MultipartBody.Part.createFormData("profile_pic", fileName, RequestBody.
                                create(MediaType.parse("multipart/form_Data"), actualfile));

                        presenter.UpdateProfile(getActivity(), ProfileData.getACCESS_TOKEN(), binding.edtName.getText().toString(), binding.edtMobile.getText().toString(),user_image);
                    } else {

                        presenter.UpdateProfile(getActivity(), ProfileData.getACCESS_TOKEN(), binding.edtName.getText().toString(), binding.edtMobile.getText().toString(),user_image);
                    }

                } else {
                    NetworkCheck.showNetworkFailureAlert(getContext());
                }

            }

        });

        return binding.getRoot();
    }

    private void showpictureDialog() {

        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Take photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                pickImageFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void pickImageFromCamera() {
        if (!PermissionCaller.getInstance(getActivity()).isListOfPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA}, REQUEST_CAPTURE))
            return;

        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            int random =  (int)(Math.random()*(1000-0+1)+0);
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            path.mkdir();
            String photoFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/images" + random + ".jpg";
            File imageFile = new File(photoFilePath);
            captureMediaFile = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider", imageFile);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, captureMediaFile);

            List<ResolveInfo> resInfoList = getActivity().getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                getActivity().grantUriPermission(packageName, captureMediaFile, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CAPTURE:
                    if (captureMediaFile != null) {
                        isimageFromGallery = false;
                        uriProfile = captureMediaFile;
                        binding.imgProfile.setImageURI(null);
                        binding.imgProfile.setImageURI(uriProfile);
                    }
                    break;

            }
        }
    }

    @Override
    public void onUpdateProfilesSuccess(UpdateProfileResBean item) {
        if(item.isStatus()){
            new SharedPreferenceData(getActivity()).SavedUpdateProfileData(item);
            Toast.makeText(getActivity(),item.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void enableLoadingBar(Context context, boolean enable) {

    }

    @Override
    public void onError(String reason) {

    }
}