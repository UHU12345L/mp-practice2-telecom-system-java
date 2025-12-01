package libClases;
import java.util.Scanner;

public class Empresa implements Cloneable, Proceso{
	private Cliente[]listaClientes;
	private int nClientes;
	private int nMaxClientes=10; //si nClientes>nMaxClientes hago nMaxClientes*2
	
	public Empresa() {
		this.listaClientes = new Cliente [nMaxClientes];
		nClientes=0;
	}
	private int buscarCliente(String dni) {
		//existe devueve pos lista sino devuelve -1
		boolean existe=false;
		int i=0;
		while(i<nClientes && !existe) {
			if(listaClientes[i].getNIF().equals(dni)) {
				existe=true;
			}else {
				i++;
			}
			
		}
		if(!existe) return -1;
		
		return i;
	}
	
	public int getN() {
		return nClientes;
	}
	
	public int nClienteMovil() {
		int nClienteMovil=0;
		for(int i=0; i<nClientes;i++) {
			if(listaClientes[i] instanceof ClienteMovil) {
				nClienteMovil++;
			}
		}
		return nClienteMovil;
	}
	
	public void alta(Cliente cli) {
		if(cli==null || buscarCliente(cli.getNIF())!=-1) {
			return;
		}
		listaClientes[nClientes++]=cli;
		
		if(nClientes==listaClientes.length) {
			Cliente[] tmp=new Cliente[listaClientes.length*2];
			for(int i=0;i<listaClientes.length; i++) {
				tmp[i]=listaClientes[i];
			}
			listaClientes=tmp;
			
		}
	}
	
	public void alta() {
		//verificar existe cliente
		Scanner s = new Scanner(System.in);
        String dni;
        System.out.println("DNI: ");
        dni = s.nextLine(); 
        int pos=this.buscarCliente(dni); //i=pos del cliente

      //cliente existe, dar de alta
        if(pos!=-1) { 
            System.out.println("Ya existe un cliente con ese DNI");
            System.out.println(listaClientes[pos]);
        }
        else { //pedir datos del nuevo cliente
            Cliente c = null;
            String nombre;
            Fecha fNac, fAlta;
            float minutosHablados;
            System.out.println("Nombre: ");
            nombre = s.nextLine();
            System.out.println("Fecha de Nacimiento: ");
            fNac = Fecha.pedirFecha();
            System.out.println("Fecha de Alta: ");
            fAlta = Fecha.pedirFecha();
            System.out.println("Minutos que habla al mes: ");
            minutosHablados = s.nextFloat();
            System.out.println("Indique tipo de cliente (1-Movil, 2-Tarifa Plana): ");
            int tipo;
            tipo=s.nextInt();
            //crear segun cliente movil o tarifa plana
            if(tipo==1) {
                System.out.println("Precio por minuto: ");
                float precio;
                precio=s.nextFloat();
                System.out.println("Fecha fin permanencia: ");
                Fecha FPer = Fecha.pedirFecha();
                c = new ClienteMovil(dni, nombre, fNac, fAlta, FPer, minutosHablados, precio);
            }
            else if (tipo==2) {
                String nacionalidad;
                System.out.print("Nacionalidad: ");
                nacionalidad = s.nextLine();
                c = new ClienteTarifaPlana(dni, nombre, fNac, fAlta, minutosHablados, nacionalidad);
            }

            alta(c);

        }

    }
	
	
	
	public void baja(String dni) {
		Scanner s = new Scanner(System.in);
		int pos=buscarCliente(dni);
		if(pos!=-1) {
			//pedir confirmacion si quiere eliminar
			System.out.println(listaClientes[pos]);
	        System.out.print("¿Seguro que desea eliminarlo (s/n)? ");
	        String respuesta = s.nextLine();
			
	        if(respuesta.equalsIgnoreCase("s")) {
	        	String nombre=listaClientes[pos].getNombre();
	        	for(int i=pos;i<nClientes;i++) {
					listaClientes[i]=listaClientes[i+1];
				}
				nClientes--;
				
				if(nClientes<listaClientes.length/2) {
					Cliente[]tmp=new Cliente[listaClientes.length/2];
					for(int i=0; i<tmp.length;i++) {
						tmp[i]=listaClientes[i];
					}
					listaClientes=tmp;
				}
				
				System.out.println("El cliente " +nombre+" con dni "+dni+" ha sido eliminado \n");
	        } else {
	        	System.out.println("El cliente con dni "+dni+" no se eliminao \n")
	        }
			
		}
		
	}
	
	public void baja() {
		    Scanner s = new Scanner(System.in);
		    System.out.print("Introduzca nif cliente a dar de baja: ");
		    String dni = s.nextLine();
		    baja(dni); 
		
	}
	
	public void descuento(int desc) {
		for(int i=0; i<nClientes;i++) {
			if(listaClientes[i]instanceof ClienteMovil) {
				ClienteMovil c=(ClienteMovil)listaClientes[i];
				float precioNuevo=c.getPrecioMinuto()*(100-desc)/100;
				c.setPrecioMinutos(precioNuevo);
			}
		}
	}
	
	@Override
	public void ver() {
		System.out.println(this);
	}
	
	/*@Override
	public float factura() {
		float total=0;
		for(int i=0; i<nClientes; i++) {
			if(listaClientes[i] instanceof ClienteMovil) {
				ClienteMovil cm=(ClienteMovil) listaClientes[i];
				total=total+cm.factura();
			}else if(listaClientes[i] instanceof ClienteTarifaPlana) {
				ClienteTarifaPlana cp=(ClienteTarifaPlana) listaClientes[i];
				total=total+cp.factura();
			}
		}
		return total;
	}*/
	
	public float factura() {//factura global de todos los clientes
		float total=0;
		
		for(int i=0;i<nClientes;i++) {
			total=total+listaClientes[i].factura();
		}	
		return total;
	}
	
	/*
	COPIAR SOLO LOS INT y ambas compartirian mismo string
	public Object clone() {
    Object obj = null;
    try {
        obj = super.clone();  
    } catch (CloneNotSupportedException ex) { }
    return obj;
}*/
	@Override
	public Object clone() {
		Empresa obj=null;
		try {
			obj=(Empresa) super.clone();
			obj.listaClientes=listaClientes.clone();
			for (int i=0; i<nClientes;i++) {
				obj.listaClientes[i]=(Cliente)listaClientes[i].clone();
			}
		}catch(CloneNotSupportedException ex) {
			System.out.println("No ha soportado el clone");
		}
		return (Object)obj;
	}

	
	@Override 
	public String toString() {
		String mostrar="";
		for(int i=0; i<nClientes;i++) {
			mostrar=mostrar+listaClientes[i]+"\n";
		}
		return mostrar;
	}
}
