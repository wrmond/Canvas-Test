package com.android.test.canvastest;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	private static final String TAG = "MySurfaceView";  
	private Context mContext;
	private Bitmap mWallpaperBitmap;
	private Bitmap mActor = null;
	private Bitmap mActor2 = null;
	private boolean mVisible = false;
	
	private final Handler mHandler = new Handler();
	private final Runnable mDrawBackground = new Runnable() {
        public void run() {
        	drawBackground();
        }
    };
    private final Runnable mDrawActor = new Runnable() {
        public void run() {
        	drawActor();
        }
    };
	
	public MySurfaceView(Context context , AttributeSet attrs , Drawable wallpaper) {
		super(context, attrs);
		mContext = context; 
        // register our interest in hearing about changes to our surface
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        mWallpaperBitmap = ((BitmapDrawable) wallpaper).getBitmap();
        Log.i(TAG, "MySurfaceView wallpaperBitmap:" + mWallpaperBitmap.toString());
        loadAssets();
	}
	
	private void loadAssets(){
		try{
			InputStream is = mContext.getAssets().open("rilakkuma_001.png");
			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inDither = false;
			opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
			mActor = BitmapFactory.decodeStream(is, null, opt);
			
			is = mContext.getAssets().open("rilakkuma_002.png");
			mActor2 = BitmapFactory.decodeStream(is, null, opt);
		}catch (Exception e){
		}
	}
	
	public void setVisible(boolean v){
		mVisible = v;
	}

//SurfaceHolder.Callback
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		Log.i(TAG, "surfaceCreated start");
		mVisible = true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.i(TAG, "surfaceChanged");
		drawBackground();
		drawActor();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i(TAG, "surfaceDestroyed");
		mVisible = false;
	}
	
	private void drawBackground(){
		final SurfaceHolder holder = this.getHolder();
        Canvas canvas = null;
        try {
        	canvas = holder.lockCanvas();
            if (canvas != null) {
                // draw something
            	canvas.drawBitmap(mWallpaperBitmap, 0, 0, null);
            }
        } finally {
            if (canvas != null) holder.unlockCanvasAndPost(canvas);
        }

        // Reschedule the next redraw
        mHandler.removeCallbacks(mDrawBackground);
        
        //if (mVisible) {
        //    mHandler.postDelayed(mDrawFrame, 1000 / 25);
        //}
	}
	
	private void drawActor(){
		final SurfaceHolder holder = this.getHolder();
        Canvas canvas = null;
        try {
        	canvas = holder.lockCanvas();
            if (canvas != null) {
                // draw something
            	canvas.drawBitmap(mActor2, 75, 50, null);
            	
            	canvas.saveLayer(null, null, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
            	//canvas.saveLayer(null, null, Canvas.FULL_COLOR_LAYER_SAVE_FLAG);
            	
            	
            	canvas.drawBitmap(mActor, 50, 50, null);
            	canvas.restore();
            }
        } finally {
            if (canvas != null) holder.unlockCanvasAndPost(canvas);
        }
        

        // Reschedule the next redraw
        mHandler.removeCallbacks(mDrawActor);
        //if (mVisible) {
        //    mHandler.postDelayed(mDrawFrame, 1000 / 25);
        //}
	}
}



