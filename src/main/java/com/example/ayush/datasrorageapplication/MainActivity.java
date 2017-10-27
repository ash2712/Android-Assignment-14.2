package com.example.ayush.datasrorageapplication;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isExternalStorageWritable()) {
            final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
            //converts drawable image into bitmap
            File sd = Environment.getExternalStorageDirectory();
            String fileName = "test.png";
            //creates a file names test.png
            File dest = new File(sd, fileName);
            try {
                FileOutputStream out;
                out = new FileOutputStream(dest);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Button bt = (Button) findViewById(R.id.button);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView = (ImageView) findViewById(R.id.image);
                    imageView.setImageBitmap(bitmap);
                    //displays the image onClick of the button
                }
            });

        }
    }

    public boolean isExternalStorageWritable() {
        //checks if there is an SD card
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        else{
            Toast.makeText(this,"external storage not mounted", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
