package da.da;

import java.util.concurrent.TimeUnit;

public class dbRunnable implements Runnable{
	static String s;
	static database db = new database();
	public void run() {
		while(1 == 1) {
			try {
				s = Communication.receive();
				System.out.println(s);
				if(s != null) {
					db.writeData(s);
				}
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
