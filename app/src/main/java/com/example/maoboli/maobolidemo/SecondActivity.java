package com.example.maoboli.maobolidemo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private BlurringView mBlurringView;
    private Button shuffle_button;
    private Button suibian;
    private View blurredView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        shuffle_button = (Button) findViewById(R.id.shuffle_button);
        suibian = (Button) findViewById(R.id.suibian);

        mBlurringView = (BlurringView) findViewById(R.id.blurring_view);
        blurredView = findViewById(R.id.iv_blur);
        // Give the blurring view a reference to the blurred view.
        mBlurringView.setBlurredView(blurredView);

        shuffle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });

        suibian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideView(true);
                showDialog();
            }
        });
    }

    public void hideView(boolean hide){
        int hideView = hide ? View.GONE : View.VISIBLE;
        shuffle_button.setVisibility(hideView);
        suibian.setVisibility(hideView);
        mBlurringView.setVisibility(hideView);
    }

    public void showDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.request_dialog, null);

        BlurringView blurring_view = (BlurringView) view.findViewById(R.id.blurring_view);
        Button back_button = (Button) view.findViewById(R.id.back_button);
        blurring_view.setBlurredView(blurredView);


        final Dialog dialog = new Dialog(SecondActivity.this, R.style.DialogStyle);
        dialog.setContentView(view);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                hideView(false);
            }
        });

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        lp.width = (int) (displayWidth * 0.68); //宽度设置为屏幕的0.5
        lp.height = (int) (displayHeight * 0.25); //高度设置为屏幕的0.5
        view.measure(0, 0);
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

}
