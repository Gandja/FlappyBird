package com.example.flappybird;

import android.graphics.Canvas;

public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    static int gameState;

    public GameEngine() {
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        gameState = 0;
    }

    public void updateAndDrawBackgroundImage(Canvas canvas) {
        backgroundImage.setBackgroundImageX(backgroundImage.getBackgroundImageX() -
                backgroundImage.getBackgroundImageVelocity());
        if (backgroundImage.getBackgroundImageX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setBackgroundImageX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getBackgroundImageX(),
                backgroundImage.getBackgroundImageY(), null);
        if (backgroundImage.getBackgroundImageX() < -AppConstants.getBitmapBank().getBackgroundWidth() -
                AppConstants.SCREEN_WIDTH) ;
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getBackgroundImageX() +
                AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getBackgroundImageY(), null);
    }

    public void updateAndDrawBird(Canvas canvas) {
        if(gameState == 1) {
            if (bird.getBirdY() < AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight() || bird.getVelocity() < 0) {
                bird.setVelocity(bird.getVelocity() + AppConstants.gravity);
                bird.setBirdY(bird.getBirdY() + bird.getVelocity());
            }
        }
        int currentFrame = bird.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), bird.getBirdX(), bird.getBirdY(), null);
        currentFrame++;
        //if it exceed maxFrame re-initialize to 0
        if (currentFrame > bird.getCurrentFrame()) {
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);
    }
}
