package clienteP3;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.StringHolder;

import org.json.JSONException;
import org.json.JSONObject;

import com.toedter.calendar.JDateChooser;

public class Principal {

	private JFrame frame;
	private JTextField inputReferencia;
	private JTextField inputClaveRest;
	private JTextField input_dni;
	private JTextField input_IBAN;
	private JTextField input_cp;
	private JTextField inputID;
	private JTextField inputRefPR;
	private JTextField inputCodAsig;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
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
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 819, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanel.setBounds(10, 9, 783, 404);
		frame.getContentPane().add(tabbedPanel);

		JPanel panel = new JPanel();
		tabbedPanel.addTab("Flujo 1", null, panel, null);
		panel.setLayout(null);

		inputReferencia = new JTextField();
		inputReferencia.setBounds(196, 101, 86, 20);
		panel.add(inputReferencia);
		inputReferencia.setColumns(10);

		JSpinner inputUnidades = new JSpinner();
		inputUnidades.setBounds(336, 101, 48, 20);
		panel.add(inputUnidades);

		JButton btnComprar = new JButton("Comprar");
		btnComprar.setBounds(433, 100, 101, 21);
		panel.add(btnComprar);

		JLabel lblRefProducto = new JLabel("Ref. Producto:");
		lblRefProducto.setBounds(196, 76, 101, 14);
		panel.add(lblRefProducto);

		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setBounds(336, 76, 86, 14);
		panel.add(lblUnidades);

		JTextArea panelRespuesta = new JTextArea();
		panelRespuesta.setBounds(196, 149, 338, 141);
		panelRespuesta.setWrapStyleWord(true);
		panel.add(panelRespuesta);

