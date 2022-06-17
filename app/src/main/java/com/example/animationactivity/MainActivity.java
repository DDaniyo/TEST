package com.example.animationactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public class AnimationActivity extends AppCompatActivity {

        ImageView imageView;

        ArrayList<Drawable> drawableList = new ArrayList<Drawable>();
        Handler handler = new Handler();

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            startService(new Intent(getApplicationContext(), Music.class));

            Resources res = getResources();
            drawableList.add(res.getDrawable(R.drawable.image1));
            drawableList.add(res.getDrawable(R.drawable.image2));
            drawableList.add(res.getDrawable(R.drawable.image3));
            drawableList.add(res.getDrawable(R.drawable.image4));
            drawableList.add(res.getDrawable(R.drawable.image5));

            imageView = findViewById(R.id.imageView);
            Button button = (Button) findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnimThread thread = new AnimThread();
                    thread.start();
                }
            });
        }

        class AnimThread extends Thread {
            public void run() {
                int index = 0;
                for (int i = 0; i < 100; i++) {
                    final Drawable drawable = drawableList.get(index);
                    index += 1;
                    if (index > 3) {
                        index = 0;
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageDrawable(drawable);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }}}

