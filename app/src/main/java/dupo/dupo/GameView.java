package dupo.dupo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;

/**
 * Created by harald on 14.05.16.
 */
public class GameView extends View {
    Paint paint = new Paint();
    Canvas canvas = new Canvas();
    Player player;
    Player opponent;
    Ball ball;
    TextView score;
    Context context;
    WindowManager wm;
    WindowManager.LayoutParams params;
    int playerScoreOld, playerScoreNew, opponentScoreOld, opponentScoreNew = 0;
    TextView scoreTextView;
    protected Sound sound;

    public Point size = new Point();
    public GameView(Context context) {
        super(context);
        setClickable(true);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(size);
        sound = new Sound(context);

        scoreTextView = new TextView(context);
        scoreTextView.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT));
        scoreTextView.setTextSize(50);
        scoreTextView.setText("0 : 0");
        scoreTextView.setGravity(Gravity.CENTER);
        this.params = new WindowManager.LayoutParams (
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT ) ;
        wm.addView(scoreTextView, params);
        this.context = context;
        this.ball = new Ball(size.x / 2 + 25, size.y / 2 + 25, 50, this.size, context);
        this.ball.setColor(Color.RED);
        this.player = new Player(size.x / 2 - 200, 5* (Math.round((size.y - 200) / 5)), size.x / 2 + 200, size.y - 100, size.x, this);
        this.player.setColor(Color.BLACK);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.ball.draw(canvas);
        this.player.draw(canvas);
        this.playerScoreNew = this.player.getScore();
        if(playerScoreNew != playerScoreOld || opponentScoreNew != opponentScoreOld) {
            playerScoreOld = playerScoreNew;
            opponentScoreOld = opponentScoreNew;
            showScore();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        this.player.move(event.getX());
        draw(canvas);
        return super.onTouchEvent(event);
    }

    public void showScore() {
        String score;
        try {
            wm.removeView(this.scoreTextView);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.player.getScore() == 10) {
            score = "Du hast gewonnen!";
            sound.wonGame();
        } else if(this.opponent.getScore() == 10) {
            score = "Du hast verloren!";
            sound.lostGame();
        } else {
            score = Integer.toString(this.player.getScore()) + " : " + Integer.toString(this.opponent.getScore());
        }
        scoreTextView.setText(score);
        wm.addView((View) scoreTextView, params);
    }
}
