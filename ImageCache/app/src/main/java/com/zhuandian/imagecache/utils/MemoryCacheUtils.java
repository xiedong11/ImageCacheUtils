package com.zhuandian.imagecache.utils;


import android.graphics.Bitmap;
import android.util.LruCache;

/**
 *   内存缓存
 * Created by xiedong on 2017/3/16.
 */

public class MemoryCacheUtils {
    private LruCache<String ,Bitmap> mLruCache;

    public MemoryCacheUtils(){
        long maxMemory = Runtime.getRuntime().maxMemory();
        mLruCache = new LruCache<String,Bitmap>((int) (maxMemory / 8)){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getRowBytes()*value.getWidth();
                return byteCount;
            }
        };
    }


    public Bitmap getFromMemory(String url){
        System.out.println("--------------load Image from memory-------------"+url);
        return mLruCache.get(url);
    }




    public void setToMemory(String url ,Bitmap bitmap){
        mLruCache.put(url,bitmap);
        System.out.println("---------- save to memory success ---------");
    }
}
