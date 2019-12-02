package com.example.flappybird;

public class BackgroundImage {

    private int backgroundImageX, backgroundImageY, backgroundImageVelocity;

    public BackgroundImage() {
        backgroundImageX = 0;
        backgroundImageY = 0;
        backgroundImageVelocity = 3;
    }

    public int getBackgroundImageX() {
        return backgroundImageX;
    }

    public int getBackgroundImageY() {
        return backgroundImageY;
    }

    public int getBackgroundImageVelocity() {
        return backgroundImageVelocity;
    }

    public void setBackgroundImageX(int backgroundImageX) {
        this.backgroundImageX = backgroundImageX;
    }

    public void setBackgroundImageY(int backgroundImageY) {
        this.backgroundImageY = backgroundImageY;
    }

    public void setBackgroundImageVelocity(int backgroundImageVelocity) {
        this.backgroundImageVelocity = backgroundImageVelocity;
    }
}
