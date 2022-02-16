package main.java.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.util.Pair;
import main.java.synccom.SYNCCOM_Loader;

public class JSONFXLoader {
	Map<?, ?> reg;
	Map<String, Object> history;
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
			reg = (Map<?, ?>) map.get("REGISTERS");
			history = mapper.readValue(new File(getFolderPath("/config/history.json")), Map.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected Map<?, ?> getRegisters() {
		return this.reg;
	}

	public Register getRegister(AVAILABLE_REGS reg) {
		return new RegisterImpl(reg);
	}

	public Map<String, Object> getHistory() {
		return history;
	}

	public String getFolderPath(String relativeDllPath) {
		String path = "";
		try {
			URL resource = SYNCCOM_Loader.class.getResource(relativeDllPath);

			path = resource.toURI().getPath();
//			path = path != null ? path : "";

			if (resource != null && path != null && path.lastIndexOf("!") == -1)
				log.info("encontrado en el classpath, resource devuelve: " + resource.getPath() + ", y path: " + path
						+ " terminado");
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

	protected class RegisterImpl implements Register {
		Map<?, ?> register;

		public RegisterImpl(AVAILABLE_REGS reg) {
			this.register = (Map<?, ?>) getRegisters().get(reg.toString());
		}

		private Map<Object, Object> getControlHasMap(String id_) {
			return (Map<Object, Object>) this.register.get(id_);
		}

		@Override
		public ArrayList<String> getOptionAsArray(String id_) {
			Map<Object, Object> options = getOptionHasMap(id_);
			return new ArrayList<String>(Arrays.asList(options.keySet().toArray(new String[options.size()])));
		}
		
		@Override
		public Map<Object, Object> getDefaultState() {
			return getControlHasMap(SYNCCOMKeys.DEFAULT_BITS);
		} 

		@Override
		public Map<Object, Object> getOptionHasMap(String id_) {
			Map<Object, Object> control = getControlHasMap(id_);
			return (Map<Object, Object>) control.get(OPTIONS);
		}

		@Override
		public Map<Object, Object> getTipHasMap(String id_) {
			Map<Object, Object> control = getControlHasMap(id_);
			return (Map<Object, Object>) control.get(TIPS);
		}

		@Override
		public String getTipByID(String id_, String tip_) {
			Map<Object, Object> tips = getTipHasMap(id_);
			if (tips != null) {
				String tip = (String) tips.get(tip_);
				return tip != null ? tip : "";
			} else
				return "";
		}

		@Override
		public boolean isAvailableID(String id_) {
			ArrayList<String> id_list = (ArrayList<String>) this.register.get(SYNCCOMKeys.ID_CONTROL_LIST);
			for (String id : id_list) {
				if (id.equals(id_)) {
					return true;
				}
			}
			return false;
		}
	}
}
