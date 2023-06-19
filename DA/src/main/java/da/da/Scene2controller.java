package da.da;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.stage.Stage;


public class Scene2controller {

 private Stage stage;
 private Scene scene;
 private Parent root;
 @FXML
 private BarChart lichtchart;
 @FXML
 private BarChart phchart;
 @FXML
 private BarChart feuchchart;
 private plant daplant;

 database db = new database();

 public void setPlant(plant plant) {
     this.daplant = plant;
 }
 @FXML
 public void switchToScene1(ActionEvent event) throws IOException {
	 FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
	 Parent scene1Root = loader.load();
	 Scene1controller scene1Controller = loader.getController();
	 scene1Controller.setPlant(daplant);

	 // Switch to Scene 1
	 Scene scene1 = new Scene(scene1Root);
	 Stage primaryStage = new Stage();
	 primaryStage.setScene(scene1);
	 primaryStage.setTitle("Scene 1");
	 primaryStage.show();
	}
 
 public void initialize2 () {
	 db.readAllData(daplant.id, phchart, lichtchart, feuchchart);
 }
}