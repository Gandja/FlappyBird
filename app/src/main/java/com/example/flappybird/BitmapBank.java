package com.example.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap background;
    Bitmap[] bird;
    

    public BitmapBank(Resources resources) {
        background = BitmapFactory.decodeResource(resources, R.drawable.main_background);
        background = scaleImage(background);
        bird = new Bitmap[4];
        bird[0] = BitmapFactory.decodeResource(resources,R.drawable.bird_frame1);
        bird[1] = BitmapFactory.decodeResource(resources,R.drawable.bird_frame2);
        bird[2] = BitmapFactory.decodeResource(resources,R.drawable.bird_frame3);
        bird[3] = BitmapFactory.decodeResource(resources,R.drawable.bird_frame4);
    }

    public Bitmap getBird(int frame) {
        return bird[frame];
    }

    public  int getBirdWidth() {
        return bird[0].getWidth();
    }

    public int getBirdHeight() {
        return bird[0].getHeight();
    }

    public Bitmap getBackground() {
        return background;
    }

    public int getBackgroundWidth() {
        return background.getWidth();
    }

    public int getBackgrountHeight() {
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgrountHeight();
        /*
        We'll multiply widthHeightRatio with screenHeight to get scaled width bitmap.
        Then call createScaledBitMao() to create a new bitmap, scaled from an existing bitmap, when possible.
         */
        int backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
