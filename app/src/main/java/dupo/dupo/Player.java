package dupo.dupo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by harald on 08.05.16.
 */
public class Player extends GameObject {
    protected float left, top, right, bottom;
    protected int width, score;
    protected float screenWidth;
    protected Rect rect;

    public Player(float left, float top, float right, float bottom, float screenWidth) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.rect = new Rect((int) left,(int)  top, (int) right, (int) bottom);
        this.width = (int) (right - left);
        this.paint = new Paint();
        this.screenWidth = screenWidth;
    }

    public void move(float x) {
        if (x - width / 2 >= 0 && x + (this.width / 2) <= this.screenWidth) {
            this.left = x - width / 2;
            this.right = x + width / 2;
        }
        this.rect.left = (int) this.left;
        this.rect.right = (int) this.right;
    }

    public void incrementScore() {
        this.score++;
    }

    public void checkCollision(Ball ball) {
        if (this.top == ball.getY() + ball.getRadius()) {
            ball.bounceOffTop(this.left, this.left + this.width / 2, this.right);
        }
        if(this.top < ball.getY() + ball.getRadius() && this.bottom >= ball.getY() - ball.getRadius()) {
            ball.bounceOffSide(this.left, this.right);
        }
    }

    public void setColor(int p) {
        this.paint.setColor(p);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(this.left, this.top, this.right, this.bottom, this.paint);
    }
}

