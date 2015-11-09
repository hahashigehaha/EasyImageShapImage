#### 说明
 EasyShapImageView是能够将ImageView中得图片顺利转换成圆形、椭圆形、圆角矩形的ImageVIew。
###  效果图
 ![](https://github.com/flyme2012/EasyImageShapImage/blob/master/showimage.png)

####  使用

######  <com.moshangjian.ledu.easyimageshaplibrary.EasyShapImageeView
	 android:layout_width="120dp"
        android:layout_height="100dp"
        android:src="@drawable/easyimage_test2"
        shap:imageshap="round"
       />

通过imageshap的自定义属性来控制显示样式，目前可用的样式有：
round、ellipse、fillet三个