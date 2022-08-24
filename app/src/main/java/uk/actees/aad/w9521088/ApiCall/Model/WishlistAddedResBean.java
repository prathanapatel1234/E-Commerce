package uk.actees.aad.w9521088.ApiCall.Model;

import com.google.gson.annotations.SerializedName;

public class WishlistAddedResBean {

	@SerializedName("message")
	private String message;

	@SerializedName("is_wishlist")
	private int isWishlist;

	@SerializedName("status")
	private boolean status;

	public String getMessage(){
		return message;
	}

	public int getIsWishlist(){
		return isWishlist;
	}

	public boolean isStatus(){
		return status;
	}
}