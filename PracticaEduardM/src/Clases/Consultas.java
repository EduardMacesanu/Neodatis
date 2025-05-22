package Clases;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;

public class Consultas {
	
	   public static void centrosProvincia(ODB odb, Scanner sc) {
		   
	   	System.out.print("Introduce una provincia: ");
	       String provincia = sc.nextLine();
	       
	       //Encontramos la provincia
	       IQuery queryP = new CriteriaQuery(Gimnasio.class, Where.equal("provincia", provincia));
	       queryP.orderByAsc("localidad");
	       
	       
	       Objects<Gimnasio> gimnasios = odb.getObjects(queryP);
	       while (gimnasios.hasNext()) {
	    	   //Mostrar los resultados de cada uno
	           Gimnasio gimnasio = gimnasios.next();
	           System.out.printf("%s, %s, %s\n",
	        		   gimnasio.getNombre(),
	        		   gimnasio.getResponsable(),
	        		   gimnasio.getLocalidad());
	       }
	   }
	   
	   

	   public static void mediaDificultad(ODB odb, Scanner sc) {
		   
	   	System.out.print("Introduce el nivel de dificultad: ");
	       String dificultad = sc.nextLine();
	       
	       //Sacamos la media de la dificultad
	       Values valores = odb.getValues(new ValuesCriteriaQuery(Clase.class, Where.equal("dificultad", dificultad)).avg("Durac_min"));
	       
	       ObjectValues ov = valores.nextValues();
	       BigDecimal mediaDif = (BigDecimal) ov.getByAlias("Durac_min");
	       
	       //Mostrar los resultados de cada uno con 1 decimal
	       System.out.printf("Duración media : %.1f minutos\n",
	    		   mediaDif.doubleValue()); 
	   }
	   
	   

	   public static void monitoresMujeres(ODB odb) {
		   
	   	IQuery queryM = new CriteriaQuery(Monitor.class, new And().add(Where.equal("sexo", 'M')).add(Where.not(Where.like("nombre", "%a"))));
	   	
	       Objects<Monitor> monitores = odb.getObjects(queryM);
	       
	       while (monitores.hasNext()) {
	           Monitor monitor = monitores.next();
	           //Mostrar los resultados de cada uno
	           System.out.printf("%d, %s, %s, %s, %c, %d\n",
	        		   monitor.getCodMonitor(),
	        		   monitor.getNombre(), 
	        		   monitor.getApellido(),
	        		   monitor.getFechaNac(), 
	        		   monitor.getSexo(), 
	        		   monitor.getCodCentro());
	       }
	   }

	   
	   
	   public static void sumaClases(ODB odb) {
		   
	       Values sumaClase = odb.getValues(new ValuesCriteriaQuery(Clase.class).sum("duracMin"));
	       
	       if (sumaClase.hasNext()) {
	    	   
	           ObjectValues valores = sumaClase.nextValues();
	           BigDecimal suma = (BigDecimal) valores.getByIndex(0);
	           
	           //Mostrar el resultado y ponemos 1 decimal
	           System.out.printf("La duración de las clases es : %.1f minutos\n", suma.doubleValue());
	       }
	   }


	   public static void centrosEnProvincia(ODB odb) {

	       IQuery queryP = new CriteriaQuery(Gimnasio.class);
	       Objects<Gimnasio> gimnasios = odb.getObjects(queryP);
	       
	       //Lista para almacenar
	       List<String> provincias = new ArrayList<>();
	       
	       while (gimnasios.hasNext()) {
	    	   
	           Gimnasio gimnasio = gimnasios.next();
	           String provincia = gimnasio.getProvincia();
	           
	           if (!provincias.contains(provincia)) {
	        	   //Contador que va sumando la cantidad de provincias
	               int contador = 0;
	               for (Gimnasio g : gimnasios) {
	                   if (g.getProvincia().equals(provincia)) {
	                	   //Suma si las provincias coinciden
	                       contador++; 
	                   }
	               }
	               
	               //Mostrar los resultados de cada uno
	               System.out.printf("Provincia: %s / Número de Gimnasios: %d\n", provincia, contador);
	               provincias.add(provincia);
	           }
	       }
	   }
	   
	   
	   
	 public static void menuPrincipal () {
		 
		 //Abrir el ODB
		 ODB odb = ODBFactory.open("neodatis.db");
	     Scanner sc = new Scanner(System.in);
	      
	     //Bucle infinito
		 while (true) {
	           System.out.println("==== MENU ====");
	           System.out.println("1. Mostrar nombre del centro, nombre del responsable y localidad por provincia");
	           System.out.println("2. Mostrar la media de duración de clases de un nivel de dificultad");
	           System.out.println("3. Mostrar los monitores mujeres y que su nombre no acaba en A");
	           System.out.println("4. Mostrar la suma de la duración de las clases por monitor");
	           System.out.println("5. Mostrar la provincia y el número de centros agrupados por provincia");
	           System.out.println("6. Salir");
	           System.out.print("Ingrese el número de la opción: ");
	           int opcion = sc.nextInt();
	           sc.nextLine();
	           switch (opcion) {
	               case 1:
	                   Consultas.centrosProvincia(odb, sc);
	                   break;
	               case 2:
	            	   Consultas.mediaDificultad(odb, sc);
	                   break;
	               case 3:
	            	   Consultas.monitoresMujeres(odb);
	                   break;
	               case 4:
	            	   Consultas.sumaClases(odb);
	                   break;
	               case 5:
	            	   Consultas.centrosEnProvincia(odb);
	                   break;
	               case 6:
	                   odb.close();
	                   System.out.println("¡ Fin del programa !");
	                   return;
	               default:
	                   System.out.println("Opción incorrecta, inténtalo otra vez");
	           }
	       }
		 
	 }
}
