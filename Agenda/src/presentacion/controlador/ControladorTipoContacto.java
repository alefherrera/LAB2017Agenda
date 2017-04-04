package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.vista.VentanaABM;

public class ControladorTipoContacto implements ActionListener {

	private VentanaABM vista;
	private ControladorListadoTipoContacto controladorListado;
	private Agenda agenda;
	private TipoContactoDTO currentTipoContacto;

	public ControladorTipoContacto(VentanaABM vista, ControladorListadoTipoContacto controladorListado,  Agenda agenda, TipoContactoDTO current) {
		// TODO Auto-generated constructor stub
		this.vista = vista;
		this.agenda = agenda;
		this.controladorListado = controladorListado;
		this.currentTipoContacto = current;
		vista.getBtnAgregar().addActionListener(this);
		if (currentTipoContacto != null){
			this.vista.getTxtNombre().setText(currentTipoContacto.getDescripcion());
			this.vista.getBtnAgregar().setText("Editar");
		}
		vista.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == vista.getBtnAgregar()) {
			
			if (currentTipoContacto == null) {
				agenda.agregarTipoContacto(new TipoContactoDTO(0, vista.getTxtNombre().getText()));
			}
			else
			{
				currentTipoContacto.setDescripcion(vista.getTxtNombre().getText());
				agenda.actualizarTipoContacto(currentTipoContacto);
			}
			
			vista.setVisible(false);
			vista.dispose();
			controladorListado.llenarTabla();
		}
	}

}
