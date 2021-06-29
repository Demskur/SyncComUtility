/**
 * 
 */
/**
 * @author fpinilla
 *
 */
module fastcom_test {
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires java.logging;
	requires java.xml.bind;
	requires java.xml;
	requires com.fasterxml.jackson.databind;

	exports main to javafx.fxml, javafx.graphics;
	exports main.java.controlador to javafx.fxml, javafx.graphics;

	opens main.java.util;
	opens main.java.controlador;
}
