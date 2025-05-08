package com.example.hoang_project_base.base;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}

