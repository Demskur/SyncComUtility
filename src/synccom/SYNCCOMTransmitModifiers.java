package synccom;

public enum SYNCCOMTransmitModifiers {
	XF(0), XREP(1), TXT(2), TXEXT(4);

	private Integer value;

	SYNCCOMTransmitModifiers(Integer value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}
