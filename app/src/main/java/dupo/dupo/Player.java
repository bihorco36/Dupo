package dupo.dupo;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by harald on 08.05.16.
 */
public class Player extends GameObject {
    private float left, top, right, bottom;
    private Paint paint;

    public Player(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.paint = new Paint();
    }

    public float getLeft() {
        return this.left;
    }

    public float getRight() {
        return this.right;
    }

    public float getTop() {
        return this.top;
    }

    public float getBottom() {
        return this.bottom;
    }

    public void setLeft(float l) {
        this.left = l;
    }

    public void setRight(float r) {
        this.right = r;
    }

    public void setTop(float t) {
        this.top = t;
    }

    public void setBottom(float b) {
        this.bottom = b;
    }

    public void setColor(int col) {
        this.paint.setColor(col);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(this.left, this.top, this.right, this.bottom, this.paint);
    }
}

