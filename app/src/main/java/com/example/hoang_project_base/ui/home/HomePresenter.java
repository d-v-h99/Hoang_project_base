package com.example.hoang_project_base.ui.home;

import com.example.hoang_project_base.base.BasePresenter;

public class HomePresenter implements BasePresenter<HomeView> {
    private HomeView view;
    @Override public void attachView(HomeView view) { this.view = view; }
    @Override public void detachView() { this.view = null; }
    public void onButtonClicked() {
        // ví dụ xử lý
        view.showMessage("Button clicked!");
    }
}
