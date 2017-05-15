package com.yhy.tabpager.pager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yhy.tabpager.TpgActivity;
import com.yhy.tabpager.utils.ToastUtils;
import com.yhy.tabnav.pager.TpgFragment;

import java.util.Random;

public class HybridPager extends TpgFragment {

    private boolean isLoaded;

    @Override
    protected View getSuccessView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        tv.setText("嵌套页面加载成功");
        tv.setTextColor(Color.RED);
        tv.setTextSize(32);
        tv.setGravity(Gravity.CENTER);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, TpgActivity.class);
                startActivity(intent);
            }
        });

        return tv;
    }

    /**
     * 由于该页面是第一页，所以需要重写该方法，并返回true
     *
     * @return 是否首先加载
     */
    @Override
    public boolean shouldLoadDataAtFirst() {
        Bundle args = getArguments();
        boolean firstPage = args.getBoolean("firstPage");
        return !isLoaded && firstPage;
    }

    @Override
    protected void initData() {
        isLoaded = true;

        getDataFromServer();
    }

    @Override
    public void reloadDate(Bundle args) {
        //调用父类方法才会重新显示加载中页面，否则只是执行重新加载操作，不会显示加载中页面
        super.reloadDate(args);

        //子类的具体操作...
        String temp = args.getString("args");
        ToastUtils.shortToast(temp + "页面重新加载数据");

        getDataFromServer();
    }

    private void getDataFromServer() {
        final Random random = new Random();
        new Thread() {
            @Override
            public void run() {
                //模拟网络加载延迟
                SystemClock.sleep(3000);

                //数据加载结束后，需要手动刷新页面状态
                int temp = random.nextInt(3);
                switch (temp) {
                    case 0:
                        mRltHandler.sendSuccessHandler();
                        break;
                    case 1:
                        mRltHandler.sendErrorHandler();
                        break;
                    case 2:
                        mRltHandler.sendEmptyHandler();
                        break;
                    default:
                        break;
                }
            }
        }.start();
    }
}