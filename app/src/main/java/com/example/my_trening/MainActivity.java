package com.example.my_trening;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.my_trening.R;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    //объявляем объекты
    int Quantity;
    int time_zero;
    private static final String KEY_COUNT = "top";
    TextView Tablo_for_time_trening;
    Button Click_zero;
    Button Click_here;
    Button Click_for_timer;
    Button Click_for_zero_timer;
    TextView Tablo;
    MyTask mt;
    int h,m,s,val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //запрещает экрану переворачиваться
        //определяем объекты
        Tablo_for_time_trening = findViewById(R.id.tablo_time_trening);
        Click_here = findViewById(R.id.Button_for_click);
        Click_for_timer = findViewById(R.id.Button_for_timer);
        Click_for_zero_timer=findViewById(R.id.Button_for_timer_zero);
        Tablo = findViewById(R.id.tablo);
        Click_zero = findViewById(R.id.Button_for_zero);
        Click_for_zero_timer.setEnabled(false);

        //пишем обработчик для нажатия кнопки


        Click_for_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mt=new MyTask();
                //time_zero=0;
                mt.execute();
                Click_for_timer.setEnabled(false);
                Click_for_zero_timer.setEnabled(true);


            }
        });
        //пишем обработчик для нажатия кнопки
        Click_zero.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view)
            {
                Quantity = 0;
                Tablo.setText("" + Quantity);
            }
        });
        //пишем обработчик для нажатия кнопки
        Click_for_zero_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //time_zero=1;
                mt.cancel(false);
                Click_for_timer.setEnabled(true);
                Click_for_zero_timer.setEnabled(false);

            }
        });
        //пишем обработчик для нажатия кнопки
        Click_here.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                Quantity++;
                Tablo.setText("" + Quantity);

            }
        });
    }
    //пишем класс MyTask
    class MyTask extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Tablo_for_time_trening.setText("Старт");
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i=0;i<=100000;i++)
            {//if(time_zero==1){break;}
                if (isCancelled()) return null;
                try {

                    sleep(1000);
                    publishProgress(i);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            h=values[0]/3600;
            m=(values[0]-h/3600)/60;
            s=values[0]-h/3600-m*60;
            //if(time_zero==1){h=0;m=0;s=0;}
            Tablo_for_time_trening.setText(Integer.toString(h)+":"+Integer.toString(m)+":"+Integer.toString(s));
            //if(time_zero==1){time_zero=0;mt.cancel(false);}


        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Tablo_for_time_trening.setText("End");
        }
    }
    //Переодределяем метод onSaveInstanceState для сохранения данных приложения при уничтожении Activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, Quantity);
    }
    //Описываем метод onRestoreInstanceState для восстановления значений при восстановлении Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Quantity = savedInstanceState.getInt("top");
        final TextView Tablo;
        Tablo=findViewById(R.id.tablo);
        Tablo.setText(""+Quantity);
    }
}







