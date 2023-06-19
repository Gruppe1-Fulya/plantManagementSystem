package da.da;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
	static database db = new database();
	static plant daplant = new plant();
	static Scene1controller scene1Controller;

	public void start(Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			scene1Controller = fxmlLoader.getController();
			db.readLastData(daplant);
			db.readOptimalData(daplant);
			System.out.println(daplant.id);
			scene1Controller.setPlant(daplant);
			stage.setScene(scene);
			scene1Controller.initilize2();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		dbRunnable dbr = new dbRunnable();
		Thread thread = new Thread(dbr);
		thread.start();
		launch(args);
	}
}
