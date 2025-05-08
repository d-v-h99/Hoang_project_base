// SuccessFragment.java
package com.example.hoang_project_base.ui.success;

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
import com.example.hoang_project_base.navigation.InputKey;
import com.example.hoang_project_base.navigation.SuccessKey;
import com.zhuinden.simplestack.Backstack;
import com.zhuinden.simplestack.History;
import com.zhuinden.simplestack.StateChange;
import com.zhuinden.simplestack.navigator.Navigator;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SuccessFragment
        extends BaseFragment<SuccessView, SuccessPresenter>
        implements SuccessView {

    private String name;
    private double amount;

    public SuccessFragment() {
        super(R.layout.fragment_success);
    }

    @Override
    protected int getLayoutRes() { return R.layout.fragment_success; }

    @Override
    protected SuccessPresenter createPresenter() {
        return new SuccessPresenter();
    }

    @Override public void showLoading() {}
    @Override public void hideLoading() {}
    @Override public void showError(String msg) {}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bs) {
        super.onViewCreated(view, bs);
        SuccessKey key = (SuccessKey) Navigator
                .getBackstack(requireActivity())
                .top();
        name   = key.getName();
        amount = key.getAmount();

        ((TextView)view.findViewById(R.id.tvSuccessTitle))
                .setText("Giao dịch thành công");
        ((TextView)view.findViewById(R.id.tvSuccessName))
                .setText("Người nhận: " + name);
        ((TextView)view.findViewById(R.id.tvSuccessAmount))
                .setText("Số tiền: " + amount);

        Button btn = view.findViewById(R.id.btnHome);
        btn.setOnClickListener(v -> {
//                    Navigator.getBackstack(requireActivity()).jumpToRoot() quay lai stack dau danh sach
                    Backstack backstack = Navigator.getBackstack(requireActivity());
                    backstack.goUp(new InputKey()); //tao man hinh moi r them vao stack phai them phuong thuc uals()/hashCode() của InputKey chỉ dựa trên class (vì nó không có args)
//     c2               backstack.setHistory(
//                            History.of(new InputKey()),            // chỉ chứa 1 key mới
//                            StateChange.REPLACE                    // replace toàn bộ => tao route moi xoa toan bo man hinh phia truoc
//                    );
            // cách 3: quay trả về màn đầu tiên => thêm màn mới inputkey
//                    // 1) Pop hết về HomeKey
//                    backstack.jumpToRoot();
//                    // 2) Thêm một instance hoàn toàn mới của InputKey
//                    backstack.goTo(new InputKey());
            // cách 4: thiết lập nguyên history gồm 2 key
                    // Lấy homeKey cũ
//                    Object homeKey = backstack.getHistory().get(0);
//                    // Tạo 1 history mới gồm [homeKey đó, InputKey mới]
//                    backstack.setHistory(
//                            History.of(homeKey, new InputKey()),
//                            StateChange.REPLACE    // REPLACE để xoá sạch lịch sử cũ
//                    );
                }

        );
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
