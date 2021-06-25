package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import dll.SYNCCOM_Loader;

public class JSONFXLoader {
	Map<?, ?> registers;

	private static class InstanceClass {
		static final JSONFXLoader instance = new JSONFXLoader();
	}

	public static JSONFXLoader getInstance() {
		return InstanceClass.instance;
	}

	private JSONFXLoader() {
		try {
			// create object mapper instance
			ObjectMapper mapper = new ObjectMapper();
			// convert JSON file to map
			Map<?, ?> map = mapper
					.readValue(new File(SYNCCOM_Loader.class.getResource("/config/config.json").getFile()), Map.class);
			registers = (Map<?, ?>) map.get("REGISTERS");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Map<?, ?> getRegisters() {
		return registers;
	}

}
