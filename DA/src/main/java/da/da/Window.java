package da.da;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class Window extends Application{
	plant daplant;
	
	public void setPlant(plant p) {
		this.daplant = p;
	}

	@Override
	public void start(Stage stage) {
		Scene1controller scene1Controller = new Scene1controller();
		scene1Controller.setPlant(daplant);

		Scene2controller scene2Controller = new Scene2controller();
		scene2Controller.setPlant(daplant);

		Scenecontroller sceneController = new Scenecontroller();
		sceneController.setPlant(daplant);

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
//			fxmlLoader.setController(scene1Controller);
//			Parent root = fxmlLoader.load();

			Scene scene = new Scene(fxmlLoader.load(), 320, 240);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void launchWindow(String[] args) {
        launch(args);
    }
}
