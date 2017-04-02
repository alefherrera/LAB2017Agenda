package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfiguracion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPort;
	private JTextField txtIp;
	private JTextField txtUser;
	private JTextField txtPass;
	private JButton okButton;
	private JButton cancelButton;
	private JButton buttonTest;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			VentanaConfiguracion dialog = new VentanaConfiguracion();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public VentanaConfiguracion() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Configuracion");
		setBounds(100, 100, 323, 374);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setLocationRelativeTo(null);


		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 279, 126);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtIp = new JTextField();
		txtIp.setBounds(80, 52, 116, 22);
		panel.add(txtIp);
		txtIp.setColumns(10);
		{
			txtPort = new JTextField();
			txtPort.setBounds(80, 87, 116, 22);
			panel.add(txtPort);
			txtPort.setColumns(10);
		}

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(12, 55, 56, 16);
		panel.add(lblIp);

		JLabel lblPort = new JLabel("Puerto");
		lblPort.setBounds(12, 90, 56, 16);
		panel.add(lblPort);

		JLabel lblDireccionMysql = new JLabel("Direccion MySql");
		lblDireccionMysql.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccionMysql.setBounds(12, 13, 255, 26);
		panel.add(lblDireccionMysql);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 152, 279, 126);
		contentPanel.add(panel_1);

		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(80, 52, 116, 22);
		panel_1.add(txtUser);

		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(80, 87, 116, 22);
		panel_1.add(txtPass);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(12, 55, 56, 16);
		panel_1.add(lblUser);

		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(12, 90, 56, 16);
		panel_1.add(lblPass);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuario.setBounds(12, 13, 255, 26);
		panel_1.add(lblUsuario);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});

				buttonTest = new JButton("Test");
				buttonTest.setActionCommand("OK");
				buttonPane.add(buttonTest);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getBotonOK() {
		return okButton;
	}

	public JButton getBotonCancel() {
		return cancelButton;
	}

	public JButton getBotonTest() {
		return buttonTest;
	}

	public JTextField getTxtIp() {
		return txtIp;
	}

	public JTextField getTxtPort() {
		return txtPort;
	}

	public JTextField getTxtUser() {
		return txtUser;
	}

	public JTextField getTxtPass() {
		return txtPass;
	}
}
