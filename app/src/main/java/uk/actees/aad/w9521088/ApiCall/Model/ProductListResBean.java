package uk.actees.aad.w9521088.ApiCall.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductListResBean {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage(){
		return message;
	}

	public class DataItem{

		@SerializedName("image")
		private String image;

		@SerializedName("is_cart")
		private int isCart;

		@SerializedName("cart_count")
		private int cartCount;

		@SerializedName("product_mrp_price")
		private String productMrpPrice;

		@SerializedName("description")
		private String description;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("product_name")
		private String productName;

		@SerializedName("created_by")
		private String createdBy;

		public void setIsWishlist(int isWishlist) {
			this.isWishlist = isWishlist;
		}

		@SerializedName("is_wishlist")
		private int isWishlist;

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("id")
		private int id;

		@SerializedName("short_desc")
		private String shortDesc;

		@SerializedName("slug")
		private String slug;

		@SerializedName("status")
		private String status;

		public String getImage(){
			return image;
		}

		public int getIsCart(){
			return isCart;
		}

		public int getCartCount(){
			return cartCount;
		}

		public String getProductMrpPrice(){
			return productMrpPrice;
		}

		public String getDescription(){
			return description;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public String getProductName(){
			return productName;
		}

		public String getCreatedBy(){
			return createdBy;
		}

		public int getIsWishlist(){
			return isWishlist;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public int getId(){
			return id;
		}

		public String getShortDesc(){
			return shortDesc;
		}

		public String getSlug(){
			return slug;
		}

		public String getStatus(){
			return status;
		}
	}
}