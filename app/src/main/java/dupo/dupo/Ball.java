package dupo.dupo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Vibrator;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by harald on 08.05.16.
 */
public class Ball extends GameObject {
    private float cx, cy, radius, initCx, initCy;
    private Point displaySize;
    private int ballSpeedTop = 5;
    private int ballSpeedRight = 15;
    private boolean startDown;
    private Vibrator v;

    public Ball(float cx, float cy, float radius, Point displaySize, Context context) {
        this.initCx = this.cx = cx;
        this.initCy = this.cy = cy;
        this.displaySize = displaySize;
        this.radius = radius;
        this.paint = new Paint();
        this.v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void setColor(int col) {
        this.paint.setColor(col);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(this.cx, this.cy, this.radius, this.paint);
    }

    public void move() {
        this.cx += this.ballSpeedRight;
        this.cy += this.ballSpeedTop;
    }

    public float getY() {
        return this.cy;
    }

    public float getX() {
        return this.cx;
    }

    public float getRadius() {
        return this.radius;
    }

    public void checkCollission(Player p, Player bot) {
        if (this.cx >= this.displaySize.x - 1.5*radius) {
            this.ballSpeedRight = invert(this.ballSpeedRight);
        }
        if (this.cx <= 1.5*radius) {
            this.ballSpeedRight = invert(this.ballSpeedRight);
        }
        if (this.cy >= this.displaySize.y - radius) {
            this.toInitialPos();
            this.startDown = false;
            bot.incrementScore();
            v.vibrate(300);
        }
        if (this.cy - this.radius <= 0) {
            this.startDown = true;
            toInitialPos();
            p.incrementScore();
            v.vibrate(300);
        }
        Log.d("Score", Integer.toString(p.score) + " Player - Bot " + Integer.toString(bot.score));
    }

    public void bounceOffTop(float left, float center, float right) {
        if(left <= this.cx && center >= this.cx) {
            this.ballSpeedRight = invert(Math.abs(randomAngle()));
            this.ballSpeedTop = invert(this.ballSpeedTop);
        }
        if(right >= this.cx && center <= this.cx) {
            this.ballSpeedRight = Math.abs(randomAngle());
            this.ballSpeedTop = invert(this.ballSpeedTop);
        }
    }

    public void bounceOffSide (float left, float right) {
//        if((left >= this.cx && this.ballSpeedRight /*+ this.radius */> 0) || (right <= this.cx && this.ballSpeedRight /*- this.radius */< 0)) {
//            this.ballSpeedRight = invert(this.ballSpeedRight);
//        }
    }

    public int randomAngle() {
        Random r = new Random();
        return (r.nextInt(25 - 10) + 10);
    }

    public int invert(int i) {
        return i*-1;
    }

    public void stop() {
        this.ballSpeedRight = this.ballSpeedTop = 0;
    }

    public void toInitialPos() {
        this.cx = this.initCx;
        this.cy = this.initCy;
        if (startDown) {
            this.ballSpeedTop = Math.abs(this.ballSpeedTop);
        } else {
            this.ballSpeedTop = invert(Math.abs(ballSpeedTop));
        }
    }

    public void invertBallSpeedRight() {
        this.ballSpeedRight = invert(this.ballSpeedRight);
    }

    public int getBallSpeedTop() {
        return this.ballSpeedTop;
    }

}
