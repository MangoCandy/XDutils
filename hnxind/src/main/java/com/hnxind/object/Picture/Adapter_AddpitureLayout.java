package com.hnxind.object.Picture;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hnxind.object.MUtils;
import com.hnxind.object.MView.ShowImage.Act_ShowImage;
import com.hnxind.object.R;
import com.hnxind.object.Toast.MToast;

import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPagerActivity;

/**
 * Created by Administrator on 2016/5/5.
 */
public class Adapter_AddpitureLayout extends BaseAdapter {
    ArrayList<String> paths;
    boolean DELETE_MODE = false;
    Context context;
    public Adapter_AddpitureLayout(List<String> paths){
        this.paths = (ArrayList<String>) paths;
    }


    AddPictureListener addPictureListener ;
    public void setAddPictureListener(AddPictureListener addPictureListener){
        this.addPictureListener = addPictureListener;
    }
    @Override
    public int getCount() {
        return paths.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return paths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    int p;
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        context = parent.getContext();
        p = position;
        if(p == paths.size()){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_button,null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DELETE_MODE&&paths.size()>0){
                        DELETE_MODE = false;
                        notifyDataSetChanged();
                    }else{
                        if(addPictureListener==null){
                            MToast.show(parent.getContext(),"请配置监听器");
                        }else{
                            addPictureListener.AddPicture();
                        }
                    }
                }
            });
        }else {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_addpicture,null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addPictureListener.onNormalClick(position);
                }
            });
            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            Glide.with(parent.getContext()).load(paths.get(position)).into(image);
            ImageView close = (ImageView) convertView.findViewById(R.id.delete);

            if(DELETE_MODE){
                Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
                vib.vibrate(100);
                close.setVisibility(View.VISIBLE);
                image.setAnimation(AnimationUtils.loadAnimation(context,R.anim.big_and_small));
            }else{
                image.clearAnimation();
                close.setVisibility(View.GONE);
            }
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paths.remove(position);
                    notifyDataSetChanged();
                }
            });

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    DELETE_MODE = true;
                    notifyDataSetChanged();
                    return false;
                }
            });
        }

        if(p ==9){
            convertView.setVisibility(View.GONE);
        }else{
            convertView.setVisibility(View.VISIBLE);
        }

        if(convertView!=null){
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = new MUtils(parent.getContext()).getWidth()/3;
            convertView.setLayoutParams(params);
        }
        return convertView;
    }

    public static interface AddPictureListener{
        public void AddPicture();
        public void onNormalClick(int position);
    }
}
