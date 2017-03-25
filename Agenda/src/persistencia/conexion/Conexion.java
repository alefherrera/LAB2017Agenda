package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

import util.ConfigService;
import util.Configuration;

public class Conexion {
	public static Conexion instancia;
	private final static String driver = "com.mysql.jdbc.Driver";
	private Connection conexion;

	private Conexion() {
		try {
			Configuration config = ConfigService.GetConfiguration();
			String user = null, pass = null, ip = null, port = null;
			if (config != null) {
				user = config.getUser();
				pass = config.getPass();
				ip = config.getIp();
				port = config.getPort();
			}
			
			Class.forName(driver).newInstance();
			conexion = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/agenda", ip, port), user, pass);
			System.out.println("Conexion exitosa");
		} catch (Exception e) {
			System.out.println("Conexion fallida");
		}
	}

	public static boolean connectionTest(Configuration config)
	{
		Connection conex = null;
		try {
			String user = null, pass = null, ip = null, port = null;
			if (config != null) {
				user = config.getUser();
				pass = config.getPass();
				ip = config.getIp();
				port = config.getPort();
			}
			
			Class.forName(driver).newInstance();
			conex = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/agenda", ip, port), user, pass);
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (conex != null) {
				conex = null;
			}
		}
	}
	
	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return conexion;
	}

	public void cerrarConexion() {
		instancia = null;
	}
}
