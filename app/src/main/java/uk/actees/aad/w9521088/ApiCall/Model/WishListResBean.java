package uk.actees.aad.w9521088.ApiCall.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class WishListResBean {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public List<DataItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}

	public class DataItem{

		@SerializedName("product")
		private Product product;

		@SerializedName("variant_id")
		private String variantId;

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("user_id")
		private int userId;

		@SerializedName("product_id")
		private int productId;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("id")
		private int id;

		@SerializedName("status")
		private int status;

		public Product getProduct(){
			return product;
		}

		public String getVariantId(){
			return variantId;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public int getUserId(){
			return userId;
		}

		public int getProductId(){
			return productId;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public int getId(){
			return id;
		}

		public int getStatus(){
			return status;
		}

		public class Product{

			@SerializedName("image")
			private String image;

			@SerializedName("product_mrp_price")
			private String productMrpPrice;

			@SerializedName("updated_at")
			private String updatedAt;

			@SerializedName("description")
			private String description;

			@SerializedName("created_at")
			private String createdAt;

			@SerializedName("id")
			private int id;

			@SerializedName("short_desc")
			private String shortDesc;

			@SerializedName("product_name")
			private String productName;

			@SerializedName("created_by")
			private String createdBy;

			@SerializedName("slug")
			private String slug;

			@SerializedName("status")
			private String status;

			public String getImage(){
				return image;
			}

			public String getProductMrpPrice(){
				return productMrpPrice;
			}

			public String getUpdatedAt(){
				return updatedAt;
			}

			public String getDescription(){
				return description;
			}

			public String getCreatedAt(){
				return createdAt;
			}

			public int getId(){
				return id;
			}

			public String getShortDesc(){
				return shortDesc;
			}

			public String getProductName(){
				return productName;
			}

			public String getCreatedBy(){
				return createdBy;
			}

			public String getSlug(){
				return slug;
			}

			public String getStatus(){
				return status;
			}
		}
	}
}