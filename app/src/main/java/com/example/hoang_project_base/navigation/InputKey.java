package com.example.hoang_project_base.navigation;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hoang_project_base.base.BaseKey;
import com.example.hoang_project_base.ui.input.InputFragment;

public class InputKey extends BaseKey implements Parcelable {
    // dummy field để Parcelable không lỗi
    private final String dummy = "";
    public InputKey() {
    }
    protected InputKey(Parcel in) {
        // nếu bạn có args, đọc ra ở đây
        // ví dụ: someField = in.readString();
    }
    public static final Parcelable.Creator<InputKey> CREATOR = new Parcelable.Creator<InputKey>() {
        @Override
        public InputKey createFromParcel(Parcel in) {
            return new InputKey(in);
        }

        @Override
        public InputKey[] newArray(int size) {
            return new InputKey[size];
        }
    };
    @NonNull
    @Override
    public Fragment instantiateFragment() {
        return new InputFragment();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

    }
    @Override
    public boolean equals(Object o) {
        return (o instanceof InputKey);
    }
    @Override
    public int hashCode() {
        return InputKey.class.hashCode();
    }
}
