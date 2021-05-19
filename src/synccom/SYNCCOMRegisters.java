package synccom;

import java.util.ArrayList;
import java.util.List;

public class SYNCCOMRegisters {
	static long[] __reserved1 = { -1, -1 };
	static long FIFOT = -1;
	static long[] __reserved2 = { -1, -1 };
	static long CMDR = -1;
	static long STAR = -1; /* Read-only */
	static long CCR0 = -1;
	static long CCR1 = -1;
	static long CCR2 = -1;
	static long BGR = -1;
	static long SSR = -1;
	static long SMR = -1;
	static long TSR = -1;
	static long TMR = -1;
	static long RAR = -1;
	static long RAMR = -1;
	static long PPR = -1;
	static long TCR = -1;
	static long VSTR = -1; /* Read-only */
	static long[] __reserved4 = { -1 };
	static long IMR = -1;
	static long DPLLR = -1;
	static long FCR = -1;

	public static long[] get__reserved1() {
		return __reserved1;
	}

	public static void set__reserved1(long[] c__reserved1) {
		__reserved1 = c__reserved1;
	}

	public static long getFIFOT() {
		return FIFOT;
	}

	public static void setFIFOT(long fIFOT) {
		FIFOT = fIFOT;
	}

	public static long[] get__reserved2() {
		return __reserved2;
	}

	public static void set__reserved2(long[] c__reserved2) {
		__reserved2 = c__reserved2;
	}

	public static long getCMDR() {
		return CMDR;
	}

	public static void setCMDR(long cMDR) {
		CMDR = cMDR;
	}

	public static long getSTAR() {
		return STAR;
	}

	public static void setSTAR(long sTAR) {
		STAR = sTAR;
	}

	public static long getCCR0() {
		return CCR0;
	}

	public static void setCCR0(long cCR0) {
		CCR0 = cCR0;
	}

	public static long getCCR1() {
		return CCR1;
	}

	public static void setCCR1(long cCR1) {
		CCR1 = cCR1;
	}

	public static long getCCR2() {
		return CCR2;
	}

	public static void setCCR2(long cCR2) {
		CCR2 = cCR2;
	}

	public static long getBGR() {
		return BGR;
	}

	public static void setBGR(long bGR) {
		BGR = bGR;
	}

	public static long getSSR() {
		return SSR;
	}

	public static void setSSR(long sSR) {
		SSR = sSR;
	}

	public static long getSMR() {
		return SMR;
	}

	public static void setSMR(long sMR) {
		SMR = sMR;
	}

	public static long getTSR() {
		return TSR;
	}

	public static void setTSR(long tSR) {
		TSR = tSR;
	}

	public static long getTMR() {
		return TMR;
	}

	public static void setTMR(long tMR) {
		TMR = tMR;
	}

	public static long getRAR() {
		return RAR;
	}

	public static void setRAR(long rAR) {
		RAR = rAR;
	}

	public static long getRAMR() {
		return RAMR;
	}

	public static void setRAMR(long rAMR) {
		RAMR = rAMR;
	}

	public static long getPPR() {
		return PPR;
	}

	public static void setPPR(long pPR) {
		PPR = pPR;
	}

	public static long getTCR() {
		return TCR;
	}

	public static void setTCR(long tCR) {
		TCR = tCR;
	}

	public static long getVSTR() {
		return VSTR;
	}

	public static void setVSTR(long vSTR) {
		VSTR = vSTR;
	}

	public static long[] get__reserved4() {
		return __reserved4;
	}

	public static void set__reserved4(long[] c__reserved4) {
		__reserved4 = c__reserved4;
	}

	public static long getIMR() {
		return IMR;
	}

	public static void setIMR(long iMR) {
		IMR = iMR;
	}

	public static long getDPLLR() {
		return DPLLR;
	}

	public static void setDPLLR(long dPLLR) {
		DPLLR = dPLLR;
	}

	public static long getFCR() {
		return FCR;
	}

	public static void setFCR(long fCR) {
		FCR = fCR;
	}

	public static List<Object> getRegisters() {
		return new ArrayList<Object>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{
				add(__reserved1);
				add(FIFOT);
				add(__reserved2);
				add(CMDR);
				add(STAR);
				add(CCR0);
				add(CCR1);
				add(CCR2);
				add(BGR);
				add(SSR);
				add(SMR);
				add(TSR);
				add(TMR);
				add(RAR);
				add(RAMR);
				add(PPR);
				add(TCR);
				add(VSTR);
				add(__reserved4);
				add(IMR);
				add(DPLLR);
				add(FCR);
			}
		};
	}
}
