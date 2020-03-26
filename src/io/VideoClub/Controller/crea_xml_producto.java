/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.VideoClub.Controller;

import io.VideoClub.Model.Product;
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

/**
 *
 * @author espin
 */
public class crea_xml_producto {
     public static void leerXML() {
        try {
            File archivo = new File("productos.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            
            document.getDocumentElement().normalize();
            
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            
            NodeList e = document.getElementsByTagName("Producto");
            
            for(int i = 0 ; i < e.getLength() ; i++) {
                Node nodo = e.item(i);
                System.out.println("Elemento: " + nodo.getNodeName());
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    System.out.println("Nombre: " + element.getElementsByTagName("Nombre").item(0).getTextContent());
                    System.out.println("Descripcion: " + element.getElementsByTagName("Descripcion").item(0).getTextContent());
                    System.out.println("Precio: " + element.getElementsByTagName("Precio").item(0).getTextContent());
                    System.out.println("Key: " + element.getElementsByTagName("Key").item(0).getTextContent());
                    System.out.println("Estado: " + element.getElementsByTagName("Estado").item(0).getTextContent());
                    System.out.println("Tipo: " + element.getElementsByTagName("Tipo").item(0).getTextContent());
                    
                    System.out.println("");
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void writeList_into_XML(Set<Product> l, String url) throws TransformerConfigurationException, TransformerException{
         try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build;
            build = dFact.newDocumentBuilder();   
            Document doc = build.newDocument();
            
            Element root = doc.createElement("Repositorio");

            for (Product products : l) {

                Element con = doc.createElement("Producto");                
                
               
                
                Element nombre = doc.createElement("Nombre");
                nombre.appendChild(doc.createTextNode(products.getName()));
                con.appendChild(nombre);
             
                
                Element description = doc.createElement("Descripcion");
                description.appendChild(doc.createTextNode(products.getDescription()));
                con.appendChild(description);
                
                Element prize = doc.createElement("Precio");
                prize.appendChild(doc.createTextNode(String.valueOf(products.getPrize())));
                con.appendChild(prize);
                
                 Element key = doc.createElement("Key");
                key.appendChild(doc.createTextNode(products.getKey()));
                con.appendChild(key);
                
                Element status = doc.createElement("Estado");
                status.appendChild(doc.createTextNode(products.getStatus().toString()));
                con.appendChild(status);
                
                Element type = doc.createElement("Tipo");
                type.appendChild(doc.createTextNode(products.getType().toString()));
                con.appendChild(type);             
                
                

                root.appendChild(con);
            }
            doc.appendChild(root);

            
             Source source = new DOMSource(doc);            
            
            Result result = new StreamResult(new java.io.File("productos.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        
        } catch(ParserConfigurationException e) {
            
        }
    }
    
    
}
