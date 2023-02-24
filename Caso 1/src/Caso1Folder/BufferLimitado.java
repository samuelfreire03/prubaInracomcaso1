package Caso1Folder;

import java.util.LinkedList;
import java.util.List;

public class BufferLimitado {

	//Inicalizacion de atributos de buffer(lista, su tamaño y el numero de mensajes qeu debe procesar)
	
	private List<String> buffer;
	
	private int size;
	
	private int Nmessages;
	
	public BufferLimitado(int size, int numThreads, int numMessages)
	{
		
		this.size = size;
		this.buffer = new LinkedList<String>();
		this.Nmessages=numThreads*numMessages;
	}
	
	//Verificacion de existencia de productos

	public synchronized boolean hasProducts()
	{
		return this.buffer.size() > 0;
	}
	
	
	//Insercion y espera pasiva del proceso azul
	
	public synchronized void insertProductAzul(String message)
	{
		//SI no puede meter productos espera

		while (this.buffer.size() == this.size)
		{
			
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		if (!buffer.contains(message))
		{
			this.buffer.add(message);
			this.Nmessages--;
		}

		//Notifica cuadno termine
		
		notifyAll();
		
	}

	//Recoleccion y espera pasiva del proceso azul

	public synchronized String recogerProductAzul()
	{
		String message = "";

		//Espera si esta vacio
		
		while (this.buffer.size() == 0)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		//Verifica si es un producto azul (proceso 0)

		if(buffer.get(0).contains("proceso 0"))
		{
			message = this.buffer.remove(0);
		}

		//Notifica cuando termina

		notifyAll();
					
		return message;
		
	}
	

	
	//Estos tres metdoso siguiente simulan el comportamiento del  thread naranja o el prcoeso naranja de espera semiactiva
	
	
	public synchronized boolean insertProductNaranja(String message, ProcesoNaranja procesoNaranja)
	{
		//Pregunta si esta lleno y espera de manera semiactiva si si

		if (this.buffer.size() == this.size)
		{
			return false;
		}
		
		if (!buffer.contains(message))
		{
			this.buffer.add(message);
			this.Nmessages--;
		}

		//notifica cuando termine
		
		notifyAll();
		
		return true;
	}

	
	public synchronized String recogerProductNaranja(ProcesoNaranja procesoNaranja)
	{
		String message = "";

		//Verifica si esta vacio
		
		if (this.buffer.size() == 0)
		{
			
			return message;
			
		}

		//Verifica si es un producto naranja (proceso 1)
		
		if(buffer.get(0).contains("proceso 1"))
		{
			message = this.buffer.remove(0);
		}

		notifyAll();
	
		return message;
		
	}
	
	//Verifica si ya proceso todos los producto

	public synchronized boolean isFinishedBuffer() {
		return this.Nmessages<=0;
	}
	
	public List<String> getBuffer() {
		return buffer;
	}
	
	//Insercion productos en etapa final
	
	public  void insertEtapaFinal(String message)
	{
			this.buffer.add(message);
			System.out.println(message + " Recibido Etapa Final");
	}

	//Recoleccion productos en etapa final ( Este metodo no es fundamental )
	
	public String recogerProductRojo()
	{
		String message = "";
		
		if (this.buffer.size() == 0)
		{
			
			return message;
			
		}
		
		message = this.buffer.remove(0);
	
		return message;
		
	}
	
	public int getNmessages() {
		return Nmessages;
	}
	

}
