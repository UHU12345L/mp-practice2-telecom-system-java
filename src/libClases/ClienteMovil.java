package libClases;

public class ClienteMovil extends Cliente {
	private Fecha fechaPermanencia;
	private float minutosHablados;
	private float precioMinuto;
	
	public ClienteMovil(String nif, String nom, Fecha fNac, Fecha fAlta, Fecha fPermanencia, float minutosHablados, float precioMinuto) {
		//public Cliente (String nif, String nom, Fecha fNac, Fecha fAlta)
		super(nif, nom, fNac, fAlta);
		this.minutosHablados=minutosHablados;
		this.precioMinuto=precioMinuto;
		this.fechaPermanencia=(Fecha)fPermanencia.clone();
	}
	
	public ClienteMovil(String nif, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, float precioMinuto ) {
		//no tengo fPermanencia, por defecto 1 año después de fAlta
		this(nif, nom, fNac, fAlta, new Fecha(fAlta.getDia(), fAlta.getMes(),fAlta.getAnio()+1), minutosHablados, precioMinuto);
	}

	public ClienteMovil(String nif, String nom, Fecha fNac, float minutosHablados, float precioMinuto) {
		//no tengo fAlta ni fPermanencia
		this(nif, nom, fNac, getFechaPorDefecto(), minutosHablados, precioMinuto);
	}

	public ClienteMovil(ClienteMovil c) {
		super(c);
		this.minutosHablados=c.minutosHablados;
		this.precioMinuto=c.precioMinuto;
		this.fechaPermanencia=(Fecha)c.fechaPermanencia.clone();
	}
	
	public void setFPermanencia(Fecha fechaper) {
		this.fechaPermanencia = (Fecha)fechaper.clone();
	}
	
	public Fecha getFPermanencia() {
		return (Fecha)fechaPermanencia.clone();
	}
	
	public float getMinutos(){
        return minutosHablados;
    }
	
    public float getPrecioMinuto(){
        return precioMinuto;
    }
    
    public void setPrecioMinutos(float precio) {
		this.precioMinuto=precio;	
	}
    
    @Override
    public float factura() {
    	return minutosHablados*precioMinuto;
    }
	
    @Override
    public String toString() {
    	return super.toString()+" "+fechaPermanencia+" "+minutosHablados+"x"+precioMinuto+"-->"+factura();
    }
    
    @Override
    public Object clone() {
    	return new ClienteMovil(getNIF(), getNombre(), getFechaNac(), getFechaAlta(), getFPermanencia(), getMinutos(), getPrecioMinuto());
    }
	
    @Override
    public boolean equals(Object o) {
    	return o instanceof ClienteMovil && getNIF().equals(((Cliente)o).getNIF());
    }
}
