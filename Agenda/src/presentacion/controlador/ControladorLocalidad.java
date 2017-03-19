package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dto.LocalidadDTO;
import modelo.Agenda;
import presentacion.vista.VentanaABM;
import presentacion.vista.VentanaListado;

public class ControladorLocalidad implements ActionListener {

	private VentanaABM vista;
	private ControladorListadoLocalidad controladorListado;
	private Agenda agenda;
	private LocalidadDTO currentLocalidad;

	public ControladorLocalidad(VentanaABM vista, ControladorListadoLocalidad controladorListado, Agenda agenda,
			LocalidadDTO currentLocalidad) {
		// TODO Auto-generated constructor stub
		this.vista = vista;
		this.agenda = agenda;
		this.controladorListado = controladorListado;
		this.currentLocalidad = currentLocalidad;
		if (currentLocalidad != null)
			this.vista.getTxtNombre().setText(currentLocalidad.getDescripcion());
		vista.getBtnAgregar().addActionListener(this);
		vista.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == vista.getBtnAgregar()) {

			if (currentLocalidad == null) {
				agenda.agregarLocalidad(new LocalidadDTO(0, vista.getTxtNombre().getText()));
			} else {
				currentLocalidad.setDescripcion(vista.getTxtNombre().getText());
				agenda.actualizarLocalidad(new LocalidadDTO(0, vista.getTxtNombre().getText()));
			}

			controladorListado.llenarTabla();

		}
	}

}
