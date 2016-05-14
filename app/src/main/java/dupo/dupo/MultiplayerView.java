package dupo.dupo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.Timer;

/**
 * Created by harald on 14.05.16.
 */
public class MultiplayerView extends GameView {
        public Point size = new Point();
        public MultiplayerView(Context context) {
            super(context);
            this.size.set(this.x, this.y);;
            this.opponent = new Player(size.x / 2 - 200, 100, size.x / 2 + 200, 200, this.size, this);
            this.opponent.setColor(Color.BLACK);
            this.MODE = 2;
            Timer t = new Timer();
            t.scheduleAtFixedRate(new MultiplayerGameThread(this.player, this.ball, this, this.opponent,size), 0, 10);
            draw(canvas);
        }

        public MultiplayerView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MultiplayerView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        public void onDraw(Canvas canvas) {
            this.opponentScoreNew = this.opponent.getScore();
            super.onDraw(canvas);
            this.opponent.draw(canvas);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float y = event.getY();
            if(y > size.y / 2) {
                this.player.move(event.getX());
            }
            if(y < size.y / 2){
                this.opponent.move(event.getX());
            }
            draw(canvas);
            return super.onTouchEvent(event);
        }

        public void showScore() {
            super.showScore();

        }

    }
