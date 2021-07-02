package main.java.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
			Map<?, ?> map = mapper.readValue(new File("src/main/resources/config/config.json"), Map.class);
			registers = (Map<?, ?>) map.get("REGISTERS");
			history = mapper.readValue(new File("src/main/resources/config/history.json"), Map.class);
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

	public void setHistory(Map<String, Object> map) {
		try {
			mapper.writeValue(new File("src/main/resources/config/history.json"), map);
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
