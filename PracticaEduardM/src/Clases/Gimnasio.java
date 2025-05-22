package Clases;

public class Gimnasio {
	
	   private int codCentro;
	   private String nombre;
	   private int responsable;
	   private String direccion;
	   private String localidad;
	   private String provincia;
	   
	   public Gimnasio(int codCentro, String nombre, int responsable, String direccion, String localidad, String provincia) {
	       this.codCentro = codCentro;
	       this.nombre = nombre;
	       this.responsable = responsable;
	       this.direccion = direccion;
	       this.localidad = localidad;
	       this.provincia = provincia;
	   }
	   
	   public int getCodCentro() {
	       return codCentro;
	   }
	   public String getNombre() {
	       return nombre;
	   }
	   public int getResponsable() {
	       return responsable;
	   }
	   public String getDireccion() {
	       return direccion;
	   }
	   public String getLocalidad() {
	       return localidad;
	   }
	   public String getProvincia() {
	       return provincia;
	   }
}

