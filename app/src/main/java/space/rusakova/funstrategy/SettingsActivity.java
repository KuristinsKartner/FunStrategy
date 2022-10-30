package space.rusakova.funstrategy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent i = getIntent();
        String message = i.getStringExtra("COOL");

    }

    public void handleText(View v)
    {
        EditText t = findViewById(R.id.source);
        String input = t.getText().toString();
        /*((TextView)findViewById(R.id.output)).setText(input);
        Log.d("info", input);
        Toast.makeText(this, input, Toast.LENGTH_LONG ).show();*/

        Intent i = new Intent(this, StepOne.class);
        i.putExtra("name", input);
        startActivity(i);
    }

}