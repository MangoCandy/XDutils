package com.hnxind.object.MView.ShowImage;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class Adapter_zoomimage extends PagerAdapter {
    List<String> pathList;
    List<Fragment> fragmentList = new ArrayList<>();
    public Adapter_zoomimage(List<String> pathList){
        this.pathList = pathList;
        getFragmentList();
    }
    @Override
    public int getCount() {
        return pathList.size();
    }
    public void getFragmentList(){
        for(String path:pathList){
            Frg_ZoomImage frgZoomImage = new Frg_ZoomImage().getInstance(path);
            fragmentList.add(frgZoomImage);
        }
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return fragmentList.get(position);
    }

}
