/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Controller;

import io.VideoClub.Model.IClient;
import java.io.File;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author espin
 */
public class crear_xml_clientes { 
     
       
    
    public static void leerXML() {
        try {
            File archivo = new File("clientes.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            
            document.getDocumentElement().normalize();
            
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            
            NodeList e = document.getElementsByTagName("Cliente");
            
            for(int i = 0 ; i < e.getLength() ; i++) {
                Node nodo = e.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    System.out.println("ID: " + element.getElementsByTagName("ID").item(0).getTextContent());
                    System.out.println("Nombre: " + element.getElementsByTagName("Nombre").item(0).getTextContent());
                    System.out.println("Telefono: " + element.getElementsByTagName("Telefono").item(0).getTextContent());
                    System.out.println("Fecha: " + element.getElementsByTagName("Fecha").item(0).getTextContent());
                    
                    System.out.println("");
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void writeList_into_XML(Set<IClient> l, String url) throws TransformerConfigurationException, TransformerException{
         try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build;
            build = dFact.newDocumentBuilder();   
            Document doc = build.newDocument();
            
            Element root = doc.createElement("Repositorio");

            for (IClient clients : l) {

                Element con = doc.createElement("Cliente");
               
                
                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(clients.getName()));
                con.appendChild(nombre);
             
                
                Element id = doc.createElement("ID");
                id.appendChild(doc.createTextNode(clients.getID()));
                con.appendChild(id);
                
                Element phone = doc.createElement("Telefono");
                phone.appendChild(doc.createTextNode(clients.getPhone()));
                con.appendChild(phone); 
                
                Element date = doc.createElement("Fecha");
                date.appendChild(doc.createTextNode(clients.getTime().toString()));
                con.appendChild(date);

                root.appendChild(con);
            }
            doc.appendChild(root);

            
             Source source = new DOMSource(doc);            
            
            Result result = new StreamResult(new java.io.File("clientes.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        
        } catch(ParserConfigurationException e) {
            
        }
    }
}
    

    

