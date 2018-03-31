
/**
 * HolaMundoSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.holamundo;

/**
 * HolaMundoSkeleton java skeleton for the axisService
 */
public class HolaMundoSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param newOperation
	 * @return newOperationResponse
	 */

	public org.example.www.holamundo.NewOperationResponse newOperation(
			org.example.www.holamundo.NewOperation newOperation) {
		// TODO : fill this with the necessary business logic
		org.example.www.holamundo.NewOperationResponse respuesta=new org.example.www.holamundo.NewOperationResponse();
		respuesta.localOut=newOperation.localIn;
		return respuesta;
	}

}
