package libClases;

public class ClienteTarifaPlana extends Cliente{
	private static float tarifa=20f;
	private static float limiteMinutos=300;
	private static float costeAdicional=0.15f;
	
	private float minutosHablados;
	private String nacionalidad;
	
	public ClienteTarifaPlana(String nif, String nom, Fecha fNac, Fecha fAlta, float minutosHablados, String nacionalidad) {
		super(nif, nom, fNac, fAlta);
		this.minutosHablados=minutosHablados;
		this.nacionalidad=nacionalidad;
	}
	
	public ClienteTarifaPlana(String nif, String nom, Fecha fNac, float minutosHablados, String nacionalidad) {
		super(nif, nom, fNac);
		this.minutosHablados=minutosHablados;
		this.nacionalidad=nacionalidad;
	}
	
	public ClienteTarifaPlana(Cliente c) {
		super(c);
	}
	
	public void setMinutos(int min) {
		minutosHablados = min;
	}

	public void setNacionalidad(String nac) {
		this.nacionalidad = nac;
	}

	public static void setTarifa(int min, float t) {
		limiteMinutos = min;
		tarifa = t;
	}

	public static float getLimite() {
		return limiteMinutos;
	}

	public static float getTarifa() {
		return tarifa;
	}
	
	public float getMinutos() {
		return minutosHablados;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	@Override
	public float factura() {
		float total=tarifa;
		if(minutosHablados>limiteMinutos) 
			total=total+(minutosHablados-limiteMinutos)*costeAdicional;
		return total;
	}
	
	@Override
	public Object clone() {
		return new ClienteTarifaPlana(getNIF(), getNombre(), getFechaNac(), getFechaAlta(), getMinutos(), getNacionalidad());
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof ClienteTarifaPlana &&getNIF().equals(((Cliente)o).getNIF());
	}
	
}
