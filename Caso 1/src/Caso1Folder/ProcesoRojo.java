package Caso1Folder;

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
		
		while(!bufFinal.isFinishedBuffer()||this.bufFinal.hasProducts())
		{	
			
			int count = 0;
			while((cantproc*cantproductos)> count) 
			{
				
				String message = this.bufFinal.recogerProductRojo();
				System.out.println("Producto Terminado");
				count+=1;
				
			}
			
			
		}
		
	}
}
