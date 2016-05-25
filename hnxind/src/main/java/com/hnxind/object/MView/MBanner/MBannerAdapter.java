package com.hnxind.object.MView.MBanner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MBannerAdapter extends PagerAdapter {
    List<View> views = new ArrayList<>();
    public MBannerAdapter(List<View> views){
        this.views = views;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position % views.size());
        if(container.equals(view.getParent())){
            container.removeView(view);
        }
        container.addView(view);
        return view;
    }
}
