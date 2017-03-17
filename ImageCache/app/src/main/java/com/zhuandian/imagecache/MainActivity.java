package com.zhuandian.imagecache;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhuandian.imagecache.utils.MyBitMapUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private MyBitMapUtils myBitMapUtils;
    private static  final String url= "http://img07.tooopen.com/images/20170306/tooopen_sy_200775896618.jpg";
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageview);
        mButton = (Button) findViewById(R.id.btn_loadimg);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });



    }

    private void loadImage() {
        myBitMapUtils = new MyBitMapUtils();
        myBitMapUtils.displayImage(imageView,url);








}
}
