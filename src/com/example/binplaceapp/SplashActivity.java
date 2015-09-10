package com.example.binplaceapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

public class SplashActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				finish();
			}
		};
		handler.sendEmptyMessageDelayed(0, 2000);
		
		
		
		/*
		Handler hd = new Handler();
		hd.postDelayed(new Runnable() {
			@Override
			
			
			
			public void run() {
				finish();
			}
		}, );
		*/
	}
}
