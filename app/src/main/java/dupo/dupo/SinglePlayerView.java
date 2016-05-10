package dupo.dupo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;


public class SinglePlayerView extends View {
    Paint paint = new Paint();
    Canvas canvas = new Canvas();
    Ball ball;
    Player player;
    Bot bot;
    GameThread game;
    public Point size = new Point();
    public SinglePlayerView(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(size);
        this.ball = new Ball(size.x / 2 + 25, size.y / 2 + 25, 50, this.size);
        this.ball.setColor(Color.RED);
        this.player = new Player(size.x / 2 - 200, size.y - 300, size.x / 2 + 200, size.y - 200);
        this.player.setColor(Color.BLACK);
        this.bot = new Bot(size.x / 2 - 200, 100, size.x / 2 + 200, 200);
        this.bot.setColor(Color.BLACK);
//        this.game = new GameThread(this.bot, this.player, this.ball, this, this.canvas);
//        this.game.setRunning(true);
//        this.game.start();
        draw(canvas);

        Timer t = new Timer();
        t.scheduleAtFixedRate(new GameThread(this.ball, this), 0, 50);
    }

    public SinglePlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SinglePlayerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.ball.draw(canvas);
        this.player.draw(canvas);
        this.bot.draw(canvas);
    }
}
