package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            final Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ConnectivityManager connMgr = (ConnectivityManager)
                            getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                    if (networkInfo != null && networkInfo.isConnected()) {
                        // Create background thread to connect and get data
                        String netType = "";
                        if(networkInfo.getTypeName().matches("WIFI")){
                            netType  = "網路狀態是:WiFi";
                        }
                        if(networkInfo.getTypeName().matches("MOBILE")){
                            netType = "網路狀態是:行動數據";
                        }
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("目前網路狀態")        //設定視窗標題
                                .setMessage(netType)    //設定顯示的文字
                                .setPositiveButton("好喔", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();//dismiss:關閉回傳值 ；cancel:尚未完成工作關閉
                                    }
                                })            //設定結束的子視窗
                                .show();        //呈現對話視窗
                    }
                    else {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("目前網路狀態")        //設定視窗標題
                                .setMessage("目前沒有網路")    //設定顯示的文字
                                .setNeutralButton("好的", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();//dismiss:關閉回傳值 ；cancel:尚未完成工作關閉
                                    }
                                })            //設定結束的子視窗
                                .show();        //呈現對話視窗
                    }
                }
            });
        }
}