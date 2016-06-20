package com.hnxind.object.Picture;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/6/16.
 */
public class RecycleItemSpace extends RecyclerView.ItemDecoration {
    private int space;

    public RecycleItemSpace(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildPosition(view) != 0)
            outRect.top = space*2;
            outRect.left = space/2;
            outRect.right = space/2;
    }
}
