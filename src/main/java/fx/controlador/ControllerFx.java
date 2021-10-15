/**
 * Sample Skeleton for 'principal.fxml' Controller Class
 */

package main.java.fx.controlador;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.synccom.SYNCCOM_Loader;
import main.java.util.AVAILABLE_REGS;
import main.java.util.EventQueue;
import main.java.util.JSONFXLoader;
import main.java.util.Register;
import main.java.util.Registers;
import main.java.util.SYNCCOMKeys;

public class ControllerFx implements EventQueue.EventProcess<ControllerFx.QueueEvent>, SYNCCOMKeys {
	Logger log = Logger.getLogger(this.getClass().getName());
	boolean viewAsHex = false;
	boolean viewAsBin = false;
	boolean viewAsASCII = false;
	boolean viewAsText = true;
	Registers registers = new Registers();
	Register CCR0;
	Map<String, Object> actionHistory = new LinkedHashMap<>();
	JSONFXLoader parameterLoader = JSONFXLoader.getInstance();

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

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private MenuItem menuSaveMonitor;

	@FXML
	private Font x12;

	@FXML
	private Color x22;

	@FXML
	private ComboBox<String> clockList;

	@FXML
	private TextField clockFrequency;

	@FXML
	private Button loadPresets;

	@FXML
	private ComboBox<String> transmissionModeList;

	@FXML
	private ComboBox<String> lineEncondingModeList;

	@FXML
	private ComboBox<String> frameSyncModeList;

	@FXML
	private CheckBox shareFlagMode;

	@FXML
	private CheckBox interframeTimeFillMode;

	@FXML
	private ComboBox<String> numberOfSyncBytesMode;

	@FXML
	private ComboBox<String> numberOfTerminationBytesMode;

	@FXML
	private CheckBox maskedInterruptsMode;

	@FXML
	private ComboBox<String> crcFrameCheckModeList;

	@FXML
	private CheckBox msbMode;

	@FXML
	private ComboBox<String> addressModeList;

	@FXML
	private CheckBox receiverDisabledMode;

	@FXML
	private ComboBox<String> externalSignalSelectModeList;

	@FXML
	private Button resetToDefaultButton;

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

	// -----------------------------------------------------------------
	Stage primaryStage;
	ByteArrayOutputStream rxBuffer = new ByteArrayOutputStream();

