package org.example.www.servicioPractica1;

public class ServicioPractica1Proxy implements org.example.www.servicioPractica1.ServicioPractica1_PortType {
  private String _endpoint = null;
  private org.example.www.servicioPractica1.ServicioPractica1_PortType servicioPractica1_PortType = null;
  
  public ServicioPractica1Proxy() {
    _initServicioPractica1Proxy();
  }
  
  public ServicioPractica1Proxy(String endpoint) {
    _endpoint = endpoint;
    _initServicioPractica1Proxy();
  }
  
  private void _initServicioPractica1Proxy() {
    try {
      servicioPractica1_PortType = (new org.example.www.servicioPractica1.ServicioPractica1_ServiceLocator()).getservicioPractica1SOAP();
      if (servicioPractica1_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicioPractica1_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicioPractica1_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicioPractica1_PortType != null)
      ((javax.xml.rpc.Stub)servicioPractica1_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.example.www.servicioPractica1.ServicioPractica1_PortType getServicioPractica1_PortType() {
    if (servicioPractica1_PortType == null)
      _initServicioPractica1Proxy();
    return servicioPractica1_PortType;
  }
  
  public boolean validarNIF(java.lang.String nif, java.lang.String soapKey) throws java.rmi.RemoteException{
    if (servicioPractica1_PortType == null)
      _initServicioPractica1Proxy();
    return servicioPractica1_PortType.validarNIF(nif, soapKey);
  }
  
  public void validarIBAN(java.lang.String iban, java.lang.String soapKey, javax.xml.rpc.holders.BooleanHolder existe, javax.xml.rpc.holders.StringHolder error) throws java.rmi.RemoteException{
    if (servicioPractica1_PortType == null)
      _initServicioPractica1Proxy();
    servicioPractica1_PortType.validarIBAN(iban, soapKey, existe, error);
  }
  
  public void consultaCodigoPostal(java.lang.String cp, java.lang.String soapKey, javax.xml.rpc.holders.StringHolder codigoPostal, javax.xml.rpc.holders.StringHolder poblacion, javax.xml.rpc.holders.StringHolder provincia) throws java.rmi.RemoteException{
    if (servicioPractica1_PortType == null)
      _initServicioPractica1Proxy();
    servicioPractica1_PortType.consultaCodigoPostal(cp, soapKey, codigoPostal, poblacion, provincia);
  }
  
  public void generarPresupuesto(java.util.Date fechaPresupuesto, java.lang.String soapKey, java.lang.String referenciaProducto, int cantidadProducto, int idCliente, javax.xml.rpc.holders.IntHolder idPresupuesto, javax.xml.rpc.holders.BooleanHolder presupuestoGeneradoCorrectamente) throws java.rmi.RemoteException{
    if (servicioPractica1_PortType == null)
      _initServicioPractica1Proxy();
    servicioPractica1_PortType.generarPresupuesto(fechaPresupuesto, soapKey, referenciaProducto, cantidadProducto, idCliente, idPresupuesto, presupuestoGeneradoCorrectamente);
  }
  
  
}