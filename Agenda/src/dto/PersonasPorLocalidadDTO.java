package dto;

public class PersonasPorLocalidadDTO {
	
	private int cantidad;
	private String localidad;
	
	public PersonasPorLocalidadDTO(int cantidad, String localidad) {
		super();
		this.cantidad = cantidad;
		this.localidad = localidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
	
	
}
