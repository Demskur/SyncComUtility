package main;

import main.java.controlador.ControllerFx;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.synccom.SYNCCOM_Loader;
import main.java.util.SerialThread;
import main.java.synccom.SYNCCOMRegisters;
import main.java.util.EventQueue;

public class Main extends Application {

	public static void main(String[] args) {
		SYNCCOM_Loader.init();
		SYNCCOMRegisters regs = new SYNCCOMRegisters();
//		regs.BGR = 0;
		regs.CCR0 = 0x00112004;
		SYNCCOM_Loader.SYNCCOM_SET_REGISTERS(regs);
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(new File("src/main/resources/fx/principal.fxml").toURI().toURL());
		Parent rootPane = loader.load();
		primaryStage.setScene(new Scene(rootPane));
		primaryStage.show();
		ControllerFx controller = (ControllerFx) loader.getController();
		EventQueue<ControllerFx.QueueEvent> equeue = new EventQueue<ControllerFx.QueueEvent>(controller);
		var serialThread = new SerialThread(null, 100, equeue);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				serialThread.stop();
				SYNCCOM_Loader.close();
				Platform.exit();
				System.exit(0);
			}
		});
	}
}
