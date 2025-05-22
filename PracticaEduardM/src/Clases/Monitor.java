package Clases;


import java.util.Date;


public class Monitor {
   private int codMonitor;
   private String nombre;
   private String apellido;
   private Date fechaNac;
   private char sexo;
   private int codCentro;
   public Monitor(int codMonitor, String nombre, String apellido, Date fechaNac, char sexo, int codCentro) {
       this.codMonitor = codMonitor;
       this.nombre = nombre;
       this.apellido = apellido;
       this.fechaNac = fechaNac;
       this.sexo = sexo;
       this.codCentro = codCentro;
   }
   
   public int getCodMonitor() {
       return codMonitor;
   }
   public String getNombre() {
       return nombre;
   }
   public String getApellido() {
       return apellido;
   }
   public Date getFechaNac() {
       return fechaNac;
   }
   public char getSexo() {
       return sexo;
   }
   public int getCodCentro() {
       return codCentro;
   }
}

