package com.example.quizuno.clienteparcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Receptor extends Thread{
	
	Socket s;

	OnMessage observer;

	public Receptor(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		
		try {
			
			InputStream in = s.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			
			while(true) {
				String line = reader.readLine();
				System.out.println(line);

				observer.OnRecieve(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public interface OnMessage{
		public void OnRecieve(String mensaje);
	}

	public void setObserver(OnMessage observer){
		this.observer = observer;
	}
	
	

}
