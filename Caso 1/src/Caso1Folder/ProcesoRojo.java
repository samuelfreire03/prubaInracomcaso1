package Caso1Folder;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcesoRojo extends Thread{
	
	private BufferLimitado bufFinal;
	private int cantproductos;
	private int cantproc;
	
	public ProcesoRojo(BufferLimitado bufFinal,int cantproductos,int cantproc) 
	{
		this.bufFinal = bufFinal;
		this.cantproductos=cantproductos;
		this.cantproc=cantproc;
	}
	
	@Override
	public void run() 
	{
		
		List<String> lista = bufFinal.getBuffer()
;		
		if(this.bufFinal.hasProducts())
		{
			
			int id = 1;
			
			while (id<=cantproc*cantproductos)
			{
				int conteo = 0;
				
				while(conteo<lista.size())
				{
					
					if(lista.get(conteo).contains("id" + id))
					{
						
						System.out.println(lista.get(conteo) + "Progreso producto: 100% [####################] Producto Terminado");
						id+=1;
				
					}
					
					conteo +=1;
					
				}
			}
		}
		
	}
}
