/**
 * 
 */
package main.java.synccom;

import java.util.ArrayList;

import main.java.util.JSONFXLoader;

/**
 * @author fpinilla
 *
 */
public class SYNCCOM_Loader {
	static {
//		File dll = new File("src/main/resources/dll/fastcomdll.dll");
		// System.load(dll.getAbsolutePath());
		/// dll/fastcomdll.dll funciona porque esta puesto
		/// <directory>src/main/resources</directory>
		String relativeDllPath = "/dll/fastcomdll.dll";
		System.load(JSONFXLoader.getInstance().getFolderPath(relativeDllPath));

//		try {
//			URL resource = SYNCCOM_Loader.class.getResource(relativeDllPath);
//			String path = resource.toURI().toURL().getPath();
//			if (resource != null && path.lastIndexOf("!") == -1)
//				System.load(path);
//			else {
//				String pathName = SYNCCOM_Loader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
//				pathName = pathName.substring(0,pathName.lastIndexOf("/") );
//				System.load(pathName + relativeDllPath);
//			}
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
	}

	public static native int init();

	public static native void close();

	public static native byte[] read();

	public static native byte[] readWithTimeout(int timeout);

	public static native int write(byte[] data);

	public static native boolean setClockFrequency(long hz);

	public static native ArrayList<Object> SYNCCOM_GET_REGISTERS();

	public static native void SYNCCOM_SET_REGISTERS(Object registers);// ArrayList<Object> registers);

	public static native void SYNCCOM_PURGE_TX();

	public static native void SYNCCOM_PURGE_RX();

	public static native void SYNCCOM_ENABLE_APPEND_STATUS();

	public static native void SYNCCOM_DISABLE_APPEND_STATUS();

	public static native int SYNCCOM_GET_APPEND_STATUS();

	public static native void SYNCCOM_SET_MEMORY_CAP();

	public static native ArrayList<Object> SYNCCOM_GET_MEMORY_CAP(ArrayList<Object> mcapregisters);

	public static native void SYNCCOM_SET_CLOCK_BITS(long clockbits, int ppm);

	public static native void SYNCCOM_ENABLE_IGNORE_TIMEOUT();

	public static native void SYNCCOM_DISABLE_IGNORE_TIMEOUT();

	public static native int SYNCCOM_GET_IGNORE_TIMEOUT();

	public static native void SYNCCOM_SET_TX_MODIFIERS(int modifiers);

	public static native int SYNCCOM_GET_TX_MODIFIERS();

	public static native void SYNCCOM_ENABLE_RX_MULTIPLE();

	public static native void SYNCCOM_DISABLE_RX_MULTIPLE();

	public static native int SYNCCOM_GET_RX_MULTIPLE();

	public static native void SYNCCOM_ENABLE_APPEND_TIMESTAMP();

	public static native void SYNCCOM_DISABLE_APPEND_TIMESTAMP();

	public static native int SYNCCOM_GET_APPEND_TIMESTAMP();

	public static native void SYNCCOM_ENABLE_WAIT_ON_WRITE();

	public static native void SYNCCOM_DISABLE_WAIT_ON_WRITE();

	public static native void SYNCCOM_GET_WAIT_ON_WRITE();

	public static native long SYNCCOM_GET_MEM_USAGE();

//	public native void SYNCCOM_REPROGRAM();

	public static native int SYNCCOM_GET_FIRMWARE();

}