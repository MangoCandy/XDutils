package com.hnxind.object.Picture;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2016/6/8.
 */
public class FileUtils {
    public FileUtils(){

    }
    //Bitmap转String
    public String file2String(Bitmap bitmap){
        FileInputStream inputStream=null;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte[] buffer=null;
//        try {
//            inputStream=new FileInputStream(head_img);
//            buffer=new byte[inputStream.available()];
//            inputStream.read(buffer);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        buffer=baos.toByteArray();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }
}
