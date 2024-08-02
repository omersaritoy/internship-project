

package com.example.Example.mernis;
//------------------------------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 9.0.1.0
//
// Created by Quasar Development 
//
// This class has been generated for test purposes only and cannot be used in any commercial project.
// To use it in commercial project, you need to generate this class again with Premium account.
// Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.
//
// Licence: 656479C1301C6A31985379BCE8FAA6A9573D38C243E03EA4090CFDF98306C3CA6A9E50259376755F8643E2D1E2350132170CE870D5FABB1D765BA81123806EDE
//------------------------------------------------------------------------
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.*;
import java.io.InputStream;

enum HWGSoapVersion {
    v1_0,
    v1_1,
    v1_2
}

interface HWGISerializableObject
{
    void loadWithXml(org.w3c.dom.Element __node, HWGRequestResultHandler __handler);
    void serialize(org.w3c.dom.Element __node,HWGRequestResultHandler __handler);
}

interface HWGIReferenceObject
{
}

public class HWGRequestResultHandler
{
    private HWGSoapVersion _version=HWGSoapVersion.v1_1;
    
    public HWGSoapVersion getVersion(){
        return _version;
    }
    private String soapNS = "";
    private HashMap<Object, String> reverseReferencesTable =new HashMap<Object, String>();
    private HashMap<String, Object> referencesTable = new HashMap<String, Object>();
    private org.w3c.dom.Element body=null;
    private org.w3c.dom.Element header=null;

    private org.w3c.dom.Element outputBody=null;
    private org.w3c.dom.Element outputHeader=null;
    
    private HashMap<String, String> namespaces = new HashMap<String, String>();

    private static HWGDateTimeParser dateTimeConverter=new HWGStandardDateTimeParser();
    private static HashMap< String, Class> classNames = new HashMap< String, Class>();
    private static HashMap< Class, String> reverseClassNames = new HashMap< Class, String>();
    private static HashMap< String, String> elementNames = new HashMap< String, String>();

    public HWGRequestResultHandler(HWGSoapVersion version)
    {
        _version=version;
    }



    public String getSoapNamespace()
    {
        return soapNS;
    }

    public org.w3c.dom.Element getBody() {
        return body;
    }

    public void setBody(org.w3c.dom.Element body) {
        this.body = body;
    }

    public org.w3c.dom.Element getHeader() {
        return header;
    }

    public void setHeader(org.w3c.dom.Element header) {
        this.header = header;
    }

    public org.w3c.dom.Element getOutputBody() {
        return outputBody;
    }

    public void setOutputBody(org.w3c.dom.Element outputBody) {
        this.outputBody = outputBody;
    }

    public org.w3c.dom.Element getOutputHeader() {
        return outputHeader;
    }

    public void setOutputHeader(org.w3c.dom.Element outputHeader) {
        this.outputHeader = outputHeader;
    }

    public static void setDateTimeParser(HWGDateTimeParser converter)
    {
        if(converter==null)
        {
            converter = new HWGStandardDateTimeParser();
        }
        dateTimeConverter=converter;
    }

    public static HWGDateTimeParser getDateTimeParser()
    {
        return dateTimeConverter;
    }

    public org.w3c.dom.Attr addAttribute(org.w3c.dom.Element element, String namespace,String name, String value)
    {
        String prefix=ensureNamespace(namespace,element.getOwnerDocument(),null);
        String attrName=name;
        if(!HWGHelper.isEmpty(prefix))
        {
            attrName=prefix+":"+name;
        }
        org.w3c.dom.Attr attr=element.getOwnerDocument().createAttributeNS(namespace,attrName);
        attr.setValue(value);
        element.setAttributeNodeNS(attr);
        return attr;
    }

    public org.w3c.dom.Document createEnvelopeXml() throws ParserConfigurationException
    {
        soapNS = _version == HWGSoapVersion.v1_2 ? "http://www.w3.org/2003/05/soap-envelope" : "http://schemas.xmlsoap.org/soap/envelope/";
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
        org.w3c.dom.Document xml =docBuilder.newDocument();
       
        org.w3c.dom.Element envelope=xml.createElementNS(soapNS,"soap:Envelope");
        header=xml.createElementNS(soapNS,"soap:Header");
        body =xml.createElementNS(soapNS,"soap:Body");
        xml.appendChild(envelope);
        envelope.appendChild(header);
        envelope.appendChild(body);

        ensureNamespace(soapNS,xml,"soap");
        ensureNamespace(HWGHelper.MS_SERIALIZATION_NS,xml,"ms");
        ensureNamespace(HWGHelper.XSI,xml,"xsi");
        return xml;
    }
    
    protected void finishEnvelopeXml(org.w3c.dom.Document doc) {
            
    }

