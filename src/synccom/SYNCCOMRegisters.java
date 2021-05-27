package synccom;

import java.util.ArrayList;
import java.util.List;

public class SYNCCOMRegisters {
	public long[] __reserved1 = { -1, -1 };	
	public long FIFOT = -1;
	public long[] __reserved2 = { -1, -1 };
	public long CMDR = -1;
	public long STAR = -1; /* Read-only */
	public long CCR0 = -1;
	public long CCR1 = -1;
	public long CCR2 = -1;
	public long BGR = -1;
	public long SSR = -1;
	public long SMR = -1;
	public long TSR = -1;
	public long TMR = -1;
	public long RAR = -1;
	public long RAMR = -1;
	public long PPR = -1;
	public long TCR = -1;
	public long VSTR = -1; /* Read-only */
	public long[] __reserved4 = { -1 };
	public long IMR = -1;
	public long DPLLR = -1;
	public long FCR = -1;

	public long[] get__reserved1() {
		return __reserved1;
	}

	public void set__reserved1(long[] c__reserved1) {
		__reserved1 = c__reserved1;
	}

	public long getFIFOT() {
		return FIFOT;
	}

	public void setFIFOT(long fIFOT) {
		FIFOT = fIFOT;
	}

	public long[] get__reserved2() {
		return __reserved2;
	}

	public void set__reserved2(long[] c__reserved2) {
		__reserved2 = c__reserved2;
	}

	public long getCMDR() {
		return CMDR;
	}

	public void setCMDR(long cMDR) {
		CMDR = cMDR;
	}

	public long getSTAR() {
		return STAR;
	}

	public void setSTAR(long sTAR) {
		STAR = sTAR;
	}

	public long getCCR0() {
		return CCR0;
	}

	public void setCCR0(long cCR0) {
		CCR0 = cCR0;
	}

	public long getCCR1() {
		return CCR1;
	}

	public void setCCR1(long cCR1) {
		CCR1 = cCR1;
	}

	public long getCCR2() {
		return CCR2;
	}

	public void setCCR2(long cCR2) {
		CCR2 = cCR2;
	}

	public long getBGR() {
		return BGR;
	}

	public void setBGR(long bGR) {
		BGR = bGR;
	}

	public long getSSR() {
		return SSR;
	}

	public void setSSR(long sSR) {
		SSR = sSR;
	}

	public long getSMR() {
		return SMR;
	}

	public void setSMR(long sMR) {
		SMR = sMR;
	}

	public long getTSR() {
		return TSR;
	}

	public void setTSR(long tSR) {
		TSR = tSR;
	}

	public long getTMR() {
		return TMR;
	}

	public void setTMR(long tMR) {
		TMR = tMR;
	}

	public long getRAR() {
		return RAR;
	}

	public void setRAR(long rAR) {
		RAR = rAR;
	}

	public long getRAMR() {
		return RAMR;
	}

	public void setRAMR(long rAMR) {
		RAMR = rAMR;
	}

	public long getPPR() {
		return PPR;
	}

	public void setPPR(long pPR) {
		PPR = pPR;
	}

	public long getTCR() {
		return TCR;
	}

	public void setTCR(long tCR) {
		TCR = tCR;
	}

	public long getVSTR() {
		return VSTR;
	}

	public void setVSTR(long vSTR) {
		VSTR = vSTR;
	}

	public long[] get__reserved4() {
		return __reserved4;
	}

	public void set__reserved4(long[] c__reserved4) {
		__reserved4 = c__reserved4;
	}

	public long getIMR() {
		return IMR;
	}

	public void setIMR(long iMR) {
		IMR = iMR;
	}

	public long getDPLLR() {
		return DPLLR;
	}

	public void setDPLLR(long dPLLR) {
		DPLLR = dPLLR;
	}

	public long getFCR() {
		return FCR;
	}

	public void setFCR(long fCR) {
		FCR = fCR;
	}

	public List<Object> getRegisters() {
		return new ArrayList<Object>() {
			/**
			 * 
			 */
			private final long serialVersionUID = 1L;
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
