package com.hnxind.object.MView.MBanner;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.hnxind.object.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MBanner extends RelativeLayout {
    Context context;
    public MBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        initView();
    }
    List<View> viewList;
    int size;
    private int delaytime = 3000;
    public void addViews(List<View> viewList){
        this.viewList = viewList;
        size = viewList.size();
        initView();
    }

    int position;
    public void addViews(List<String> paths, final OnItemClickListenner onItemClickListenner){
        this.onItemClickListenner = onItemClickListenner;
        viewList = new ArrayList<>();
        for(int i = 0;i<((paths.size()==2)?4:paths.size());i++){
            String path = paths.get(i%paths.size());
            Log.i("qaz",i%paths.size()+"");
            final ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(path).placeholder(R.drawable.icon_image).into(imageView);
            position = i%paths.size();
            viewList.add(imageView);
        }
        for(int i = 0;i<viewList.size();i++){
            final int finalI = i;
            viewList.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListenner == null){
                        return;
                    }
                    onItemClickListenner.onClick(finalI %size);
                    handler.removeMessages(1);
                    handler.sendEmptyMessageDelayed(1,delaytime);
                }
            });
        }
        size = paths.size();
        initView();
    }

    OnItemClickListenner onItemClickListenner;
//    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner){
//        this.onItemClickListenner = onItemClickListenner;
//    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            post(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            });
//            handler.sendEmptyMessageDelayed(1,delaytime);
        }
    };
    MBannerAdapter mBannerAdapter;
    LinearLayout linearLayout;
    ViewPager viewPager;
    public void initView(){
        viewPager = new ViewPager(context);
        mBannerAdapter = new MBannerAdapter(viewList);
        viewPager.setAdapter(mBannerAdapter);
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            PageChangeDurationScroller scroller = new PageChangeDurationScroller(viewPager.getContext());
            field.set(viewPager, scroller);
        } catch (Exception e) {

        }
        addView(viewPager);

        linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(Color.parseColor("#44aaaaaa"));
        RelativeLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,80);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(params);
        addView(linearLayout);

        initPoint();
        handler.sendEmptyMessageDelayed(1,delaytime);
//        switch2point(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch2point(position % size);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_DRAGGING){
                    handler.removeMessages(1);
                }else if(state == ViewPager.SCROLL_STATE_IDLE){
                    handler.sendEmptyMessageDelayed(1,delaytime);
                }
            }
        });

        viewPager.setCurrentItem(Integer.MAX_VALUE/2);
    }

    public void switch2point(int position){
//        int x = (position%viewList.size());
        for(int i = 0;i<size;i++){
            ImageView imageView = (ImageView)points.get(i);
            imageView.setSelected(false);
        }
        points.get(position).setSelected(true);

    }
    List<ImageView> points = new ArrayList<>();
    public void initPoint(){
        points.clear();
        for(int i=0;i<size;i++){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.selector_bgabanner_point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15,15);
            params.setMargins(15,15,15,15);
            imageView.setLayoutParams(params);
            linearLayout.addView(imageView);
            points.add(imageView);
        }
    }

    public interface OnItemClickListenner{
        public void onClick(int postion);
    }
}
