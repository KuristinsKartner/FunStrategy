package space.rusakova.funstrategy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        // Исполняемый код программы
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window w= getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // скрываем нижнюю панель навигации
        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY); // Появляется поверх игры и исчезает
        //Выключим дневную тема - начало
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //Выключим дневную тему - конец

        setContentView(R.layout.activity_main);// телефон, у тебя есть экран с activity_main
        /*final Button startButton=(Button)findViewById(R.id.startButton);// телефон, у тебя есть кнопка
        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startButton.setBackgroundResource(R.drawable.ru_start_button_press); // Почему-то изменяется ширина кнопки при нажатии
            }
        });*/
    }
    public void run_app(View v){
        //launch a new activity
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);

    }

}