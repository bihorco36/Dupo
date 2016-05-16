package dupo.dupo;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.View;

/**
 * Created by harald on 08.05.16.
 */
public class Bot extends Player {
    private SharedPreferences sharedPreferences;
    private int botSpeed;

    public Bot(float left, float top, float right, float bottom, Point size, View v) {
        super(left, top, right, bottom, size, v);
        this.sharedPreferences = v.getContext().getSharedPreferences("settings", 0);
        this.botSpeed = sharedPreferences.getInt("difficulty", 10);
        Log.d("Botspeed", Integer.toString(this.botSpeed));
    }

    public void move(Ball ball, Point screen) {
        if(ball.getY() < screen.y / 2 && ball.getBallSpeedTop() < 0) {
            if(ball.getX() > this.left + this.width / 2 && this.right <= screen.x - botSpeed) {
                this.left += botSpeed;
                this.right = this.left + this.width;
            }
            if(ball.getX() < this.left + this.width / 2 && this.left >= botSpeed) {
                this.left -= botSpeed;
                this.right = this.left + this.width;
            }
        }
    }

    @Override
    public void setColor(int col) {
        super.setColor(col);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void checkCollision(Ball ball) {
        if(this.bottom == ball.getY() - ball.getRadius()) {
            ball.bounceOffTop(this.left, this.left + this.width / 2, this.right);
        }
    }
}
