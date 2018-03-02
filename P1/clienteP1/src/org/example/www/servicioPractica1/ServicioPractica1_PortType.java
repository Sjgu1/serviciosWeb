/**
 * ServicioPractica1_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.example.www.servicioPractica1;

public interface ServicioPractica1_PortType extends java.rmi.Remote {
    public boolean validarNIF(java.lang.String nif, java.lang.String soapKey) throws java.rmi.RemoteException;
    public void validarIBAN(java.lang.String iban, java.lang.String soapKey, javax.xml.rpc.holders.BooleanHolder existe, javax.xml.rpc.holders.StringHolder error) throws java.rmi.RemoteException;
    public void consultaCodigoPostal(java.lang.String cp, java.lang.String soapKey, javax.xml.rpc.holders.StringHolder codigoPostal, javax.xml.rpc.holders.StringHolder poblacion, javax.xml.rpc.holders.StringHolder provincia) throws java.rmi.RemoteException;
    public void generarPresupuesto(java.util.Date fechaPresupuesto, java.lang.String soapKey, java.lang.String referenciaProducto, int cantidadProducto, int idCliente, javax.xml.rpc.holders.IntHolder idPresupuesto, javax.xml.rpc.holders.BooleanHolder presupuestoGeneradoCorrectamente) throws java.rmi.RemoteException;
}
