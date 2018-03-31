
/**
 * WS_almacenCentralSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.ws_almacencentral;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * WS_almacenCentralSkeleton java skeleton for the axisService
 */
public class WS_almacenCentralSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param actualizarStock
	 * @return actualizarStockResponse
	 * @throws Exception 
	 */

	public org.example.www.ws_almacencentral.ActualizarStockResponse actualizarStock(
			org.example.www.ws_almacencentral.ActualizarStock actualizarStock) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String myUrl = "jdbc:mysql://localhost/almacencentral";
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

	/**
	 * Auto generated method signature
	 * 
	 * @param comprobarStock
	 * @return comprobarStockResponse
	 * @throws Exception 
	 */

	public org.example.www.ws_almacencentral.ComprobarStockResponse comprobarStock(
			org.example.www.ws_almacencentral.ComprobarStock comprobarStock) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String myUrl = "jdbc:mysql://localhost/almacencentral";
			Connection conn = DriverManager.getConnection(myUrl, "user", "user");

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"select stock from productos where id like '" + comprobarStock.getReferenciaProducto() + "';");

			ComprobarStockResponse respuesta = new ComprobarStockResponse();

			while (rs.next()) {
				if (rs.getInt(1) < comprobarStock.getNumeroUnidades()) {
					respuesta.setExiste(false);
				} else {
					respuesta.setExiste(true);
				}
			}
			conn.close();

			return respuesta;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Problemas con la base de datos");

		}
	}

}
