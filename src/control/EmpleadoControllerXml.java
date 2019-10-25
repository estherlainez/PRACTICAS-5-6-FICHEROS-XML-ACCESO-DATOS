package control;
import org.w3c.dom.*;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.xml.parsers.*;
import model.Empleado;
import org.xml.sax.*;


public class EmpleadoControllerXml {
	
	
	private String path;
	File f;
	public EmpleadoControllerXml() {
		path="src/Empleados.xml";
		 f= new File(path);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	
	public boolean insertarEmpleado(Empleado e) {
		File f= new File(path);
		
		try{
		 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document registroEmpleados=null;
				if(f.exists() && f.length()>0) {
			
					registroEmpleados=builder.parse(f);
			
				}else {
			
					DOMImplementation im=builder.getDOMImplementation();
					registroEmpleados = im.createDocument(null, "empleados", null);
					registroEmpleados.setXmlVersion("1.0");
					
				}
				
				Element empleado=registroEmpleados.createElement("empleado");
				registroEmpleados.getDocumentElement().appendChild(empleado);
				
				Element nIF=registroEmpleados.createElement("nIF");
				nIF.appendChild(registroEmpleados.createTextNode(e.getnIF()));
				empleado.appendChild(nIF);
				
				Element nombre=registroEmpleados.createElement("nombre");
				nombre.appendChild(registroEmpleados.createTextNode(e.getNombre()));
				empleado.appendChild(nombre);
				
				Element apellidos=registroEmpleados.createElement("apellidos");
				apellidos.appendChild(registroEmpleados.createTextNode(e.getApellido()));
				empleado.appendChild(apellidos);
				 
				Element salario=registroEmpleados.createElement("salario");
				salario.appendChild(registroEmpleados.createTextNode(String.valueOf(e.getSalario())));
				empleado.appendChild(salario);
				 
				//Creo origen y resultado 
				Source origen=new DOMSource(registroEmpleados);
				Result resultado=new StreamResult(new File(path));
				//Creo una TransformerFactory
				Transformer transformador=TransformerFactory.newInstance().newTransformer();	
				transformador.transform(origen, resultado);
				//Muestro resultado por la salida estandar	
				Result salida=new StreamResult(System.out);
				transformador.transform(origen, salida);
				
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerFactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
		
		
		
		
	}
	
	public boolean modificarEmpleado(Empleado e) {
		File f= new File(path);
		
		try{
		 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document registroEmpleados=registroEmpleados=builder.parse(f);
				
			NodeList empleados=registroEmpleados.getElementsByTagName("empleado");
			
			for(int i=0;i<empleados.getLength();i++) {
				Node em=empleados.item(i);
				
				Element empleado=(Element)em;
					if(empleado.getElementsByTagName("nIF").item(0).getTextContent().equals(e.getnIF())) {
						
					
						registroEmpleados.getDocumentElement().appendChild(empleado);
						
						empleado.getElementsByTagName("nIF").item(0).setTextContent(e.getnIF());
						empleado.getElementsByTagName("nombre").item(0).setTextContent(e.getNombre());
						empleado.getElementsByTagName("apellidos").item(0).setTextContent(e.getApellido());
						empleado.getElementsByTagName("salario").item(0).setTextContent(String.valueOf(e.getSalario()));
						
						
						Source origen=new DOMSource(registroEmpleados);
						Result resultado=new StreamResult(new File(path));
						
						Transformer transformador=TransformerFactory.newInstance().newTransformer();	
						transformador.transform(origen, resultado);
						
						Result salida=new StreamResult(System.out);
						transformador.transform(origen, salida);
						return true;
					}	
			}
				
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerFactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
			
	}
	
	
	public void listaEmpleado() {
	//TAMBIEN SE PUEDE HACER MEDIANTE UN ARRAYLIST
	//public ArrayList<Empleado> listaEmpleados() {
		//ArrayList<Empleado>misEmpleados=new ArrayList<Empleado>();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=dbf.newDocumentBuilder();
			Document registroEmpleados=builder.parse(new File(path));
			registroEmpleados.getDocumentElement().normalize();
			System.out.println("Elemento raiz: "+registroEmpleados.getDocumentElement().getNodeName());
			
			NodeList empleados=registroEmpleados.getElementsByTagName("empleado");
			
			for(int i=0;i<empleados.getLength();i++) {
				Node em=empleados.item(i);
				
				if(em.getNodeType()==Node.ELEMENT_NODE) {
					Element empleado=(Element)em;
				
					System.out.println("NIF "+ empleado.getElementsByTagName("nIF").item(0).getTextContent());
					System.out.println("Nombre "+ empleado.getElementsByTagName("nombre").item(0).getTextContent());
					System.out.println("Apellidos "+ empleado.getElementsByTagName("apellidos").item(0).getTextContent());
					System.out.println("Salario "+ empleado.getElementsByTagName("salario").item(0).getTextContent());
					System.out.println("===================");
					//misEmpleados.add(new Empleado(empleado.getElementsByTagName("nIF").item(0).getTextContent(), 
						//	empleado.getElementsByTagName("nombre").item(0).getTextContent(),
						//	empleado.getElementsByTagName("apellidos").item(0).getTextContent(), 
						//	Double.valueOf(empleado.getElementsByTagName("salario").item(0).getTextContent())));
				}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		
		//return misEmpleados;
		
	}
	
	
	public boolean eliminarEmpleado(Empleado e) {
		File f= new File(path);
		
		try{
		 
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document registroEmpleados=registroEmpleados=builder.parse(f);
				
			NodeList empleados=registroEmpleados.getElementsByTagName("empleado");
			
			for(int i=0;i<empleados.getLength();i++) {
				Node em=empleados.item(i);
				
				Element empleado=(Element)em;
					if(empleado.getElementsByTagName("nIF").item(0).getTextContent().equals(e.getnIF())) {
						
						empleado.getParentNode().removeChild(empleado);
						
						Source origen=new DOMSource(registroEmpleados);
						Result resultado=new StreamResult(new File(path));
						
						Transformer transformador=TransformerFactory.newInstance().newTransformer();	
						transformador.transform(origen, resultado);
						
						Result salida=new StreamResult(System.out);
						transformador.transform(origen, salida);
						return true;
					}
				
			}
				
				
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransformerFactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	
	}
	
	public void listarTodosSax() {
		try {
			XMLReader procesadorxml=XMLReaderFactory.createXMLReader();
			procesadorxml.setContentHandler(new EmpleadosReader());
			InputSource xmlFile= new InputSource(this.path);
			if(f.exists() && f.length()>0)
			procesadorxml.parse(xmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
