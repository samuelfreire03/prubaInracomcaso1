package Caso1Folder;

import java.util.Scanner;

public class Main {
	
	private static Numero idproductos;
	
	public static void main(String[] args) 
	{

		//Creacion y lectura de los inputs del ususario
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Capacidad buffer: ");

		//Capacidad de los dos primeros buffers

	    int capacidadBuffer = Integer.valueOf(myObj.nextLine());
	    
	    System.out.println("Cantidad productos: ");

		//Cantidad de productos de cada proceso
	    
	    int cantProductos = Integer.valueOf(myObj.nextLine());
	    
	    System.out.println("Cantidad procesos: ");

		//Cantidad de procesos en las tres primeras etapas
	    
	    int cantProceso = Integer.valueOf(myObj.nextLine());

		//Inicializacion de los ids del producto
	    
	    int idproductosid = 0;
	    
	    //esta es la objeto identificador
        
        idproductos = new Numero(idproductosid);
	
        //esto es la creacion del buffer primera etapa
        
		BufferLimitado buf1 = new BufferLimitado(capacidadBuffer,cantProceso,cantProductos);
        
        //esto es la creacion del buffer primera etapa
        
        BufferLimitado buf2 = new BufferLimitado(capacidadBuffer,cantProceso,cantProductos);
        
        //esto es la creacion del buffer segunda etapa
        
        BufferLimitado buf3 = new BufferLimitado(cantProceso*cantProductos+1,cantProceso,cantProductos);
        
        //Creacion de listas para meter los threads de las tres etapas
        
        ProcesoAzul [] listaprocesosetapa1 = new ProcesoAzul[cantProceso-1];
        ProcesoAzul [] listaprocesosetapa2 = new ProcesoAzul[cantProceso-1];
        ProcesoAzul [] listaprocesosetapa3 = new ProcesoAzul[cantProceso-1];
	    
        //Creacion de los threads

        for(int i = 0;i<cantProceso-1;i++)
	    {
        	listaprocesosetapa1[i] = new ProcesoAzul(idproductos, buf1,null,null,cantProductos,1);
        	listaprocesosetapa2[i] = new ProcesoAzul(idproductos, buf1,buf2,null,cantProductos,2);
        	listaprocesosetapa3[i] = new ProcesoAzul(idproductos, null,buf2,buf3,cantProductos,3);
	    }
        
        ProcesoNaranja pnaranja = new ProcesoNaranja(idproductos, buf1,null,null,cantProductos,1);
        ProcesoNaranja pnaranjaet2 = new ProcesoNaranja(idproductos, buf1,buf2,null, cantProductos,2);
        ProcesoNaranja pnaranjaet3 = new ProcesoNaranja(idproductos, null,buf2,buf3, cantProductos,3);

		//Inicializacion de los threads
        
        pnaranja.start();
		pnaranjaet2.start();
        
        for(int i = 0;i<cantProceso-1;i++)
	    {
        	listaprocesosetapa1[i].start();
        	listaprocesosetapa2[i].start();
	    }

		pnaranjaet3.start();

		for(int i = 0;i<cantProceso-1;i++)
	    {
        	listaprocesosetapa3[i].start();
	    }

		//Creacion e inicializacion del thread rojo
        ProcesoRojo p10 = new ProcesoRojo(buf3,cantProceso,cantProductos);
		
		p10.start();

	
	}

}
