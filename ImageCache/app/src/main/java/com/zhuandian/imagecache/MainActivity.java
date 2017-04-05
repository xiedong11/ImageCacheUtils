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
    private static  final String url= "http://images.cnitblog.com/blog/325852/201303/31175530-93003055329b413fabf5b6215e28ab25.png";
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
