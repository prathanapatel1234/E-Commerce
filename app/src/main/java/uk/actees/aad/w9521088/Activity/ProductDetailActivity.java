package uk.actees.aad.w9521088.Activity;

import androidx.databinding.DataBindingUtil;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import uk.actees.aad.w9521088.ApiCall.Model.ProductDetailResBean;
import uk.actees.aad.w9521088.ApiCall.Model.WishlistAddedResBean;
import uk.actees.aad.w9521088.ApiCall.Presenter.ProductDetailPresenter;
import uk.actees.aad.w9521088.ApiCall.Presenter.WishlistAddedPresenter;
import uk.actees.aad.w9521088.ApiCall.View.IProductDetailView;
import uk.actees.aad.w9521088.ApiCall.View.IWishlistAddedView;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.ApiConstants;
import uk.actees.aad.w9521088.Utils.NetworkCheck;
import uk.actees.aad.w9521088.Utils.SharedPreferenceData;
import uk.actees.aad.w9521088.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends BaseActivity implements IProductDetailView, IWishlistAddedView {

    ActivityProductDetailBinding binding;
    ProductDetailPresenter presenter;
    WishlistAddedPresenter wishlistAddedPresenter;
    SharedPreferenceData ProfileData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil. setContentView(this,R.layout.activity_product_detail);

        String Product_id = getIntent().getExtras().getString("product_id");

       presenter = new ProductDetailPresenter();
       presenter.setView(this);
       ProfileData = new SharedPreferenceData(this);
       wishlistAddedPresenter = new WishlistAddedPresenter();
       wishlistAddedPresenter.setView(this);

       if(NetworkCheck.isConnected(this)){
           presenter.onProductDetailCall(this,ProfileData.getACCESS_TOKEN(),Product_id);
       }else{
           NetworkCheck.showNetworkFailureAlert(this);
       }

       binding.imgBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });

       binding.btnAddWishlist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               wishlistAddedPresenter.onWishlistAdded(ProductDetailActivity.this,ProfileData.getACCESS_TOKEN(),Product_id,"");
           }
       });

    }

    @Override
    public void onProductDetailSuccess(ProductDetailResBean item) {
        String image = "";
        String txtProductType = "";
        String txtPrice = "";
        if(item.getSuccess()){
            for (int i = 0; i<item.getData().size();i++){
                 image = item.getData().get(i).getImage();
                 txtProductType = item.getData().get(i).getProductName();
                txtPrice = item.getData().get(i).getProductMrpPrice();
            }

            Picasso.get().load(ApiConstants.BASE_IMAGE_URL +"/"+ image).into(binding.imgUniform);
            binding.txtUniformType.setText(txtProductType);
            binding.txtPrice.setText("$"+txtPrice);

        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onError(String reason) {

    }

    @Override
    public void onWishListAdded(WishlistAddedResBean item) {
        if(item.isStatus()){
            Toast.makeText(this, item.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}