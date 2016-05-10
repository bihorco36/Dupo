package dupo.dupo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by harald on 08.05.16.
 */
public class Ball extends GameObject {
    private float cx, cy, radius;
    private Paint paint;
    private Point displaySize;
    private int ballSpeedTop = 15;
    private int ballSpeedRight = 15;

    public Ball(float cx, float cy, float radius, Point displaySize) {
        this.cx = cx;
        this.cy = cy;
        this.displaySize = displaySize;
        this.radius = radius;
        this.paint = new Paint();
    }

    public float getX() {
        return this.cx;
    }

    public float getY() {
        return this.cy;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setX(float x) {
        this.cx = x;
    }

    public void setY(float y) {
        this.cy = y;
    }

    public void setRadius(float r) {
        this.radius = radius;
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

    public void checkCollission() {
        Log.v("asdf", Float.toString(this.cx));
        Log.v("ghjk", Float.toString(this.displaySize.x - (radius / 2)));
        if (this.cx >= this.displaySize.x - 1.5*radius) {
            this.ballSpeedRight *= -1;
        }
        if (this.cx <= 1.5*radius) {
            this.ballSpeedRight *= -1;
        }
        if (this.cy >= this.displaySize.y - 5*radius) {
            this.ballSpeedTop = 0;
        }
    }
}
