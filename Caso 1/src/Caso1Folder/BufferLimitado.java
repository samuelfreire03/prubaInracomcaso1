package Caso1Folder;

import java.util.LinkedList;
import java.util.List;

public class BufferLimitado {
	
	private List<String> buffer;
	
	private int size;
	
	private int Nmessages;
	
	public BufferLimitado(int size, int numThreads, int numMessages)
	{
		
		this.size = size;
		this.buffer = new LinkedList<String>();
		this.Nmessages=numThreads*numMessages;
	}
	
	public synchronized boolean hasProducts()
	{
		return this.buffer.size() > 0;
	}
	
	
	//Estos tres metodos siguientes definene el comprtamiento de un thrread o proceso de tipo azul
	
	public synchronized void insertProductAzul(String message)
	{
		
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
		
		notifyAll();
		
	}
	
	public synchronized String recogerProductAzul()
	{
		String message = "";
		
		while (this.buffer.size() == 0)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(buffer.get(0).contains("proceso 0"))
		{
			message = this.buffer.remove(0);
		}
		
		notifyAll();
					
		return message;
		
	}
	

	
	//Estos tres metdoso siguiente simulan el comportamiento del  thread naranja o el prcoeso naranja de espera semiactiva
	
	
	public synchronized boolean insertProductNaranja(String message, ProcesoNaranja procesoNaranja)
	{
		if (this.buffer.size() == this.size)
		{
			return false;
		}
		
		if (!buffer.contains(message))
		{
			this.buffer.add(message);
			this.Nmessages--;
		}
		
		notifyAll();
		
		return true;
	}

	
	public synchronized String recogerProductNaranja(ProcesoNaranja procesoNaranja)
	{
		String message = "";
		
		if (this.buffer.size() == 0)
		{
			
			return message;
			
		}
		
		if(buffer.get(0).contains("proceso 1"))
		{
			message = this.buffer.remove(0);
		}

		notifyAll();
	
		return message;
		
	}
	
	
	public synchronized boolean isFinishedBuffer() {
		return this.Nmessages<=0;
	}
	
	public List<String> getBuffer() {
		return buffer;
	}
	
	//prueba
	
	public  void insertEtapaFinal(String message)
	{
			this.buffer.add(message);
			System.out.println(message + " Recibido Etapa Final");
	}
	
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

	public void imprimirbuffer()
	{
		System.out.println("empieza");
		for(String message: buffer)
		{
			System.out.println(message);
		}
		System.out.println("acaba");
	}
	

}
