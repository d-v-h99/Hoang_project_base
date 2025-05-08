package com.example.hoang_project_base.ui.input;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hoang_project_base.R;
import com.example.hoang_project_base.base.BaseFragment;
import com.example.hoang_project_base.event.SampleEvent;
import com.example.hoang_project_base.navigation.ConfirmKey;
import com.zhuinden.simplestack.navigator.Navigator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class InputFragment extends BaseFragment<InputView, InputPresenter> implements InputView {
    private EditText etName, etAmount;
    public InputFragment() {
        super(R.layout.fragment_input);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_input;
    }

    @Override
    protected InputPresenter createPresenter() {
        return new InputPresenter();
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
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        Button btn = view.findViewById(R.id.btnNext);
        btn.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String a = etAmount.getText().toString().trim();
            if(name.isEmpty() || a.isEmpty()) {
                showError("Vui lòng nhập đủ");
                return;
            }
            double amount = Double.parseDouble(a);
            Navigator
                    .getBackstack(requireActivity())
                    .goTo(new ConfirmKey(name, amount));
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

    @Override
    public void onResume() {
        super.onResume();
        etName.setText("");
        etAmount.setText("");
    }
}
