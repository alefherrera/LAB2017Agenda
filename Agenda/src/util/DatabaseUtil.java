package util;

import persistencia.conexion.Conexion;

public class DatabaseUtil {
	
	public static boolean testConnection(Configuration config)
	{
		return Conexion.connectionTest(config);
	}
	
}
