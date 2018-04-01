/**
 * WS_compraRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mtis.bpel.invoke;

public class WS_compraRequest  implements java.io.Serializable {
    private java.lang.String referenciaProducto;

    private int numeroUnidades;

    public WS_compraRequest() {
    }

    public WS_compraRequest(
           java.lang.String referenciaProducto,
           int numeroUnidades) {
           this.referenciaProducto = referenciaProducto;
           this.numeroUnidades = numeroUnidades;
    }


    /**
     * Gets the referenciaProducto value for this WS_compraRequest.
     * 
     * @return referenciaProducto
     */
    public java.lang.String getReferenciaProducto() {
        return referenciaProducto;
    }


    /**
     * Sets the referenciaProducto value for this WS_compraRequest.
     * 
     * @param referenciaProducto
     */
    public void setReferenciaProducto(java.lang.String referenciaProducto) {
        this.referenciaProducto = referenciaProducto;
    }


    /**
     * Gets the numeroUnidades value for this WS_compraRequest.
     * 
     * @return numeroUnidades
     */
    public int getNumeroUnidades() {
        return numeroUnidades;
    }


    /**
     * Sets the numeroUnidades value for this WS_compraRequest.
     * 
     * @param numeroUnidades
     */
    public void setNumeroUnidades(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WS_compraRequest)) return false;
        WS_compraRequest other = (WS_compraRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.referenciaProducto==null && other.getReferenciaProducto()==null) || 
             (this.referenciaProducto!=null &&
              this.referenciaProducto.equals(other.getReferenciaProducto()))) &&
            this.numeroUnidades == other.getNumeroUnidades();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getReferenciaProducto() != null) {
            _hashCode += getReferenciaProducto().hashCode();
        }
        _hashCode += getNumeroUnidades();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WS_compraRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://invoke.bpel.mtis", ">WS_compraRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenciaProducto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://invoke.bpel.mtis", "referenciaProducto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroUnidades");
        elemField.setXmlName(new javax.xml.namespace.QName("http://invoke.bpel.mtis", "numeroUnidades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
