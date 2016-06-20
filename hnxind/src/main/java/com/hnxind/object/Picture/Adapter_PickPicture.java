package com.hnxind.object.Picture;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hnxind.object.MUtils;
import com.hnxind.object.MView.ZhenDong;
import com.hnxind.object.R;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Adapter_PickPicture extends RecyclerView.Adapter<Adapter_PickPicture.MViewHolder> {
    List<String> paths;
    Context context;
    public Adapter_PickPicture(List<String> paths){
        this.paths = paths;
    }
    OnItemOnclickListener onItemOnclickListener;
    public void setOnItemOnclickListener(OnItemOnclickListener onItemOnclickListener){
        this.onItemOnclickListener = onItemOnclickListener;
    }
    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_addpicture,parent,false));
    }

    boolean isDelete = false;

    @Override
    public void onBindViewHolder(final MViewHolder holder, final int position) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = new MUtils(context).getWidth()/3;
        holder.itemView.setLayoutParams(params);
        holder.imageView.setVisibility(View.VISIBLE);
        holder.plus.setVisibility(View.GONE);
        holder.delete.setAnimation(AnimationUtils.loadAnimation(context,R.anim.big_and_small));
        if(position<paths.size()){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemOnclickListener.onItemClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    isDelete = isDelete?false:true;
                    ZhenDong.zhendong(context,100);
                    notifyDataSetChanged();
                    return false;
                }
            });
            Glide.with(context).load(paths.get(position)).into(holder.imageView);
            if(isDelete){
                holder.delete.setVisibility(View.VISIBLE);
            }else {
                holder.delete.clearAnimation();
                holder.delete.setVisibility(View.GONE);
            }
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paths.remove(position);
                    notyfydata();
                }
            });
        } else if(paths.size() == position){
            holder.plus.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);
            holder.delete.clearAnimation();
            holder.delete.setVisibility(View.GONE);
//            holder.imageView .setImageResource(R.drawable.icon_plus);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isDelete){
                        isDelete = false;
                        notifyDataSetChanged();
                    }
                    onItemOnclickListener.onPick();
                }
            });
            holder.itemView.setVisibility(paths.size()>=9?View.GONE:View.VISIBLE);
        }
    }

    public void notyfydata(){
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return paths.size()<9?paths.size()+1:paths.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView delete;
        ImageView plus;
        public MViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            delete = (ImageView)itemView.findViewById(R.id.delete);
            plus = (ImageView)itemView.findViewById(R.id.plus);
        }
    }

    public static interface OnItemOnclickListener{
        public void onItemClick(int position);
        public void onPick();
    }
}
