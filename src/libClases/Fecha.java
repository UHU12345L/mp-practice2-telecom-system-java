package libClases;
import java.util.Scanner;

//cout es system.out
//leer de teclado: 
	//Scanner s =new Scanner(System.in); es leer de teclado
	//system.in: entrada de teclado (cin)
	//s.nextLine();Leer toda la línea hasta Enter (getline)
	//s.nextInt(); Leer entero (cin >> num)
	//s.nextFloat(); Leer decimal (cin >> float)
//convertir cadenas a numeros: Integer.parseInt()
//@Override sobreescribo método de clase padre o interfaz
//verificar objeto sea una fecha (o instanceof Fecha)) 

//Separar cadenas:String[] partes = linea.split("/"); 
//super(c); constructor copia de padre (Cliente), despues copiar atributos especificos de la clase

//C++: ==, !=, length(), substr(), +
//Java: equals(), equalsIgnoreCase(), length(), substring(), +

//Comparar strings:
	//str1 == str2 En Java compara REFERENCIAS, no contenido
	//str1.equals(str2)  Compara contenido (equivale a str1 == str2 en C++)
	// str1.equalsIgnoreCase(s)  Compara ignorando mayúsculas/minúsculas

//Longitud:str.length() 
//Herencia:  C++: class Hijo : public Padre, virtual, Java: class Hijo extends Padre, @Override  
//public final no clases derivadas       

//Verificar tipos: C++: dynamic_cast, typeid. Java: instanceof, getClass()
	//instanceof: si objeto es de clase o subclases

//hacer casting a fecha: Fecha f=(Fecha) o;
/*
Manejo de excepciones:
try {
    // código
} catch(Exception e) {
    // manejo del error
}
*/


public final class Fecha implements Cloneable, Proceso {
	private int dia;
	private int mes;
	private int anio;
	
	public void setFecha(int d, int m, int a) {
		int maxDias[]= {0,31,28,31,30,31,30,31,31,30,31,30,31};
		
		this.dia=d;
		this.mes=m;
		this.anio=a;
		
		if(m<1) 
			this.mes=1;
		else if(m>12) 
			this.mes=12;
		
		
		if(bisiesto()) 
			maxDias[2]=29;
		
		if(dia<1) {
			this.dia=1;
		}else if(dia>maxDias[this.mes]) {
			this.dia=maxDias[this.mes];
		}

	}
	
	public void setFecha(Fecha f) {
		setFecha(f.dia,f.mes,f.anio);
	}
	
	//CONSTRUCTORES
	public Fecha(int dia, int mes, int anio) {  
		  setFecha(dia, mes, anio); 
		 } 	  
	public Fecha(Fecha f) { 
		  setFecha(f.dia, f.mes, f.anio);
	} 
	
	//GETTERS
	public int getDia() { return this.dia; } 
	public int getMes() { return this.mes; } 
	public int getAnio() { return this.anio; } 
	
	
	public boolean bisiesto() {
		boolean bi=false;
		if(anio%4==0) {
			bi=true;
			if(anio%100==0 &&anio%400!=0)
				bi=false;
		}
		return bi;
	}
	
	public static Fecha pedirFecha() {
		Scanner s =new Scanner(System.in);
		Fecha f=null;
		boolean valida;
		
		do {
			System.out.print("Introduce la fecha (dd/mm/aa): ");
			String[] datos=s.nextLine().split("/");
			
			//length() en strings
			if(datos.length !=3) {
				valida=false;
			}else {
				int fDia=Integer.parseInt(datos[0]);
				int fMes=Integer.parseInt(datos[1]);
				int fAnio=Integer.parseInt(datos[2]);
				
				int maxDias[]= {0,31,(fAnio%400==0||(fAnio%4==0 && fAnio%100!=0))?29:28, 31,30,31,30,31,31,30,31,30,31};
				valida=fMes>=1 && fMes<=12 && fDia>=1 && fDia<=maxDias[fMes];
				
				if(valida) 
					f=new Fecha(fDia, fMes, fAnio);
				
			}
			if(!valida) 
				System.out.println("Fecha no valida");
			
		}while(!valida);
		return f;
	}
	
	static public boolean mayor(Fecha f1, Fecha f2) {
		if(f1.anio*10000+f1.mes*100+f1.dia>f2.anio*10000+f2.mes*100+f2.dia)
			return true;
		else
			return false;
	}
	
	public void ver() {
	    System.out.println(this);
	}
	
	public String toString() {
		String s="";
		//si menor que 10 añadir 0 delante
		if(dia<10) s=s+0;
		s=s+dia+"/";
		if(mes<10) s=s+0;
		s=s+mes+"/"+anio;
		return s;
		//rellenar con 0 minimo 2 digitos: return String.format("%02d/%02d/%d", dia, mes, anio);
	}
	
	public Fecha diaSig() {
		Fecha fechaSig=new Fecha(this.dia, this.mes, this.anio);
		int diaMes[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	    if (bisiesto())//si el año es bisiesto febrero tiene 29 dias
	      diaMes[2]=29;

	    fechaSig.dia=this.dia+1;
	    
	    if(fechaSig.dia>diaMes[fechaSig.mes]) {
	    	do {
	    		//restar dias del mes actual y pasar al siguiente
	    		fechaSig.dia=fechaSig.dia-diaMes[fechaSig.mes];
	    		fechaSig.mes++;
	    		if (fechaSig.mes>12) {
	    			fechaSig.mes=1;
	    			fechaSig.anio++;
	    			if(fechaSig.bisiesto()) {
	    				diaMes[2]=29;
	    			}else {
	    				diaMes[2]=28;
	    			}
	    		}
	    	}while(fechaSig.dia>diaMes[fechaSig.mes]);
	    }
	    return fechaSig;
	}

	@Override 
	public Object clone() {
		Object obj=null;
		try {
			obj=super.clone();
		}catch(CloneNotSupportedException ex) {
			
		}
		return obj;
	}
	
	@Override
	public boolean equals(Object o) {
	    if(!(o instanceof Fecha)) 
	    	return false;
	    Fecha f=(Fecha) o;
	    return dia==f.dia && mes==f.mes && anio==f.anio;
	}
	


/*public static void main(String[] args) { 
	  Fecha f1 = new Fecha(29,2,2001), f2 = new Fecha(f1), f3 = new Fecha(29,2,2004); 
	  final Fecha f4=new Fecha(05,12,2023);  //es constante la referencia f4 
	  System.out.println("Fechas: " + f1.toString() + ", "+f2+ ", " +f3+ ", " +f4+ "\n"); 
	  f1=new Fecha(31,12,2016);  //31/12/2016 
	  f4.setFecha(28, 2, 2008);              //pero no es constante el objeto al que apunta 
	  System.out.println(f1 +" "+ f2.toString() +" " + f3 +" "+ f4+" "+ f1); 
	  f1=new Fecha(f4.getDia()-10, f4.getMes(), f4.getAnio()-7);      //f1=18/02/2001 
	  f3=Fecha.pedirFecha(); //pide una fecha por teclado 
	  if (f3.bisiesto() && Fecha.mayor(f2,f1)) 
	    System.out.print("El " + f3.getAnio() + " fue bisiesto, " + f1 + ", " + f3); 
} */
	
}