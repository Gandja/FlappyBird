package com.example.flappybird;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    static int gameState;
    ArrayList<Tube> tubes;
    Random random;
    int score;
    int scoringTube;
    Paint scorePaint;

    public GameEngine() {
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        gameState = 0;
        tubes = new ArrayList<>();
        random = new Random();
        score = 0;
        scoringTube = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        for (int i = 0; i < AppConstants.numberOfTubes; i++) {
            int tubeX = AppConstants.SCREEN_WIDTH + i * AppConstants.distanceBetweenTubes;
            int topTubeOffsetY = AppConstants.minTubeOffsetY + random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
            Tube tube = new Tube(tubeX, topTubeOffsetY);
            tubes.add(tube);
        }

    }

    public void updateAndDrawTubes(Canvas canvas) {
        if (gameState == 1) {
            if (tubes.get(scoringTube).getTubeX() < bird.getBirdX() + AppConstants.getBitmapBank().getBirdWidth()
                    && tubes.get(scoringTube).getTopTubeOffsetY() > bird.getBirdY()
                    || tubes.get(scoringTube).getBottomTubeY() < bird.getBirdY() + AppConstants.getBitmapBank().getBirdHeight()) {
                gameState = 2;
                //Log.d("Game", "Over");
                AppConstants.getSoundBank().playHit();
                Context context = AppConstants.gameActivityContext;
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
                ((Activity) context).finish();

            } else if (tubes.get(scoringTube).getTubeX() < bird.getBirdX() - AppConstants.getBitmapBank().getTubeWidth()) {
                score++;
                scoringTube++;
                if (scoringTube > AppConstants.numberOfTubes - 1) {
                    scoringTube = 0;
                }
                AppConstants.getSoundBank().playPoint();

            }
            for (int i = 0; i < AppConstants.numberOfTubes; i++) {
                if (tubes.get(i).getTubeX() < -AppConstants.getBitmapBank().getTubeWidth()) {
                    tubes.get(i).setTubeX(tubes.get(i).getTubeX() + AppConstants.numberOfTubes * AppConstants.distanceBetweenTubes);
                    int topTubeOffsetY = AppConstants.minTubeOffsetY + random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
                    tubes.get(i).setTopTubeOffsetY(topTubeOffsetY);
                }
                tubes.get(i).setTubeX(tubes.get(i).getTubeX() - AppConstants.tubeVelocity);
                canvas.drawBitmap(AppConstants.getBitmapBank().getTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                canvas.drawBitmap(AppConstants.getBitmapBank().getTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
            }
            canvas.drawText("Score: " + score, 0, 110, scorePaint);
        }
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
        if (gameState == 1) {
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
