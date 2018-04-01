package mtis.bpel.invoke;

public class WS_compraPortTypeProxy implements mtis.bpel.invoke.WS_compraPortType {
  private String _endpoint = null;
  private mtis.bpel.invoke.WS_compraPortType wS_compraPortType = null;
  
  public WS_compraPortTypeProxy() {
    _initWS_compraPortTypeProxy();
  }
  
  public WS_compraPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initWS_compraPortTypeProxy();
  }
  
  private void _initWS_compraPortTypeProxy() {
    try {
      wS_compraPortType = (new mtis.bpel.invoke.WS_compraLocator()).getWS_compraPort();
      if (wS_compraPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wS_compraPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wS_compraPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wS_compraPortType != null)
      ((javax.xml.rpc.Stub)wS_compraPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public mtis.bpel.invoke.WS_compraPortType getWS_compraPortType() {
    if (wS_compraPortType == null)
      _initWS_compraPortTypeProxy();
    return wS_compraPortType;
  }
  
  public mtis.bpel.invoke.WS_compraResponse process(mtis.bpel.invoke.WS_compraRequest payload) throws java.rmi.RemoteException{
    if (wS_compraPortType == null)
      _initWS_compraPortTypeProxy();
    return wS_compraPortType.process(payload);
  }
  
  
}