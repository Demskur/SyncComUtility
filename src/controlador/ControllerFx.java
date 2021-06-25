/**
 * Sample Skeleton for 'principal.fxml' Controller Class
 */

package controlador;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

import dll.SYNCCOM_Loader;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.AVAILABLE_FUNCTIONS;
import util.EventQueue;
import util.JSONFXLoader;
import util.Registers;

public class ControllerFx implements EventQueue.EventProcess<ControllerFx.QueueEvent> {
	Logger log = Logger.getLogger(this.getClass().getName());
	boolean viewAsHex = false;
	boolean viewAsBin = false;
	boolean viewAsASCII = false;
	boolean viewAsText = true;
	Registers registers = new Registers();
	Map<?, ?> CCR0;

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

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML
	private Font x12;

	@FXML
	private Color x22;

	@FXML
	private ChoiceBox<String> clockList;

	@FXML
	private TextField clockFrequency;

	@FXML
	private Button loadPresets;

	@FXML
	private Font x1;

	@FXML
	private Color x2;

	@FXML
	private TextArea monitorRead;

	@FXML
	private MenuItem menuLimpiar;

	@FXML
	private MenuItem menuCopiar;

	@FXML
	private MenuItem menuSeleccionar;

	@FXML
	private CheckMenuItem menuViewHex;

	@FXML
	private CheckMenuItem menuViewBin;

	@FXML
	private CheckMenuItem menuViewASCII;

	@FXML
	private CheckMenuItem menuViewText;

	@FXML
	private Font x11;

	@FXML
	private Color x21;

	@FXML
	private TextField monitorWrite;

	@FXML
	private Button monitorSend;

	@FXML
	private CheckBox checkLF;

	@FXML
	private CheckBox checkCR;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	@FXML
	void onActionMenuViewASCII(ActionEvent event) {
		viewAsASCII = menuViewASCII.isSelected();
		viewAsHex = false;
		viewAsBin = false;
		viewAsText = false;
		disableOthers();
	}

	@FXML
	void onActionMenuViewBin(ActionEvent event) {
		viewAsBin = menuViewBin.isSelected();
		viewAsHex = false;
		viewAsASCII = false;
		viewAsText = false;
		disableOthers();
	}

	@FXML
	void onActionMenuViewHex(ActionEvent event) {
		viewAsHex = menuViewHex.isSelected();
		viewAsASCII = false;
		viewAsBin = false;
		viewAsText = false;
		disableOthers();
	}

	@FXML
	void onActionMenuViewText(ActionEvent event) {
		viewAsText = menuViewText.isSelected();
		viewAsHex = false;
		viewAsBin = false;
		viewAsASCII = false;
		disableOthers();
	}

	@FXML
	void onSendAction(ActionEvent event) {
		String data = monitorWrite.getText();
		// Concatena el String con CR/LF si esta activado
		if (checkCR.isSelected())
			data += "\r";
		if (checkLF.isSelected())
			data += "\n";
		if (SYNCCOM_Loader.write(data.getBytes()) > 0)
			monitorRead.setText(data);
		;
		monitorRead.setStyle("-fx-text-inner-color: red;");
	}

	private void disableOthers() {
		if (viewAsASCII) {
			menuViewBin.setSelected(false);
			menuViewHex.setSelected(false);
			menuViewText.setSelected(false);

		} else if (viewAsHex) {
			menuViewBin.setSelected(false);
			menuViewASCII.setSelected(false);
			menuViewText.setSelected(false);
		} else if (viewAsBin) {
			menuViewASCII.setSelected(false);
			menuViewHex.setSelected(false);
			menuViewText.setSelected(false);

		} else if (viewAsText) {
			menuViewBin.setSelected(false);
			menuViewHex.setSelected(false);
			menuViewASCII.setSelected(false);

		} else {
			menuViewText.setSelected(true);
			menuViewBin.setSelected(false);
			menuViewHex.setSelected(false);
			menuViewASCII.setSelected(false);
			viewAsHex = false;
			viewAsBin = false;
			viewAsText = true;
			viewAsASCII = false;
		}

	}

	@FXML
	void onLoadPresets(ActionEvent event) {
		try {
			if (SYNCCOM_Loader.setClockFrequency(Long.parseLong(clockFrequency.getText()))) { // 18432000
				log.fine("Exito al cambiar clock");
			} else
				log.warning("Error al cambiar clock");
		} catch (NumberFormatException e) {
			log.warning("Valor invalido");
		}
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

	private String checkTypeView(byte[] data) throws UnsupportedEncodingException {
		if (viewAsHex) {
			monitorRead.setStyle("-fx-text-inner-color: blue;");
			return "0x" + DatatypeConverter.printHexBinary(data);
		} else if (viewAsASCII) {
			BigInteger decimal;
			if (data.length > 0) {
				decimal = new BigInteger(data);
				return "d" + decimal.toString();// new String(data, "ISO-8859-1");
			} else
				return "";
		} else if (viewAsText)
			return new String(data);
		else if (viewAsBin) {
			String binaryString = "";
			for (byte b : data) {
				binaryString += String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
			}
			return binaryString;
		} else
			return null;
	}

	@Override
	public void process(QueueEvent event) {
		switch (event.type) {
		case READ:
			String data = null;
			try {
				// Concatena el String que se esta mostrando en el monitor con el nuevo String,
				// dependiendo de la View seleccionada
				data = monitorRead.getText() + checkTypeView(event.data);
				monitorRead.setText(data);
			} catch (UnsupportedEncodingException e) {
				log.warning("Error al transformar el byte en view" + e.getMessage());
			}
			break;
		case WRITE:
			break;
		default:
			break;
		}
	}

	@FXML
	void onClockModeList(ActionEvent event) {
		registers.setClockMode(clockList.getSelectionModel().getSelectedIndex());
	}

	@FXML
	void onAddressModeList(ActionEvent event) {
		
	}

	@FXML
	void onCRCFrameCheckModeList(ActionEvent event) {

	}

	@FXML
	void onExternalSignalSelectModeList(ActionEvent event) {

	}

	@FXML
	void onFrameSyncControlModeList(ActionEvent event) {

	}

	@FXML
	void onLineEncodingModeList(ActionEvent event) {

	}

	@FXML
	void onNumberOfSyncBytesModeList(ActionEvent event) {

	}

	@FXML
	void onNumberOfTerminationBytesModeList(ActionEvent event) {

	}

	@FXML
	void onTransmissionModeList(ActionEvent event) {

	}

	@FXML
	private void initialize() {
		JSONFXLoader parameterLoader = JSONFXLoader.getInstance();
		CCR0 = (Map<?, ?>) parameterLoader.getRegisters().get(AVAILABLE_FUNCTIONS.CCR0.toString());
		// ((Map<?, ?>) CCR0.get("CLOCK_MODE")).keySet();
		clockList.getItems().addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get("CLOCK_MODE")).keySet());
	}

}
