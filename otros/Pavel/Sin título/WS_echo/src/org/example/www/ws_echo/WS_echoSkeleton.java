
/**
 * WS_echoSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
package org.example.www.ws_echo;

/**
 * WS_echoSkeleton java skeleton for the axisService
 */
public class WS_echoSkeleton {

	/**
	 * Auto generated method signature
	 * 
	 * @param echo
	 * @return echoResponse
	 */

	public org.example.www.ws_echo.EchoResponse echo(org.example.www.ws_echo.Echo echo) {
		EchoResponse response = new EchoResponse();
		response.setOut(echo.getIn());
		return response;
	}

}
