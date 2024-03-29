
/**
 * WS_proveedor1Skeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.ws_proveedor1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * WS_proveedor1Skeleton java skeleton for the axisService
 */
public class WS_proveedor1Skeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param ordenarCompra
	 * @return ordenarCompraResponse
	 */

	public org.example.www.ws_proveedor1.OrdenarCompraResponse ordenarCompra(
			org.example.www.ws_proveedor1.OrdenarCompra ordenarCompra) {
		OrdenarCompraResponse respuesta = new OrdenarCompraResponse();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			String myUrl = "jdbc:mysql://localhost/proveedor1";
			Connection conn = DriverManager.getConnection(myUrl, "user", "user");

			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("select count(1) from productos where id like '" + ordenarCompra.getReferenciaProducto() + "' ;");

			String resultado = "0";
			while (rs.next()) {
				resultado = rs.getString(1);
			}

			if (resultado.equals("0")) {
				conn.close();
				respuesta.setRealizadaCorrectamente(false);
				return respuesta;
			}
			String values = "('" + ordenarCompra.getReferenciaProducto() + "', '" + ordenarCompra.getNumeroUnidades() +"')";

			st.executeUpdate("INSERT INTO historial (idProducto, cantidad) VALUES "+ values + ";");

			respuesta.setRealizadaCorrectamente(true);

			return respuesta;
		} catch (Exception e) {
			respuesta.setRealizadaCorrectamente(false);
		}
		return respuesta;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param solicitarPresupuesto
	 * @return solicitarPresupuestoResponse
	 * @throws Exception 
	 */

	public org.example.www.ws_proveedor1.SolicitarPresupuestoResponse solicitarPresupuesto(
			org.example.www.ws_proveedor1.SolicitarPresupuesto solicitarPresupuesto) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String myUrl = "jdbc:mysql://localhost/proveedor1";
			Connection conn = DriverManager.getConnection(myUrl, "user", "user");

			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery(
					"select precio from productos where id like '" + solicitarPresupuesto.getReferenciaProducto() + "';");

			SolicitarPresupuestoResponse respuesta = new SolicitarPresupuestoResponse();
			while (rs.next()) {
				float precioActual= rs.getFloat(1);
				respuesta.setPrecio(precioActual * solicitarPresupuesto.getNumeroUnidades()); 
			}

			return respuesta;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Problemas con la base de datos");

		}
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param actualizarStock
	 * @return actualizarStockResponse
	 * @throws Exception 
	 */

	public org.example.www.ws_proveedor1.ActualizarStockResponse actualizarStock(
			org.example.www.ws_proveedor1.ActualizarStock actualizarStock) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String myUrl = "jdbc:mysql://localhost/proveedor1";
			Connection conn = DriverManager.getConnection(myUrl, "user", "user");

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"select stock from productos where id like '" + actualizarStock.getReferenciaProducto() + "';");

			ActualizarStockResponse respuesta = new ActualizarStockResponse();
			while (rs.next()) {
				int stockActual= rs.getInt(1);
				if (stockActual >= actualizarStock.getNumeroUnidades()) {
					String query = "update productos set stock = ? where id = ?";
				      PreparedStatement preparedStmt = conn.prepareStatement(query);
				      preparedStmt.setInt   (1, stockActual - actualizarStock.getNumeroUnidades());
				      preparedStmt.setString(2, actualizarStock.getReferenciaProducto());
				      preparedStmt.executeUpdate();
				   
				      respuesta.setActualizado(true);
				} else {
					respuesta.setActualizado(false);
				}
			}

			return respuesta;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Problemas con la base de datos");

		}
	}

}
