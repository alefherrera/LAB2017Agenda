package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.ConfigService;
import util.Configuration;

public class Conexion {
	public static Conexion instancia;
	private final static String driver = "com.mysql.jdbc.Driver";
	private Connection conexion;

	private Conexion() {
		conexion = openConnection();
	}

	public static Connection openConnection() {
		return openConnection(null);
	}

	private static Connection openConnection(Configuration configParam) {
		try {
			Configuration config = configParam == null ? ConfigService.GetConfiguration() : configParam;
			String user = null, pass = null, ip = null, port = null, db = null;
			if (config != null) {
				user = config.getUser();
				pass = config.getPass();
				ip = config.getIp();
				port = config.getPort();
				db = config.getDatabase();
			}

			Class.forName(driver).newInstance();
			Connection result = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", ip, port, db), user,
					pass);
			System.out.println("Conexion exitosa");
			return result;
		} catch (Exception e) {
			System.out.println("Conexion fallida");
			return null;
		}
	}

	public static boolean connectionTest(Configuration config) {
		Connection conn = openConnection(config);
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Error cerrando la conexion de prueba.");
			}
			conn = null;
			return true;
		} else {
			return false;
		}
	}

	public static void refreshConnection(){
		instancia = new Conexion();
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
