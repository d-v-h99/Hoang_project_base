package com.example.hoang_project_base.ui.confirm;

import com.example.hoang_project_base.base.BasePresenter;

public class ConfirmPresenter implements BasePresenter<ConfirmView> {
    private ConfirmView view;
    @Override public void attachView(ConfirmView view) { this.view = view; }
    @Override public void detachView() { this.view = null; }
}