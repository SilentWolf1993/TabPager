package com.yhy.tabnav.listener;

/**
 * author : 颜洪毅
 * e-mail : yhyzgn@gmail.com
 * time   : 2017-09-14 21:07
 * version: 1.0.0
 * desc   : ViewPager页面切换事件监听器
 */
public interface OnPageChangedListener {

    /**
     * 当页面滑动时
     *
     * @param position             当前页面序号，只有翻页成功的情况下最后一次调用才会变为目标页面。
     * @param positionOffset       是当前页面滑动比例
     * @param positionOffsetPixels 是当前页面滑动像素，变化情况和positionOffset一致。
     */
    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    /**
     * 页面切换成功
     *
     * @param position 当前页面索引
     */
    void onPageSelected(int position);

    /**
     * 手指操作屏幕的时候发生变化。有三个值：0（END）,1(PRESS) , 2(UP)。
     * 当setCurrentItem翻页时，会执行这个方法两次，state值分别为2 , 0 。
     *
     * @param state 有三个值：0（END）,1(PRESS) , 2(UP)
     */
    void onPageScrollStateChanged(int state);
}