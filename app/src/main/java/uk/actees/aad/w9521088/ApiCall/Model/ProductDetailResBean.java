package uk.actees.aad.w9521088.ApiCall.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailResBean {


    @SerializedName("success")
    private Boolean success;
    @SerializedName("data")
    private List<DataDTO> data;
    @SerializedName("message")
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {
        @SerializedName("id")
        private Integer id;
        @SerializedName("product_name")
        private String productName;
        @SerializedName("slug")
        private String slug;
        @SerializedName("short_desc")
        private String shortDesc;
        @SerializedName("description")
        private String description;
        @SerializedName("status")
        private String status;
        @SerializedName("image")
        private String image;
        @SerializedName("product_mrp_price")
        private String productMrpPrice;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("created_by")
        private String createdBy;
        @SerializedName("is_wishlist")
        private Integer isWishlist;
        @SerializedName("is_cart")
        private Integer isCart;
        @SerializedName("cart_count")
        private Integer cartCount;
        @SerializedName("extra_images")
        private List<ExtraImagesDTO> extraImages;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getProductMrpPrice() {
            return productMrpPrice;
        }

        public void setProductMrpPrice(String productMrpPrice) {
            this.productMrpPrice = productMrpPrice;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public Integer getIsWishlist() {
            return isWishlist;
        }

        public void setIsWishlist(Integer isWishlist) {
            this.isWishlist = isWishlist;
        }

        public Integer getIsCart() {
            return isCart;
        }

        public void setIsCart(Integer isCart) {
            this.isCart = isCart;
        }

        public Integer getCartCount() {
            return cartCount;
        }

        public void setCartCount(Integer cartCount) {
            this.cartCount = cartCount;
        }

        public List<ExtraImagesDTO> getExtraImages() {
            return extraImages;
        }

        public void setExtraImages(List<ExtraImagesDTO> extraImages) {
            this.extraImages = extraImages;
        }

        public static class ExtraImagesDTO {
            @SerializedName("id")
            private Integer id;
            @SerializedName("product_id")
            private Integer productId;
            @SerializedName("category_id")
            private Integer categoryId;
            @SerializedName("subcategory_id")
            private Object subcategoryId;
            @SerializedName("images")
            private String images;
            @SerializedName("created_at")
            private String createdAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getProductId() {
                return productId;
            }

            public void setProductId(Integer productId) {
                this.productId = productId;
            }

            public Integer getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(Integer categoryId) {
                this.categoryId = categoryId;
            }

            public Object getSubcategoryId() {
                return subcategoryId;
            }

            public void setSubcategoryId(Object subcategoryId) {
                this.subcategoryId = subcategoryId;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }
        }
    }
}