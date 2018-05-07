package cliente1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;

import org.example.www.servicioPractica1.*;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

public class Inicio {

	private JFrame frame;
	private JTextField soapKeyInput;
	private JPanel tab_dni;
	private JPanel tab_iban;
	private JPanel tab_cp;
	private JPanel tab_presupuesto;
	private JButton btnComprobar;
	private JLabel lblNewLabel_1;
	private JTextField input_dni;
	private JTextField input_IBAN;
	private JLabel label_1;
	private JTextField input_cp;
	private JTextPane respuestaCP;
	private JButton button_1;
	private JLabel lblCantidad;
	private JTextField inputID;
	private JTextField inputReferencia;
	private JSpinner inputCantidad;
	private JTextPane respuestaPresupuesto;
	private JButton btnGenerar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 590, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Soap Key");
		lblNewLabel.setBounds(134, 28, 86, 20);
		frame.getContentPane().add(lblNewLabel);

		soapKeyInput = new JTextField();
		soapKeyInput.setBounds(242, 25, 198, 20);
		frame.getContentPane().add(soapKeyInput);
		soapKeyInput.setColumns(10);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(62, 79, 483, 257);
		frame.getContentPane().add(tabbedPane);

		tab_dni = new JPanel();
		tabbedPane.addTab("DNI", null, tab_dni, null);
		tab_dni.setLayout(null);

		lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(47, 32, 46, 14);
		tab_dni.add(lblNewLabel_1);

		input_dni = new JTextField();
		input_dni.setBounds(80, 29, 350, 20);
		tab_dni.add(input_dni);
		input_dni.setColumns(10);

		JTextPane respuestaDNI = new JTextPane();
		respuestaDNI.setEditable(false);
		respuestaDNI.setBounds(47, 87, 383, 52);
		tab_dni.add(respuestaDNI);

		tab_iban = new JPanel();
		tabbedPane.addTab("IBAN", null, tab_iban, null);
		tab_iban.setLayout(null);

		input_IBAN = new JTextField();
		input_IBAN.setColumns(10);
		input_IBAN.setBounds(87, 29, 343, 20);
		tab_iban.add(input_IBAN);

		JLabel label = new JLabel("IBAN");
		label.setBounds(47, 32, 46, 14);
		tab_iban.add(label);

		JTextPane respuestaIBAN = new JTextPane();
		respuestaIBAN.setEditable(false);
		respuestaIBAN.setBounds(47, 87, 383, 52);
		tab_iban.add(respuestaIBAN);

