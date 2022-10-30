package space.rusakova.funstrategy;

import static java.util.Map.entry;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static java.util.Map.entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class StepOne extends AppCompatActivity {
    int step;
    int nextstep =0;
    Vector <HashMap<String,String>> players = new Vector<HashMap<String, String>>();

    public void CreatePlayers(String my_name)
    {
        // пока создадим два игрока
        Vector<String> names = new Vector<String> ();
        names.add(my_name);
        names.add("PK");
        for (int i = 0; i < 2; i++)
        {
            players.add(new HashMap<String, String>());
            players.elementAt(i).put("name",names.elementAt(i));
            players.elementAt(i).put("money","10000");
            players.elementAt(i).put("factory","2");
            players.elementAt(i).put("auto_factory","0");
            players.elementAt(i).put("esm","4");
            players.elementAt(i).put("egp","2");
            players.elementAt(i).put("loans_sum,","0");
            players.elementAt(i).put("capital","33000");
            players.elementAt(i).put("free_fac","2");
            players.elementAt(i).put("free_auto_fac","0");
        }
    }
    public void CreatePlayers(int gamers, String my_name) // пока не трогаем, для более 2х игроков
    {
        // пока создадим два игрока
        Map<String, String> my_person = Map.ofEntries(
                entry("name","one"),// если будет в итоге несколько игроков, то сделаем вектор мапов
                entry("money","10000"),
                entry("factory","2"),
                entry("auto_factory","0"),
                entry("esm","4"),
                entry("egp","2"),
                entry("loans_sum,","0"), // сумма непогашенных ссуд
                entry("capital","33000"), // гарантированный капитал
                entry("free_fac","2"), // кол-во фабрик, доступных для взятия под них ссуд
                entry("free_auto_fac","0") // количество автоматизированных фабрих, доступных для взятия под них ссуд
        );

        for (int i = 0; i < gamers; i++)
        {

        }
    }
    //массива с судами игрока пока нет, также нет массива строительств
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_one);
        Intent i = getIntent();
        String my_name = i.getStringExtra("name");
        ((TextView)findViewById(R.id.textView)).setText(my_name);
        step = 0;
        ((TextView)findViewById(R.id.step)).setVisibility(View.INVISIBLE);
        ((TextView)findViewById(R.id.comments)).setVisibility(View.INVISIBLE);
        ((TextView)findViewById(R.id.for_game)).setVisibility(View.INVISIBLE);
        CreatePlayers(my_name);
        UpdateInfo();



        //Game();
        /*Log.d("info", my_person.get("name"));
        my_person.put("name", my_name.toString());
        Log.d("info", my_name);
        Log.d("info", my_person.get("name"));*/

    }
    public void nextStep(View v)
    {
        ((Button)findViewById(R.id.next)).setText("Далее");
        ((TextView)findViewById(R.id.step)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.comments)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.for_game)).setVisibility(View.VISIBLE);
        //вот эту штуку можно реализовать через классы,  но мы пока по-быстрому
        if (EndofGame()){

            switch (nextstep%6)
            {
                case 0 : MechA();
                    break;
                case 1: MechB();
                    break;
                case 2: MechC();
                    break;
                case 3: MechD();
                    break;
                case 4: MechE();
                    break;
                case 5: MechI();
                    break;
                default:
                    Log.d("info", "click on default");
                    break;
            }
            UpdateInfo();
            GetStep();// ход пошел
            nextstep++;
        }
        else
        {
            ((TextView)findViewById(R.id.step)).setText ("Game Over");
        }
    }

    @SuppressLint("SetTextI18n")
    public void GetStep()
    {
        ((TextView)findViewById(R.id.step)).setText("Раунд " + (step/6+1) + "; Пункт " + (step%6+1));
        step++;
    }
    @SuppressLint("SetTextI18n")
    public void UpdateInfo()
    {
        ((TextView)findViewById(R.id.money)).setText("money = " + players.elementAt(0).get("money"));
        ((TextView)findViewById(R.id.esm)).setText("esm = " + players.elementAt(0).get("esm"));
        ((TextView)findViewById(R.id.egp)).setText("egp = " + players.elementAt(0).get("egp"));
        ((TextView)findViewById(R.id.factory)).setText("factory = " + players.elementAt(0).get("factory"));
        ((TextView)findViewById(R.id.auto_factory)).setText("auto_factory = " + players.elementAt(0).get("auto_factory"));
        ((TextView)findViewById(R.id.loans_sum)).setText("loans_sum = " + players.elementAt(0).get("loans_sum"));
        ((TextView)findViewById(R.id.capital)).setText("capital = " + players.elementAt(0).get("capital"));
        ((TextView)findViewById(R.id.free_fac)).setText("free_fac = " + players.elementAt(0).get("free_fac"));
        ((TextView)findViewById(R.id.auto_free_fac)).setText("auto_free_fac = " + players.elementAt(0).get("auto_free_fac"));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    public void MechA()
    {
        //постоянные издержи
        ((TextView)findViewById(R.id.comments)).setText(R.string.MechA);
        int allCosts = 0;
        for (int i = 0; i<2; i++)
        {
            allCosts = (players.elementAt(i).get("esm")== "null" ?0:(300 * Integer.parseInt((players.elementAt(i).get("esm"))))) +
                    + (players.elementAt(i).get("egp")== "null" ?0:(500 * Integer.parseInt((players.elementAt(i).get("egp")))))+
                    + (players.elementAt(i).get("factory")== "null" ?0: (1000 * Integer.parseInt((players.elementAt(i).get("factory"))))) +
                    + (players.elementAt(i).get("auto_factory")== "null" ?0: (1500* Integer.parseInt(players.elementAt(i).get("auto_factory"))));
            players.elementAt(i).put("money", (Integer.toString(Integer.parseInt(players.elementAt(i).get("money")) -allCosts)));

        }
    }


    public void MechB()
    {
        //определение обстановки на рынке
        ((TextView)findViewById(R.id.comments)).setText(R.string.MechB);
        // вызов функции

    }
    public int LevelCount(int current_level)
    {
        int count= (int)((Math.random() * ((12 - 1) + 1)) + 1);
/*
        if (2<=lvl && lvl <=4) {
            if (count in range (2, 5, 1))
            lvl += 4;
            else if (5 <= count && count <= 8)
                lvl += 5;
            else if (9 <= count && count <= 11)
                lvl += 1;
            else if (count == 12)
                lvl += 2;
            else lvl += 3;
        }
            if (lvl==1)
    {
            if (5<=count && count<=8):
            Lvl=2
            elif (9<=Count<=10):
            Lvl=3
            elif (Count==12):
            Lvl=5
            elif (Count==11):
            Lvl=4;}

            if (lvl==5){
            if (1<=Count<=4):
            Lvl=4
            elif (9<=Count<=10):
            Lvl=3
            elif (Count==12):
            Lvl=2
            elif (Count==11):
            Lvl=1;
            }
            if (lvl>5)
                lvl-=5;
*/
        double [][]probability_table =
        {
            {1. / 3, 1. / 3, 1. / 6, 1. / 12, 1. / 12},
            {1. / 4, 1. / 3, 1. / 4, 1. / 12, 1. / 12},
            {1. / 12, 1. / 4, 1. / 3, 1. / 4, 1. / 12},
            {1. / 12, 1. / 12, 1. / 4, 1. / 3, 1. / 4},
            {1. / 12, 1. / 12, 1. / 6, 1. / 3, 1. / 3},
        };
        final int level_counts = 5 , denominator = 12 ;

        int [][]levels_table = new int [level_counts][denominator];
        for (int i = 0; i < level_counts; i++)
        {
            int l = 0;
            for (int j = 0; j < level_counts; j++)
                for (int k = 1; k <= probability_table[i][j] * denominator; k++)
                    levels_table[i][l++] = j + 1;
        }
        int new_lvl = levels_table[current_level][count];
        Log.d("mylvl", Integer.toString(new_lvl));
        return new_lvl;
    }
public void change_esmegp(int people, Map<String,String> man, int lvl)
    {
        float round;
        int intround;
        switch (lvl)
        {
            case 1:
                man.put("esm", Integer.toString(people));
                man.put("egp", Integer.toString(3 * people));
                man.put("min", Integer.toString(800));
                man.put("max", Integer.toString(6500));
               break;
            case 2:
                round = (float)1.5 * people;
                intround = (int) round + 1; // на конце всегда 5
                man.put("esm", Integer.toString(intround));
                round = (float)2.5 * people; // проверь
                intround = (int)round + 1;
                man.put("egp", Integer.toString(intround));
                man.put("min", Integer.toString(800));
                man.put("max", Integer.toString(6500));
                break;
            case 3:
                man.put("esm", Integer.toString(2 * people));
                man.put("egp", Integer.toString(2 * people));
                man.put("min", Integer.toString(500));
                man.put("max", Integer.toString(5500));
                break;
            case 4:
                round = (float)2.5 * people; // проверь
                intround = (int)round + 1;
                man.put("esm", Integer.toString(intround));
                round = (float)1.5 * people; // проверь
                intround = (int)round + 1;
                man.put("egp", Integer.toString(2 * people));
                man.put("min", Integer.toString(800));
                man.put("max", Integer.toString(6500));
                break;
            case 5:
                man.put("esm", Integer.toString(3 * people));
                man.put("egp", Integer.toString(3* people));
                man.put("min", Integer.toString(300));
                man.put("max", Integer.toString(4500));
                break;
        }

    }
    public void MechC()
    {
        //заявки на сырье и материалы
        ((TextView)findViewById(R.id.comments)).setText(R.string.MechC);
    }

    public void MechD()
    {
        //Производство продукции
        ((TextView)findViewById(R.id.comments)).setText(R.string.MechD);
    }

    public void MechE()
    {
        //продажа продукции21
        ((TextView)findViewById(R.id.comments)).setText(R.string.MechE);
    }
    public void MechI()
    {
        //Заявки на строительство
        ((TextView)findViewById(R.id.comments)).setText(R.string.MechI);
    }

    public boolean EndofGame()
    {
        Log.d("info", String.valueOf(step));
        return step < 6 * 6;
    }

    // пока в разработке
    public void MechF()
    {
        //выплата ссудного процента
    }
    public void MechG()
    {
        //погашение ссуд
    }
    public void MechH()
    {
        //получние ссуд
    }

}