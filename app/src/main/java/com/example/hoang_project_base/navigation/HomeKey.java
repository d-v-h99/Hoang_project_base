package com.example.hoang_project_base.navigation;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hoang_project_base.base.BaseKey;
import com.example.hoang_project_base.ui.home.HomeFragment;

public class HomeKey extends BaseKey {
    public HomeKey() {
        // mặc định không args
    }

    /** Constructor dùng khi recreate từ Parcel **/
    protected HomeKey(Parcel in) {
        // nếu bạn có args, đọc ra ở đây
        // ví dụ: someField = in.readString();
    }

    public static final Parcelable.Creator<HomeKey> CREATOR = new Parcelable.Creator<HomeKey>() {
        @Override
        public HomeKey createFromParcel(Parcel in) {
            return new HomeKey(in);
        }

        @Override
        public HomeKey[] newArray(int size) {
            return new HomeKey[size];
        }
    };

    @NonNull
    @Override
    public Fragment instantiateFragment() {
        return new HomeFragment();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        // nếu có args thì dest.writeString(someField);
    }
}
