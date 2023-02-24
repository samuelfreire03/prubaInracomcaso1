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

		while(lista.size()!=cantproductos*cantproc)
		{
			//simula espera activa

			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

			System.out.println("----------------------Etapa Final--------------------");

			//organiza buffer
			
			Collections.sort(lista, new NumComparator());

			for(String mensaje: lista)
			{

				//imprime productos finalizados y ordenados

				int indexfinal = mensaje.indexOf(";");
				System.out.println(mensaje.substring(0,indexfinal) + "; Progreso producto: 100% [####################] Producto creado,Listo Etapa 2,Listo Etapa 3,Producto Terminado \n");
			}
	}
}
