package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaListado;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.PersonaDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnLocalidades().addActionListener(this);
		this.vista.getBtnTiposContacto().addActionListener(this);
		this.agenda = agenda;
		this.personas_en_tabla = null;
	}

	public void inicializar() {
		this.llenarTabla();
	}

	public void llenarTabla() {
		this.vista.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.vista.getModelPersonas().setColumnCount(0);
		this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());

		this.personas_en_tabla = agenda.obtenerPersonas();
		for (int i = 0; i < this.personas_en_tabla.size(); i++) {
			Object[] fila = { this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono(),
					this.personas_en_tabla.get(i).getLocalidad().getDescripcion(),this.personas_en_tabla.get(i).getTipoContacto().getDescripcion()};
			this.vista.getModelPersonas().addRow(fila);
		}
		this.vista.show();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBtnAgregar()) {
			VentanaPersona ventanaPer = new VentanaPersona();
			ControladorPersona ctrlPersona = new ControladorPersona(ventanaPer, agenda, null, this);
		} else if (e.getSource() == this.vista.getBtnBorrar()) {
			int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
			}

			this.llenarTabla();

		} else if (e.getSource() == this.vista.getBtnEditar()) {
			int position = this.vista.getTablaPersonas().getSelectedRows()[0];
			PersonaDTO selectedPerson = this.personas_en_tabla.get(position);

			VentanaPersona ventanaPer = new VentanaPersona();
			ControladorPersona ctrlPersona = new ControladorPersona(ventanaPer, agenda, selectedPerson, this);
		} else if (e.getSource() == this.vista.getBtnReporte()) {
			ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
			reporte.mostrar();
		} else if (e.getSource() == this.vista.getBtnLocalidades()) {
			VentanaListado ventanaListado = new VentanaListado();
			new ControladorListadoLocalidad(ventanaListado, agenda);
			
		} else if (e.getSource() == this.vista.getBtnTiposContacto()) {
			VentanaListado ventanaListado = new VentanaListado();
			new ControladorListadoTipoContacto(ventanaListado, agenda);
			
		}
	}

}
