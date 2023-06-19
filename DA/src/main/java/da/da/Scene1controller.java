package da.da;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Scene1controller implements Initializable{
 @FXML
 private ProgressBar Licht;
 @FXML
 private ProgressBar PH;
 @FXML
 private ProgressBar Feuch;
 @FXML
 private Label Lichtfb;
 @FXML
 private Label PHfb;
 @FXML
 private Label Feuchfb;
 @FXML
 private Label IDLabel;
 @FXML
 private Label opfeuch;
 @FXML
 private Label opph;
 @FXML
 private Label oplicht;
 @FXML
 private Label feuchn;
 @FXML
 private Label phn;
 @FXML
 private Label lichtn;
 private Stage stage;
 private Scene scene;
 private Parent root;
 plant daplant;
 
 database db = new database();
 
 public void setPlant(plant p) {
     this.daplant = p;
 }
 
 int id = 1;
// daplant.id = id % 2 + 1;
// db.readLastData(daplant);
// db.readOptimalData(daplant);
 
 @Override
 public void initialize(URL arg0, ResourceBundle arg1) {

 	 }
 @FXML
 public void changeplant(ActionEvent e) {
	 if (daplant.id < db.getNumOfPlants()) {
         daplant.id += 1;
     } else {
         daplant.id = 1;
     }
	 db.readLastData(daplant);
	 db.readOptimalData(daplant);
	 Lichtfb.setText(App.lichtfeedback(daplant));
 	 PHfb.setText(App.phfeedback(daplant));
 	 Feuchfb.setText(App.feuchtigkeitfeedback(daplant));
 	 opfeuch.setText(Double.toString(daplant.opfeuchtigkeitmax) + " " + Double.toString(daplant.opfeuchtigkeitmin));
	 oplicht.setText(Double.toString(daplant.oplightmax) + " " + Double.toString(daplant.oplightmin));
	 opph.setText(Double.toString(daplant.opphmax) + " " + Double.toString(daplant.opphmin));
	 phn.setText(Double.toString(daplant.pH));
	 lichtn.setText(Double.toString(daplant.licht));
	 feuchn.setText(Double.toString(daplant.feuchtigkeit));
 	 Licht.setProgress(daplant.licht / (daplant.oplightmax + daplant.oplightmin));
 	 PH.setProgress(daplant.pH / (daplant.opphmax + daplant.opphmin));
 	 Feuch.setProgress(daplant.feuchtigkeit / (daplant.opfeuchtigkeitmax + daplant.opfeuchtigkeitmin));
 }
 @FXML
 public void switchToScene2(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
     Parent scene2Root = loader.load();
     Scene2controller scene2Controller = loader.getController();
     scene2Controller.setPlant(daplant);
     scene2Controller.initialize2();
     Scene scene2 = new Scene(scene2Root);
	 Stage primaryStage = new Stage();
	 primaryStage.setScene(scene2);
	 primaryStage.setTitle("PMS");
	 primaryStage.show();
 }
 @FXML
 public void switchToScene(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene.fxml"));
     Parent sceneRoot = loader.load();
     Scenecontroller sceneController = loader.getController();
     sceneController.setPlant(daplant);
     sceneController.initialize2();

     // Switch to Scene
     Scene scene = new Scene(sceneRoot);
	 Stage primaryStage = new Stage();
	 primaryStage.setScene(scene);
	 primaryStage.setTitle("PMS");
	 primaryStage.show();
 }

    public void updateUI() {
     db.readLastData(daplant);
     db.readOptimalData(daplant);
     IDLabel.setText(Integer.toString(daplant.id));
     Lichtfb.setText(App.lichtfeedback(daplant));
     PHfb.setText(App.phfeedback(daplant));
     Feuchfb.setText(App.feuchtigkeitfeedback(daplant));
     opfeuch.setText(Double.toString(daplant.opfeuchtigkeitmax) + " " + Double.toString(daplant.opfeuchtigkeitmin));
     oplicht.setText(Double.toString(daplant.oplightmax) + " " + Double.toString(daplant.oplightmin));
     opph.setText(Double.toString(daplant.opphmax) + " " + Double.toString(daplant.opphmin));
     phn.setText(Double.toString(daplant.pH));
     lichtn.setText(Double.toString(daplant.licht));
     feuchn.setText(Double.toString(daplant.feuchtigkeit));
     Licht.setProgress(daplant.licht / (daplant.oplightmax + daplant.oplightmin));
     PH.setProgress(daplant.pH / (daplant.opphmax + daplant.opphmin));
     Feuch.setProgress(daplant.feuchtigkeit / (daplant.opfeuchtigkeitmax + daplant.opfeuchtigkeitmin));
 }
    public void initilize2() {
        // UI güncelleme işlemini başlatan zamanlayıcı oluştur
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateUI())
        );

        // Zamanlayıcıyı sonsuz tekrarlamak için ayarla
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Güncelleme aralığını başlat
        timeline.play();
    }
 }