		JButton button = new JButton("Comprobar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ServicioPractica1_Service consulta = new ServicioPractica1_ServiceLocator();
				BooleanHolder respuesta = new BooleanHolder();
				StringHolder respuestaError = new StringHolder();
				try {
					consulta.getservicioPractica1SOAP().validarIBAN(input_IBAN.getText(), soapKeyInput.getText(),
							respuesta, respuestaError);
					if (respuesta.value) {
						respuestaIBAN.setText("El IBAN introducido es válido.");
					} else {
						respuestaIBAN.setText("El IBAN introducido NO es válido.");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					respuestaIBAN.setText(e1.getMessage());
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					respuestaIBAN.setText(e1.getMessage());
				}

			}
		});
		button.setBounds(189, 184, 100, 34);
		tab_iban.add(button);

		tab_cp = new JPanel();
		tabbedPane.addTab("CP", null, tab_cp, null);
		tab_cp.setLayout(null);

		label_1 = new JLabel("C.P.");
		label_1.setBounds(47, 32, 46, 14);
		tab_cp.add(label_1);

		input_cp = new JTextField();
		input_cp.setColumns(10);
		input_cp.setBounds(80, 29, 350, 20);
		tab_cp.add(input_cp);

		respuestaCP = new JTextPane();
		respuestaCP.setEditable(false);
		respuestaCP.setBounds(47, 87, 383, 52);
		tab_cp.add(respuestaCP);

		button_1 = new JButton("Comprobar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServicioPractica1_Service consulta = new ServicioPractica1_ServiceLocator();
				StringHolder codigoPostal = new StringHolder();
				StringHolder poblacion = new StringHolder();
				StringHolder provincia = new StringHolder();
				try {
					consulta.getservicioPractica1SOAP().consultaCodigoPostal(input_cp.getText(), soapKeyInput.getText(),
							codigoPostal, poblacion, provincia);

					if (poblacion.value.equals("No se ha encontrado")) {
						respuestaCP.setText("No se han encontrado datos sobre el CP " + codigoPostal.value);
					} else {
						respuestaCP.setText("El CP " + codigoPostal.value + " se corresponde con la localidad "
								+ poblacion.value + " perteneciente a la provicincia de " + provincia.value);

					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					respuestaCP.setText(e1.getMessage());
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					respuestaCP.setText(e1.getMessage());
				}

			}
		});
		button_1.setBounds(189, 184, 100, 34);
		tab_cp.add(button_1);

		tab_presupuesto = new JPanel();
		tabbedPane.addTab("Presupuesto", null, tab_presupuesto, null);
		tab_presupuesto.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Fecha");
		lblNewLabel_2.setBounds(20, 11, 46, 14);
		tab_presupuesto.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Ref.");
		lblNewLabel_3.setBounds(20, 58, 46, 14);
		tab_presupuesto.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("ID Cliente");
		lblNewLabel_4.setBounds(273, 11, 55, 14);
		tab_presupuesto.add(lblNewLabel_4);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(273, 58, 46, 14);
		tab_presupuesto.add(lblCantidad);

		inputID = new JTextField();
		inputID.setText("");
		inputID.setBounds(355, 8, 91, 20);
		tab_presupuesto.add(inputID);
		inputID.setColumns(10);

		inputReferencia = new JTextField();
		inputReferencia.setBounds(63, 55, 172, 20);
		tab_presupuesto.add(inputReferencia);
		inputReferencia.setColumns(10);

		inputCantidad = new JSpinner();
		inputCantidad.setBounds(355, 55, 91, 20);
		tab_presupuesto.add(inputCantidad);

		respuestaPresupuesto = new JTextPane();
		respuestaPresupuesto.setEditable(false);
		respuestaPresupuesto.setBounds(63, 119, 383, 52);
		tab_presupuesto.add(respuestaPresupuesto);

		JDateChooser selectorFecha = new JDateChooser();
		selectorFecha.setDateFormatString("yyyy-MM-dd");
		selectorFecha.setBounds(63, 11, 172, 20);
		tab_presupuesto.add(selectorFecha);

		btnGenerar = new JButton("Generar");
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ServicioPractica1_Service consulta = new ServicioPractica1_ServiceLocator();
				IntHolder idPresupuesto = new IntHolder();
				BooleanHolder presupuestoGeneradoCorrectamente = new BooleanHolder();
				try {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD", Locale.getDefault());
					String d = sdf.format(selectorFecha.getDate());
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");

					Date date = null;
					try {

						date = (Date) formatter.parse(d);

					} catch (ParseException e2) {

						respuestaPresupuesto
								.setText("Ha habido un error, comrpueba que todos los datos son correctos.");

					}
					System.out.println(d);
					consulta.getservicioPractica1SOAP().generarPresupuesto(date, soapKeyInput.getText(),
							inputReferencia.getText(), Integer.parseInt(inputCantidad.getValue().toString()),
							Integer.parseInt(inputID.getText()), idPresupuesto, presupuestoGeneradoCorrectamente);

					if (presupuestoGeneradoCorrectamente.value) {
						respuestaPresupuesto.setText("Se ha generado el presupuesto con la id " + idPresupuesto.value);
					} else {
						respuestaPresupuesto.setText(
								"Ha habido algun problema, comprueba que los datos introducidos son correctos.");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					respuestaPresupuesto.setText(e1.getMessage());
				} catch (ServiceException e1) {
					// TODO Auto-generated catch block
					respuestaPresupuesto.setText(e1.getMessage());
				}
			}
		});
		btnGenerar.setBounds(198, 195, 89, 23);
		tab_presupuesto.add(btnGenerar);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ServicioPractica1_Service consulta = new ServicioPractica1_ServiceLocator();
					Boolean respuesta = consulta.getservicioPractica1SOAP().validarNIF(input_dni.getText(),
							soapKeyInput.getText());
					if (respuesta) {
						respuestaDNI.setText("El DNI introducido es válido.");
					} else {
						respuestaDNI.setText("El DNI introducido NO es válido.");
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					respuestaDNI.setText(e.getMessage());

				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					respuestaDNI.setText(e.getMessage());
				}

			}
		});
		btnComprobar.setBounds(189, 184, 100, 34);
		tab_dni.add(btnComprobar);
	}
}