		JPanel panel_1 = new JPanel();
		tabbedPanel.addTab("Flujo 2", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblClaveRest = new JLabel("Clave Rest");
		lblClaveRest.setBounds(228, 11, 75, 22);
		panel_1.add(lblClaveRest);

		inputClaveRest = new JTextField();
		inputClaveRest.setBounds(319, 12, 86, 20);
		panel_1.add(inputClaveRest);
		inputClaveRest.setColumns(10);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(135, 67, 483, 257);
		panel_1.add(tabbedPane);

		JPanel tab_dni = new JPanel();
		tabbedPane.addTab("DNI", null, tab_dni, null);
		tab_dni.setLayout(null);

		JPanel tab_iban = new JPanel();
		tabbedPane.addTab("IBAN", null, tab_iban, null);
		tab_iban.setLayout(null);

		tabbedPane.addTab("DNI", null, tab_dni, null);
		tabbedPane.addTab("IBAN", null, tab_iban, null);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(47, 32, 46, 14);
		tab_dni.add(lblNewLabel_1);

		input_dni = new JTextField();
		input_dni.setColumns(10);
		input_dni.setBounds(80, 29, 350, 20);
		tab_dni.add(input_dni);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(47, 87, 383, 52);
		tab_dni.add(textPane);

		JTextPane respuestaDNI = new JTextPane();
		respuestaDNI.setEditable(false);
		respuestaDNI.setBounds(47, 87, 383, 52);
		tab_dni.add(respuestaDNI);

		JButton btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					StringBuilder result = new StringBuilder();

					URL url = new URL("http://localhost:9090/flujo2/validarNIF/" + input_dni.getText());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("RestKey", inputClaveRest.getText());
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					rd.close();
					if (result.toString().contains("true") && result.toString().contains("200")) {
						respuestaDNI.setText("El NIF es valido");
					}

					if (result.toString().contains("false") && result.toString().contains("200")) {
						respuestaDNI.setText("El NIF  NO valido");
					}
				} catch (Exception e1) {
					if (e1.toString().contains("500")) {
						respuestaDNI.setText("La clave no es valida");
					}
					if (e1.toString().contains("405")) {
						respuestaDNI.setText("No se ha encontrado el nif.");
					}
				}
			}
		});
		btnComprobar.setBounds(189, 184, 100, 34);
		tab_dni.add(btnComprobar);

		input_IBAN = new JTextField();
		input_IBAN.setColumns(10);
		input_IBAN.setBounds(87, 29, 343, 20);
		tab_iban.add(input_IBAN);

		JLabel label_2 = new JLabel("IBAN");
		label_2.setBounds(47, 32, 46, 14);
		tab_iban.add(label_2);

		JTextPane respuestaIBAN = new JTextPane();
		respuestaIBAN.setEditable(false);
		respuestaIBAN.setBounds(47, 87, 383, 52);
		tab_iban.add(respuestaIBAN);

		JButton button_iban = new JButton("Comprobar");
		button_iban.setBounds(189, 184, 100, 34);
		tab_iban.add(button_iban);

		JPanel tab_cp = new JPanel();
		tabbedPane.addTab("CP", null, tab_cp, null);
		tab_cp.setLayout(null);

		JLabel label_cp = new JLabel("C.P.");
		label_cp.setBounds(47, 32, 46, 14);
		tab_cp.add(label_cp);

		input_cp = new JTextField();
		input_cp.setColumns(10);
		input_cp.setBounds(80, 29, 350, 20);
		tab_cp.add(input_cp);

		JTextPane respuestaCP = new JTextPane();
		respuestaCP.setEditable(false);
		respuestaCP.setBounds(47, 87, 383, 52);
		tab_cp.add(respuestaCP);

		JButton button_cp = new JButton("Comprobar");
		button_cp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					StringBuilder result = new StringBuilder();

					URL url = new URL("http://localhost:9090/flujo2/consultaCodigoPostal/" + input_cp.getText());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("RestKey", inputClaveRest.getText());
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					rd.close();
					if (result.toString().contains("true") && result.toString().contains("200")) {
						String respuesta = result.toString();
						// respuesta = respuesta.replaceAll("\\s+","");

						JSONObject jsonObj = new JSONObject(respuesta);
						JSONObject jsonObj2 = jsonObj.getJSONObject("Poblacion");
						
						String responder = "CP:"+jsonObj2.getString("codigoPostal")+"\nPoblacion: "+jsonObj2.getString("poblacion")+"\nProvincia: "+ jsonObj2.getString("provincia");
						respuestaCP.setText(responder);
					}
					
					if (result.toString().contains("false") && result.toString().contains("200")) {
						respuestaCP.setText("No se ha encontrado ninguna poblacion");
					}
				} catch (Exception e1) {
					if (e1.toString().contains("500")) {
						respuestaCP.setText("La clave no es valida");
					}
					if (e1.toString().contains("405")) {
						respuestaCP.setText("No se ha encontrado el CP.");
					}
				}
			}
		});
		button_cp.setBounds(189, 184, 100, 34);
		tab_cp.add(button_cp);

		JPanel tab_presupuesto = new JPanel();
		tabbedPane.addTab("Presupuesto", null, tab_presupuesto, null);
		tab_presupuesto.setLayout(null);

		JLabel label_4 = new JLabel("Fecha");
		label_4.setBounds(20, 11, 46, 14);
		tab_presupuesto.add(label_4);

		JLabel label_5 = new JLabel("Ref.");
		label_5.setBounds(20, 58, 46, 14);
		tab_presupuesto.add(label_5);

		JLabel label_6 = new JLabel("ID Cliente");
		label_6.setBounds(273, 11, 55, 14);
		tab_presupuesto.add(label_6);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(273, 58, 72, 14);
		tab_presupuesto.add(lblCantidad);

		inputID = new JTextField();
		inputID.setText("");
		inputID.setColumns(10);
		inputID.setBounds(355, 8, 91, 20);
		tab_presupuesto.add(inputID);

		inputRefPR = new JTextField();
		inputRefPR.setColumns(10);
		inputRefPR.setBounds(63, 55, 172, 20);
		tab_presupuesto.add(inputRefPR);

		JSpinner inputCantidad = new JSpinner();
		inputCantidad.setBounds(355, 55, 91, 20);
		tab_presupuesto.add(inputCantidad);

		JTextPane respuestaPresupuesto = new JTextPane();
		respuestaPresupuesto.setEditable(false);
		respuestaPresupuesto.setBounds(63, 119, 383, 52);
		tab_presupuesto.add(respuestaPresupuesto);

		JDateChooser selectorFecha = new JDateChooser();
		selectorFecha.setDateFormatString("yyyy-MM-dd");
		selectorFecha.setBounds(63, 11, 172, 20);

		tab_presupuesto.add(selectorFecha);
		
		JButton btnGenerarPresupuesto = new JButton("Generar");
		btnGenerarPresupuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					URL url = new URL("http://localhost:9090/flujo2/generarPresupuesto");
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					con.setDoOutput(true);
					con.setRequestProperty("Content-Type", "application/json");
					con.addRequestProperty("RestKey", inputClaveRest.getText());
					con.setConnectTimeout(5000);
					con.setReadTimeout(5000);
					DataOutputStream wr = null;
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD", Locale.getDefault()); 
					String d =  sdf.format(selectorFecha.getDate()); 
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");

					System.out.println("El selector es " + selectorFecha.getDate().toString());
					try {
						Date date = null; 

						date = (Date) formatter.parse(d);
						String newstring = new SimpleDateFormat("yyyy-MM-dd").format(date);

						System.out.println("{\"fechaPresupuesto\":\""+ newstring+ "\","
								  + "\"idCliente\": \""+ inputID.getText()+ "\","
								  + "\"referenciaProducto\": \""+ inputRefPR.getText()+ "\","
								  + "\"cantidadProducto\": \""+ inputCantidad.getValue().toString()+ "\"}");
					
						wr = new DataOutputStream(con.getOutputStream());
						wr.writeBytes("{\"fechaPresupuesto\":\""+ newstring+ "\","
								  + "\"idCliente\": \""+ inputID.getText()+ "\","
								  + "\"referenciaProducto\": \""+ inputRefPR.getText()+ "\","
								  + "\"cantidadProducto\": \""+ inputCantidad.getValue().toString()+ "\"}");
						wr.flush();
						wr.close();
					} catch (IOException exception) {
						throw exception;
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer content = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						content.append(inputLine);
					}
					in.close();
					con.disconnect();

					String respuesta = content.toString();
					
					if (respuesta.contains("201") && respuesta.contains("true")){
						JSONObject jsonObj = new JSONObject(respuesta);
						JSONObject jsonObj2 = jsonObj.getJSONObject("Presupuesto");

						respuestaPresupuesto.setText("ID del presupuesto generado:" +jsonObj2.get("idPresupuesto").toString());
					}else{
						respuestaPresupuesto.setText("Compruebe los datos.");

					}
					

				} catch (IOException e1) {
					System.out.println(e1);
					respuestaPresupuesto.setText("Compruebe los datos.");


				} catch (JSONException e2) {
					System.out.println(e2);
					respuestaPresupuesto.setText("Compruebe los datos.");


				}
			}
		});
		btnGenerarPresupuesto.setBounds(198, 195, 89, 23);
		tab_presupuesto.add(btnGenerarPresupuesto);

		tab_dni = new JPanel();
		tab_dni.setLayout(null);

		lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(47, 32, 46, 14);
		tab_dni.add(lblNewLabel_1);

		tab_iban = new JPanel();
		tab_iban.setLayout(null);

		JLabel label = new JLabel("IBAN");
		label.setBounds(47, 32, 46, 14);
		tab_iban.add(label);

		button_iban.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					StringBuilder result = new StringBuilder();

					URL url = new URL("http://localhost:9090/flujo2/validarIBAN/" + input_IBAN.getText());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("RestKey", inputClaveRest.getText());
					System.out.println("Entro");

					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					rd.close();
					System.out.println(result.toString());
					if (result.toString().contains("true") && result.toString().contains("200")) {
						respuestaIBAN.setText("El IBAN es valido");
					}
					if (result.toString().contains("false") && result.toString().contains("200")) {
						respuestaIBAN.setText("El IBAN  NO valido");
					}
				} catch (Exception e1) {
					if (e1.toString().contains("500")) {
						respuestaIBAN.setText("La clave no es valida");
					}
					if (e1.toString().contains("405")) {
						respuestaIBAN.setText("No se ha encontrado la ruta.");
					}
				}
			}
		});

		tab_cp = new JPanel();
		tab_cp.setLayout(null);

		label_cp = new JLabel("C.P.");
		label_cp.setBounds(47, 32, 46, 14);
		tab_cp.add(label_cp);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(273, 58, 46, 14);

		JPanel panel_2 = new JPanel();
		tabbedPanel.addTab("Flujo 3", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cod. Carrera");
		lblNewLabel.setBounds(237, 109, 111, 17);
		panel_2.add(lblNewLabel);

		inputCodAsig = new JTextField();
		inputCodAsig.setBounds(369, 106, 111, 20);
		panel_2.add(inputCodAsig);
		inputCodAsig.setColumns(10);

		JTextPane respuesta_flujo3 = new JTextPane();
		respuesta_flujo3.setBounds(281, 224, 178, 60);
		panel_2.add(respuesta_flujo3);

		JButton btn_flujo_3 = new JButton("Consultar");
		btn_flujo_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					URL url = new URL("http://localhost:9090/flujo3");
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					con.setDoOutput(true);
					con.setRequestProperty("Content-Type", "application/json");
					con.setConnectTimeout(5000);
					con.setReadTimeout(5000);
					DataOutputStream wr = null;

					try {
						wr = new DataOutputStream(con.getOutputStream());
						wr.writeBytes("{\"mensaje\":{ \"codigoAsignatura\":\""
								+ inputCodAsig.getText() + "\"}}");
						wr.flush();
						wr.close();
					} catch (IOException exception) {
						throw exception;
					}

					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer content = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
						content.append(inputLine);
					}
					in.close();
					con.disconnect();

					String respuesta = content.toString();

					respuesta_flujo3.setText(respuesta);

				} catch (IOException e1) {
					System.out.println(e1);
					respuesta_flujo3.setText("Ha habido algun problema");

				}
			}
		});
		btn_flujo_3.setBounds(327, 159, 89, 23);
		panel_2.add(btn_flujo_3);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					StringBuilder result = new StringBuilder();
					System.out.println(input_dni.getText());
					System.out.println(inputClaveRest.getText());

					URL url = new URL("http://localhost:9090/flujo2/validarNIF/" + input_dni.getText());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("RestKey", inputClaveRest.getText());
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String line;
					while ((line = rd.readLine()) != null) {
						result.append(line);
					}
					rd.close();
					respuestaDNI.setText(result.toString());
				} catch (Exception e1) {
					respuestaDNI.setText(e1.getMessage());
				}

			}
		});
		btnComprobar.setBounds(189, 184, 100, 34);
		tab_dni.add(btnComprobar);

		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(inputUnidades.getValue().toString()) > 0) {
					try {
						URL url = new URL("http://localhost:9090/flujo1");
						HttpURLConnection con = (HttpURLConnection) url.openConnection();
						con.setRequestMethod("POST");
						con.setDoOutput(true);
						con.setRequestProperty("Content-Type", "application/json");
						con.addRequestProperty("RestKey", "clave1");
						con.setConnectTimeout(5000);
						con.setReadTimeout(5000);
						DataOutputStream wr = null;

						try {
							wr = new DataOutputStream(con.getOutputStream());
							wr.writeBytes("{" + "\"mensaje\":{" + "\"numeroUnidades\":\""
									+ inputUnidades.getValue().toString() + "\"," + "\"referenciaProducto\": \""
									+ inputReferencia.getText() + "\"" + "}"

									+ "}");
							wr.flush();
							wr.close();
						} catch (IOException exception) {
							throw exception;
						}

						BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer content = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
							content.append(inputLine);
						}
						in.close();
						con.disconnect();

						String respuesta = content.toString();
						// respuesta = respuesta.replaceAll("\\s+","");

						JSONObject jsonObj = new JSONObject(respuesta);
						JSONObject jsonObj2 = jsonObj.getJSONObject("WS_compraResponse");

						panelRespuesta.setText(jsonObj2.get("tns:result").toString().replace("-->", "\n"));

					} catch (IOException e1) {
						System.out.println(e1);
						panelRespuesta.setText(e1.toString());

					} catch (JSONException e2) {
						System.out.println(e2);
						panelRespuesta.setText(e2.toString());

					}

				} else {
					panelRespuesta.setText("Indica una referencia y un valor mayor que 0 en las unidades.");
				}
			}
		});
	}
}
