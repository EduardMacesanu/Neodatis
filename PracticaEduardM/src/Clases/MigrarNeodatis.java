package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.neodatis.odb.ODB;

public class MigrarNeodatis {
	
	public static void migrarGimnasios (Connection conexion, ODB odb) {
		
		try {
			
			Statement stmt = conexion.createStatement();
			ResultSet rsG = stmt.executeQuery("SELECT * FROM Gimnasios");
			
			while (rsG.next()) {
				Gimnasio gimnasio = new Gimnasio(
						rsG.getInt("Cod_centro"),
						rsG.getString("Nombre"),
						rsG.getInt("Responsable"),
						rsG.getString("Direccion"),
						rsG.getString("Localidad"),
						rsG.getString("Provincia"));
				
				//Almacenar
				odb.store(gimnasio);
			}
		} catch (SQLException e) {
			System.out.println("Error SQL : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void migrarMonitores (Connection conexion, ODB odb) {
		
		try {
			
			Statement stmt = conexion.createStatement();
	        ResultSet rsM = stmt.executeQuery("SELECT * FROM Monitores");
	        
	        while (rsM.next()) {
	            Monitor monitor = new Monitor(
	            		rsM.getInt("Cod_monitor"),
	            		rsM.getString("Nombre"),
	            		rsM.getString("Apellido"),
	            		rsM.getDate("Fecha_nac"),
	            		rsM.getString("Sexo").charAt(0),
	            		rsM.getInt("Cod_centro"));
	            
	            odb.store(monitor);
	        }
		} catch (SQLException e) {
			System.out.println("Error SQL : " + e.getMessage());
			e.printStackTrace();
		}
      
	}
	
	public static void migrarClases (Connection conexion, ODB odb) {
		
		try {
			
           Statement stmt = conexion.createStatement();
           ResultSet rsC = stmt.executeQuery("SELECT * FROM Clases");
           
           while (rsC.next()) {
               Clase clase = new Clase(
            		   rsC.getInt("Cod_clase"),
            		   rsC.getString("Nombre"),
                       rsC.getString("Dificultad"),
                       rsC.getInt("Durac_min"));
               odb.store(clase);
           }
		} catch (SQLException e) {
			System.out.println("Error SQL : " + e.getMessage());
			e.printStackTrace();
		}
      
	}
}
