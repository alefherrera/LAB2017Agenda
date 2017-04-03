package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import presentacion.vista.VentanaConfiguracion;
import util.ConfigService;
import util.Configuration;
import util.DatabaseUtil;

public class ControladorConfiguracion implements ActionListener {

	private VentanaConfiguracion vista;
	private Controlador controladorPrincipal;
	private boolean isFirstRun;

	public ControladorConfiguracion(VentanaConfiguracion vista, Controlador controladorPrincipal, boolean isFirstRun) {
		this.vista = vista;
		this.controladorPrincipal = controladorPrincipal;
		this.isFirstRun = isFirstRun;
		vista.getBotonOK().addActionListener(this);
		vista.getBotonCancel().addActionListener(this);
		vista.getBotonTest().addActionListener(this);

		loadConfiguration(ConfigService.GetConfiguration());

		vista.setVisible(true);
	}

	private void loadConfiguration(Configuration conf) {
		if (conf == null)
			return;

		vista.getTxtIp().setText(conf.getIp());
		vista.getTxtPort().setText(conf.getPort());
		vista.getTxtUser().setText(conf.getUser());
		vista.getTxtPass().setText(conf.getPass());
		vista.getTxtDatabase().setText(conf.getDatabase());
		
	}

	public boolean TestConnection(Configuration config) {
		return DatabaseUtil.testConnection(config);
	}

	private void SaveConfiguration() {
		ConfigService.SaveConfiguration(createConfigFromInput());
	}

	private Configuration createConfigFromInput() {
		Configuration config = new Configuration();
		config.setIp(vista.getTxtIp().getText());
		config.setPort(vista.getTxtPort().getText());
		config.setUser(vista.getTxtUser().getText());
		config.setPass(vista.getTxtPass().getText());
		config.setDatabase(vista.getTxtDatabase().getText());
		return config;
	}

	private void validateInput() throws Exception {
		String ipregex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
		if (!vista.getTxtIp().getText().matches(ipregex)
				&& !vista.getTxtIp().getText().toLowerCase().equals("localhost")) {
			throw new Exception("Direccion IP invalida.");
		}

		try {
			Integer portNumber = Integer.parseInt(vista.getTxtPort().getText());
			if (portNumber < 0 || portNumber > 65535) {
				throw new Exception("Numero de puerto invalido.");
			}
		} catch (NumberFormatException e) {
			throw new Exception("Numero de puerto invalido.");
		}
		
		if (vista.getTxtDatabase().getText().isEmpty()) {
			throw new Exception("Base de datos invalida.");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.getBotonOK()) {

			try {
				validateInput();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(vista, e1.getMessage(), "Error en la validacion", 1);
				return;
			}

			if (TestConnection(createConfigFromInput())) {
				SaveConfiguration();
				controladorPrincipal.llenarTabla();
				vista.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(vista,
						"No se ha podido conectar a la base de datos. Revise los parametros y vuelva a intentar.",
						"Error en la configuracion", 1);
			}

		} else if (e.getSource() == vista.getBotonCancel()) {
			vista.setVisible(false);
			if (isFirstRun) {
				controladorPrincipal.finishApplication();				
			}
		} else if (e.getSource() == vista.getBotonTest()) {
			
			try {
				validateInput();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(vista, e1.getMessage(), "Error en la validacion", 1);
				return;
			}
			
			if (TestConnection(createConfigFromInput())) {
				JOptionPane.showMessageDialog(vista, "Conexion establecida satisfactoriamente.", "Aviso", 1);
			} else {
				JOptionPane.showMessageDialog(vista,
						"No se ha podido conectar a la base de datos. Revise los parametros y vuelva a intentar.",
						"Error en la configuracion", 1);
			}
		}
	}

}
