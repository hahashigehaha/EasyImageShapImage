package com.moshangjian.ledu.easyimageshaplibrary;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.moshangjian.ledu.easyimageshaplibrary.inter.ShapInter;


/**
 * Created by 陌上尖 on 15/11/2.
 */
public class RounImageView implements ShapInter {

    @Override
    public Bitmap getMixBitmap(int sWidth, int sHeight, int dWidth, int dHeight) {

        int radiou = Math.min(dWidth,dHeight)/2;

        Bitmap bitmap = Bitmap.createBitmap(sWidth,sHeight, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        mCanvas.drawCircle(sWidth/2,sHeight/2,radiou,paint);
        return bitmap;
    }


}
