package com.example.quizuno.clienteparcial;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente extends Thread {

    Socket s;
    Receptor r;

    MainActivity activity;

    public Cliente(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void run() {
        try {
            s = new Socket("10.0.2.2", 5000);

            r = new Receptor(s);
            r.setObserver(activity);
            r.start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void enviar(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //permite que el hilo se ejecute en paralelo
                try {
                    OutputStream outP = s.getOutputStream();
                    //escribe el mensaje
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(outP));
                    out.println("usuario y contraseña");
                    out.flush();
                }catch (IOException e){

                }

            }
        }).start();


    }
}
