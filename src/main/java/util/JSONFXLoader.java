package main.java.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.synccom.SYNCCOM_Loader;

public class JSONFXLoader {
	Map<?, ?> registers;
	Map<?, ?> history;
	ObjectMapper mapper = new ObjectMapper();

	private static class InstanceClass {
		static final JSONFXLoader instance = new JSONFXLoader();
	}

	public static JSONFXLoader getInstance() {
		return InstanceClass.instance;
	}

	private JSONFXLoader() {
		try {
			// create object mapper instance
			mapper = new ObjectMapper();
			// convert JSON file to map
			Map<?, ?> map = mapper.readValue(getClass().getResource("/config/config.json"), Map.class);
			registers = (Map<?, ?>) map.get("REGISTERS");
			history = mapper.readValue(getClass().getResource("/config/history.json"), Map.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Map<?, ?> getRegisters() {
		return registers;
	}

	public Map<?, ?> getHistory() {
		return history;
	}

	public static String getFolderPath() {
		String pathName = null;
		try {
			pathName = JSONFXLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pathName.substring(0, pathName.lastIndexOf("/"));
	}

	public void setHistory(Map<String, Object> map) {
		try {
			//FIXME Arreglar poder escribir json dentro del jar, solo eso falta 
			mapper.writeValue(new File("/config/history.json"), map);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
