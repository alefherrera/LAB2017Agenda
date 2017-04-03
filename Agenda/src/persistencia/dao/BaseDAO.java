package persistencia.dao;

import persistencia.conexion.Conexion;

public class BaseDAO {

	public Conexion getConexion() {
		return Conexion.getConexion();
	}

}
