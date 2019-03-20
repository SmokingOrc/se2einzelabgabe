package com.example.se2_einzelabgabe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.Integer;
import java.io.*;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
cSocket s;
EditText matnr;
TextView Serveranswer, outputt ;
Integer out;
String sentence;
String modsentence;
String manr, output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){ //Einlesen der MatNr
        matnr = this.findViewById(R.id.matnr);
        Serveranswer= this.findViewById(R.id.serveranswer);

        manr=matnr.getText().toString();
        calculate(v);

        s = new cSocket();
        new Thread(s).start();
    }
    public void calculate(View v){/*MultiplizierenSie dieIndizesder Matrikelnummer an deren Position
     gerade Ziffern stehenund
     geben Sie das Produkt aus*/
        matnr = this.findViewById(R.id.matnr);
        outputt=this.findViewById(R.id.Output);

        output=manr;
        out=1;
        for (int i = 0; i < output.length(); i++) {
            if (output.charAt(i) % 2 == 0) {
                out = out * (i + 1);
            }
        }
        outputt.setText(out.toString());

    }


class cSocket implements Runnable{// Verbindungsaufbau zum Server
    Socket s;
    @Override
    public void run(){
        try{
            s = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream ToServer = new DataOutputStream(s.getOutputStream());

            BufferedReader InServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
            sentence = manr;
            ToServer.writeBytes(sentence + "\n");

            modsentence = InServer.readLine();
            Looper.prepare();
            Serveranswer.setText(modsentence);
            s.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }



    }


}





}
