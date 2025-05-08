// SuccessKey.java
package com.example.hoang_project_base.navigation;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hoang_project_base.base.BaseKey;
import com.example.hoang_project_base.ui.success.SuccessFragment;

public class SuccessKey extends BaseKey {
    private final String name;
    private final double amount;

    public SuccessKey(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    protected SuccessKey(Parcel in) {
        name = in.readString();
        amount = in.readDouble();
    }

    public static final Creator<SuccessKey> CREATOR = new Creator<SuccessKey>() {
        @Override
        public SuccessKey createFromParcel(Parcel in) {
            return new SuccessKey(in);
        }
        @Override
        public SuccessKey[] newArray(int size) {
            return new SuccessKey[size];
        }
    };

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(amount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }
    public double getAmount() {
        return amount;
    }

    @NonNull
    @Override
    public Fragment instantiateFragment() {
        return new SuccessFragment();
    }
}
