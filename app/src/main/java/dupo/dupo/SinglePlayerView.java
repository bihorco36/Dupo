package dupo.dupo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;


public class SinglePlayerView extends GameView {
    private Bot bot;
    public Point size = new Point();
    public SinglePlayerView(Context context) {
        super(context);
        this.bot = new Bot(size.x / 2 - 200, 100, size.x / 2 + 200, 200, this.size.x, this);
        this.bot.setColor(Color.BLACK);
        this.opponent = this.bot;
        Timer t = new Timer();
        t.scheduleAtFixedRate(new SinglePlayerGameThread(this.player, this.ball, this, this.bot,size), 0, 10);
        draw(canvas);
    }

    public SinglePlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SinglePlayerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        this.opponentScoreNew = bot.getScore();
        super.onDraw(canvas);
        this.bot.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        this.player.move(event.getX());
        draw(canvas);
        return super.onTouchEvent(event);
    }

    public void showScore() {
        super.showScore();

    }
}
