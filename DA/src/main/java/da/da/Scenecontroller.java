package da.da;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class Scenecontroller  {
	@FXML
	private TextField textfieldlicht;
	@FXML
	private TextField textfieldph;
	@FXML
	private TextField textfieldfeuch;
	@FXML
	private Label lichtmax;
	@FXML
	private Label phmax;
	@FXML
	private Label feuchmax;
	@FXML
	private Label feuchmin;
	@FXML
	private Label phmin;
	@FXML
	private Label lichtmin;
	@FXML
	private Label id;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private plant daplant;
	
	database db = new database();
	 
	public void setPlant(plant p) {
	    this.daplant = p;
	}
	int ıd = 1;


	public void initialize2() {
		db.readLastData(daplant);
		db.readOptimalData(daplant);
		id.setText(Integer.toString(daplant.id));
		lichtmax.setText(Double.toString(daplant.oplightmax));
		phmax.setText(Double.toString(daplant.opphmax));
		feuchmax.setText(Double.toString(daplant.opfeuchtigkeitmax));
		lichtmin.setText(Double.toString(daplant.oplightmin));
		phmin.setText(Double.toString(daplant.opphmin));
		feuchmin.setText(Double.toString(daplant.opfeuchtigkeitmin));
	}
	@FXML
	public void changelichtmax(ActionEvent event) {
		System.out.println("id:" + daplant.id + "--" + daplant.oplightmax);
		db.writeOptimal(daplant.id, daplant.opphmax, daplant.opphmin, Double.parseDouble(textfieldlicht.getText()), daplant.oplightmin, daplant.opfeuchtigkeitmax, daplant.opfeuchtigkeitmin );
		db.readOptimalData(daplant);
		this.initialize2();
	}
	@FXML
	public void changelichtmin(ActionEvent event) {
		db.writeOptimal(daplant.id, daplant.opphmax, daplant.opphmin, daplant.oplightmax, Double.parseDouble(textfieldlicht.getText()), daplant.opfeuchtigkeitmax, daplant.opfeuchtigkeitmin );
		db.readOptimalData(daplant);
		this.initialize2();
	}
	@FXML
	public void changephmax(ActionEvent event) {
		db.writeOptimal(daplant.id, Double.parseDouble(textfieldph.getText()), daplant.opphmin, daplant.oplightmax, daplant.oplightmin, daplant.opfeuchtigkeitmax, daplant.opfeuchtigkeitmin);
		db.readOptimalData(daplant);
		this.initialize2();
	}
	@FXML
	public void changephmin(ActionEvent event) {
		db.writeOptimal(daplant.id, daplant.opphmax, Double.parseDouble(textfieldph.getText()), daplant.oplightmax, daplant.oplightmin, daplant.opfeuchtigkeitmax, daplant.opfeuchtigkeitmin);
		db.readOptimalData(daplant);
		this.initialize2();
	}
	@FXML
	public void changefeuchmin(ActionEvent event) {
		db.writeOptimal(daplant.id, daplant.opphmax, daplant.opphmin, daplant.oplightmax, daplant.oplightmin, daplant.opfeuchtigkeitmax, Double.parseDouble(textfieldfeuch.getText()));
		db.readOptimalData(daplant);
		this.initialize2();
	}
	@FXML
	public void changefeuchmax(ActionEvent event) {
		db.writeOptimal(daplant.id, daplant.opphmax, daplant.opphmin, daplant.oplightmax, daplant.oplightmin, Double.parseDouble(textfieldfeuch.getText()), daplant.opfeuchtigkeitmin);
		db.readOptimalData(daplant);
		this.initialize2();
	}
	@FXML
	public void switchToScene1(ActionEvent event) throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
	    Parent scene1Root = loader.load();
	    Scene1controller scene1Controller = loader.getController();
	    scene1Controller.setPlant(daplant);
		scene1Controller.initilize2();

	    // Switch to Scene 1
	    Scene scene1 = new Scene(scene1Root);
	    Stage primaryStage = new Stage();
	    primaryStage.setScene(scene1);
	    primaryStage.setTitle("Scene 1");
	    primaryStage.show();
	}
	@FXML
	public void changeplant(ActionEvent e) {
		if (daplant.id < db.getNumOfPlants()) {
			daplant.id += 1;
		} else {
			daplant.id = 1;
		}
		 this.initialize2();
	 }

}