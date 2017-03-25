package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import modelo.Agenda;
import presentacion.vista.VentanaPersona;

public class ControladorPersona implements ActionListener {

	private PersonaDTO currentPerson;
	private Controlador controlador;
	private VentanaPersona ventanaPersona;
	private Agenda agenda;

	public ControladorPersona(VentanaPersona vista, Agenda agenda, PersonaDTO currentPerson, Controlador controlador) {
		this.ventanaPersona = vista;
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(this);
		this.currentPerson = currentPerson;
		this.controlador = controlador;
		this.agenda = agenda;
		this.ventanaPersona.cargarFormulario(currentPerson);

		cargarCombos();

	}

	private void cargarCombos() {
		DefaultComboBoxModel<LocalidadDTO> localidadModel = new DefaultComboBoxModel<LocalidadDTO>();
		for (LocalidadDTO loc : agenda.obtenerLocalides()) {
			localidadModel.addElement(loc);
		}
		this.ventanaPersona.getCmbLocalidad().setModel(localidadModel);

		DefaultComboBoxModel<TipoContactoDTO> tipoContactoModel = new DefaultComboBoxModel<TipoContactoDTO>();
		for (TipoContactoDTO tipoContacto : agenda.obtenerTiposDeContacto()) {
			tipoContactoModel.addElement(tipoContacto);
		}
		this.ventanaPersona.getCmbTipoContacto().setModel(tipoContactoModel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
			// es una persona nueva

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date parsed;
			java.sql.Date birthdayDate = null;
			parsed = ventanaPersona.getDateChooser().getDate();
			birthdayDate = new java.sql.Date(parsed.getTime());

			if (getCurrentPerson() == null) {
				PersonaDTO nuevaPersona = new PersonaDTO(0, ventanaPersona.getTxtNombre().getText(),
						ventanaPersona.getTxtTelefono().getText(), ventanaPersona.getTxtEmail().getText(), birthdayDate,
						ventanaPersona.getTxtCalle().getText(),
						Integer.parseInt(ventanaPersona.getTxtAltura().getText()),
						Integer.parseInt(ventanaPersona.getTxtPiso().getText()),
						Integer.parseInt(ventanaPersona.getTxtDepto().getText()),
						(LocalidadDTO) ventanaPersona.getCmbLocalidad().getSelectedItem(),
						(TipoContactoDTO) ventanaPersona.getCmbTipoContacto().getSelectedItem());
				this.agenda.agregarPersona(nuevaPersona);

			} else {
				PersonaDTO personaEditar = getCurrentPerson();
				personaEditar.setNombre(ventanaPersona.getTxtNombre().getText());
				personaEditar.setTelefono(ventanaPersona.getTxtTelefono().getText());
				personaEditar.setEmail(ventanaPersona.getTxtEmail().getText());
				personaEditar.setFechaNac(birthdayDate);
				personaEditar.setCalle(ventanaPersona.getTxtCalle().getText());
				personaEditar.setAltura(Integer.parseInt(ventanaPersona.getTxtAltura().getText()));
				personaEditar.setPiso(Integer.parseInt(ventanaPersona.getTxtPiso().getText()));
				personaEditar.setDepto(Integer.parseInt(ventanaPersona.getTxtDepto().getText()));
				personaEditar.setLocalidad((LocalidadDTO) ventanaPersona.getCmbLocalidad().getSelectedItem());
				personaEditar.setTipoContacto((TipoContactoDTO) ventanaPersona.getCmbTipoContacto().getSelectedItem());

				this.agenda.actualizarPersona(personaEditar);
			}

			controlador.llenarTabla();
			this.ventanaPersona.dispose();
		}
	}

	public PersonaDTO getCurrentPerson() {
		return currentPerson;
	}

	public void setCurrentPerson(PersonaDTO currentPerson) {
		this.currentPerson = currentPerson;
	}

}