    public void addWSAddressingHeaders(String action, String replyTo,String to,List< String>  referenceParameters)
    {
        org.w3c.dom.Document xml=header.getOwnerDocument();
        org.w3c.dom.Element wsaddressingElement=writeElement("http://www.w3.org/2005/08/addressing","Action",xml );
        header.appendChild(wsaddressingElement);
        wsaddressingElement.setTextContent(action);
        addAttribute(wsaddressingElement,soapNS,"mustUnderstand",  "1");
        wsaddressingElement=writeElement( "http://www.w3.org/2005/08/addressing","MessageID",xml);
        header.appendChild(wsaddressingElement);
        wsaddressingElement.setTextContent("urn:uuid:" + UUID.randomUUID());
        wsaddressingElement=writeElement( "http://www.w3.org/2005/08/addressing","ReplyTo", xml);

        header.appendChild(wsaddressingElement);
        org.w3c.dom.Element innerWsaddressingElement=writeElement("http://www.w3.org/2005/08/addressing","Address", xml);
        innerWsaddressingElement.setTextContent(replyTo);
        wsaddressingElement.appendChild(innerWsaddressingElement);

        wsaddressingElement=writeElement( "http://www.w3.org/2005/08/addressing","To", xml);
        header.appendChild(wsaddressingElement);
        wsaddressingElement.setTextContent(to);
        addAttribute( wsaddressingElement,soapNS,"mustUnderstand",  "1");
        
        for (String param : referenceParameters) 
        {
            try {
                org.w3c.dom.Document root = HWGHelper.loadXMLFromString(param);
                org.w3c.dom.Element element=(org.w3c.dom.Element)header.getOwnerDocument().adoptNode(root.getDocumentElement());
                header.appendChild(element);
                addAttribute(element, "http://www.w3.org/2005/08/addressing","IsReferenceParameter",  "true");
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }
    }
    
    public void setAnyTypeValue(Object item, org.w3c.dom.Element propertyElement)
    {
        if (item instanceof HWGISerializableObject)
        {
            HWGISerializableObject obj=(HWGISerializableObject)item;
            obj.serialize(propertyElement ,this);
            return;
        }
        String prefix=ensureNamespace( "http://www.w3.org/2001/XMLSchema",propertyElement.getOwnerDocument(),null);
        if(item instanceof String)
        {
            addAttribute(propertyElement,"http://www.w3.org/2001/XMLSchema-instance","type",  prefix+":string");
        }
        else if(item instanceof Boolean)
        {
            addAttribute(propertyElement,"http://www.w3.org/2001/XMLSchema-instance","type",  prefix+":boolean");
        }
        else if (item instanceof Integer)
        {
            addAttribute(propertyElement,"http://www.w3.org/2001/XMLSchema-instance","type",  prefix+":int");
        }
        else if (item instanceof Double)
        {
            addAttribute(propertyElement,"http://www.w3.org/2001/XMLSchema-instance","type",  prefix+":double");
        }
        else if (item instanceof Float)
        {
            addAttribute(propertyElement,"http://www.w3.org/2001/XMLSchema-instance","type",  prefix+":float");
        }
        else if (item instanceof Long)
        {
            addAttribute(propertyElement,"http://www.w3.org/2001/XMLSchema-instance","type",  prefix+":long");
        }
        else if (item instanceof Short)
        {
            addAttribute(propertyElement,"http://www.w3.org/2001/XMLSchema-instance","type",  prefix+":short");
        }
        else if (item instanceof java.math.BigDecimal)
        {
            addAttribute(propertyElement,"http://www.w3.org/2001/XMLSchema-instance","type",  prefix+":decimal");
        }
        propertyElement.setTextContent(item.toString());
    }

    public Object getAnyTypeValue(org.w3c.dom.Element node)
    {
        String typeAttr=node.getAttributeNS(HWGHelper.XSI,"type");
        if (!HWGHelper.isEmpty(typeAttr))
        {
            String[] splitString=typeAttr.split(":");
            if (splitString.length==2)
            {
                String namespace=node.lookupNamespaceURI( splitString[0]);
                if (namespace.equals("http://www.w3.org/2001/XMLSchema"))
                {
                    String value=splitString[1];
                    if( value.equals("byte"))
                    {
                        return Byte.valueOf(node.getTextContent());
                    }
                    if( value.equals("unsignedByte"))
                    {
                        return Integer.valueOf(node.getTextContent());
                    }
                    if( value.equals("positiveInteger"))
                    {
                        return Integer.valueOf(node.getTextContent());
                    }
                    if( value.equals( "double"))
                    {
                        return Double.valueOf(node.getTextContent());
                    }
                    if( value.equals("float"))
                    {
                        return Float.valueOf(node.getTextContent());
                    }
                    if( value.equals("long"))
                    {
                        return Long.valueOf(node.getTextContent());
                    }
                    if( value.equals("unsignedLong"))
                    {
                        return Long.valueOf(node.getTextContent());
                    }
                    if( value.equals("int") || value.equals("integer") || value.equals("negativeInteger") || value.equals("nonNegativeInteger"))
                    {
                        return Integer.valueOf(node.getTextContent());
                    }
                    if( value.equals("unsignedInt"))
                    {
                        return Long.valueOf(node.getTextContent());
                    }
                    if( value.equals("decimal"))
                    {
                        return new java.math.BigDecimal(node.getTextContent());
                    }
                    if( value.equals("short"))
                    {
                        return Short.valueOf(node.getTextContent());
                    }
                    if( value.equals("unsignedShort"))
                    {
                        return Integer.valueOf(node.getTextContent());
                    }
                    else if (value.equals("bool"))
                    {
                        return node.getTextContent().equalsIgnoreCase("true");
                    }
                    else
                    {
                        return  node.getTextContent();
                    }
                }
                else
                {
                    String typeName=typeAttr;
                    if(splitString.length==2)
                    {
                        typeName= splitString[1];
                    }
                    if (namespace != null)
                    {
                        String classType=namespace+"^^" + typeName;
                        Class temp=classNames.get(classType);
                        if (temp != null)
                        {
                            Object obj = createInstance(node, temp);
                            return obj;
                        }
                    }
                }
            }
        }
        return node;
    }
    
    private String ensureNamespace(String namespace,org.w3c.dom.Document doc,String defaultPrefix)
    {
        String prefix="";
        if(!HWGHelper.isEmpty(namespace))
        {
            if(namespaces.containsKey(namespace))
            {
                prefix=namespaces.get(namespace);
            }
            else
            {
                prefix=defaultPrefix!=null?defaultPrefix:"ns"+namespaces.size();
                namespaces.put(namespace,prefix);
                doc.getDocumentElement().setAttribute("xmlns:"+prefix,namespace);
            }
        }
        return prefix;
    }

    public org.w3c.dom.Element writeElement(String namespace, String name, org.w3c.dom.Document doc)
    {
        String prefix=ensureNamespace(namespace,doc,null);
        org.w3c.dom.Element element= doc.createElementNS(namespace,name);
        if(!HWGHelper.isEmpty(prefix))
        {
            element.setPrefix(prefix);
        }
        return element;
    }

    public org.w3c.dom.Element writeElementWithType(Object obj, Class type, String name,String namespace, org.w3c.dom.Element parent, boolean skipNullProperty)
    {
        if (obj == null && skipNullProperty)
        {
            return null;
        }
        org.w3c.dom.Element propertyElement=writeElement(namespace,name,parent.getOwnerDocument());
        parent.appendChild(propertyElement);

        if (obj==null)
        {
            propertyElement.setAttributeNS(HWGHelper.XSI,"xsi:nil","true");
            return null;
        }

        if  (obj instanceof HWGIReferenceObject)
        {
            String idStr=reverseReferencesTable.get(obj);
            if (idStr != null)
            {
                propertyElement.setAttributeNS(HWGHelper.MS_SERIALIZATION_NS,"ms:Ref",idStr);
                return null;
            }

            idStr="i"+ (reverseReferencesTable.size() + 1);
            propertyElement.setAttributeNS(HWGHelper.MS_SERIALIZATION_NS,"ms:Id",idStr);
            reverseReferencesTable.put(obj,idStr);
        }

        Class currentType=obj.getClass();
        if (currentType != type)
        {
            String xmlType=reverseClassNames.get(currentType);
            if (xmlType != null)
            {
                String[] splitType = xmlType.split("\\^\\^");
                String fullname=getXmlFullName(propertyElement, splitType[0],splitType[1]);
                propertyElement.setAttributeNS(HWGHelper.XSI,"xsi:type",fullname);
            }
        }

        return propertyElement;
    }

    public org.w3c.dom.Document setResponse(HWGResponseData response) throws Exception
    {
        if(HWGHelper.isEmpty(response.getBody()))
        {
            throw new Exception("No body content");
        }
        org.w3c.dom.Document parsedXml = HWGHelper.loadXMLFromString(response.getBody());

        if(parsedXml.getDocumentElement()==null)
        {
            throw new Exception(response.getBody());
        }

        outputBody= HWGHelper.getElement(parsedXml.getDocumentElement(),soapNS,"Body");
        outputHeader=HWGHelper.getElement(parsedXml.getDocumentElement(),soapNS,"Header");

        if(outputBody==null)
        {
            throw new Exception(response.getBody());
        }

        org.w3c.dom.Element fault=HWGHelper.getElement(outputBody, soapNS,"Fault");
        if (fault != null)
        {
            org.w3c.dom.Element faultString=HWGHelper.getNodeByLocalName(fault, "faultstring",0);
            if (faultString == null)
            {
                org.w3c.dom.Element reasonNode=HWGHelper.getNodeByLocalName(fault, "Reason",0);
                if( reasonNode != null)
                {
                    faultString=HWGHelper.getNodeByLocalName(reasonNode, "Text",0);
                }
            }

            org.w3c.dom.Element faultDetail=HWGHelper.getNodeByLocalName( fault, "detail",0);
            if (faultDetail == null)
            {
                faultDetail=HWGHelper.getNodeByLocalName(fault, "Detail",0);
            }

            if (faultDetail != null)
            {
                org.w3c.dom.Element faultClass=HWGHelper.getFirstChildElement(faultDetail);
                if (faultClass != null)
                {
                    String typeName=faultClass.getLocalName();
                    String namespaceNode=parsedXml.lookupNamespaceURI(typeName);
                    String namespace;
                    if(namespaceNode==null)
                    {
                        namespace=faultClass.getNamespaceURI();
                    }
                    else
                    {
                        namespace=namespaceNode;
                    }
                    String classType=namespace+"^^"+typeName;
                    String tempKey=elementNames.get(classType);
                    if (tempKey != null)
                    {
                        classType=tempKey;
                    }
                    Class temp=classNames.get(classType);
                    if (temp != null)
                    {
                        Object faultObj=createInstance(faultClass, temp);
                        Exception faultException;
                        if(!(faultObj instanceof Exception))
                        {
                            faultException = new HWGSoapException(faultObj);
                        }
                        else
                        {
                            faultException=(Exception) faultObj;
                        }
                        throw faultException;
                    }
                }
            }
            throw new HWGSoapException(faultString.getTextContent(),faultDetail);
        }

        return parsedXml;
    }

    public Object createObject(org.w3c.dom.Element node, Class type)
    {
        if(node==null)
        {
            return null;
        }
        Class objType=type;
        org.w3c.dom.Element element=node;

        String refAttr=node.getAttributeNS(HWGHelper.MS_SERIALIZATION_NS,"Ref");
        if (!HWGHelper.isEmpty(refAttr))
        {
            return referencesTable.get(refAttr);
        }

        String nilAttr=node.getAttributeNS(HWGHelper.XSI,"nil");
        if (nilAttr != null && HWGHelper.toBoolFromString( nilAttr))
        {
            return null;
        }

        String typeAttr=node.getAttributeNS(HWGHelper.XSI,"type");
        if (!HWGHelper.isEmpty(typeAttr))
        {
            String[] splitString=typeAttr.split(":");
            String namespace = null;
            if(splitString.length==2)
            {
                namespace=node.lookupNamespaceURI(splitString[0]);
            }
            if(namespace==null)
            {
                namespace="";
            }
            String typeName=typeAttr;
            if(splitString.length == 2)
            {
                typeName=splitString[1];
            }
            String classType=namespace+"^^"+typeName;
            if(classNames.containsKey(classType))
            {
                objType=classNames.get(classType);
            }
        }

        String hrefAttr = node.getAttribute("href");
        if (HWGHelper.isEmpty(hrefAttr))
        {
            hrefAttr = node.getAttribute("ref");
        }

        if (!HWGHelper.isEmpty(hrefAttr))
        {
            String hrefId=hrefAttr.substring(1);
            org.w3c.dom.Element tempNode=node.getOwnerDocument().getElementById(hrefId);

            if (tempNode!=null)
            {
                element=tempNode;
            }
        }
        
        if(objType!=null)
        {
            Object obj=createInstance(element,objType);
            return obj;
        }
        return null;
    }
    
    private Object createInstance(org.w3c.dom.Element node, Class objType)
    {
        try
        {
            HWGISerializableObject obj = (HWGISerializableObject)objType.newInstance();
            String idAttr=node.getAttributeNS(HWGHelper.MS_SERIALIZATION_NS,"Id");
            if (!HWGHelper.isEmpty(idAttr))
            {
                referencesTable.put(idAttr,obj);
            }
            obj.loadWithXml(node,this);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String getXmlFullName(org.w3c.dom.Element element,String uri, String name)
    {
        String prefix=ensureNamespace(uri,element.getOwnerDocument(),null);
        String fullname=name;
        if (!HWGHelper.isEmpty(prefix))
        {
            fullname=prefix+":"+name;
        }
        return fullname;
    }

    public boolean hasAttribute(org.w3c.dom.Element node,String namespace,String name)
    {
        String ns=namespace;
        if(HWGHelper.isEmpty(namespace))
        {
            ns=null;
        }
        return node.hasAttributeNS(ns,name);
    }

    public org.w3c.dom.Attr getAttribute(org.w3c.dom.Element node,String namespace,String name)
    {
        String ns=namespace;
        if(HWGHelper.isEmpty(namespace))
        {
            ns=null;
        }
        return node.getAttributeNodeNS(ns,name);
    }
}