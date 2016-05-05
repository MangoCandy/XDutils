package com.hnxind.object.Contacts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hnxind.object.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2015/10/30.
 */
//联系人列表适配器
public class Adapter_show_contacts extends BaseAdapter {
    List<Contacts> contactses;
    Context context;
    boolean canCheck=false;
    Random random=new Random(9);
    HashMap<Integer, Boolean> isSelected=new HashMap<>();
    boolean singleChoise = true;

    public Adapter_show_contacts(List<Contacts> contactses, Context context,boolean canCheck){
        this.contactses=contactses;
        this.context=context;
        this.canCheck=canCheck;
    }
    @Override
    public int getCount() {
        return contactses.size();

    }
    public  HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }
    public void setIsSelected(HashMap<Integer, Boolean> is) {
        isSelected = is;
    }
    public void initData(){
        for(int i=0;i<contactses.size();i++){
            isSelected.put(i,false);
        }
    }
    @Override
    public Object getItem(int position) {
        return contactses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView name;
        TextView name_head;
        TextView num;
        CheckBox checkBox;
        RelativeLayout button;
    }
    Contacts contacts;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        String BEIZHU="无备注";
        contacts= (Contacts) getItem(position);
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.single_edit_contacts,null);
            viewHolder.name=(TextView)convertView.findViewById(R.id.single_contact_name);
            viewHolder.name_head=(TextView)convertView.findViewById(R.id.name_head_text);
            viewHolder.num=(TextView)convertView.findViewById(R.id.single_contact_num);
            viewHolder.checkBox=(CheckBox)convertView.findViewById(R.id.check);
            viewHolder.button = (RelativeLayout)convertView.findViewById(R.id.layout_single_contact);
            if(canCheck){
                viewHolder.checkBox.setVisibility(View.VISIBLE);
            }else{
                viewHolder.checkBox.setVisibility(View.GONE);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ////
        if(canCheck){
            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewHolder.checkBox.isChecked()){
                        viewHolder.checkBox.setChecked(false);
                    }else{
                        viewHolder.checkBox.setChecked(true);
                    }
                }
            });
        }
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSelected.put(position,isChecked);
                contactses.get(position).setSelected(isChecked);
            }
        });
        ////
        BEIZHU=contacts.getBEIZHU();
        if(BEIZHU==null||BEIZHU.equals("")){
            BEIZHU="";
        }
        //颜色代码
        String name=contacts.getCONTACT_NAME();
        viewHolder.name.setText(name);
        viewHolder.name_head.setText(name.charAt(0)+"");
        viewHolder.num.setText(contacts.getCONTACT_NUM());
        if (contacts.getCONTACT_NUM().equals("")){
            viewHolder.num.setVisibility(View.GONE);
        }else{
            viewHolder.num.setVisibility(View.VISIBLE);
        }
        viewHolder.checkBox.setChecked(contacts.isSelected());
        return convertView;
    }
}
