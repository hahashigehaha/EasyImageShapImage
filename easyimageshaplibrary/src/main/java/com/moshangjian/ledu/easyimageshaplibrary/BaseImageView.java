package com.moshangjian.ledu.easyimageshaplibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


import com.moshangjian.ledu.easyimageshaplibrary.inter.ShapInter;

import java.lang.ref.WeakReference;

/**
 * Created by 陌上尖 on 15/11/2.
 */
public abstract  class BaseImageView extends ImageView {
    public BaseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public BaseImageView(Context context) {
        super(context);
        init(context);
    }


    private Paint mPaint ;
    private void init(Context context){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(false);
    }

    private WeakReference<Bitmap> mWeakBitmap ;
    private WeakReference<Bitmap> mMixBitmap ;

    @Override
    public void invalidate() {
        if (mWeakBitmap != null)
            mWeakBitmap = null ;
        if (mMixBitmap != null)
            mMixBitmap = null ;
        super.invalidate();
    }

    private int screenWidth ;
    private int screenHeight ;
    private int drawableWidth ;
    private int drawableHeight ;
    private ShapInter mShapInter  ;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = getWidth() ;
        screenHeight = getHeight() ;
        countDrawableLocal(getDrawable());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()){
            int i = canvas.saveLayer(0,0,screenWidth,screenHeight,null,Canvas.ALL_SAVE_FLAG);
            Bitmap bitmap = mWeakBitmap != null? mWeakBitmap.get():null;
            if (bitmap == null || bitmap.isRecycled()){
                Drawable mDrawable = getDrawable();
                if (mDrawable != null){
                   bitmap =  Bitmap.createBitmap(screenWidth,screenHeight, Bitmap.Config.ARGB_8888);
                   Canvas mCanvas =  new Canvas(bitmap);

                    int left = ( screenWidth - drawableWidth )/ 2 ;
                    int top = (screenHeight - drawableHeight) / 2 ;
                    int right = left + drawableWidth ;
                    int bottom = top + drawableHeight ;
                    mDrawable.setBounds(left, top, right, bottom);
                    mDrawable.draw(mCanvas);
                    if ( mMixBitmap == null ||  mMixBitmap.get() == null ){
                         mMixBitmap = new WeakReference<Bitmap>(mShapInter.getMixBitmap(screenWidth , screenHeight , drawableWidth,drawableHeight));
                    }
                    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                    mCanvas.drawBitmap(mMixBitmap.get(), 0, 0, mPaint);
                    mWeakBitmap = new WeakReference<Bitmap>(bitmap);
                }
            }
            if(bitmap != null){
                mPaint.setXfermode(null);
                canvas.drawBitmap(bitmap, 0, 0, mPaint);
                return;
            }
            canvas.restoreToCount(i);
        }else{
            super.onDraw(canvas);
        }
    }

    private void countDrawableLocal(Drawable mDrawable){
        if (mDrawable == null)
            return;
        float minWidth = mDrawable.getMinimumWidth();
        float minHeight = mDrawable.getMinimumHeight() ;

        boolean ratio = minWidth / minHeight >= (float)screenWidth / (float)screenHeight ;

        if (ratio){
            drawableWidth = screenWidth ;
            drawableHeight = (int) (screenWidth * minHeight / minWidth);
        }else{
            drawableHeight = screenHeight ;
            drawableWidth = (int) (minWidth * screenHeight / minHeight);
        }
    }


    protected  void setShap(ShapInter mShapInter){
        this.mShapInter = mShapInter ;
    }

}