	@SuppressWarnings("exports")
	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}

	@FXML
	void onActionMenuSaveMonitor(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto plano", "*.txt"));
		File saveFile = fileChooser.showSaveDialog(primaryStage);
		if (saveFile != null) {
			try {
				// alternativa String
//				PrintWriter writer = new PrintWriter(saveFile);
//				writer.print(checkTypeView(rxBuffer.toByteArray()));
//				writer.close();
				FileOutputStream fout = new FileOutputStream(saveFile);
				fout.write(rxBuffer.toByteArray());
				fout.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@FXML
	void onActionMenuViewASCII(ActionEvent event) {
		viewAsASCII = menuViewASCII.isSelected();
		viewAsHex = false;
		viewAsBin = false;
		viewAsText = false;
		disableOthers();
		saveHistory(HMI_VIEW_AS_ASCII, menuViewASCII.isSelected());
	}

	@FXML
	void onActionMenuViewBin(ActionEvent event) {
		viewAsBin = menuViewBin.isSelected();
		viewAsHex = false;
		viewAsASCII = false;
		viewAsText = false;
		disableOthers();
		saveHistory(HMI_VIEW_AS_BIN, menuViewBin.isSelected());
	}

	@FXML
	void onActionMenuViewHex(ActionEvent event) {
		viewAsHex = menuViewHex.isSelected();
		viewAsASCII = false;
		viewAsBin = false;
		viewAsText = false;
		disableOthers();
		saveHistory(HMI_VIEW_AS_HEX, menuViewHex.isSelected());
	}

	@FXML
	void onActionMenuViewText(ActionEvent event) {
		viewAsText = menuViewText.isSelected();
		viewAsHex = false;
		viewAsBin = false;
		viewAsASCII = false;
		disableOthers();
		saveHistory(HMI_VIEW_AS_TEXT, menuViewText.isSelected());
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
	void onSetClockFrequencyButton(ActionEvent event) {
		try {
			long frequency = Long.parseLong(clockFrequency.getText());
			if (SYNCCOM_Loader.setClockFrequency(frequency)) { // 18432000
				log.fine("Exito al cambiar clock");
			} else
				log.warning("Error al cambiar clock");
			saveHistory(CLOCK_FREQUENCY, frequency);
		} catch (NumberFormatException e) {
			log.warning("Valor invalido");
		}
	}

	@FXML
	void onWriteAction(ActionEvent event) {
	}

	@FXML
	void onCheckCRClick(ActionEvent event) {
		saveHistory(HMI_ENABLE_CR, checkCR.isSelected());
	}

	@FXML
	void onCheckLFClick(ActionEvent event) {
		saveHistory(HMI_ENABLE_LF, checkLF.isSelected());
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
			return DatatypeConverter.printHexBinary(data);
		} else if (viewAsASCII) {
			BigInteger decimal;
			if (data.length > 0) {
				decimal = new BigInteger(data);
				return decimal.toString();// new String(data, "ISO-8859-1");
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
			try {
				// Concatena el String que se esta mostrando en el monitor con el nuevo String,
				// dependiendo de la View seleccionada
				rxBuffer.write(event.data);
				monitorRead.appendText(checkTypeView(event.data));
			} catch (UnsupportedEncodingException e) {
				log.warning("Error al transformar el byte en view" + e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		int index = clockList.getSelectionModel().getSelectedIndex();
		registers.setClockMode(index);
		saveHistory(CLOCK_MODE, index);
	}

	@FXML
	void onAddressModeList(ActionEvent event) {
		int index = addressModeList.getSelectionModel().getSelectedIndex();
		registers.setAddressMode(index);
		saveHistory(ADDRES_MODE, index);
	}

	@FXML
	void onCRCFrameCheckModeList(ActionEvent event) {
		int index = crcFrameCheckModeList.getSelectionModel().getSelectedIndex();
		registers.setCRCFrameCheckMode(index);
		saveHistory(CRC_FRAME_CHECK_MODE, index);
	}

	@FXML
	void onExternalSignalSelectModeList(ActionEvent event) {
		int index = externalSignalSelectModeList.getSelectionModel().getSelectedIndex();
		registers.setExternalSignalSelectMode(index);
		saveHistory(EXTERNAL_SIGNAL_MODE, index);
	}

	@FXML
	void onFrameSyncControlModeList(ActionEvent event) {
		int index = frameSyncModeList.getSelectionModel().getSelectedIndex();
		registers.setFrameSyncControl(index);
		saveHistory(FRAME_SYNC_MODE, index);
	}

	@FXML
	void onLineEncodingModeList(ActionEvent event) {
		int index = lineEncondingModeList.getSelectionModel().getSelectedIndex();
		registers.setLineEncondingMode(index);
		saveHistory(LINE_ENCODING_MODE, index);
	}

	@FXML
	void onNumberOfSyncBytesModeList(ActionEvent event) {
		int index = numberOfSyncBytesMode.getSelectionModel().getSelectedIndex();
		registers.setNumberOfSyncBytesMode(index);
		saveHistory(NUMBER_OF_SYNC_BYTES, index);
	}

	@FXML
	void onNumberOfTerminationBytesModeList(ActionEvent event) {
		int index = numberOfTerminationBytesMode.getSelectionModel().getSelectedIndex();
		registers.setNumberOfTerminationBytesMode(index);
		saveHistory(NUMBER_OF_TERMINATION_BYTES, index);
	}

	@FXML
	void onTransmissionModeList(ActionEvent event) {
		int index = transmissionModeList.getSelectionModel().getSelectedIndex();
		registers.setTransmissionMode(index);
		saveHistory(TRANSMISSION_MODE, index);
	}

	@FXML
	void onShareFlagMode(ActionEvent event) {
		boolean state = shareFlagMode.isSelected();
		registers.setShareFlagMode(state);
		saveHistory(SHARE_FLAG, state);
	}

	@FXML
	void onResetToDefaultCCR0(ActionEvent event) {
		setAllControlls(((Map<?, ?>) CCR0.get("DEFAULT_BITS")));
	}

	@FXML
	void onMSBMode(ActionEvent event) {
		boolean state = msbMode.isSelected();
		registers.setOrderOfBitTransmissionMode(state);
		saveHistory(ORDER_OF_BYTES, state);
	}

	@FXML
	void onMaskedInterruptsVisibleMode(ActionEvent event) {
		boolean state = maskedInterruptsMode.isSelected();
		registers.setMaskedInterruptsVisibleMode(state);
		saveHistory(MASKED_INTERRUPTS_VISIBLE, state);
	}

	@FXML
	void onReceiverDisabledMode(ActionEvent event) {
		boolean state = receiverDisabledMode.isSelected();
		registers.setReceiverDisableMode(state);
		saveHistory(RECEIVER_DISABLED, state);
	}

	@FXML
	void onInterFrameTimeFillMode(ActionEvent event) {
		boolean state = interframeTimeFillMode.isSelected();
		registers.setInterFrameTimeFillMode(state);
		saveHistory(INTER_FRAME_TIME_FILL, state);
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		CCR0 = parameterLoader.getRegister(AVAILABLE_REGS.CCR0);
		clockList.getItems().setAll(CCR0.getOptionAsArray(CLOCK_MODE));
		
		clockList.getItems().setAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(CLOCK_MODE)).keySet());
		transmissionModeList.getItems()
				.addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(TRANSMISSION_MODE)).keySet());
		lineEncondingModeList.getItems()
				.addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(LINE_ENCODING_MODE)).keySet());
		frameSyncModeList.getItems()
				.addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(FRAME_SYNC_MODE)).keySet());
		numberOfSyncBytesMode.getItems()
				.addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(NUMBER_OF_SYNC_BYTES)).keySet());
		numberOfTerminationBytesMode.getItems()
				.addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(NUMBER_OF_TERMINATION_BYTES)).keySet());
		crcFrameCheckModeList.getItems()
				.addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(CRC_FRAME_CHECK_MODE)).keySet());
		addressModeList.getItems().addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(ADDRES_MODE)).keySet());
		externalSignalSelectModeList.getItems()
				.addAll((Collection<? extends String>) ((Map<?, ?>) CCR0.get(EXTERNAL_SIGNAL_MODE)).keySet());
		setAllControlls(parameterLoader.getHistory());
	}

	private void saveHistory(String key, Object value) {
		actionHistory.put(key, value);
		parameterLoader.setHistory(actionHistory);
	}

	private void setAllControlls(Map<?, ?> map) {
		for (Object obj : map.keySet()) {
			String key = (String) obj;
			Object value = map.get(key);
			switch (key) {
			case CLOCK_MODE:
				clockList.getSelectionModel().select((Integer) value);
				clockList.fireEvent(new ActionEvent());
				break;
			case TRANSMISSION_MODE:
				transmissionModeList.getSelectionModel().select((Integer) value);
				transmissionModeList.fireEvent(new ActionEvent());
				break;
			case LINE_ENCODING_MODE:
				lineEncondingModeList.getSelectionModel().select((Integer) value);
				lineEncondingModeList.fireEvent(new ActionEvent());
				break;
			case FRAME_SYNC_MODE:
				frameSyncModeList.getSelectionModel().select((Integer) value);
				frameSyncModeList.fireEvent(new ActionEvent());
				break;
			case SHARE_FLAG:
				shareFlagMode.setSelected((Boolean) value);
				shareFlagMode.fireEvent(new ActionEvent());
				break;
			case INTER_FRAME_TIME_FILL:
				interframeTimeFillMode.setSelected((Boolean) value);
				interframeTimeFillMode.fireEvent(new ActionEvent());
				break;
			case NUMBER_OF_SYNC_BYTES:
				numberOfSyncBytesMode.getSelectionModel().select((Integer) value);
				numberOfSyncBytesMode.fireEvent(new ActionEvent());
				break;
			case NUMBER_OF_TERMINATION_BYTES:
				numberOfTerminationBytesMode.getSelectionModel().select((Integer) value);
				numberOfTerminationBytesMode.fireEvent(new ActionEvent());
				break;
			case MASKED_INTERRUPTS_VISIBLE:
				maskedInterruptsMode.setSelected((Boolean) value);
				maskedInterruptsMode.fireEvent(new ActionEvent());
				break;
			case CRC_FRAME_CHECK_MODE:
				crcFrameCheckModeList.getSelectionModel().select((Integer) value);
				crcFrameCheckModeList.fireEvent(new ActionEvent());
				break;
			case ORDER_OF_BYTES:
				msbMode.setSelected((Boolean) value);
				msbMode.fireEvent(new ActionEvent());
				break;
			case ADDRES_MODE:
				addressModeList.getSelectionModel().select((Integer) value);
				addressModeList.fireEvent(new ActionEvent());
				break;
			case RECEIVER_DISABLED:
				receiverDisabledMode.setSelected((Boolean) value);
				receiverDisabledMode.fireEvent(new ActionEvent());
				break;
			case EXTERNAL_SIGNAL_MODE:
				externalSignalSelectModeList.getSelectionModel().select((Integer) value);
				externalSignalSelectModeList.fireEvent(new ActionEvent());
				break;
			case HMI_ENABLE_CR:
				checkCR.setSelected((Boolean) value);
				checkCR.fireEvent(new ActionEvent());
				break;
			case HMI_ENABLE_LF:
				checkLF.setSelected((Boolean) value);
				checkLF.fireEvent(new ActionEvent());
				break;
			case HMI_VIEW_AS_ASCII:
				menuViewASCII.setSelected((Boolean) value);
				menuViewASCII.fire();
				break;
			case HMI_VIEW_AS_HEX:
				menuViewHex.setSelected((Boolean) value);
				menuViewHex.fire();
				break;
			case HMI_VIEW_AS_BIN:
				menuViewBin.setSelected((Boolean) value);
				menuViewBin.fire();
				break;
			case HMI_VIEW_AS_TEXT:
				menuViewText.setSelected((Boolean) value);
				menuViewText.fire();
				break;
			case CLOCK_FREQUENCY:
				clockFrequency.setText(Integer.toString((Integer) value));
				loadPresets.fireEvent(new ActionEvent());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + key);
			}
		}
	}

}
