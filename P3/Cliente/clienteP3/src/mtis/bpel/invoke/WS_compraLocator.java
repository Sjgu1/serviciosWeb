/**
 * WS_compraLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mtis.bpel.invoke;

public class WS_compraLocator extends org.apache.axis.client.Service implements mtis.bpel.invoke.WS_compra {

    public WS_compraLocator() {
    }


    public WS_compraLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WS_compraLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WS_compraPort
    private java.lang.String WS_compraPort_address = "http://localhost:8080/ode/processes/WS_compra.WS_compraPort/";

    public java.lang.String getWS_compraPortAddress() {
        return WS_compraPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WS_compraPortWSDDServiceName = "WS_compraPort";

    public java.lang.String getWS_compraPortWSDDServiceName() {
        return WS_compraPortWSDDServiceName;
    }

    public void setWS_compraPortWSDDServiceName(java.lang.String name) {
        WS_compraPortWSDDServiceName = name;
    }

    public mtis.bpel.invoke.WS_compraPortType getWS_compraPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WS_compraPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWS_compraPort(endpoint);
    }

    public mtis.bpel.invoke.WS_compraPortType getWS_compraPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            mtis.bpel.invoke.WS_compraBindingStub _stub = new mtis.bpel.invoke.WS_compraBindingStub(portAddress, this);
            _stub.setPortName(getWS_compraPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWS_compraPortEndpointAddress(java.lang.String address) {
        WS_compraPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (mtis.bpel.invoke.WS_compraPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                mtis.bpel.invoke.WS_compraBindingStub _stub = new mtis.bpel.invoke.WS_compraBindingStub(new java.net.URL(WS_compraPort_address), this);
                _stub.setPortName(getWS_compraPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WS_compraPort".equals(inputPortName)) {
            return getWS_compraPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://invoke.bpel.mtis", "WS_compra");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://invoke.bpel.mtis", "WS_compraPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WS_compraPort".equals(portName)) {
            setWS_compraPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
