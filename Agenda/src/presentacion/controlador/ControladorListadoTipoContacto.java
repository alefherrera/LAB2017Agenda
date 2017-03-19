package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dto.LocalidadDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.vista.VentanaABM;
import presentacion.vista.VentanaListado;

public class ControladorListadoTipoContacto implements ActionListener {
	private VentanaListado vista;
	private List<TipoContactoDTO> tiposContacto;
	private Agenda agenda;

	public ControladorListadoTipoContacto(VentanaListado vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.agenda = agenda;
		this.tiposContacto = null;
		inicializar();
	}

	public void inicializar() {
		this.llenarTabla();
	}

	public void llenarTabla() {
		this.vista.getTableSource().setRowCount(0); // Para vaciar la tabla
		this.vista.getTableSource().setColumnCount(0);
		this.vista.getTableSource().setColumnIdentifiers(this.vista.getNombreColumnas());

		this.tiposContacto = agenda.obtenerTiposDeContacto();
		for (int i = 0; i < this.tiposContacto.size(); i++) {
			Object[] fila = { this.tiposContacto.get(i).getDescripcion() };
			this.vista.getTableSource().addRow(fila);
		}
		this.vista.show();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBtnAgregar()) {
			
			VentanaABM ventanaPer = new VentanaABM();
			new ControladorTipoContacto(ventanaPer, this, agenda, null);
			
		} else if (e.getSource() == this.vista.getBtnBorrar()) {
			int[] filas_seleccionadas = this.vista.getTablaEntidad().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				this.agenda.eliminarTipoContacto(this.tiposContacto.get(fila));
			}

			this.llenarTabla();
			
		} else if (e.getSource() == this.vista.getBtnEditar()) {
			int position = this.vista.getTablaEntidad().getSelectedRows()[0];
			TipoContactoDTO selectedPerson = this.tiposContacto.get(position);
			
			VentanaABM ventanaPer = new VentanaABM();
			new ControladorTipoContacto(ventanaPer, this, agenda, selectedPerson);
		}
	}

}
