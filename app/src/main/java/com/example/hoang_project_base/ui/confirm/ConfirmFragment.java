// ConfirmFragment.java
package com.example.hoang_project_base.ui.confirm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hoang_project_base.R;
import com.example.hoang_project_base.base.BaseFragment;
import com.example.hoang_project_base.event.SampleEvent;
import com.example.hoang_project_base.navigation.ConfirmKey;
import com.example.hoang_project_base.navigation.SuccessKey;
import com.zhuinden.simplestack.navigator.Navigator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ConfirmFragment
        extends BaseFragment<ConfirmView, ConfirmPresenter>
        implements ConfirmView {

    private String name;
    private double amount;

    public ConfirmFragment() {
        super(R.layout.fragment_confirm);
    }

    @Override
    protected int getLayoutRes() { return R.layout.fragment_confirm; }

    @Override
    protected ConfirmPresenter createPresenter() {
        return new ConfirmPresenter();
    }

    @Override public void showLoading() { }
    @Override public void hideLoading() { }
    @Override public void showError(String msg) { }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bs) {
        super.onViewCreated(view, bs);
        ConfirmKey key = (ConfirmKey) Navigator
                .getBackstack(requireActivity())
                .top();
        name = key.getName();
        amount = key.getAmount();
        ((TextView)view.findViewById(R.id.tvName)).setText(name);
        ((TextView)view.findViewById(R.id.tvAmount))
                .setText(String.valueOf(amount));

        Button btn = view.findViewById(R.id.btnConfirm);
        btn.setOnClickListener(v -> {
            Navigator
                    .getBackstack(requireActivity())
                    .goTo(new SuccessKey(name, amount));
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
