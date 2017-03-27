package presentacion.vista;


import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private JLabel lblLocalidad;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JLabel lblFechaNac;
	private JLabel lblTipoDeContacto;
	private JComboBox<LocalidadDTO> cmbLocalidad;
	private JComboBox<TipoContactoDTO> cmbTipoContacto;
	private JDateChooser dateChooser;

	public VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 658, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 615, 278);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(509, 208, 87, 57);
		panel.add(btnAgregarPersona);
		
		txtCalle = new JTextField();
		txtCalle.setColumns(10);
		txtCalle.setBounds(133, 79, 164, 20);
		panel.add(txtCalle);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 82, 113, 14);
		panel.add(lblCalle);
		
		txtAltura = new JTextField();
		txtAltura.setColumns(10);
		txtAltura.setBounds(133, 109, 164, 20);
		panel.add(txtAltura);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 112, 113, 14);
		panel.add(lblAltura);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(133, 139, 164, 20);
		panel.add(txtPiso);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 142, 113, 14);
		panel.add(lblPiso);
		
		txtDepto = new JTextField();
		txtDepto.setColumns(10);
		txtDepto.setBounds(133, 169, 164, 20);
		panel.add(txtDepto);
		
		JLabel lblDepto = new JLabel("Depto");
		lblDepto.setBounds(10, 172, 113, 14);
		panel.add(lblDepto);
		
		lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(309, 52, 113, 14);
		panel.add(lblLocalidad);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(432, 79, 164, 20);
		panel.add(txtEmail);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(309, 82, 113, 14);
		panel.add(lblEmail);
		
		lblFechaNac = new JLabel("Fecha Nacimiento");
		lblFechaNac.setBounds(309, 111, 113, 14);
		panel.add(lblFechaNac);
		
		lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(309, 141, 113, 14);
		panel.add(lblTipoDeContacto);
		
		cmbLocalidad = new JComboBox<LocalidadDTO>();
		cmbLocalidad.setBounds(434, 48, 162, 22);
		panel.add(cmbLocalidad);
		
		cmbTipoContacto = new JComboBox<TipoContactoDTO>();
		cmbTipoContacto.setBounds(434, 138, 162, 22);
		panel.add(cmbTipoContacto);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(432, 107, 164, 22);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		panel.add(dateChooser);
		
		this.setVisible(true);
	}
	
	public void cargarFormulario(PersonaDTO persona)
	{
		if (persona == null)
			return;
		
		txtNombre.setText(persona.getNombre());
		txtTelefono.setText(persona.getTelefono());
		txtEmail.setText(persona.getEmail());
		
		dateChooser.setDate(persona.getFechaNac());
		txtCalle.setText(persona.getCalle());
		txtAltura.setText(String.valueOf(persona.getAltura()));
		txtPiso.setText(String.valueOf(persona.getPiso()));
		txtDepto.setText(String.valueOf(persona.getDepto()));
		
		cmbLocalidad.setSelectedItem(persona.getLocalidad());
		cmbTipoContacto.setSelectedItem(persona.getTipoContacto());
		
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JTextField getTxtAltura() {
		return txtAltura;
	}

	public JTextField getTxtPiso() {
		return txtPiso;
	}

	public JTextField getTxtDepto() {
		return txtDepto;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}
	
	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public JComboBox<LocalidadDTO> getCmbLocalidad() {
		return cmbLocalidad;
	}
	
	public JComboBox<TipoContactoDTO> getCmbTipoContacto() {
		return cmbTipoContacto;
	}
}

