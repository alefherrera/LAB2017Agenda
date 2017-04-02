package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;

public class TipoContactoDAO extends BaseDAO {
	private static final String insert = "INSERT INTO tiposContacto VALUES (?, ?)";
	private static final String delete = "DELETE FROM tiposContacto WHERE id = ?";
	private static final String update = "UPDATE tiposContacto SET Descripcion = ? WHERE id = ?";
	private static final String readall = "SELECT * FROM tiposContacto";
	private static final String readById = "SELECT * FROM tiposContacto WHERE id = ?";
	
	public TipoContactoDTO get(int id) {
		TipoContactoDTO localidad = null;
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(readById);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				localidad = new TipoContactoDTO(result.getInt("id"), result.getString("descripcion"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			getConexion().cerrarConexion();
		}

		return localidad;
	}

	public boolean insert(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(insert);
			statement.setInt(1, tipoContacto.getId());
			statement.setString(2, tipoContacto.getDescripcion());
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			getConexion().cerrarConexion();
		}
		return false;
	}

	public boolean delete(TipoContactoDTO tipoContacto) throws SQLException {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(tipoContacto.getId()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally
		{
			getConexion().cerrarConexion();
		}
		return false;
	}
	
	public boolean update(TipoContactoDTO tipoContacto) {
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(update);
			statement.setString(1, tipoContacto.getDescripcion());
			statement.setInt(2, tipoContacto.getId());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			getConexion().cerrarConexion();
		}
		return false;
	}
	
	public List<TipoContactoDTO> readAll() {
		List<TipoContactoDTO> ret = new ArrayList<TipoContactoDTO>();
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(readall);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ret.add(new TipoContactoDTO(result.getInt("id"), result.getString("descripcion")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			getConexion().cerrarConexion();
		}

		return ret;
	}
}
