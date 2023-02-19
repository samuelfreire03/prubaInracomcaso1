package Caso1Folder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static Numero idproductos;
	
	private  BufferLimitado buf1;
	
	private  BufferLimitado buf2;
	
	private  BufferLimitado buf3;
	
	private static int cantidad;
	
	public static void main(String[] args) 
	{
        
		//Todo el siguiente codigo emula la creacion de los hilos de los procesos azules
		
	        //esto es la creacion del buffer primera etapa
	        
			BufferLimitado buf1 = new BufferLimitado(5,3,5);
	        
	        //esta es la creacion del numero
	        
	        idproductos = new Numero(0);
	        
	        //esto es la creacion del buffer primera etapa
	        
	        BufferLimitado buf2 = new BufferLimitado(5,3,5);
	        
	        //esto es la creacion del buffer segunda etapa
	        
	        BufferLimitado buf3 = new BufferLimitado(100,3,5);
	        
	        //esta es la cantidad 
	        
	        ProcesoAzul p1 = new ProcesoAzul(idproductos, buf1,null,null, 5,true,1);
	        
	        ProcesoAzul p2 = new ProcesoAzul(idproductos, buf1,null,null, 5,true,1);
	        
	        ProcesoNaranja p3 = new ProcesoNaranja(idproductos, buf1,null,null, 5,true,1);
	        
	        
	        
	        ProcesoAzul p4 = new ProcesoAzul(idproductos, buf1,buf2,null, 5,true,2);
	        
	        ProcesoAzul p5 = new ProcesoAzul(idproductos, buf1,buf2,null, 5,true,2);
	        
	        ProcesoNaranja p6 = new ProcesoNaranja(idproductos, buf1,buf2,null, 5,true,2);
	        
	        
	        
	        
	        ProcesoAzul p7 = new ProcesoAzul(idproductos, null,buf2,buf3, 5,true,3);
	        
	        ProcesoAzul p8 = new ProcesoAzul(idproductos, null,buf2,buf3, 5,true,3);
	        
	        ProcesoNaranja p9 = new ProcesoNaranja(idproductos, null,buf2,buf3, 5,true,3);
	        
	        
	        
	        
	        ProcesoRojo p10 = new ProcesoRojo(buf3,3,5);
	        
			
			//etapa 1
			
	        p1.start();
	        p2.start();
	        p3.start();
	        
	     	//etapa 2
	        
			p4.start();
			p5.start();
			p6.start();
			
			//etapa 3
			
			p7.start();
			p8.start();
			p9.start();
			
			try {
				p7.join();
				p8.join();
				p9.join();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//etapa final
			
			p10.start();
			


		
	}

}
