package main.java;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.java.fx.App;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		App.main(args);
	}

}
