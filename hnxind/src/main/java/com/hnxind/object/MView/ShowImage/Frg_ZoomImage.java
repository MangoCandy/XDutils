package com.hnxind.object.MView.ShowImage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hnxind.object.R;

/**
 * Created by Administrator on 2016/6/28.
 */
public class Frg_ZoomImage extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_zoomimage,null);
    }

    public Frg_ZoomImage getInstance(String path){
        Frg_ZoomImage frgZoomImage = new Frg_ZoomImage();
        Bundle bundle = new Bundle();
        bundle.putString("path",path);
        frgZoomImage.setArguments(bundle);
        return frgZoomImage;
    }
}
