package main.java.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.synccom.SYNCCOM_Loader;

public class JSONFXLoader {
	Map<?, ?> registers;
	Map<?, ?> history;
	Logger log = Logger.getLogger(this.getClass().getName());
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
			Map<?, ?> map = mapper.readValue(new File(getFolderPath("/config/config.json")), Map.class);
			registers = (Map<?, ?>) map.get("REGISTERS");
			history = mapper.readValue(new File(getFolderPath("/config/history.json")), Map.class);
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

	public String getFolderPath(String relativeDllPath) {
		String path = "";
		try {
			URL resource = SYNCCOM_Loader.class.getResource(relativeDllPath);

			path = resource.toURI().getPath();
//			path = path != null ? path : "";
			
			if (resource != null && path !=null && path.lastIndexOf("!") == -1)
				log.info("encontrado en el classpath, resource devuelve: " + resource.getPath() + ", y path: " + path + " terminado");
			else {
				String pathName = SYNCCOM_Loader.class.getProtectionDomain().getCodeSource().getLocation().toURI()
						.getPath();
				pathName = pathName != null ? pathName : "";
				path = pathName.substring(0, pathName.lastIndexOf("/")) + relativeDllPath;
				log.info("encontrado en la carpeta root, resource devuelve: " + resource.getPath() + ", y path: "
						+ path);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	public void setHistory(Map<String, Object> map) {
		try {
			mapper.writeValue(new File(getFolderPath("/config/history.json")), map);
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
