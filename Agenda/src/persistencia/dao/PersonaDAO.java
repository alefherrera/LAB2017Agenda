package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PersonaDTO;
import dto.PersonaDTO.AtributoPersona;
import persistencia.conexion.Conexion;

public class PersonaDAO extends BaseDAO{
	private static final String insert = "INSERT INTO personas(nombre, telefono, email, fechaNac, calle, altura, piso, depto, localidad, tipoContacto) VALUES(?, ?, ? ,?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String update = "UPDATE personas SET nombre = ?, telefono = ?, email = ?, fechaNac = ?, calle = ?, altura = ?, piso = ?, depto = ?, localidad = ?, tipoContacto = ? WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";

	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(insert);
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

	public boolean update(PersonaDTO persona) {
		PreparedStatement statement;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(update);
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

	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = getConexion().getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally
		{
			getConexion().cerrarConexion();
		}
		return false;
	}

	public List<PersonaDTO> readAll()
	{
		return readInner(readall);
	}

	public List<PersonaDTO> readAllOrdered(AtributoPersona atributo) {
		String query = readall;
		
		switch (atributo) {
		case LOCALIDAD:
			query = query + " ORDER BY localidad";
			break;
		case TIPOCONTACTO:
			query = query + " ORDER BY tipoContacto";
			break;
		default:
			break;
		}
		
		return readInner(query);
	}
	
	private List<PersonaDTO> readInner(String query)
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try
		{
			statement = getConexion().getSQLConexion().prepareStatement(query);
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
		finally
		{
			getConexion().cerrarConexion();
		}
		return personas;
	}
	
}
