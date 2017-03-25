package presentacion.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.LocalidadDTO;
import modelo.Agenda;
import presentacion.vista.VentanaABM;
import presentacion.vista.VentanaListado;

public class ControladorListadoLocalidad implements ActionListener {
	private VentanaListado vista;
	private List<LocalidadDTO> localidades;
	private Agenda agenda;

	public ControladorListadoLocalidad(VentanaListado vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.agenda = agenda;
		this.localidades = null;
		inicializar();
	}

	public void inicializar() {
		this.llenarTabla();
	}

	public void llenarTabla() {
		this.vista.getTableSource().setRowCount(0); // Para vaciar la tabla
		this.vista.getTableSource().setColumnCount(0);
		this.vista.getTableSource().setColumnIdentifiers(this.vista.getNombreColumnas());

		this.localidades = agenda.obtenerLocalides();
		for (int i = 0; i < this.localidades.size(); i++) {
			Object[] fila = { this.localidades.get(i).getDescripcion() };
			this.vista.getTableSource().addRow(fila);
		}
		this.vista.show();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.vista.getBtnAgregar()) {

			VentanaABM ventanaPer = new VentanaABM();
			new ControladorLocalidad(ventanaPer, this, agenda, null);

		} else if (e.getSource() == this.vista.getBtnBorrar()) {
			int[] filas_seleccionadas = this.vista.getTablaEntidad().getSelectedRows();
			for (int fila : filas_seleccionadas) {
				try {
					this.agenda.eliminarLocalidad(this.localidades.get(fila));
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(vista.frame,
							"No se puede borrar esta localidad. Alguna persona vive en esta localidad.",
							"Error borrando.", 1);
				}

			}

			this.llenarTabla();

		} else if (e.getSource() == this.vista.getBtnEditar()) {
			int position = this.vista.getTablaEntidad().getSelectedRows()[0];
			LocalidadDTO selectedLoc = this.localidades.get(position);

			VentanaABM ventanaPer = new VentanaABM();
			new ControladorLocalidad(ventanaPer, this, agenda, selectedLoc);
		}
	}

}
