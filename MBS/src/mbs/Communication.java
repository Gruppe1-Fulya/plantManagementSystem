package mbs;

import java.io.*;
import java.net.*;

public class Communication {
	public String makePackage(int id, double ph, double light, double water) {
		return String.format("%d-%f-%f-%f",id, ph, light, water); //id ph light water
	}
	
	public void sendPackage(String s) {
		try {
			Socket daSocket = new Socket("localhost", 4242);
			PrintWriter daOut = new PrintWriter(daSocket.getOutputStream(), true);
			BufferedReader daIn = new BufferedReader(new InputStreamReader(daSocket.getInputStream()));
			daOut.println(s);
			
			daOut.close();
            daIn.close();
            daSocket.close();
            
            System.out.println(s);
			
			
		}catch (IOException e) {
            e.printStackTrace();
        }
	}
}
