package dupo.dupo;

import android.graphics.Point;
import android.view.View;

/**
 * Created by harald on 14.05.16.
 */
public class LocalMultiplayerGameThread extends GameThread {
    public LocalMultiplayerGameThread(Player player, Ball ball, View view, Player opponent, Point size) {
        super(player, ball, view, opponent, size);
    }

    @Override
    public void run() {
        super.run();
        this.opponent.checkCollision(this.ball);
    }

}
