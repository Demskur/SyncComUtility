/**
 * 
 */
package util;

import java.util.BitSet;

import dll.SYNCCOM_Loader;
import synccom.SYNCCOMRegisters;

/**
 * @author fpinilla
 *
 */
public class Registers extends SYNCCOMRegisters {

	BitSet bitsetRegCCR0 = new BitSet(32);
	BitSet bitsetRegCCR1 = new BitSet(32);

	/**
	 * MODE[1:0] – Mode select: <br/>
	 * Default. Enable HDLC operating mode.
	 * <p>
	 * HDLC operating mode will set some configuration information that is mandated
	 * by the HDLC specification and prevent those configuration bits from being
	 * altered:
	 * </p>
	 * 
	 * <p>
	 * o # of Sync Bytes = 1 CCR0:NSB[3:0] = 001<br />
	 * o # of Term Bytes = 1 CCR0:NTB[3:0] = 001<br />
	 * o Sync = 0x7E SSR:SYNC[31:0] = 0x0000007E<br />
	 * o Term = 0x7E TSR:TERM[31:0] = 0x0000007E<br />
	 * o 0 bit insertion &ndash; On CCR1:ZINS = 1<br />
	 * o Transmitter appends TERM sequence CCR1:DTERM=0
	 * </p>
	 * <p>
	 * <p>
	 * Note these two settings are what is generally considered to be used with
	 * HDLC, however they can be changed to suit your specific application.
	 * </p>
	 * o CRC = CCITT CCR0:CRC[1:0] = 01<br />
	 * o CRC Reset = 0xFFFF CCR1:CRCR = 0
	 * </p>
	 * MODE[1:0] – Mode select: <br>
	 * Enable X-Sync operating mode <br>
	 * MODE[1:0] – Mode select: <br>
	 * Enable Transparent operating mode
	 */
	public void setTransmissionMode(int mode) {
		switch (mode) {
		case 0: // Enable HDLC operating mode.
			bitsetRegCCR0.set(0, false);
			bitsetRegCCR0.set(1, false);
			break;
		case 1: // Enable X-Sync operating mode
			bitsetRegCCR0.set(0);
			bitsetRegCCR0.set(1, false);
			break;
		case 2: // Enable Transparent operating mode
			bitsetRegCCR0.set(0, false);
			bitsetRegCCR0.set(1);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
		setCCR0(bitsetRegCCR0.toLongArray()[0]);
		SYNCCOM_Loader.SYNCCOM_SET_REGISTERS(this);
	}

	/**
	 * CM[2:0]: Clock Mode Select. <br>
	 * This bit field selects one of the eight clock modes.
	 * <p>
	 * CM = 000 clock mode 0<br />
	 * CM = 001 clock mode 1<br />
	 * CM = 010 clock mode 2<br />
	 * CM = 011 clock mode 3<br />
	 * CM = 100 clock mode 4<br />
	 * CM = 101 clock mode 5<br />
	 * CM = 110 clock mode 6<br />
	 * CM = 111 clock mode 7
	 * </p>
	 * 
	 * @param int mode
	 */
	public void setClockMode(int mode) {
		switch (mode) {
		case 0: // Clock Mode 0
			bitsetRegCCR0.set(2, false);
			bitsetRegCCR0.set(3, false);
			bitsetRegCCR0.set(4, false);
			break;
		case 1: // Clock Mode 1
			bitsetRegCCR0.set(2);
			bitsetRegCCR0.set(3, false);
			bitsetRegCCR0.set(4, false);
			break;
		case 2: // Clock Mode 2
			bitsetRegCCR0.set(2, false);
			bitsetRegCCR0.set(3);
			bitsetRegCCR0.set(4, false);
			break;
		case 3: // Clock Mode 3
			bitsetRegCCR0.set(2);
			bitsetRegCCR0.set(3);
			bitsetRegCCR0.set(4, false);
			break;
		case 4: // Clock Mode 4
			bitsetRegCCR0.set(2, false);
			bitsetRegCCR0.set(3, false);
			bitsetRegCCR0.set(4);
			break;
		case 5: // Clock Mode 5
			bitsetRegCCR0.set(2);
			bitsetRegCCR0.set(3, false);
			bitsetRegCCR0.set(4);
			break;
		case 6: // Clock Mode 6
			bitsetRegCCR0.set(2, false);
			bitsetRegCCR0.set(3);
			bitsetRegCCR0.set(4);
			break;
		case 7: // Clock Mode 7
			bitsetRegCCR0.set(2);
			bitsetRegCCR0.set(3);
			bitsetRegCCR0.set(4);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
		setCCR0(bitsetRegCCR0.toLongArray()[0]);
		SYNCCOM_Loader.SYNCCOM_SET_REGISTERS(this);
	}

	/**
	 * LE[2:0]: Line Encoding.<br>
	 * This bit field selects the line encoding of the serial port.
	 * <p>
	 * LE = 000 NRZ data encoding<br />
	 * LE = 001 NRZI data encoding<br />
	 * LE = 010 FM0 data encoding<br />
	 * LE = 011 FM1 data encoding<br />
	 * LE = 100 Manchester data encoding<br />
	 * LE = 101 Differential Manchester data encoding<br />
	 * LE = 110 reserved<br />
	 * LE = 111 reserved
	 * </p>
	 * 
	 * @param int mode
	 */
	public void setLineEncondingMode(int mode) {
		switch (mode) {
		case 0: // NRZ data encoding
			bitsetRegCCR0.set(5, false);
			bitsetRegCCR0.set(6, false);
			bitsetRegCCR0.set(7, false);
			break;
		case 1: // NRZI data encoding
			bitsetRegCCR0.set(5);
			bitsetRegCCR0.set(6, false);
			bitsetRegCCR0.set(7, false);
			break;
		case 2: // FM0 data encoding
			bitsetRegCCR0.set(5, false);
			bitsetRegCCR0.set(6);
			bitsetRegCCR0.set(7, false);
			break;
		case 3: // FM1 data encoding
			bitsetRegCCR0.set(5);
			bitsetRegCCR0.set(6);
			bitsetRegCCR0.set(7, false);
			break;
		case 4: // Manchester data encoding
			bitsetRegCCR0.set(5, false);
			bitsetRegCCR0.set(6, false);
			bitsetRegCCR0.set(7);
			break;
		case 5: // Differential Manchester data encoding
			bitsetRegCCR0.set(5);
			bitsetRegCCR0.set(6, false);
			bitsetRegCCR0.set(7);
			break;
		case 6: // reserved
			bitsetRegCCR0.set(5, false);
			bitsetRegCCR0.set(6);
			bitsetRegCCR0.set(7);
			break;
		case 7: // reserved
			bitsetRegCCR0.set(5);
			bitsetRegCCR0.set(6);
			bitsetRegCCR0.set(7);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}

	/**
	 * FSC[2:0]: Frame Sync Control.<br>
	 * This bit field selects the behavior of the Frame Sync signals.
	 * <p>
	 * FSC = 000 No Frame Sync<br />
	 * FSC = 001 Mode 1<br />
	 * FSC = 010 Mode 2<br />
	 * FSC = 011 Mode 3<br />
	 * FSC = 100 Both Mode 2 and Mode 3 at the same time.<br />
	 * FSC = 101 Mode 5<br />
	 * FSC = 110 reserved<br />
	 * FSC = 111 reserved
	 * </p>
	 * 
	 * @param int mode
	 */
	public void setFrameSyncControl(int mode) {
		switch (mode) {
		case 0: // No Frame Sync
			bitsetRegCCR0.set(8, false);
			bitsetRegCCR0.set(9, false);
			bitsetRegCCR0.set(10, false);
			break;
		case 1: // Mode 1
			bitsetRegCCR0.set(8);
			bitsetRegCCR0.set(9, false);
			bitsetRegCCR0.set(10, false);
			break;
		case 2: // Mode 2
			bitsetRegCCR0.set(8, false);
			bitsetRegCCR0.set(9);
			bitsetRegCCR0.set(10, false);
			break;
		case 3: // Mode 3
			bitsetRegCCR0.set(8);
			bitsetRegCCR0.set(9);
			bitsetRegCCR0.set(10, false);
			break;
		case 4: // Both Mode 2 and Mode 3 at the same time.
			bitsetRegCCR0.set(8, false);
			bitsetRegCCR0.set(9, false);
			bitsetRegCCR0.set(10);
			break;
		case 5: // Mode 5
			bitsetRegCCR0.set(8);
			bitsetRegCCR0.set(9, false);
			bitsetRegCCR0.set(10);
			break;
		case 6: // reserved
			bitsetRegCCR0.set(8, false);
			bitsetRegCCR0.set(9);
			bitsetRegCCR0.set(10);
			break;
		case 7: // reserved
			bitsetRegCCR0.set(8);
			bitsetRegCCR0.set(9);
			bitsetRegCCR0.set(10);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}

	/**
	 * SFLAG – :Shared Flag Mode.<br>
	 * This bit enables shared flag transmission and reception in HDLC and X-Sync
	 * protocol modes. If more than one transmit frame start is stored in the
	 * transmit FIFO, the closing flag of the preceding frame becomes the opening
	 * flag of the next frame.
	 * 
	 * @param enable
	 */
	public void setShareFlagMode(boolean enable) {
		if (enable)
			// Default. Do not share flags
			bitsetRegCCR0.set(11);
		else
			// Enable shared flags
			bitsetRegCCR0.set(11, false);
	}

	/**
	 * ITF – Inter-frame Time Fill.<br>
	 * This bit selects the idle state of the transmit data pin. if true, Continuous
	 * logical ‘1’ is sent during idle periods, else, Continuous SYNC sequences are
	 * sent during idle periods
	 * 
	 * @param enable
	 */
	public void setInterFrameTimeFillMode(boolean enable) {
		if (enable)
			// Continuous logical ‘1’ is sent during idle periods
			bitsetRegCCR0.set(12);
		else
			// Continuous SYNC sequences are sent during idle periods
			bitsetRegCCR0.set(12, false);
	}

	/**
	 * NSB[2:0] – Number of Sync Bytes.<br>
	 * This bit field selects the number of sync bytes to use when synchronizing to
	 * data.
	 * <p>
	 * NSB = 000 No synchronization bytes used (e.g. Transparent Mode)<br />
	 * NSB = 001 One byte. Use byte SYNC1 to synchronize (forced when
	 * CCR0:HDLC=1).<br />
	 * NSB = 010 Two bytes. Use bytes SYNC[2:1] to synchronize<br />
	 * NSB = 011 Three bytes. Use bytes SYNC[3:1] to synchronize<br />
	 * NSB = 100 Four bytes. Use bytes SYNC[4:1] to synchronize
	 * </p>
	 * 
	 * @param mode
	 */
	public void setNumberOfSyncBytesMode(int mode) {
		switch (mode) {
		case 0: // No synchronization bytes used (e.g. Transparent Mode)
			bitsetRegCCR0.set(13, false);
			bitsetRegCCR0.set(14, false);
			bitsetRegCCR0.set(15, false);
			break;
		case 1: // One byte. Use byte SYNC1 to synchronize (forced when CCR0:HDLC=1).
			bitsetRegCCR0.set(13);
			bitsetRegCCR0.set(14, false);
			bitsetRegCCR0.set(15, false);
			break;
		case 2: // Two bytes. Use byte s SYNC[2:1] to synchronize
			bitsetRegCCR0.set(13, false);
			bitsetRegCCR0.set(14);
			bitsetRegCCR0.set(15, false);
			break;
		case 3: // Three bytes. Use bytes SYNC[3:1] to synchronize
			bitsetRegCCR0.set(13);
			bitsetRegCCR0.set(14);
			bitsetRegCCR0.set(15, false);
			break;
		case 4: // Four bytes. Use bytes SYNC[4:1] to synchronize
			bitsetRegCCR0.set(13, false);
			bitsetRegCCR0.set(14, false);
			bitsetRegCCR0.set(15);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}

	/**
	 * NTB[2:0] – Number of Termination Bytes.<br>
	 * This bit field selects the number of terminating bytes to use when detecting
	 * frame end.
	 * <p>
	 * NTB = 000 No termination bytes used (e.g. Transparent Mode)<br />
	 * NTB = 001 One byte. Use byte TERM1 to terminate (forced when
	 * CCR0:HDLC=1).<br />
	 * NTB = 010 Two bytes. Use bytes TERM[2:1] to terminate.<br />
	 * NTB = 011 Three bytes. Use bytes TERM[3:1] to terminate.<br />
	 * NTB = 100 Four bytes. Use bytes TERM[4:1] to terminate.
	 * </p>
	 * 
	 * @param mode
	 */

	public void setNumberOfTerminationBytesMode(int mode) {
		switch (mode) {
		case 0: // No termination bytes used (e.g. Transparent Mode)
			bitsetRegCCR0.set(16, false);
			bitsetRegCCR0.set(17, false);
			bitsetRegCCR0.set(18, false);
			break;
		case 1: // One byte. Use byte TERM1 to terminate (forced when CCR0:HDLC=1).
			bitsetRegCCR0.set(16);
			bitsetRegCCR0.set(17, false);
			bitsetRegCCR0.set(18, false);
			break;
		case 2: // Two bytes. Use bytes TERM[2:1] to terminate.
			bitsetRegCCR0.set(16, false);
			bitsetRegCCR0.set(17);
			bitsetRegCCR0.set(18, false);
			break;
		case 3: // Three bytes. Use bytes TERM[3:1] to terminate.
			bitsetRegCCR0.set(16);
			bitsetRegCCR0.set(17);
			bitsetRegCCR0.set(18, false);
			break;
		case 4: // Four bytes. Use bytes TERM[4:1] to terminate.
			bitsetRegCCR0.set(16, false);
			bitsetRegCCR0.set(17, false);
			bitsetRegCCR0.set(18);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}

	/**
	 * VIS – Masked Interrupts Visible.<br>
	 * if <b>true</b>, Masked interrupt status bits are visible and automatically
	 * cleared on interrupt status register (ISR) read accesses. These interrupts
	 * will not generate an interrupt to the interrupt controller, they will only be
	 * visible in ISR. <b>else</b>, Default. Masked interrupt status bits are not
	 * visible on interrupt status register (ISR) read accesses.
	 * 
	 * @param enable
	 */
	public void setMaskedInterruptsVisibleMode(boolean enable) {
		if (enable)
			// Masked interrupt status bits are visible and automatically cleared on
			// interrupt status register (ISR) read accesses. These interrupts will not
			// generate an interrupt to the interrupt controller, they will only be visible
			// in ISR.
			bitsetRegCCR0.set(19);
		else
			// Default. Masked interrupt status bits are not visible on interrupt status
			// register (ISR) read accesses.
			bitsetRegCCR0.set(19, false);
	}

	/**
	 * CRC[1:0] – CRC Frame Check Mode.<br>
	 * This bit field selects the algorithm used in calculating CRC.
	 * <p>
	 * CRC = 00 Use CRC-8 algorithm.<br />
	 * CRC = 01 Use CRC-CCITT algorithm (forced when CCR0:HDLC=1).<br />
	 * CRC = 10 Use CRC-16 algorithm.<br />
	 * CRC = 11 Use CRC-32 algorithm.
	 * </p>
	 * 
	 * @param mode
	 */
	public void setCRCFrameCheckMode(int mode) {
		switch (mode) {
		case 0: // Use CRC-8 algorithm.
			bitsetRegCCR0.set(20, false);
			bitsetRegCCR0.set(21, false);
			break;
		case 1: // Use CRC-CCITT algorithm (forced when CCR0:HDLC=1).
			bitsetRegCCR0.set(20);
			bitsetRegCCR0.set(21, false);
			break;
		case 2: // Use CRC-16 algorithm.
			bitsetRegCCR0.set(20, false);
			bitsetRegCCR0.set(21);
			break;
		case 3: // Use CRC-32 algorithm.
			bitsetRegCCR0.set(20);
			bitsetRegCCR0.set(21);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}

	/**
	 * OBT – Order of Bit Transmission.<br/>
	 * Specifies whether data is considered least significant bit (LSB) first or
	 * most significant bit (MSB) first.<br/>
	 * if TRUE, enable MSB First, else, enable LSB First.
	 * 
	 * @param enable
	 */
	public void setOrderOfBitTransmissionMode(boolean enable) {
		if (enable)
			// MSB First
			bitsetRegCCR0.set(22);
		else
			// LSB First
			bitsetRegCCR0.set(22, false);
	}

	/**
	 * ADM[1:0] – Address Mode (HDLC only).<br/>
	 * This bit field selects the number of bytes used in receive address
	 * comparison.*
	 * <p>
	 * ADM = 00 No address checking.<br />
	 * ADM = 01 1 byte address checking.<br />
	 * ADM = 10 2 byte address checking.<br />
	 * ADM = 11 Reserved
	 * </p>
	 * 
	 * @param mode
	 */
	public void setAddressMode(int mode) {
		switch (mode) {
		case 0: // No address checking.
			bitsetRegCCR0.set(23, false);
			bitsetRegCCR0.set(24, false);
			break;
		case 1: // 1 byte address checking.
			bitsetRegCCR0.set(23);
			bitsetRegCCR0.set(24, false);
			break;
		case 2: // 2 byte address checking.
			bitsetRegCCR0.set(23, false);
			bitsetRegCCR0.set(24);
			break;
		case 3: // Reserved
			bitsetRegCCR0.set(23);
			bitsetRegCCR0.set(24);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}

	/**
	 * RECD – Receiver Disable.<br/>
	 * When set, this bit disables the receiver. This can be useful, for example,
	 * when in Transparent mode and transmitting only. You would not want to receive
	 * an endless stream of idles which do nothing but generate unnecessary
	 * interrupts. if <b>true</b>, Receiver enabled, <b>else</b>Receiver disabled.
	 * 
	 * @param enable
	 */
	public void setReceiverDisableMode(boolean enable) {
		if (enable)
			// Receiver enabled.
			bitsetRegCCR0.set(25);
		else
			// Receiver disabled.
			bitsetRegCCR0.set(25, false);
	}

	/**
	 * EXTS[1:0] – External Signal Select.<br/>
	 * These bits select the external input source pin for the TXEXT command.
	 * <p>
	 * EXTS = 00 CTS selected<br />
	 * EXTS = 01 DSR selected<br />
	 * EXTS = 10 RI selected<br />
	 * EXTS = 11 CD selected
	 * </p>
	 * 
	 * @param mode
	 */
	public void setExternalSignalSelectMode(int mode) {
		switch (mode) {
		case 0: // No address checking.
			bitsetRegCCR0.set(23, false);
			bitsetRegCCR0.set(24, false);
			break;
		case 1: // 1 byte address checking.
			bitsetRegCCR0.set(23);
			bitsetRegCCR0.set(24, false);
			break;
		case 2: // 2 byte address checking.
			bitsetRegCCR0.set(23, false);
			bitsetRegCCR0.set(24);
			break;
		case 3: // Reserved
			bitsetRegCCR0.set(23);
			bitsetRegCCR0.set(24);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + mode);
		}
	}

}
