package dupo.dupo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MultiplayerMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer_mode);
    }

    public void goToLocalMultiplayer(View view) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new LocalMultiplayerView(this));
    }

    public void goToNetworkMultiplayer(View view) {
        //Intent intent = new Intent(this, BluetoothConnect.class);
        //startActivity(intent);
    }
}
