package com.example.hoang_project_base.ui.input;

import com.example.hoang_project_base.base.BasePresenter;

public class InputPresenter implements BasePresenter<InputView> {
    private InputView view;
    @Override public void attachView(InputView view) { this.view = view; }
    @Override public void detachView() { this.view = null; }
}