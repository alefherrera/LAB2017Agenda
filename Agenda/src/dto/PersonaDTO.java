package dto;

import java.sql.Date;

public class PersonaDTO
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String email;
	private Date fechaNac;
	private String calle;
	private int altura;
	private int piso;
	private int depto;
	private LocalidadDTO localidad;
	private TipoContactoDTO tipoContacto;

	public PersonaDTO(int idPersona, String nombre, String telefono, String email, Date fechaNac, String calle,
			int altura, int piso, int depto, LocalidadDTO localidad, TipoContactoDTO tipoContacto) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.fechaNac = fechaNac;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.localidad = localidad;
		this.tipoContacto = tipoContacto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPersona;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonaDTO other = (PersonaDTO) obj;
		if (idPersona != other.idPersona)
			return false;
		return true;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public int getDepto() {
		return depto;
	}

	public void setDepto(int depto) {
		this.depto = depto;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public TipoContactoDTO getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(TipoContactoDTO tipoContacto) {
		this.tipoContacto = tipoContacto;
	}
	
	public String getLocalidadDescripcion(){
		return this.localidad.getDescripcion();
	}
	
	public String getServidorMail() {
		return "gmail";
	}
	
	
	public enum AtributoPersona
	{
		LOCALIDAD,
		TIPOCONTACTO
	}
	
	
}
