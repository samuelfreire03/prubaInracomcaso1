package Caso1Folder;

import java.util.Random;

public class ProcesoNaranja extends Thread{
	
	private Numero idProductos;
	
	private BufferLimitado buf1;
	
	private BufferLimitado buf2;
	
	private int cantProductos;
	
	private BufferLimitado buf3;
	
	private int mensajesprocesadoset2;
	
	private int mensajesprocesadoset3;
	
	private int etapa;
	
	public ProcesoNaranja(Numero id,BufferLimitado buf1,BufferLimitado buf2,BufferLimitado buf3,int cantidadproductos,int etapa)
	{
		
		this.idProductos = id;
		this.buf1 = buf1;
		this.buf2=buf2;
		this.cantProductos = cantidadproductos;
		this.mensajesprocesadoset2 = cantidadproductos;
		this.mensajesprocesadoset3 = cantidadproductos;
		this.etapa = etapa;
		this.buf3 = buf3;

		
	}
	
	private void MandarProducto(int id,String message)
	{

		//Verificacion de etapa
		
		if(etapa==1) 
		{

			//Creacion del producto e insercion en el buffer 1 o etapa 2
			
			while(!this.buf1.insertProductNaranja("El producto con el id" + id + ", sale del proceso 1; Progreso producto: 25% [#####---------------] Producto Creado ",this)) 
			{
				this.yield();
			}
		}
		else if(etapa==2)  
		{
			//Implementacion de transformacion

			Random random = new Random();
        	int randomNumber = random.nextInt(451) + 50;

			try {
				this.sleep(randomNumber);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Insercion de producto en buffer2 o etapa 3
			
			System.out.println(message + ", recibido en etapa 2 por proceso 1 \n");
			int indexfinal = message.indexOf(";");
			while(!this.buf2.insertProductNaranja(message.substring(0,indexfinal) + "; Progreso producto: 50% [##########----------] Producto creado,Listo Etapa 2 TiempoTranformacion: " + randomNumber + " ms",this)) 
			{
				this.yield();
			}
		}
		else 
		{

			//Implementacion de transformacion

			Random random = new Random();
        	int randomNumber = random.nextInt(451) + 50;

			try {
				this.sleep(randomNumber);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Insercion de producto en buffer3 o etapa final

			System.out.println(message + ", recibido en etapa 3 por proceso 1 \n");
			int indexfinal = message.indexOf(";");
			this.buf3.insertEtapaFinal(message.substring(0,indexfinal) + "; Progreso producto: 75% [###############-----] Producto creado,Listo Etapa 2,Listo Etapa 3 TiempoTranformacion: " + randomNumber + " ms");

		}
	}
	
	
	@Override
	public void run() 
	{
		//Primera etapa 
		
		if (etapa==1)
		{
			//Crea la cantidad de productos especificada

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
	
			while(mensajesprocesadoset2>0)
			{	
				//Recoge los productos y los manda a etapa 3

				String message = this.buf1.recogerProductNaranja(this);
				if (message != "")
				{
					int i=0;
					this.MandarProducto(i, message);
					mensajesprocesadoset2--;
				}
				else {
					this.yield();
				}
			}
			
		}
		
		else
		{
	
			while(mensajesprocesadoset3>0)
			{	
				//Recoge los productos y los manda a etapa final

				String message = this.buf2.recogerProductNaranja(this);
				if (message != "")
				{
					int i=0;
					this.MandarProducto(i, message);
					mensajesprocesadoset3--;
				}
				else {
					this.yield();
				}
			}
		}
	}

}