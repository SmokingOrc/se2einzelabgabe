package com.example.se2_einzelabgabe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import java.lang.Integer;
import java.io.*;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {
cSocket s;
EditText matnr;
EditText Serveranswer, outputt ;
Integer out;
String sentence;
String modsentence;
String manr, output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View v){
        matnr = this.findViewById(R.id.matnr);
        Serveranswer= this.findViewById(R.id.serveranswer);

        manr=matnr.getText().toString();


        s = new cSocket();
        new Thread(s).start();
    }
    public void calculate(){
        matnr = this.findViewById(R.id.matnr);
        outputt=this.findViewById(R.id.Output);

        output=outputt.getText().toString();
        out =Integer.parseInt(output);
        out= out%9;
        switch(out) {
            case 1:
                outputt.setText(("Ist Primzahl");
                break;
            case 2:
                outputt.setText(("Ist Primzahl");
                break;
            case 5:
                outputt.setText(("Ist Primzahl");
                break;
            case 7:
                outputt.setText(("Ist Primzahl");
                break;

            default:
                outputt.setText("ist keine Primzahl");
        }
    }


class cSocket implements Runnable{
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
