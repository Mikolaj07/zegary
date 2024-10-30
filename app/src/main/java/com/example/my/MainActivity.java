package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int sekundy = 0;
    private TextView textViewCzas;
    private boolean dziala = false;
    private boolean stop = false;
    private boolean reset = false;
    String czasdowyswietlania;
    String czasydpZapisywanie = "zapisywanie czasy:\n";
    Button buttonStart;
    Button buttonStop;
    Button buttonReset;
    Button buttonZapisz;
    TextView textViewzapiszneczasy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = findViewById(R.id.buttononStart);
        buttonStop = findViewById(R.id.buttonPauza);
        buttonReset = findViewById(R.id.buttonReset);
        textViewCzas = findViewById(R.id.textViewAktualnyczas);
        buttonZapisz = findViewById(R.id.buttonZapisz);
        textViewzapiszneczasy = findViewById(R.id.textViewZapisanieCzasy);
        if(savedInstanceState !=null){
            sekundy = savedInstanceState.getInt("sekundy");

        }

        uruchomZegar();
        buttonStart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dziala = true;

                    }
                }
        );
        buttonStop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dziala = false;
                    }
                }
        );
        buttonReset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       sekundy = 0;
                       dziala = false;
                       wyswietl(sekundy);
                       czasydpZapisywanie = "0";
                       textViewzapiszneczasy.setText("ni ma");
                    }
                }
        );
        buttonZapisz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czasydpZapisywanie += czasdowyswietlania+ "\n";
                        //TODO wyswoetlic w TExtView pod przyciskiem
                        textViewzapiszneczasy.setText(czasydpZapisywanie);
                    }
                }
        );


    }


    private void uruchomZegar(){
        Handler handler = new Handler();
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {
                        if(dziala) {
                            sekundy++;


                            //TODO wyswietlic czas
                            wyswietl(sekundy);

                        }
                        handler.postDelayed(this,1);
                        //co jaki czas cos robi ttak działa handdler
                    }

                }


        );



    }
    private void wyswietl(int sekundy){
        int s60 = sekundy % 60;
        int h60 = sekundy / 3600;
        int m60 = (sekundy - h60 * 3600) / 60;
        czasdowyswietlania = String.format("%02d:%02d:%02d", h60, m60, s60);
        textViewCzas.setText(czasdowyswietlania);
    }

}