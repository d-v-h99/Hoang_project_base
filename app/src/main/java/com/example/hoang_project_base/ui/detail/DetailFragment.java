package com.example.hoang_project_base.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hoang_project_base.R;
import com.example.hoang_project_base.base.BaseFragment;
import com.example.hoang_project_base.event.SampleEvent;
import com.example.hoang_project_base.navigation.DetailKey;
import com.zhuinden.simplestack.Backstack;
import com.zhuinden.simplestack.navigator.Navigator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailFragment extends BaseFragment<DetailView, DetailPresenter> implements DetailView {

    public DetailFragment() {
        super(R.layout.fragment_detail);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_detail;
    }

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter();
    }

    @Override
    public void showLoading() { /* nếu có ProgressBar */ }

    @Override
    public void hideLoading() { /*  */ }

    @Override
    public void showError(String msg) { /* Toast */ }

    @Override
    public void showDetailText(String text) {
        TextView tv = getView().findViewById(R.id.textDetail);
        tv.setText(text);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bs) {
        super.onViewCreated(view, bs);
        Backstack backstack = Navigator.getBackstack(requireActivity());
        DetailKey key = (DetailKey) backstack.top();
// hoặc: backstack.getHistory().top()
        String userId = key.getUserId();
        presenter.loadDetail(userId);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSomeEvent(SampleEvent e) {
        Toast.makeText(getContext(),
                        "Man Hinh detail: " + e.getMessage(),
                        Toast.LENGTH_SHORT)
                .show();
        EventBus.getDefault().removeStickyEvent(e);
    }
}
