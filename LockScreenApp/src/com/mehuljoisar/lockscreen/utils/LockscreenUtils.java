package com.mehuljoisar.lockscreen.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import com.mehuljoisar.lockscreen.R;

public class LockscreenUtils {

	// Member variables
	private OverlayDialog mOverlayDialog;
	private OnLockStatusChangedListener mLockStatusChangedListener;
	
	// Interface to communicate with owner activity
	public interface OnLockStatusChangedListener
	{
		public void onLockStatusChanged(boolean isLocked);
	}
	
	// Reset the variables
	public LockscreenUtils() {
		reset();
	}

	// Display overlay dialog with a view to prevent home button click
	public void lock(Activity activity) {
		if (mOverlayDialog == null) {
			mOverlayDialog = new OverlayDialog(activity);
			mOverlayDialog.show();
			mLockStatusChangedListener = (OnLockStatusChangedListener) activity;
		}
	}
	
	// Reset variables
	public void reset() {
		if (mOverlayDialog != null) {
			mOverlayDialog.dismiss();
			mOverlayDialog = null;
		}
	}

	// Unlock the home button and give callback to unlock the screen
	public void unlock() {
		if (mOverlayDialog != null) {
			mOverlayDialog.dismiss();
			mOverlayDialog = null;
			if(mLockStatusChangedListener!=null)
			{
				mLockStatusChangedListener.onLockStatusChanged(false);
			}
		}
	}

	// Create overlay dialog for lockedscreen to disable hardware buttons
	private static class OverlayDialog extends AlertDialog {

		public OverlayDialog(Activity activity) {
			super(activity, R.style.OverlayDialog);
			WindowManager.LayoutParams params = getWindow().getAttributes();
			params.type = LayoutParams.TYPE_SYSTEM_ERROR;
			params.dimAmount = 0.0F;
			params.width = 0;
			params.height = 0;
			params.gravity = Gravity.BOTTOM;
			getWindow().setAttributes(params);
			getWindow().setFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED | LayoutParams.FLAG_NOT_TOUCH_MODAL,
					0xffffff);
			setOwnerActivity(activity);
			setCancelable(false);
		}

		// consume touch events
		public final boolean dispatchTouchEvent(MotionEvent motionevent) {
			return true;
		}

	}
}
