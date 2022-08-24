package uk.actees.aad.w9521088.ApiCall.View;


import uk.actees.aad.w9521088.ApiCall.Model.LoginResBean;
import uk.actees.aad.w9521088.ApiCall.Model.ProductDetailResBean;
import uk.actees.aad.w9521088.ApiCall.Model.ProductListResBean;
import uk.actees.aad.w9521088.ApiCall.Model.RegisterResBean;
import uk.actees.aad.w9521088.ApiCall.Model.UpdateProfileResBean;
import uk.actees.aad.w9521088.ApiCall.Model.WishListResBean;
import uk.actees.aad.w9521088.ApiCall.Model.WishlistAddedResBean;
import uk.actees.aad.w9521088.Utils.ApiConstants;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @FormUrlEncoded
    @POST(ApiConstants.LOGIN_URL)
    @Headers({"Accept: application/json"})
    Call<LoginResBean> login(@Header("Authorization") String accessToken,
                             @Field("mobile") String mobile,
                             @Field("password") String password);


    @FormUrlEncoded
    @POST(ApiConstants.REGISTER_URL)
    Call<RegisterResBean> Register(@Field("first_name") String first_name,
                                   @Field("last_name") String last_name,
                                   @Field("mobile") String mobile,
                                   @Field("password") String password,
                                   @Field("confirm_password") String confirm_password);

    @POST(ApiConstants.PRODUCT_LIST_URL)
    @Headers({"Accept: application/json"})
    Call<ProductListResBean> ProductList(@Header("Authorization") String accessToken);

    @Multipart
    @POST(ApiConstants.UPDATE_PROFILE_URL)
    @Headers({"Accept: application/json"})
    Call<UpdateProfileResBean> UpdateProfile(@Header("Authorization") String accessToken,
                                             @Part("first_name") RequestBody name,
                                             @Part("mobile")RequestBody mobile,
                                             @Part MultipartBody.Part user_image);
    @FormUrlEncoded
    @POST(ApiConstants.PRODUCT_DETAIL_URL)
    @Headers({"Accept: application/json"})
    Call<ProductDetailResBean> Detail(@Header("Authorization") String accessToken,
                                      @Field("product_id") String product_id);

    @POST(ApiConstants.WISHLIST_URL)
    @Headers({"Accept: application/json"})
    Call<WishListResBean> Wishlist(@Header("Authorization") String accessToken);

    @FormUrlEncoded
    @POST(ApiConstants.WISHLIST_Added_URL)
    @Headers({"Accept: application/json"})
    Call<WishlistAddedResBean> WishlistAdded(@Header("Authorization") String accessToken,
                                      @Field("product_id") String product_id,
                                      @Field("variant_id") String variant_id);





}



