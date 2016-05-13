package dupo.dupo;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by harald on 13.05.16.
 */
public class Sound {
    MediaPlayer won, lost, start, bounce;

    public Sound(Context context) {
        this.won = MediaPlayer.create(context, R.raw.won);
        this.lost = MediaPlayer.create(context, R.raw.lost);
        this.start = MediaPlayer.create(context, R.raw.start);
        this.bounce = MediaPlayer.create(context, R.raw.bounce);
    }

    public void startGame() {
        this.start.start();
    }

    public void wonGame() {
        this.won.start();
    }

    public void lostGame() {
        this.lost.start();
    }

    public void bounce() {
        this.bounce.start();
    }
}
