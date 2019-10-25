package vista;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import control.EmpleadoControllerXml;
import control.EmpleadosReader;
import model.Empleado;

public class Vista {

	public static void main(String[] args) {
		Scanner stdin= new Scanner(System.in);
		int op=0;
		do {
			System.out.println("1. Insertar");
			System.out.println("2. Modificar");
			System.out.println("3. Listar Todos");
			System.out.println("4. Borrar");
			System.out.println("5. Leer con Sax");
			op=stdin.nextInt();
		
		
		switch (op) {
		case 1:
			//Creariamos una funcion
			stdin.nextLine();
			Empleado e= new Empleado();
			System.out.println("Introduce NIF");
			e.setnIF(stdin.nextLine());
			System.out.println("Introduce Nombre");
			e.setNombre(stdin.nextLine());
			System.out.println("Introduce Apellidos");
			e.setApellido(stdin.nextLine());
			System.out.println("Introduce Salario");
			e.setSalario(stdin.nextDouble());
			if(new EmpleadoControllerXml().insertarEmpleado(e)) {
				System.out.println("Empleado insertado");
			}else {
				System.out.println("Empleado no insertado");
			}
			stdin.nextLine();
			break;
		
			
		case 2:
			stdin.nextLine();
			e= new Empleado();
			System.out.println("Introduce NIF");
			e.setnIF(stdin.nextLine());
			System.out.println("Introduce Nombre");
			e.setNombre(stdin.nextLine());
			System.out.println("Introduce Apellidos");
			e.setApellido(stdin.nextLine());
			System.out.println("Introduce Salario");
			e.setSalario(stdin.nextDouble());
			if(new EmpleadoControllerXml().modificarEmpleado(e)) {
				System.out.println("Empleado insertado");
			}else {
				System.out.println("Empleado no insertado");
			}
			stdin.nextLine();
			break;
			
		case 3:
			
			EmpleadoControllerXml empl=new EmpleadoControllerXml();
			empl.listaEmpleado();
			break;

		case 4:
			stdin.nextLine();
			e= new Empleado();
			System.out.println("Introduce NIF");
			e.setnIF(stdin.nextLine());
			EmpleadoControllerXml emp=new EmpleadoControllerXml();
			if(emp.eliminarEmpleado(e)) {
				System.out.println("Empleado borrado");
			}else {
				System.out.println("Empleado no borrado");
			}
			//stdin.nextLine();
			break;
		
		case 5:
			EmpleadoControllerXml em=new EmpleadoControllerXml();
			em.listarTodosSax();
			
			break;

		case 6:
			System.out.println("Buen dia!!");
			break;
		default:
			
		}
		
		}while(op!=6);
	}

}
