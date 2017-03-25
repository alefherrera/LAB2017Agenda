package persistencia.dao;

import persistencia.conexion.Conexion;

public class BaseDAO {

	private Conexion conexion;

	public Conexion getConexion() {
		if (conexion == null)
			conexion = Conexion.getConexion();
		return conexion;
	}

}
