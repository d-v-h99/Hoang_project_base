package com.example.hoang_project_base.navigation;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hoang_project_base.ui.detail.DetailFragment;
import com.example.hoang_project_base.base.BaseKey;

public class DetailKey extends BaseKey {
    private final String userId;

    public DetailKey(String userId) {
        this.userId = userId;
    }

    protected DetailKey(Parcel in) {
        // phải đọc đúng thứ tự và kiểu bạn đã write bên dưới
        userId = in.readString();
    }

    public static final Creator<DetailKey> CREATOR = new Creator<DetailKey>() {
        @Override
        public DetailKey createFromParcel(Parcel in) {
            return new DetailKey(in);
        }
        @Override
        public DetailKey[] newArray(int size) {
            return new DetailKey[size];
        }
    };

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        // ghi đúng thứ tự
        dest.writeString(userId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /** Cho phép Fragment lấy được userId này */
    public String getUserId() {
        return userId;
    }

    @NonNull
    @Override
    public Fragment instantiateFragment() {
        return new DetailFragment();
    }
}
