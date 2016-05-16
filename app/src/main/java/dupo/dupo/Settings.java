package dupo.dupo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    Spinner difficulty;
    Switch vibration, sound;
    Button apply;
    int selectedDifficulty;
    SharedPreferences settings;
    ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settings = getSharedPreferences("settings", 0);
        difficulty = (Spinner)findViewById(R.id.difficulty);
        vibration = (Switch)findViewById(R.id.vibration);
        vibration.setChecked(settings.getBoolean("vibration", true));
        sound = (Switch)findViewById(R.id.sound);
        sound.setChecked(settings.getBoolean("sound", true));
        adapter = ArrayAdapter.createFromResource(this, R.array.difficulty, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);
        selectedDifficulty = settings.getInt("difficulty", 10) / 5 - 2;
        difficulty.setSelection(selectedDifficulty);
        apply = (Button)findViewById(R.id.apply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int botSpeed;
                SharedPreferences.Editor editor = settings.edit();
                editor.clear();
                editor.putBoolean("vibration", vibration.isChecked());
                editor.putBoolean("sound", sound.isChecked());
                botSpeed = (difficulty.getSelectedItemPosition() + 1) * 5 + 5;
                editor.putInt("difficulty", botSpeed);
                editor.commit();
            }
        });
    }
}
