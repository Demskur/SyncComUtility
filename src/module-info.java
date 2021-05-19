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
	exports main to javafx.fxml, javafx.graphics;
	exports controlador to javafx.fxml, javafx.graphics;
	opens util;
	opens controlador;	
}
