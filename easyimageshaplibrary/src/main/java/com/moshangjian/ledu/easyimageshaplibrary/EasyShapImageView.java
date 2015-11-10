package com.moshangjian.ledu.easyimageshaplibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * Created by 陌上尖 on 15/11/6.
 */
public class EasyShapImageView extends  BaseImageView {
    private final static String TAG = "EasyShapImageView";

    public EasyShapImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    public EasyShapImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public EasyShapImageView(Context context) {
        super(context);
        init(context,null);
    }

    private int radio ;

    private void init(Context context ,AttributeSet attrs){
        if (attrs == null )
            return;
        TypedArray typedArray =  context.obtainStyledAttributes(attrs, R.styleable.EasyImageShap);
        int imageShap = typedArray.getInteger(R.styleable.EasyImageShap_imageshap,0);
        radio = typedArray.getDimensionPixelSize(R.styleable.EasyImageShap_filletradio,5);
        typedArray.recycle();
        setImageShapAttr(imageShap);
    }


    private void setImageShapAttr(int shapStype){
        if (shapStype == 0 )
            setShap(new RounImageView());
        else if(shapStype == 1)
            setShap(new EllipseImageView());
        else if (shapStype == 2)
            setShap(new FilletImageView(radio));
    }



}
