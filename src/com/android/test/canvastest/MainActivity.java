package com.android.test.canvastest;

import android.app.Activity;
import android.app.WallpaperManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	
	private static final String TAG = "MainActivity";

	private MySurfaceView mSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Log.i(TAG, "onCreate");
    	WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
    	Drawable mWallpaper = wallpaperManager.getDrawable();
    	Log.i(TAG, mWallpaper.toString());
    	
    	mSurfaceView = new MySurfaceView(this, null , mWallpaper);
    	setContentView(mSurfaceView);
    	
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mSurfaceView.setVisible(true);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSurfaceView.setVisible(true);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mSurfaceView.setVisible(false);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mSurfaceView.setVisible(false);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
