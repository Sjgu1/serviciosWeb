
/**
 * ServicioPractica1MessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.3  Built on : Jun 27, 2015 (11:17:49 BST)
 */
        package org.example.www.serviciopractica1;

        /**
        *  ServicioPractica1MessageReceiverInOut message receiver
        */

        public class ServicioPractica1MessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver{


        public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault{

        try {

        // get the implementation class for the Web Service
        Object obj = getTheImplementationObject(msgContext);

        ServicioPractica1Skeleton skel = (ServicioPractica1Skeleton)obj;
        //Out Envelop
        org.apache.axiom.soap.SOAPEnvelope envelope = null;
        //Find the axisOperation that has been set by the Dispatch phase.
        org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext().getAxisOperation();
        if (op == null) {
        throw new org.apache.axis2.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
        }

        java.lang.String methodName;
        if((op.getName() != null) && ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(op.getName().getLocalPart())) != null)){


        

            if("validarIBAN".equals(methodName)){
                
                org.example.www.serviciopractica1.ValidarIBANResponse validarIBANResponse17 = null;
	                        org.example.www.serviciopractica1.ValidarIBAN wrappedParam =
                                                             (org.example.www.serviciopractica1.ValidarIBAN)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.serviciopractica1.ValidarIBAN.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               validarIBANResponse17 =
                                                   
                                                   
                                                         skel.validarIBAN(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), validarIBANResponse17, false, new javax.xml.namespace.QName("http://www.example.org/servicioPractica1/",
                                                    "validarIBAN"));
                                    } else 

            if("generarPresupuesto".equals(methodName)){
                
                org.example.www.serviciopractica1.GenerarPresupuestoResponse generarPresupuestoResponse19 = null;
	                        org.example.www.serviciopractica1.GenerarPresupuesto wrappedParam =
                                                             (org.example.www.serviciopractica1.GenerarPresupuesto)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.serviciopractica1.GenerarPresupuesto.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               generarPresupuestoResponse19 =
                                                   
                                                   
                                                         skel.generarPresupuesto(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), generarPresupuestoResponse19, false, new javax.xml.namespace.QName("http://www.example.org/servicioPractica1/",
                                                    "generarPresupuesto"));
                                    } else 

            if("validarNIF".equals(methodName)){
                
                org.example.www.serviciopractica1.ValidarNIFResponse validarNIFResponse21 = null;
	                        org.example.www.serviciopractica1.ValidarNIF wrappedParam =
                                                             (org.example.www.serviciopractica1.ValidarNIF)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.serviciopractica1.ValidarNIF.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               validarNIFResponse21 =
                                                   
                                                   
                                                         skel.validarNIF(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), validarNIFResponse21, false, new javax.xml.namespace.QName("http://www.example.org/servicioPractica1/",
                                                    "validarNIF"));
                                    } else 

            if("consultaCodigoPostal".equals(methodName)){
                
                org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1 consultaCodigoPostalResponse123 = null;
	                        org.example.www.serviciopractica1.ConsultaCodigoPostal wrappedParam =
                                                             (org.example.www.serviciopractica1.ConsultaCodigoPostal)fromOM(
                                    msgContext.getEnvelope().getBody().getFirstElement(),
                                    org.example.www.serviciopractica1.ConsultaCodigoPostal.class,
                                    getEnvelopeNamespaces(msgContext.getEnvelope()));
                                                
                                               consultaCodigoPostalResponse123 =
                                                   
                                                   
                                                         skel.consultaCodigoPostal(wrappedParam)
                                                    ;
                                            
                                        envelope = toEnvelope(getSOAPFactory(msgContext), consultaCodigoPostalResponse123, false, new javax.xml.namespace.QName("http://www.example.org/servicioPractica1/",
                                                    "consultaCodigoPostal"));
                                    
            } else {
              throw new java.lang.RuntimeException("method not found");
            }
        

        newMsgContext.setEnvelope(envelope);
        }
        }
        catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
        }
        
        //
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.serviciopractica1.ValidarIBAN param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.serviciopractica1.ValidarIBAN.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.serviciopractica1.ValidarIBANResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.serviciopractica1.ValidarIBANResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.serviciopractica1.GenerarPresupuesto param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.serviciopractica1.GenerarPresupuesto.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.serviciopractica1.GenerarPresupuestoResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.serviciopractica1.GenerarPresupuestoResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.serviciopractica1.ValidarNIF param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.serviciopractica1.ValidarNIF.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.serviciopractica1.ValidarNIFResponse param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.serviciopractica1.ValidarNIFResponse.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.serviciopractica1.ConsultaCodigoPostal param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.serviciopractica1.ConsultaCodigoPostal.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
            private  org.apache.axiom.om.OMElement  toOM(org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1 param, boolean optimizeContent)
            throws org.apache.axis2.AxisFault {

            
                        try{
                             return param.getOMElement(org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1.MY_QNAME,
                                          org.apache.axiom.om.OMAbstractFactory.getOMFactory());
                        } catch(org.apache.axis2.databinding.ADBException e){
                            throw org.apache.axis2.AxisFault.makeFault(e);
                        }
                    

            }
        
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.serviciopractica1.ValidarIBANResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.serviciopractica1.ValidarIBANResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.serviciopractica1.ValidarIBANResponse wrapvalidarIBAN(){
                                org.example.www.serviciopractica1.ValidarIBANResponse wrappedElement = new org.example.www.serviciopractica1.ValidarIBANResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.serviciopractica1.GenerarPresupuestoResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.serviciopractica1.GenerarPresupuestoResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.serviciopractica1.GenerarPresupuestoResponse wrapgenerarPresupuesto(){
                                org.example.www.serviciopractica1.GenerarPresupuestoResponse wrappedElement = new org.example.www.serviciopractica1.GenerarPresupuestoResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.serviciopractica1.ValidarNIFResponse param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.serviciopractica1.ValidarNIFResponse.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.serviciopractica1.ValidarNIFResponse wrapvalidarNIF(){
                                org.example.www.serviciopractica1.ValidarNIFResponse wrappedElement = new org.example.www.serviciopractica1.ValidarNIFResponse();
                                return wrappedElement;
                         }
                    
                    private  org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory, org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1 param, boolean optimizeContent, javax.xml.namespace.QName methodQName)
                        throws org.apache.axis2.AxisFault{
                      try{
                          org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
                           
                                    emptyEnvelope.getBody().addChild(param.getOMElement(org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1.MY_QNAME,factory));
                                

                         return emptyEnvelope;
                    } catch(org.apache.axis2.databinding.ADBException e){
                        throw org.apache.axis2.AxisFault.makeFault(e);
                    }
                    }
                    
                         private org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1 wrapconsultaCodigoPostal(){
                                org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1 wrappedElement = new org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1();
                                return wrappedElement;
                         }
                    


        /**
        *  get the default envelope
        */
        private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory){
        return factory.getDefaultEnvelope();
        }


        private  java.lang.Object fromOM(
        org.apache.axiom.om.OMElement param,
        java.lang.Class type,
        java.util.Map extraNamespaces) throws org.apache.axis2.AxisFault{

        try {
        
                if (org.example.www.serviciopractica1.ConsultaCodigoPostal.class.equals(type)){
                
                        return org.example.www.serviciopractica1.ConsultaCodigoPostal.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1.class.equals(type)){
                
                        return org.example.www.serviciopractica1.ConsultaCodigoPostalResponse1.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.serviciopractica1.GenerarPresupuesto.class.equals(type)){
                
                        return org.example.www.serviciopractica1.GenerarPresupuesto.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.serviciopractica1.GenerarPresupuestoResponse.class.equals(type)){
                
                        return org.example.www.serviciopractica1.GenerarPresupuestoResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.serviciopractica1.ValidarIBAN.class.equals(type)){
                
                        return org.example.www.serviciopractica1.ValidarIBAN.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.serviciopractica1.ValidarIBANResponse.class.equals(type)){
                
                        return org.example.www.serviciopractica1.ValidarIBANResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.serviciopractica1.ValidarNIF.class.equals(type)){
                
                        return org.example.www.serviciopractica1.ValidarNIF.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
                if (org.example.www.serviciopractica1.ValidarNIFResponse.class.equals(type)){
                
                        return org.example.www.serviciopractica1.ValidarNIFResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
                    

                }
            
        } catch (java.lang.Exception e) {
        throw org.apache.axis2.AxisFault.makeFault(e);
        }
           return null;
        }



    

        /**
        *  A utility method that copies the namepaces from the SOAPEnvelope
        */
        private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env){
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
        org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator.next();
        returnMap.put(ns.getPrefix(),ns.getNamespaceURI());
        }
        return returnMap;
        }

        private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();
        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }

        }//end of class
    