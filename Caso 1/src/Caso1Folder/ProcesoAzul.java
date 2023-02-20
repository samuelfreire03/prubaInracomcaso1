package Caso1Folder;

import java.util.Random;

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
		if(etapa==1) 
		{

			this.buf.insertProductAzul("El producto con el id" + id + ", sale del proceso 0. Progreso producto: 25% [#####---------------] Producto Creado");
		}
		else if(etapa==2)  
		{
			Random random = new Random();
        	int randomNumber = random.nextInt(451) + 50;

			try {
				this.sleep(randomNumber);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(message + ", recibido etapa 2 por proceso 0 \n");
			this.buf2.insertProductAzul(message.substring(0,43) + " Progreso producto: 50% [##########----------] Producto creado,Listo Etapa 2 TiempoTranformacion: " + randomNumber + " ms");
		}
		else 
		{
			Random random = new Random();
        	int randomNumber = random.nextInt(451) + 50;

			try {
				this.sleep(randomNumber);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(message + ", recibido etapa 3 por proceso 0 \n");
			this.buf3.insertEtapaFinal(message.substring(0,43) + " Progreso producto: 75% [###############-----] Producto creado,Listo Etapa 2,Listo Etapa 3 TiempoTranformacion: " + randomNumber + " ms");
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
				
			}
			
		}	
	}

}
