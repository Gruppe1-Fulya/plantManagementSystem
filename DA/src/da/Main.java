package da;
import java.util.concurrent.TimeUnit;

public class Main{
	static database db = new database();
	public static void main(String[] args) {
		plant daplant = new plant();
		daplant.id = 2;
		db.readLastData(daplant);
		dbRunnable dbr = new dbRunnable();
		Thread thread = new Thread(dbr);
		thread.start();
		while(1 == 1) {
			db.readLastData(daplant);
			System.out.println(daplant.id + "--" + daplant.pH);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (Exception e) {
				System.out.println("Sleep error!");
			}
		}
	}
}
