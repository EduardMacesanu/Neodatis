package Clases;

public class Clase {
	
	   private int codClase;
	   private String nombre;
	   private String dificultad;
	   private int Durac_min;
	   
	   public Clase(int codClase, String nombre, String dificultad, int Durac_min) {
	       this.codClase = codClase;
	       this.nombre = nombre;
	       this.dificultad = dificultad;
	       this.Durac_min = Durac_min;
	   }
	   
	   
	   public int getCodClase() {
	       return codClase;
	   }
	   public String getNombre() {
	       return nombre;
	   }
	   public String getDificultad() {
	       return dificultad;
	   }
	   public int getDuracMin() {
	       return Durac_min;
	   }
	}

