
/**
 * ServicioPractica1Skeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.serviciopractica1;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.iban4j.Iban;
import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;

/**
 * ServicioPractica1Skeleton java skeleton for the axisService
 */
public class ServicioPractica1Skeleton {
	
	 public static final int IBANNUMBER_MIN_SIZE = 15;
	    public static final int IBANNUMBER_MAX_SIZE = 34;
	    public static final BigInteger IBANNUMBER_MAGIC_NUMBER = new BigInteger("97");
	    
	private boolean comprobarSoapKey(String clave) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/mtis", "user", "user");
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery("select count(1) from soap where clave like '" + clave + "' ;");

			String resultado = "0";
			while (rs.next()) {
				resultado = rs.getString(1);
			}

			conexion.close();

			if (resultado.equals("1")) {
				return true;
			}
			return false;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param validarIBAN
	 * @return validarIBANResponse
	 */

	public ValidarIBANResponse validarIBAN(ValidarIBAN validarIBAN) throws Exception {
		if (comprobarSoapKey(validarIBAN.getSoapKey())) {
			Boolean correcto = Boolean.valueOf(false);
			ValidarIBANResponse respuesta = new ValidarIBANResponse();
			String newAccountNumber = validarIBAN.getIban();

	        // Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid. We could also check
	        // for specific length according to country, but for now we won't
	        if (newAccountNumber.length() < IBANNUMBER_MIN_SIZE || newAccountNumber.length() > IBANNUMBER_MAX_SIZE) {
	        	respuesta.setExiste(false);
	        	respuesta.setError("Error");
	        	return respuesta;
	        }

	        // Move the four initial characters to the end of the string.
	        newAccountNumber = newAccountNumber.substring(4) + newAccountNumber.substring(0, 4);

	        // Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35.
	        StringBuilder numericAccountNumber = new StringBuilder();
	        for (int i = 0;i < newAccountNumber.length();i++) {
	            numericAccountNumber.append(Character.getNumericValue(newAccountNumber.charAt(i)));
	        }

	        // Interpret the string as a decimal integer and compute the remainder of that number on division by 97.
	        BigInteger ibanNumber = new BigInteger(numericAccountNumber.toString());
	       Boolean resultado = ibanNumber.mod(IBANNUMBER_MAGIC_NUMBER).intValue() == 1;
	    		   if(resultado){
	    			   respuesta.setExiste(true);
	   	        	respuesta.setError("Es valido");
	   	        	return respuesta;
	    		   }else{
	    			   respuesta.setExiste(false);
		   	        	respuesta.setError("No es valido");
		   	        	return respuesta;
	    		   }

		}
		throw new Exception("Clave soap incorrecta");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param generarPresupuesto
	 * @return generarPresupuestoResponse
	 */

	public GenerarPresupuestoResponse generarPresupuesto(GenerarPresupuesto generarPresupuesto) throws Exception {
		System.out.println(generarPresupuesto.getSoapKey());

		if (comprobarSoapKey(generarPresupuesto.getSoapKey())) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String myUrl = "jdbc:mysql://localhost/mtis";
				Connection conn = DriverManager.getConnection(myUrl, "user", "user");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(generarPresupuesto.getFechaPresupuesto()).toString();

				Statement st = conn.createStatement();
				String values = "('" + date + "', '" + generarPresupuesto.getIdCliente() + "', '"
						+ generarPresupuesto.getReferenciaProducto() + "', '" + generarPresupuesto.getCantidadProducto()
						+ "')";

				st.executeUpdate(
						"INSERT INTO presupuestos (fechaPresupuesto, idCliente, referenciaProducto, cantidadProducto) VALUES "
								+ values + ";");

				ResultSet rs = st.executeQuery("select id from presupuestos where fechaPresupuesto like '" + date
						+ "' and idCliente = " + generarPresupuesto.getIdCliente() + " and referenciaProducto like '"
						+ generarPresupuesto.getReferenciaProducto() + "' and cantidadProducto = "
						+ generarPresupuesto.getCantidadProducto() + " LIMIT 1;");

				GenerarPresupuestoResponse respuesta = new GenerarPresupuestoResponse();

				respuesta.setIdPresupuesto(0);
				respuesta.setPresupuestoGeneradoCorrectamente(false);

				while (rs.next()) {
					System.out.println(rs.getInt(1));
					respuesta.setIdPresupuesto(rs.getInt(1));
					respuesta.setPresupuestoGeneradoCorrectamente(true);
				}

				conn.close();

				return respuesta;
			} catch (Exception e) {
				throw new Exception("Problema de acceso a la base de datos");
			}
		}

		throw new Exception("Clave soap incorrecta");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param validarNIF
	 * @return validarNIFResponse
	 */

	public ValidarNIFResponse validarNIF(ValidarNIF validarNIF) throws Exception {
		if (comprobarSoapKey(validarNIF.getSoapKey())) {
			boolean correcto = false;
			Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
			Matcher matcher = pattern.matcher(validarNIF.getNif());
			if (matcher.matches()) {
				String letra = matcher.group(2);
				String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
				int index = Integer.parseInt(matcher.group(1));
				index %= 23;
				String reference = letras.substring(index, index + 1);
				if (reference.equalsIgnoreCase(letra)) {
					correcto = true;
				} else {
					correcto = false;
				}
			} else {
				correcto = false;
			}
			ValidarNIFResponse respuesta = new ValidarNIFResponse();
			respuesta.setOut(correcto);
			return respuesta;
		}
		throw new Exception("Clave soap incorrecta");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param consultaCodigoPostal
	 * @return consultaCodigoPostalResponse1
	 */

	public ConsultaCodigoPostalResponse1 consultaCodigoPostal(ConsultaCodigoPostal consultaCodigoPostal)
			throws Exception {
		if (comprobarSoapKey(consultaCodigoPostal.getSoapKey())) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/mtis", "user", "user");
				Statement s = conexion.createStatement();
				String cp = consultaCodigoPostal.getCp();
				ResultSet rs = s.executeQuery(
						"select cod_postal, poblacion, provincia from poblaciones INNER JOIN provincias ON provincias.cod_prov=poblaciones.cod_prov where cod_postal = "
								+ cp + " LIMIT 1;");

				ConsultaCodigoPostalResponse1 respuesta = new ConsultaCodigoPostalResponse1();

				respuesta.setCodigoPostal(cp);
				respuesta.setPoblacion("No se ha encontrado");
				respuesta.setProvincia("No se ha encontrado");

				while (rs.next()) {
					respuesta.setCodigoPostal(rs.getString(1));
					respuesta.setPoblacion(rs.getString(2));
					respuesta.setProvincia(rs.getString(3));
				}
				conexion.close();
				return respuesta;
			} catch (Exception e) {
				throw new Exception("Problema al acceder a la base de datos");
			}
		}

		throw new Exception("Clave soap incorrecta");
	}

}
