package da.da;

import java.io.*;
import java.net.*;

public class Communication {
	public static String receive() {
		String dataPack = new String();
		try {
			 ServerSocket daSocket = new ServerSocket(4242);
			 Socket mbsSocket = daSocket.accept();
			 BufferedReader mbsIn = new BufferedReader(new InputStreamReader(mbsSocket.getInputStream()));
			 PrintWriter mbsOut = new PrintWriter(mbsSocket.getOutputStream(), true);
			 dataPack = mbsIn.readLine();
			 
			 mbsIn.close();
	         mbsOut.close();
	         mbsSocket.close();
	         daSocket.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
        return dataPack;
	}
}
