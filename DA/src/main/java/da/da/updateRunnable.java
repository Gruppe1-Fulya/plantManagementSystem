package da.da;

import java.util.concurrent.TimeUnit;

public class updateRunnable implements Runnable {
    Scene1controller s;
    public void controllerSet(Scene1controller s) {
        this.s = s;
    }
    public void run() {
        while(1 == 1) {
            try {
                System.out.println("th2");
                s.initilize2();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
