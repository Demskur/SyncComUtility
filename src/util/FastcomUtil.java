/**
 * 
 */
package util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author fpinilla
 *
 */
public class FastcomUtil {
	static File device = new File("\\\\.\\SYNCCOM0");

	/**
	 * Escribir hacia el puerto, abre y cierra OutputStream
	 * 
	 * @param data
	 */
	public static void send(String data) {
		FileOutputStream output;
		try {
			output = new FileOutputStream(device);
			output.write(data.getBytes());
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lee desde el puerto, abre y cierra InputStream
	 * 
	 * @return bytes convertidos a String.
	 */
	public static byte[] read() {
//		FileInputStream input;
//		byte[] data = null;
//		try {
//			input = new FileInputStream(device);
//			
//			if (input.available() > 0) {
//				int size = input.read();
//				data = input.readNBytes(size);
//			}
//			input.close();
//			return data;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
		DataInputStream input;
		byte[] data = null;
		try {
			input = new DataInputStream(new FileInputStream(device));	
			
//			if (input.available() > 0) {
//				int size = input.read();
//				data = input.readNBytes(size);
//			}
			input.available();
			input.close();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
