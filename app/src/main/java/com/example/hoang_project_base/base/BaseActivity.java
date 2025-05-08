package com.example.hoang_project_base.base;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.zhuinden.simplestack.AheadOfTimeWillHandleBackChangedListener;
import com.zhuinden.simplestack.BackHandlingModel;
import com.zhuinden.simplestack.Backstack;
import com.zhuinden.simplestack.History;
import com.zhuinden.simplestack.SimpleStateChanger;
import com.zhuinden.simplestack.StateChange;
import com.zhuinden.simplestack.navigator.Navigator;
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentStateChanger;
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider;

public abstract class BaseActivity
        extends AppCompatActivity
        implements SimpleStateChanger.NavigationHandler {
    private static final String TAG = "BaseActivity";

    protected Backstack backstack;
    private DefaultFragmentStateChanger fragmentStateChanger;

    private final OnBackPressedCallback backPressedCallback =
            new OnBackPressedCallback(false) {
                @Override
                public void handleOnBackPressed() {
                    backstack.goBack();
                }
            };

    private final AheadOfTimeWillHandleBackChangedListener updateBackPressedCallback =
            new AheadOfTimeWillHandleBackChangedListener() {
                @Override
                public void willHandleBackChanged(boolean willHandleBack) {
                    backPressedCallback.setEnabled(willHandleBack);
                }
            };

    protected abstract int getLayoutRes();
    protected abstract int getContainerId();
    protected abstract Object initialScreenKey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        getOnBackPressedDispatcher().addCallback(this, backPressedCallback);

        fragmentStateChanger =
                new DefaultFragmentStateChanger(getSupportFragmentManager(), getContainerId());

        backstack = Navigator.configure()
                .setBackHandlingModel(BackHandlingModel.AHEAD_OF_TIME)
                .setScopedServices(new DefaultServiceProvider())
                .setStateChanger(new SimpleStateChanger(this))
                .install(
                        this,
                        (ViewGroup) findViewById(getContainerId()),   // <-- fix ở đây
                        History.single(initialScreenKey())
                );

        backPressedCallback.setEnabled(backstack.willHandleAheadOfTimeBack());
        backstack.addAheadOfTimeWillHandleBackChangedListener(updateBackPressedCallback);
    }

    @Override
    protected void onDestroy() {
        backstack.removeAheadOfTimeWillHandleBackChangedListener(updateBackPressedCallback);
        super.onDestroy();
    }

    @Override
    public void onNavigationEvent(@NonNull StateChange stateChange) {
        // 1) Thực hiện navigation lên fragment
        fragmentStateChanger.handleStateChange(stateChange);

        // 2) Lấy key nằm ở top mới
        Object topKey = stateChange.getNewKeys().top();
        Log.d(TAG, "Navigated to key: " + topKey);

        // 3) Lấy instance fragment vừa replace vào container
//        for(Fragment f : getSupportFragmentManager().getFragments()) {
//            if(f != null && f.isVisible()) {
//                Log.d(TAG, "Visible fragment: " + f.getClass().getSimpleName());
//                break;
//            }
//        }
        // 3) Log tên fragment qua class của key
        String fragmentName = topKey.getClass().getSimpleName();
        Log.d(TAG, "Now showing fragment for key: " + fragmentName);
    }
}
