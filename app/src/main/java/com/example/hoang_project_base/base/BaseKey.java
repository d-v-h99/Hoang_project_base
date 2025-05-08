package com.example.hoang_project_base.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey;

/**
 * Mỗi màn hình chỉ cần extends BaseKey và implement instantiateFragment().
 * DefaultFragmentKey đã implement sẵn Parcelable, StateKey, v.v…
 */
public abstract class BaseKey extends DefaultFragmentKey {
    @NonNull
    @Override
    public String getFragmentTag() {
        // Sử dụng tên class làm tag ổn định
        return getClass().getSimpleName();
    }
    @NonNull
    @Override
    public abstract Fragment instantiateFragment();
}
