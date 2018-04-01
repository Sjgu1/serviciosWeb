package clienteP3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.wsdl.Service;
import javax.xml.rpc.ServiceException;
import javax.activation.DataHandler;
import javax.mail.*;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

import mtis.bpel.invoke.*;;

public class Principal {

	private JFrame frame;
	private JTextField inputReferencia;

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
		frame.setBounds(100, 100, 345, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblRefProducto = new JLabel("Ref. Producto:");
		lblRefProducto.setBounds(31, 11, 72, 14);
		frame.getContentPane().add(lblRefProducto);

		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setBounds(150, 11, 58, 14);
		frame.getContentPane().add(lblUnidades);

		inputReferencia = new JTextField();
		inputReferencia.setBounds(31, 36, 98, 20);
		frame.getContentPane().add(inputReferencia);
		inputReferencia.setColumns(10);

		JSpinner inputUnidades = new JSpinner();
		inputUnidades.setBounds(170, 36, 29, 20);
		frame.getContentPane().add(inputUnidades);

		JTextPane panelRespuesta = new JTextPane();
		panelRespuesta.setEditable(false);
		panelRespuesta.setBounds(28, 91, 276, 99);
		frame.getContentPane().add(panelRespuesta);

		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(inputUnidades.getValue().toString()) > 0) {
					panelRespuesta.setText("Probando.");

					WS_compraRequest req = new WS_compraRequest();
					req.setReferenciaProducto(inputReferencia.getText());
					req.setNumeroUnidades(Integer.parseInt(inputUnidades.getValue().toString()));
					
					try {
						WS_compraLocator locator = new WS_compraLocator();
						WS_compraPortType port = locator.getWS_compraPort();
						String mensaje = locator.getWS_compraPortAddress();
						panelRespuesta.setText(mensaje);
						WS_compraResponse resp = port.process(req);
						
						panelRespuesta.setText(resp.getResult());
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						//panelRespuesta.setText("Ha habido problemas con la llamada.");
						//panelRespuesta.setText(e1.getMessage());
					} catch (ServiceException e1) {
						// TODO Auto-generated catch block
						//panelRespuesta.setText("Ha habido problemas con la llamada.");
						//panelRespuesta.setText(e1.getMessage());
						e1.printStackTrace();
					}

				} else {
					panelRespuesta.setText("Indica una referencia y un valor mayor que 0 en las unidades.");
				}
			}
		});
		btnComprar.setBounds(230, 35, 89, 23);
		frame.getContentPane().add(btnComprar);

	}
}
