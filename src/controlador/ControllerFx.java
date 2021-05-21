/**
 * Sample Skeleton for 'principal.fxml' Controller Class
 */

package controlador;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.logging.Logger;

import dll.SYNCCOM_Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import synccom.SYNCCOMRegisters;
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
	private CheckBox checkCR;

	@FXML
	private CheckBox checkLF;

	@FXML
	private MenuItem menuLimpiar;

	@FXML
	private MenuItem menuCopiar;

	@FXML
	private MenuItem menuSeleccionar;

	@FXML
	void onClockListDrag(MouseEvent event) {
	}

	@FXML
	void onSendAction(ActionEvent event) {
		String data = monitorWrite.getText();
		SYNCCOM_Loader.write(data.getBytes());
		SYNCCOM_Loader.SYNCCOM_SET_REGISTERS(new SYNCCOMRegisters());
		if (SYNCCOM_Loader.setClockFrequency(18432000))
			System.out.println("Exito al cambiar clock");
		else 
			System.out.println("Error al cambiar clock");
	}

	@FXML
	void onWriteAction(ActionEvent event) {
	}

	@FXML
	void onCheckCRClick(ActionEvent event) {
		if (checkCR.isSelected())
			;
	}

	@FXML
	void onCheckLFClick(ActionEvent event) {
		if (checkLF.isSelected())
			;
	}

	@FXML
	void onContextMenuCopiar(ActionEvent event) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(new StringSelection(monitorRead.getText()), null);
	}

	@FXML
	void onContextMenuLimpiar(ActionEvent event) {
		monitorRead.setText("");
	}

	@FXML
	void onContextMenuSeleccionar(ActionEvent event) {

	}

	@Override
	public void process(QueueEvent event) {
		switch (event.type) {
		case READ:
			var data = monitorRead.getText() + new String(event.data);
			if (checkCR.isSelected())
				data += "\r";
			if (checkLF.isSelected())
				data += "\n";
			monitorRead.setText(data);
			break;
		case WRITE:
			break;
		default:
			break;
		}

	}

}
