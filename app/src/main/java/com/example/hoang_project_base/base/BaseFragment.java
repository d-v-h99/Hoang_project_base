package com.example.hoang_project_base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zhuinden.simplestackextensions.fragments.KeyedFragment;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends KeyedFragment implements BaseView {
    protected P presenter;
    public BaseFragment(@LayoutRes int layoutRes) {
        super(layoutRes);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }
    /** Layout của fragment **/
    protected abstract int getLayoutRes();

    /** Tạo Presenter **/
    protected abstract P createPresenter();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V) this);
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
        presenter.detachView();
    }
}
