
import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class pelota extends Applet implements Runnable, ActionListener {

	//Propiedades
	private Thread h1;
	private Font fuente;
	long CONTADOR=1;
	private boolean parar;
	private Button b1; //botones del Applet
	private int x= 60;
	private int y= 150;
	//int ancho = Integer.parseInt(this.getParameter("WIDTH"));
	boolean izquierda = false;
	
	
	public void start(){
		if (h1!=null && h1.isAlive()){ //si el hilo está corriendo y vivo no hago nada
				}
		else
		{
			h1=new Thread(this);
			h1.start();
		}
		
	}	
	
	
	
	
	//método init
	public void init(){
		setBackground(Color.yellow);//color de fondo
		
		//añado botón 1 y su listener
		add(b1=new Button("Parar pelota"));
		b1.addActionListener(this);
		
		fuente=new Font("Verdana",Font.BOLD,26); //tipo de letra
		
		run();
	}
	

	public void run() {
		//inicializo parar a falso
		parar=false;
		int ancho = Integer.parseInt(this.getParameter("WIDTH"));
		ancho = ancho-20;
		//recojo hiloActual
		Thread hiloActual=Thread.currentThread();
		
		while (h1==hiloActual){
			try{
				Thread.sleep(300);
			}catch (InterruptedException e){e.printStackTrace();}
			repaint();
			if(h1!=null){
				if(!izquierda){
					x+=20;
				}else{
					x-=20;
				}
				if(x>=ancho || izquierda==true){
					izquierda=true;
				}else{
					izquierda=false;
				}
				if(x<=0 || izquierda==false){
					izquierda=false;
				}else{
					izquierda=true;
				}
			}
			
		}//fin while
		
	}//fin run
	
	
	public void paint(Graphics g){
		g.setFont(fuente);
		//g.drawString(Long.toString((long)CONTADOR), 60, 100);//escribe contador
		
		 //int ancho = Integer.parseInt(this.getParameter("WIDTH")); 
		 //int alto = Integer.parseInt(this.getParameter("HEIGHT")); 
		 int diam = 20;
		 
		 g.fillArc(x,y,diam,diam,0,360); 
		
	}
	
	
	//parar controlar pulsación botones	
	public void actionPerformed(ActionEvent e) {
		b1.setLabel("Parar pelota");
		
		if (e.getSource()==b1){//comienzo
			h1=null;
		}
		
		
	}//actionperformed
	
	public void stop(){
		
		h1=null;
	}

	
	





}