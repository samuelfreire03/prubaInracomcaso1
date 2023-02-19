package Caso1Folder;

public class ProcesoAzul extends Thread{
	
	private Numero idProductos;
	
	private BufferLimitado buf;
	
	private BufferLimitado buf2;
	
	private BufferLimitado buf3;
	
	private int cantProductos;
	
	private boolean tipo;
	
	private int etapa;
	
	public ProcesoAzul(Numero id,BufferLimitado buf,BufferLimitado buf2,BufferLimitado buf3, int cantidadproductos,boolean tipo,int etapa)
	{
		
		this.idProductos = id;
		this.buf = buf;
		this.buf2 = buf2;
		this.cantProductos = cantidadproductos;
		this.tipo = tipo;
		this.etapa = etapa;
		this.buf3 = buf3;

		
	}
	
	//MandarProducto este metodo manda los productos al primer buffer desde la etapa a el buffer siguiente.
	
	private void MandarProducto(int id,String message)
	{
		if(etapa==1) {
		this.buf.insertProductAzul("El producto con el id" + id + ", sale del proceso 0 en la etapa 1 a la etapa 2");
		}
		else if(etapa==2)  {
		System.out.println(message + ", recibido en etapa 2 por proceso 0");
		String mensaje = message + ", recibido en etapa 2 por proceso 0";
		this.buf2.insertProductAzul(mensaje  + " El producto sale de la etapa 2 a etapa 3");
		}
		else {
			System.out.println(message + ", recibido en etapa 3 por proceso 0");
			String mensaje = message + ", recibido en etapa 3 por proceso 0";
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
			while(!buf.isFinishedBuffer()||this.buf.hasProducts())
			{	
				
				String message = this.buf.recogerProductAzul();
				if (message != "")
				{
					int i=0;
					this.MandarProducto(i, message);
					cantProductos-=1;
				}
				else if (cantProductos<=0)
				{
					this.interrupt();
				}
			}
		}
		
		else
		{
			while(!buf2.isFinishedBuffer()||this.buf2.hasProducts())
			{	
				
				String message = this.buf2.recogerProductAzul();
				if (message != "")
				{
					int i=0;
					this.MandarProducto(i, message);
					cantProductos-=1;
				}
				else if (cantProductos<=0)
				{
					this.interrupt();
				}
				
			}
			
		}	
	}

}
