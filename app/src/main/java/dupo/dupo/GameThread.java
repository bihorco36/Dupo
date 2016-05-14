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
    Player opponent;
    Player player;
    Ball ball;
    View view;
    Point size;
    public GameThread() {
        super();
    }

    public GameThread(Player player, Ball ball, View view, Player opponent, Point size) {
        this.player = player;
        this.ball = ball;
        this.view = view;
        this.size = size;
        this.opponent = opponent;
    }

    @Override
    public void run() {
        if(!player.running) {
            this.cancel();
        }
        this.ball.move();
        this.ball.checkCollission(this.player, this.opponent);
        this.player.checkCollision(this.ball);
        this.view.post(new Runnable() {
            @Override
            public void run() {
                view.invalidate();
            }
        });
    }
}
