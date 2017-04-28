package com.wpl.worklog.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.wpl.worklog.R;

import butterknife.ButterKnife;

/**
 * 所有Activity的基类
 * Created by valentine on 2017/4/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Context context;
    protected Toast toast;
    protected Bundle savedInstanceState;

    protected abstract int initLayoutId();

    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        initLayoutId();
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        context = this;
        ButterKnife.bind(this);
        initStatusBar();
        initView();
    }

    private void initStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.app_color), 30);
    }

    public void ToastShow(String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
