package dupo.dupo;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;

import java.util.TimerTask;

public class GameThread extends TimerTask {
    Bot bot;
    Player player;
    Ball ball;
    View view;
    Point size;
    public GameThread() {
        super();
    }
    public int botScore = 0;
    public int playerScore= 0;

    int playerPoints;
    int botPoints;

    public GameThread(Player player, Ball ball, View view, Bot bot, Point size) {
//        this.bot = bot;
        this.player = player;
        this.ball = ball;
        this.view = view;
        this.size = size;
        this.bot = bot;
    }

    @Override
    public void run() {
        if(!player.running) {
            this.cancel();
        }
        this.ball.move();
        this.bot.move(this.ball, this.size);
        this.bot.checkCollision(this.ball);
        this.ball.checkCollission(this.player, this.bot);
        this.player.checkCollision(this.ball);
        this.view.post(new Runnable() {
            @Override
            public void run() {
                view.invalidate();
            }
        });
    }
}
