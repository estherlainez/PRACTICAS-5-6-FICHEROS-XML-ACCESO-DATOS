package control;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmpleadosReader extends DefaultHandler {
	public EmpleadosReader() {
		super();
	}
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("Inicio Documento");
		
	}
	
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("Final Documento");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals("empleados")) {
			System.out.printf("Lista de: %s %n", localName);
			
		}else {
			if(localName.equals("empleado")) {
				System.out.print("");
			}else {
				System.out.print(localName+" ");
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(localName.equals("empleado")) {
			System.out.println("=====================");
		}else {
			System.out.println();
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String cadena=new String(ch,start,length);
		cadena.trim();
		cadena.replace("[\t\n]","");
		//System.out.printf(": %s %n",cadena);
		System.out.print(new String (" "+cadena));
	}
	
	

}
