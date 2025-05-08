package com.example.hoang_project_base.ui.success;

import com.example.hoang_project_base.base.BasePresenter;

public class SuccessPresenter implements BasePresenter<SuccessView> {
    private SuccessView view;
    @Override public void attachView(SuccessView view) { this.view = view; }
    @Override public void detachView() { this.view = null; }
}
