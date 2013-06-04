package com.lockscreen;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class StartLockScreen extends Activity {
/*	 KeyguardManager.KeyguardLock k1;

	 public void onAttachedToWindow() {
			// TODO Auto-generated method stub
			 this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
			super.onAttachedToWindow();
		}*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startService(new Intent(this,MyService.class));
		finish();


		/*getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
	        KeyguardManager km =(KeyguardManager)getSystemService(KEYGUARD_SERVICE);
	        k1 = km.newKeyguardLock("IN");
	        k1.disableKeyguard();*/


	}

}
