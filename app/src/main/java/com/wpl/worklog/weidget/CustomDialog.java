package com.wpl.worklog.weidget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.wpl.worklog.R;


/**
 * 自定义透明的dialog
 */
public class CustomDialog extends Dialog {
    private String content;
    private boolean isBackDismiss;
    private DialogPreBack dialogPreBack;

    public CustomDialog(Context context, String content) {
        super(context, R.style.CustomDialog);
        this.content = content;
        initView();
    }

    /**
     * 添加返回键监听
     */
    public CustomDialog(Context context, String content, DialogPreBack dialogPreBack) {
        super(context, R.style.CustomDialog);
        this.content = content;
        this.dialogPreBack = dialogPreBack;
        initView();
    }

    /**
     * 点击返回键 -> dialog是否消息，默认为不消失
     *
     * @param isBackDismiss isBackDismiss
     */
    public void isBackDismiss(boolean isBackDismiss) {
        this.isBackDismiss = isBackDismiss;
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (dialogPreBack != null) {
                    dialogPreBack.onDialogBack();
                }
                if (isBackDismiss) {
                    if (CustomDialog.this.isShowing())
                        CustomDialog.this.dismiss();
                }
                break;
        }
        return true;
    }

    private void initView() {
        setContentView(R.layout.dialog_view);
        ((TextView) findViewById(R.id.tvContent)).setText(content);
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.9f;
        getWindow().setAttributes(attributes);
        setCancelable(false);
    }

    public interface DialogPreBack {
        void onDialogBack();
    }
}