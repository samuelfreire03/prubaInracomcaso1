package Caso1Folder;

public class ProcesoNaranja extends Thread{
	
	private Numero idProductos;
	
	private BufferLimitado buf1;
	
	private BufferLimitado buf2;
	
	private int cantProductos;
	
	private boolean tipo;
	
	private BufferLimitado buf3;
	
	private int etapa;
	
	public ProcesoNaranja(Numero id,BufferLimitado buf1,BufferLimitado buf2,BufferLimitado buf3,int cantidadproductos,boolean tipo,int etapa)
	{
		
		this.idProductos = id;
		this.buf1 = buf1;
		this.buf2=buf2;
		this.cantProductos = cantidadproductos;
		this.tipo = tipo;
		this.etapa = etapa;
		this.buf3 = buf3;

		
	}
	
	private void MandarProducto(int id,String message)
	{
		
		if(etapa==1) {
			
			while(!this.buf1.insertProductNaranja("El producto con el id" + id + ", sale del proceso 1 en la etapa 1 a la etapa 2",this)) {
				this.yield();
			}
			}
			else if(etapa==2)  {
				
				System.out.println(message + ", recibido en etapa 2 por proceso 1");
				String mensaje = message + ", recibido en etapa 2 por proceso 1";
			while(!this.buf2.insertProductNaranja(mensaje  + " El producto sale de la etapa 2 a etapa 3",this)) {
				this.yield();
			}
				this.buf2.insertProductAzul(mensaje  + " El producto sale de la etapa 2 a etapa 3");
			}
			else {
				System.out.println(message + ", recibido en etapa 3 por proceso 1");
				String mensaje = message + ", recibido en etapa 3 por proceso 1";
				this.buf3.insertEtapaFinal(mensaje  + " El producto sale de la etapa 3 a etapa final");
			}
	}
	
	
	@Override
	public void run() 
	{
		//Primera etapa 
		
		if (etapa==1)
		{
			for(int i = 0; i < this.cantProductos;i++)
			{
				idProductos.MasNumero();
				String message = null;
				
				this.MandarProducto(idProductos.getNumero(),message);
			}
			
		}
		
		//Segunda y tercera etapa
		
		else if(etapa==2)
		{
	
			while(!buf1.isFinishedBuffer()||this.buf1.hasProducts())
			{	
				
				String message = this.buf1.recogerProductNaranja(this);
				if (message != "")
				{
					int i=0;
					this.MandarProducto(i, message);
					cantProductos--;
				}
				else {
					this.yield();
				}
				if (cantProductos<=0)
				{
					this.interrupt();
				}
			}
			
		}
		
		else
		{
	
			while(!buf2.isFinishedBuffer()||this.buf2.hasProducts())
			{	
				
				String message = this.buf2.recogerProductNaranja(this);
				if (message != "")
				{
					int i=0;
					this.MandarProducto(i, message);
					cantProductos--;
				}
				else {
					this.yield();
				}
				if (cantProductos<=0)
				{
					this.interrupt();
				}
			}
		}
	}

}