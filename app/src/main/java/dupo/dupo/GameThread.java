package dupo.dupo;

import android.app.Activity;
import android.graphics.Canvas;
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
    Canvas canvas;
    Thread thread;

    public GameThread() {
        super();
    }

    int playerPoints;
    int botPoints;
    boolean running;

    public GameThread(Ball ball, View view) {
//        this.bot = bot;
//        this.player = player;
        this.ball = ball;
        this.view = view;
    }

    @Override
    public void run() {
        this.ball.move();
        this.ball.checkCollission();
        this.view.post(new Runnable() {
            @Override
            public void run() {
                view.invalidate();
            }
        });
    }
}
