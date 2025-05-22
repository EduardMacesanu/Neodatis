package Clases;

import java.math.BigDecimal;
import java.sql.*;
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
import Clases.Clase;
import Clases.Gimnasio;
import Clases.MigrarNeodatis;
import Clases.Monitor;

public class Main {
   public static void main(String[] args) {
   	
       String url = "jdbc:mysql://localhost/BDrel_clases";
       String user = "root";
       String contrasenia = "12345678";
      
       try  {
       	
       	Connection conexion = DriverManager.getConnection(url, user, contrasenia);
       	ODB odb = ODBFactory.open("FitnessClub.neo");
       	
       	System.out.println("Migrando los datos...");
       	
       	MigrarNeodatis.migrarGimnasios(conexion, odb);
       	MigrarNeodatis.migrarMonitores(conexion, odb);
       	MigrarNeodatis.migrarClases(conexion, odb);
       	
        System.out.println("Datos migrados con éxito !");
        
        conexion.close();
        odb.close();
          
       } catch (SQLException e) {
    	  System.out.println("Error SQL : " + e.getMessage());
          e.printStackTrace();
       }
       
       //Mostrar el menú
       Consultas.menuPrincipal();
   }
}
