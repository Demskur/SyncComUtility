package main;

import controlador.ControllerFx;
import dll.SYNCCOM_Loader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.EventQueue;

public class Main extends Application {

	public static void main(String[] args) {
		SYNCCOM_Loader.init();
//		try {
//			SYNCCOM_Loader.init();
//			Thread.sleep(1000);
//			SYNCCOM_Loader.write("hola".getBytes());
//			Thread.sleep(1000);			
//			String msj = new String(SYNCCOM_Loader.read());
//			System.out.println(msj);
//			//SYNCCOM_Loader.garbageC();
//
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		var panePrincipal = (Pane) FXMLLoader.load(Main.class.getResource("/fx/principal.fxml"));
		primaryStage.setScene(new Scene(panePrincipal));
		primaryStage.show();
		SerialThread thread = new SerialThread(null, 300);
		EventQueue<ControllerFx.QueueEvent> equeue = new EventQueue<ControllerFx.QueueEvent>(thread);
	}
}
