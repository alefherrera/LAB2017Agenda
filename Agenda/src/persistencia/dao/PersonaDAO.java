package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class PersonaDAO {
	private static final String insert = "INSERT INTO personas(nombre, telefono, email, fechaNac, calle, altura, piso, depto, localidad, tipoContacto) VALUES(?, ?, ? ,?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String update = "UPDATE personas SET nombre = ?, telefono = ?, email = ?, fechaNac = ?, calle = ?, altura = ?, piso = ?, depto = ?, localidad = ?, tipoContacto = ? WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final Conexion conexion = Conexion.getConexion();

	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			//statement.setInt(1, persona.getIdPersona());
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getEmail());
			statement.setDate(4, persona.getFechaNac());
			statement.setString(5, persona.getCalle());
			statement.setInt(6, persona.getAltura());
			statement.setInt(7, persona.getPiso());
			statement.setInt(8, persona.getDepto());
			statement.setInt(9, persona.getLocalidad().getId());
			statement.setInt(10, persona.getTipoContacto().getId());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public boolean update(PersonaDTO persona) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getEmail());
			statement.setDate(4, persona.getFechaNac());
			statement.setString(5, persona.getCalle());
			statement.setInt(6, persona.getAltura());
			statement.setInt(7, persona.getPiso());
			statement.setInt(8, persona.getDepto());
			statement.setInt(9, persona.getLocalidad().getId());
			statement.setInt(10, persona.getTipoContacto().getId());
			statement.setInt(11, persona.getIdPersona());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecut� devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				personas.add(
						new PersonaDTO(
								resultSet.getInt("idPersona"),
								resultSet.getString("Nombre"),
								resultSet.getString("Telefono"),
								resultSet.getString("email"),
								resultSet.getDate("fechaNac"),
								resultSet.getString("calle"),
								resultSet.getInt("altura"),
								resultSet.getInt("piso"),
								resultSet.getInt("depto"),
								new LocalidadDAO().get(resultSet.getInt("localidad")),
								new TipoContactoDAO().get(resultSet.getInt("tipoContacto"))
								));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return personas;
	}
}
