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
		
		List<String> lista = bufFinal.getBuffer();

		System.out.println(lista.size());
			
			int id = 1;

			System.out.println("----------------------Etapa Final--------------------");
			
			while (id<=cantproc*cantproductos)
			{
				int conteo = 0;
				
				while(conteo<lista.size())
				{
					
					if(lista.get(conteo).contains("id" + id + ","))
					{
						int indexfinal = lista.get(conteo).indexOf(";");

						System.out.println(lista.get(conteo).substring(0,indexfinal) + "; Progreso producto: 100% [####################] Producto creado,Listo Etapa 2,Listo Etapa 3,Producto Terminado \n");
						id+=1;
				
					}
					
					conteo +=1;
					
				}
			}
	}
}
