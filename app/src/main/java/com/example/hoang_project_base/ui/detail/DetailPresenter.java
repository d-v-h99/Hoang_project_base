package com.example.hoang_project_base.ui.detail;

import com.example.hoang_project_base.base.BasePresenter;

public class DetailPresenter implements BasePresenter<DetailView> {
    private DetailView view;
    @Override public void attachView(DetailView view) { this.view = view; }
    @Override public void detachView() { this.view = null; }

    public void loadDetail(String userID ) {
        if(view != null) {
            view.showDetailText("Đây là màn hình Detail!" + userID);
        }
    }
}
