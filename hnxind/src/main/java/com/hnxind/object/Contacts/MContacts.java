package com.hnxind.object.Contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/19.
 */
public class MContacts {//弹出联系人 选择 本地或自己填写
    Context context;
    List<Contacts> contactses;
    boolean canCheck = false;

    public MContacts(Context context){
        this.context = context;
        contactses = new ArrayList<>();
    }
    String alerTitle="选择联系人";
    //资源方式
    int LOCAL_NUM=0;//获取本地联系人
    int ZIDINGYI_NUM=1;
    int RESOURSE_FLAG = LOCAL_NUM;
    // 获取手机联系人
    private static final String[] PHONES_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.Contacts.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID };
    /**联系人显示名称**/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    /**电话号码**/
    private static final int PHONES_NUMBER_INDEX = 1;

    /**头像ID**/
    private static final int PHONES_PHOTO_ID_INDEX = 2;

    /**联系人的ID**/
    private static final int PHONES_CONTACT_ID_INDEX = 3;

    public void getContacts(){
        contactses.clear();
        ContentResolver resolver = context.getContentResolver();
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,PHONES_PROJECTION, null, null, ContactsContract.CommonDataKinds.Phone.SORT_KEY_ALTERNATIVE);

        if (phoneCursor != null) {
            if(phoneCursor.getCount()<1){

            }
            while (phoneCursor.moveToNext()) {

                //得到手机号码
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                //当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;

                //得到联系人名称
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);

                //得到联系人ID
                Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);

                //得到联系人头像ID
                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);

                //得到联系人头像Bitamp
                Bitmap contactPhoto = null;
                Contacts contacts=new Contacts();
                contacts.setCONTACT_NAME(contactName);
                contacts.setCONTACT_NUM(phoneNumber);
                contacts.setBEIZHU(null);
                contactses.add(contacts);
            }
        }
        phoneCursor.close();
        adapter.notifyDataSetChanged();
        adapter.initData();
    }

    OnGetContactsListListener onGetContactsListListener;
    public void  setOnGetContactsListListener(OnGetContactsListListener onGetContactsListListener){
        this.onGetContactsListListener = onGetContactsListListener;
    }
    OnGetContactListener onGetContactListener;
    public void setOnGetContactListener(OnGetContactListener onGetContactListener){
        this.onGetContactListener = onGetContactListener;
    }

    ListView show_contactses;
    //显示dialog 联系人
    private  AlertDialog.Builder builder=null;
    private  AlertDialog alertDialog=null;
    Adapter_show_contacts adapter;
    public void showCts(){
        if(show_contactses==null){
            adapter = new Adapter_show_contacts(contactses,context,canCheck);
            show_contactses = new ListView(context);
            if(RESOURSE_FLAG == LOCAL_NUM){
                show_contactses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(onGetContactListener!=null){
                            onGetContactListener.returnContact(contactses.get(position));
                            alertDialog.dismiss();
                        }else{
                            Toast.makeText(context,"请配置监听器",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            show_contactses.setAdapter(adapter);
        }

        if(alertDialog==null){
            builder =new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle(alerTitle)
                    .setNegativeButton("取消", null);
            if(RESOURSE_FLAG == ZIDINGYI_NUM){
                builder .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(onGetContactsListListener!=null){
                            List<Contacts> selectContacts=new ArrayList<Contacts>();
                            List<Integer> indexs=new ArrayList<Integer>();
                            for(int i=0;i<contactses.size();i++){
                                if(contactses.get(i).isSelected()){
                                    selectContacts.add(contactses.get(i));
                                    indexs.add(i);
                                }
                            }
                            onGetContactsListListener.returnGetList(selectContacts,indexs);
                        }else{
                            Toast.makeText(context,"请配置监听器",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            builder.setView(show_contactses);
            alertDialog=builder.show();
        }else{
            alertDialog.show();
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(RESOURSE_FLAG==LOCAL_NUM){
                    if(contactses.size()<1){
                        getContacts();
                    }
                }else{
                    adapter.notifyDataSetChanged();
                }
            }
        },200);
    }

    public void setAlerTitle(String title){
        alerTitle = title;
    }

    public void setContactses(List<Contacts> contactses){
        RESOURSE_FLAG = ZIDINGYI_NUM;
        contactses.clear();
        this.contactses = contactses;
//        adapter.initData();
    }

    public void setName(List<String> names){
        RESOURSE_FLAG = ZIDINGYI_NUM;
        this.contactses.clear();
        for(int i=0;i<names.size();i++){
            Contacts contacts = new Contacts();
            contacts.setCONTACT_NAME(names.get(i));
            contacts.setCONTACT_NUM("");
            this.contactses.add(contacts);
        }
//        adapter.initData();
    }

    public void alertContacts(){
        showCts();
    }

    public void withCheckbox(){
        canCheck = true;
    }

    public interface OnGetContactsListListener{
        public void returnGetList(List<Contacts> contactses,List<Integer> indexs);
    }
    public interface OnGetContactListener{
        public void returnContact(Contacts contact);
    }
}
