package com.xialan.beautymall.utils;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by Administrator on 2017/11/23.
 */

public class DrawableUtil {
    /**
     * @param rgb		随机图片颜色
     * @param radius	指定圆角半径
     * @return			绘制出来的图片对象
     */
    @SuppressLint("WrongConstant")
    public static Drawable drawableBitmap(int rgb, int radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        //指定绘制图片的类型(形状),绘制矩形
        gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
        //绘制矩形的颜色
        gradientDrawable.setColor(rgb);
        //指定圆角半径大小
        gradientDrawable.setCornerRadius(radius);
        //返回按照需求绘制的图片
        return gradientDrawable;
    }

    //
    /**
     * @param normal	未选中图片
     * @param press		选中图片
     * @return	根据传递进来的图片,去匹配状态,normal匹配未选中状态,press选中状态
     */
    public static StateListDrawable getStateListDrawable(Drawable normal, Drawable press) {
        //1.创建状态列表的对象
        StateListDrawable stateListDrawable = new StateListDrawable();
        //2.状态和图片绑定
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, press);
        stateListDrawable.addState(new int[]{}, normal);
        //3.返回图片状态选择器对象
        return stateListDrawable;
    }
}
