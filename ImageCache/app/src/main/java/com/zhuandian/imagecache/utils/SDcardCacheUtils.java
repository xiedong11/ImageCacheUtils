package com.zhuandian.imagecache.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 本地缓存
 * Created by xiedong on 2017/3/16.
 */

public class SDcardCacheUtils {

    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/zhuandian";

    public Bitmap getFromSDcard(String url) {
        String fileName = url.substring(0, 5);
        File file = new File(CACHE_PATH, fileName);


        if (file.exists()) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));

                System.out.println("------------- load Iamge from SDcard----------");
                return bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    public void saveToSDcard(String url, Bitmap bitmap) {
        String fileName = url.substring(0, 4);

        File file = new File(CACHE_PATH, fileName);
        System.out.println("------" + CACHE_PATH);
        if (!file.exists()){
            System.out.println("---------file is not exist-------");
            try {
                file.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File parentFile = file.getParentFile();

            if (!parentFile.exists()) {
                parentFile.mkdirs();

                System.out.println("---------parent file is not exist-------");
            }

            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
