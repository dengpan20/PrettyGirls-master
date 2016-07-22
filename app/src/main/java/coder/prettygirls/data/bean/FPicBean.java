package coder.prettygirls.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FPicBean implements Parcelable{
	private String title;
	private String url;
	private String num;
	private String imgUrl;

	public FPicBean() {
	}

	protected FPicBean(Parcel in) {
		title = in.readString();
		url = in.readString();
		num = in.readString();
		imgUrl = in.readString();
	}

	public static final Creator<FPicBean> CREATOR = new Creator<FPicBean>() {
		@Override
		public FPicBean createFromParcel(Parcel in) {
			return new FPicBean(in);
		}

		@Override
		public FPicBean[] newArray(int size) {
			return new FPicBean[size];
		}
	};

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.title);
		dest.writeString(this.url);
		dest.writeString(this.num);
		dest.writeString(this.imgUrl);
	}
}
