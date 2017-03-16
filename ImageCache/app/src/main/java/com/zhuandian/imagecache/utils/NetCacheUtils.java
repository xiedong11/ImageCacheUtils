package com.zhuandian.imagecache.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xiedong on 2017/3/16.
 */

public class NetCacheUtils {

    private ImageView mImageView;
    private String mUrl;


    private SDcardCacheUtils mSDcardCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils( SDcardCacheUtils sdcardCacheUtils ,MemoryCacheUtils memoryCacheUtils){
        mMemoryCacheUtils =memoryCacheUtils;
        mSDcardCacheUtils=sdcardCacheUtils;

    }



    public void getImageFromNet(ImageView imageView ,String url){

        new MyAsyncTask().execute(imageView,url);
    }


    class MyAsyncTask extends AsyncTask<Object,Void,Bitmap>{



        @Override
        protected Bitmap doInBackground(Object... params) {
           mImageView = (ImageView) params[0];
            mUrl = (String) params[1];
            mImageView.setTag(mUrl);

            Bitmap bitmap = downLoadBitmap(mUrl);
            return bitmap;

        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null){
                String url = (String) mImageView.getTag();

                if(url.equals(mUrl)){
                    mImageView.setImageBitmap(bitmap);

                    mMemoryCacheUtils.setToMemory(mUrl,bitmap);
                    mSDcardCacheUtils.saveToSDcard(mUrl,bitmap);


                    System.out.println("-----------load image from net -----------");
                }
            }
        }
    }

    private Bitmap downLoadBitmap(String mUrl) {

        HttpURLConnection conn = null;
        try {
            URL url = new URL(mUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.connect();

            if(conn.getResponseCode() ==200){
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
