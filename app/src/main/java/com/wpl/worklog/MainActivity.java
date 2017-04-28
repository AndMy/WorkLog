package com.wpl.worklog;

import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.wpl.worklog.adapter.InnerListAdapter;
import com.wpl.worklog.base.BaseActivity;
import com.wpl.worklog.weidget.CustomDialog;
import com.wpl.worklog.utils.JEmailHelper;
import com.wpl.worklog.weidget.InnerListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements JEmailHelper.JieEmailInterface {

    @Bind(R.id.addresseeList)
    InnerListView listView;
    @Bind(R.id.titleName)
    AppCompatTextView titleTv;
    @Bind(R.id.sendTv)
    AppCompatTextView sendTv;

    private JEmailHelper helper;
    private CustomDialog sendDialog;
    private List toEmail; //收件人
    private String title;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        sendDialog = new CustomDialog(this, "正在发送邮件...");
        helper = new JEmailHelper();

        sendDialog.isBackDismiss(false);

        toEmail = new ArrayList<>();
        toEmail.add("wei917351143vip@foxmail.com");
        toEmail.add("917351143@qq.com");

        Date date = new Date(System.currentTimeMillis());
        String curDate = new SimpleDateFormat("MM/dd").format(date);
        title = "科技公司-魏培龙" + curDate + "工作日志";

        listView.setAdapter(new InnerListAdapter(toEmail, this));
        titleTv.setText(title);

        sendTv.setText("18244267955@163.com");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuUser: //账户
                ToastShow("账户");
                break;
            case R.id.menuSet:  //设置
                ToastShow("设置");
                break;
            case R.id.menuAbout: //关于
                ToastShow("关于");
        }
        return true;
    }

    @OnClick(R.id.btn1)
    void onClick() {
        sendMail();
    }

    private void sendMail() {
        helper.setParams(toEmail, null, title, "Dear All : 这是一封测试邮件！", null);
        helper.setJieEmailInterface(this);
        helper.sendEmail();
    }

    @Override
    public void startSend() {
        sendDialog.show();
    }

    @Override
    public void SendStatus(JEmailHelper.SendStatus sendStatus) {
        sendDialog.dismiss();
        switch (sendStatus) {
            case SEND_OK:
                ToastShow("发送成功");
                break;
            case SEND_FAIL:
                ToastShow("发送失败");
                break;
            case SENDING:
                ToastShow("发送中，请稍后再试");
                break;
            default:
                ToastShow("其他错误");
                break;
        }
    }
}
