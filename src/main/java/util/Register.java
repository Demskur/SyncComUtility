package main.java.util;

import java.util.ArrayList;
import java.util.Map;

public interface Register {
	public ArrayList<String> getOptionAsArray(String control_);

	public Map<Object, Object> getOptionHasMap(String control_);

	public Map<Object, Object> getTipHasMap(String control_);

	public String getTipByID(String option, String tip);

	public Map<Object, Object> getDefaultState();

	public boolean isAvailableID(String option);

	public static String ID = "ID";
	public static String OPTIONS = "OPTIONS";
	public static String TIPS = "TIPS";
}
