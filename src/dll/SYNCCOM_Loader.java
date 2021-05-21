/**
 * 
 */
package dll;

import java.util.ArrayList;

import synccom.SYNCCOMRegisters;

/**
 * @author fpinilla
 *
 */
public class SYNCCOM_Loader {
	static {
		System.loadLibrary("fastcomdll");
	}
	
	public static native int init();
	
	public static native void close();

	public static native byte[] read();

	public static native byte[] readWithTimeout(int timeout);

	public static native int write(byte[] data);

	public static native boolean setClockFrequency(long hz);

	public static native ArrayList<Object> SYNCCOM_GET_REGISTERS();

	public static native void SYNCCOM_SET_REGISTERS(SYNCCOMRegisters registers);//ArrayList<Object> registers);

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