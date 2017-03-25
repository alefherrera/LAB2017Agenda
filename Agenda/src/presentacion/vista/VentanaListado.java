package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;

public class VentanaListado {
	public JDialog frame;
	private JTable tablaEntidad;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private DefaultTableModel tableSource;
	private String[] nombreColumnas = { "Descripcion" };
	private JButton btnEditar;

	public VentanaListado() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setBounds(100, 100, 446, 305);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 239);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(12, 11, 414, 182);
		panel.add(spPersonas);

		tableSource = new DefaultTableModel(null, nombreColumnas);
		tablaEntidad = new JTable(tableSource);

		tablaEntidad.getColumnModel().getColumn(0).setResizable(false);

		spPersonas.setViewportView(tablaEntidad);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(20, 206, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(119, 206, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(218, 206, 89, 23);
		panel.add(btnBorrar);
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		//show();
		
	}

	public void show() {
		this.frame.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}
	
	public JButton getBtnEditar() {
		return btnEditar;
	}
	
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public DefaultTableModel getTableSource() {
		return tableSource;
	}

	public JTable getTablaEntidad() {
		return tablaEntidad;
	}
	
	
	
}
