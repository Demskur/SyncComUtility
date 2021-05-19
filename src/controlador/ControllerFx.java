/**
 * Sample Skeleton for 'principal.fxml' Controller Class
 */

package controlador;

import java.util.logging.Logger;

import dll.SYNCCOM_Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.FastcomUtil;
import util.EventQueue;

public class ControllerFx implements EventQueue.EventProcess<ControllerFx.QueueEvent> {
	Logger Log = Logger.getLogger(this.getClass().getName());


	public static class QueueEvent {
		public enum QueueEventType {
			READ, WRITE
		}

		final QueueEventType type;
		final byte[] data;
		final String description;

		public QueueEvent(QueueEventType type, byte[] data, String description) {
			this.type = type;
			this.data = data;
			this.description = description;
		}
	}

	@FXML // fx:id="x1"
	private Font x1; // Value injected by FXMLLoader

	@FXML // fx:id="x2"
	private Color x2; // Value injected by FXMLLoader

	@FXML // fx:id="clockList"
	private ChoiceBox<?> clockList; // Value injected by FXMLLoader

	@FXML // fx:id="monitorWrite"
	private TextField monitorWrite; // Value injected by FXMLLoader

	@FXML // fx:id="x11"
	private Font x11; // Value injected by FXMLLoader

	@FXML // fx:id="x21"
	private Color x21; // Value injected by FXMLLoader

	@FXML // fx:id="monitorSend"
	private Button monitorSend; // Value injected by FXMLLoader

	@FXML // fx:id="monitorRead"
	private TextArea monitorRead; // Value injected by FXMLLoader

	@FXML // fx:id="x3"
	private Font x3; // Value injected by FXMLLoader

	@FXML // fx:id="x4"
	private Color x4; // Value injected by FXMLLoader

	@FXML
	void onClockListDrag(MouseEvent event) {
	}

	@FXML
	void onSendAction(ActionEvent event) {
		String data = monitorWrite.getText();
		monitorRead.setText(data);
		SYNCCOM_Loader.write(data.getBytes());
	}

	@FXML
	void onWriteAction(ActionEvent event) {
	}

	@Override
	public void process(QueueEvent event) {
		System.out.println("probando");
	}

}
