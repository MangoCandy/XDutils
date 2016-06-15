package com.hnxind.object.Picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.util.Base64;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/6/8.
 */
public class FileUtils {
    Random random;
    public FileUtils(){
        random = new Random();
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

    public File compressImage(final File file, final int maxsize, final String savepath){
        Bitmap bitmap= BitmapFactory.decodeFile(file.getPath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        double beishu = 1;
        double size = baos.toByteArray().length/1024;
        if(size>maxsize){
            beishu = size/maxsize;
            bitmap = zoomImage(bitmap,bitmap.getWidth()/Math.sqrt(beishu),bitmap.getHeight()/Math.sqrt(beishu));
        }
        String filename = savepath+"/";
        return saveBitmapFile(bitmap,filename);
    }

    public static Bitmap zoomImage(final Bitmap bgimage, final double newWidth,
                                   final double newHeight) {
                // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    public File saveBitmapFile(Bitmap bitmap,String path){
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        File file=new File(path+"alj"+random.nextInt(1234)+sdf.format(new Date())+".jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
