package com.example.hoang_project_base.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hoang_project_base.R;
import com.example.hoang_project_base.base.BaseFragment;
import com.example.hoang_project_base.event.SampleEvent;
import com.example.hoang_project_base.navigation.DetailKey;
import com.example.hoang_project_base.navigation.InputKey;
import com.zhuinden.simplestack.Backstack;
import com.zhuinden.simplestack.navigator.Navigator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = view.findViewById(R.id.buttonSendEvent);
        btn.setOnClickListener(v -> {
            presenter.onButtonClicked();
            EventBus.getDefault().post(new SampleEvent("Hello from Home"));
        });
        Button btnDetail = view.findViewById(R.id.buttonGoDetail);
        btnDetail.setOnClickListener(v -> {
            // Lấy backstack từ Activity
            Backstack backstack = Navigator.getBackstack(requireActivity());
            // Push DetailKey lên stack
            backstack.goTo(new DetailKey("ABC123"));
            EventBus.getDefault().post(new SampleEvent("Hello from kiki"));
        });
        view.findViewById(R.id.buttonGoInput).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy backstack từ Activity
                Backstack backstack = Navigator.getBackstack(requireActivity());
                // Push DetailKey lên stack
                backstack.goTo(new InputKey());
            }
        });

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSampleEvent(SampleEvent event) {
        // xử lý event, ví dụ show Toast
        Toast.makeText(getContext(),
                        "Received event: " + event.getMessage(),
                        Toast.LENGTH_SHORT)
                .show();
    }
}
