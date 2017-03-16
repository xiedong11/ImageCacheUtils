package com.zhuandian.imagecache.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by xiedong on 2017/3/16.
 */

public class MyBitMapUtils {

    private NetCacheUtils netCacheUtils;
    private SDcardCacheUtils sDcardCacheUtils;
    private MemoryCacheUtils memoryCacheUtils;

    public MyBitMapUtils() {
        memoryCacheUtils = new MemoryCacheUtils();
        sDcardCacheUtils = new SDcardCacheUtils();
        netCacheUtils = new NetCacheUtils(sDcardCacheUtils,memoryCacheUtils);
    }



    public void displayImage(ImageView imageView,String url){
        Bitmap fromMemory = memoryCacheUtils.getFromMemory(url);

        if (fromMemory!=null){
            imageView.setImageBitmap(fromMemory);
            return;
        }


        Bitmap froSDCard = sDcardCacheUtils.getFromSDcard(url);
        if (froSDCard!= null){
            imageView.setImageBitmap(froSDCard);

            memoryCacheUtils.setToMemory(url,froSDCard);
            return;
        }


        netCacheUtils.getImageFromNet(imageView,url);

    }
}
