package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public class ConfigService {

	private static final String CONFIG_FILEPATH = System.getProperty("user.home") + File.separatorChar + "AgendaApp" + File.separatorChar;
	private static final String CONFIG_FULLPATH = CONFIG_FILEPATH + "config.xml";
	
	private static void ensureFolder()
	{
		File file = new File(CONFIG_FILEPATH);
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	
	public static Configuration GetConfiguration() {
		
		ensureFolder();
		
		File configFile = new File(CONFIG_FULLPATH);
		if (configFile.isFile()) {
			try {
				return PersistenciaService.getInstance().get(CONFIG_FULLPATH);
			} catch (FileNotFoundException e) {
				System.out.println("WARNING: No se pudo leer el archivo de configuracion.");
				return null;
			}
		}
		return null;
	}

	public static void SaveConfiguration(Configuration config_to_save) {
		if (config_to_save == null)
			return;
		
		try {
			PersistenciaService.getInstance().put(config_to_save, CONFIG_FULLPATH);
		} catch (FileNotFoundException e) {
			System.out.println("WARNING: No se pudo guardar el archivo de configuracion.");
		}
	}

}
