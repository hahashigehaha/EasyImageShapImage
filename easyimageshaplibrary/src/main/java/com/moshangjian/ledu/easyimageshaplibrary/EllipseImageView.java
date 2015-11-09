package com.moshangjian.ledu.easyimageshaplibrary;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;

import com.moshangjian.ledu.easyimageshaplibrary.inter.ShapInter;

/**
 * Created by 陌上尖 on 15/11/9.  椭圆
 */
public class EllipseImageView implements ShapInter {
    private final static String TAG = "EllipseImageView";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Bitmap getMixBitmap(int sWidth, int sHeight, int dWidth, int dHeight) {

        Bitmap bitmap = Bitmap.createBitmap(sWidth,sHeight, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        RectF oval = new RectF();
        oval.left = ( sWidth - dWidth )/ 2 ;
        oval.top = (sHeight - dHeight) / 2 ;
        oval.right = oval.left + dWidth ;
        oval.bottom = oval.bottom + dHeight ;
        mCanvas.drawOval(oval,paint);
        return bitmap;
    }
}
