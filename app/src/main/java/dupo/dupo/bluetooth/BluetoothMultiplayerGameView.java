package dupo.dupo.bluetooth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import dupo.dupo.GameView;

/**
 * Created by harald on 16.05.16.
 */
public class BluetoothMultiplayerGameView extends GameView {
    public BluetoothMultiplayerGameView(Context context) {
        super(context);
        draw(new Canvas());
    }

    public void onDraw(Canvas canvas) {
        Paint paint =  new Paint();
        paint.setColor(Color.BLACK);
        this.showScore();
    }
}
