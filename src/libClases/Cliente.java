package libClases;

public class Cliente implements Cloneable, Proceso {
	 private final String nif;       //dni del cliente (letra incluida) (NO puede cambiar)   
	  private final int codCliente;   //codigo único (y fijo) generado por la aplicación 
	  private String nombre;          //nombre completo del cliente (SI se puede modificar) 
	  private final Fecha fechaNac;   //fecha nacimiento del cliente (NO se puede cambiar) 
	  private Fecha fechaAlta;  //fecha de alta del cliente (SI se puede modificar) 
	 
	  private static final Fecha FechaDefecto = new Fecha(01,01,2018);
	  private static int contadorClientes=1;
	  //CONSTRUCTORES
	  public Cliente (String nif, String nom, Fecha fNac, Fecha fAlta) {
		  this.nif=nif;
		  this.nombre=nom;
		  //this.fechaNac=fNac; asi copio referencia (si cambio original cambia el otro), tengo que crear nuevo objeto con mismos valores
		  this.fechaNac=(Fecha)fNac.clone();
		  this.fechaAlta=(Fecha)fAlta.clone();
		  this.codCliente=contadorClientes++;
	  }  
	  public Cliente (String nif, String nom, Fecha fNac) {
		  Fecha fAlta=new Fecha(01,01,2018);
		  this.nif=nif;
		  this.nombre=nom;
		  this.fechaNac=(Fecha)fNac.clone();
		  this.fechaAlta=(Fecha)fAlta.clone();
		  this.codCliente=contadorClientes++;
	  }
	  
	  public Cliente(Cliente c1) {
		  this.nif=c1.nif;
		  this.nombre=c1.nombre;
		  this.fechaNac=(Fecha)c1.fechaNac.clone();
		  this.fechaAlta=(Fecha)c1.fechaAlta.clone();
		  this.codCliente=contadorClientes++;
	  }
	  
	  public String getNIF(){ return this.nif;}
		
		public int getCodCliente(){return this.codCliente;}
		
		public String getNombre(){return this.nombre;}
		
		public void setNombre(String nombre){this.nombre=nombre;}

		public static Fecha getFechaPorDefecto() {return (Fecha)FechaDefecto.clone();}
		
		public static void setFechaPorDefecto(Fecha f) {FechaDefecto.setFecha(f);}
		
		public Fecha getFechaNac() {
			return (Fecha)fechaNac.clone();
		}
		
		public Fecha getFechaAlta() {
			return (Fecha)fechaAlta.clone();
		}
		
		public void setFechaAlta(Fecha fAlta) {
			fechaAlta.setFecha(fAlta);
		}
		
		public float factura() { //para poder utilizarfactura de movil y tp
			return 0;
		}
	  
		@Override
		public Object clone() {
			return new Cliente(this);
		}

	  
	  @Override
		public void ver() {
			System.out.println(this);
		}
		
		@Override
	    public String toString(){ 
	        return nif+" "+fechaNac+": "+nombre+" ("+codCliente+" - "+fechaAlta+")";
	    }

		@Override
	    public boolean equals(Object o){
			if(!(o instanceof Cliente)) return false;
			Cliente c=(Cliente)o;
	        return this.getClass() == c.getClass() && nif.equals(c.nif);
	    } 	  
}
