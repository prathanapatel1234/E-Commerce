package uk.actees.aad.w9521088.ApiCall.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResBean {

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("data")
	private Data data;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("message")
	private String message;

	@SerializedName("expires_in")
	private int expiresIn;

	@SerializedName("status")
	private boolean status;

	public String getAccessToken(){
		return accessToken;
	}

	public Data getData(){
		return data;
	}

	public String getTokenType(){
		return tokenType;
	}

	public String getMessage(){
		return message;
	}

	public int getExpiresIn(){
		return expiresIn;
	}

	public boolean isStatus(){
		return status;
	}


	public class Data{

		@SerializedName("device_key")
		private Object deviceKey;

		@SerializedName("city")
		private Object city;

		@SerializedName("mobile")
		private String mobile;

		@SerializedName("locality")
		private Object locality;

		@SerializedName("profile_pic")
		private String profilePic;

		@SerializedName("last_name")
		private String lastName;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("otp")
		private int otp;

		@SerializedName("is_verified")
		private int isVerified;

		@SerializedName("registration_type")
		private String registrationType;

		@SerializedName("register_otp")
		private int registerOtp;

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("id")
		private int id;

		@SerializedName("first_name")
		private String firstName;

		@SerializedName("email")
		private String email;

		@SerializedName("status")
		private String status;

		public Object getDeviceKey(){
			return deviceKey;
		}

		public Object getCity(){
			return city;
		}

		public String getMobile(){
			return mobile;
		}

		public Object getLocality(){
			return locality;
		}

		public String getProfilePic(){
			return profilePic;
		}

		public String getLastName(){
			return lastName;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public int getOtp(){
			return otp;
		}

		public int getIsVerified(){
			return isVerified;
		}

		public String getRegistrationType(){
			return registrationType;
		}

		public int getRegisterOtp(){
			return registerOtp;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public int getId(){
			return id;
		}

		public String getFirstName(){
			return firstName;
		}

		public String getEmail(){
			return email;
		}

		public String getStatus(){
			return status;
		}
	}
}