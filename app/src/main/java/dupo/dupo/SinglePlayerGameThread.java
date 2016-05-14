package dupo.dupo;

import android.graphics.Point;
import android.view.View;

import java.util.TimerTask;

/**
 * Created by harald on 14.05.16.
 */
public class SinglePlayerGameThread extends GameThread {
    public SinglePlayerGameThread(Player player, Ball ball, View view, Bot opponent, Point size) {
        super(player, ball, view, opponent, size);
    }

    @Override
    public void run() {
        this.opponent.checkCollision(this.ball);
        this.opponent.move(this.ball, this.size);
        super.run();
    }
}
