package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import persistencia.conexion.Conexion;

public class LocalidadDAO extends BaseDAO {
	private static final String insert = "INSERT INTO localidades VALUES (?, ?)";
	private static final String delete = "DELETE FROM localidades WHERE id = ?";
	private static final String update = "UPDATE localidades SET Descripcion = ? WHERE id = ?";
	private static final String readall = "SELECT * FROM localidades";
	private static final String readById = "SELECT * FROM localidades WHERE id = ?";

	public LocalidadDTO get(int id) {
		LocalidadDTO localidad = null;
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(readById);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()){
				localidad = new LocalidadDTO(result.getInt("id"), result.getString("descripcion"));
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

	public boolean insert(LocalidadDTO localidad) {
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(insert);
			statement.setInt(1, localidad.getId());
			statement.setString(2, localidad.getDescripcion());
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

	public boolean delete(LocalidadDTO localidad) throws SQLException {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(localidad.getId()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				return true;
		} catch (SQLException e) {
			throw e;
		} finally
		{
			getConexion().cerrarConexion();
		}
		return false;
	}

	public boolean update(LocalidadDTO localidad) {
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(update);
			statement.setString(1, localidad.getDescripcion());
			statement.setInt(2, localidad.getId());
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
	
	public List<LocalidadDTO> readAll() {
		List<LocalidadDTO> ret = new ArrayList<LocalidadDTO>();
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(readall);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ret.add(new LocalidadDTO(result.getInt("id"), result.getString("descripcion")));
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
