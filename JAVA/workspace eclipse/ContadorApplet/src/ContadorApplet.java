
import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class ContadorApplet extends Applet implements Runnable, ActionListener {

	//Propiedades
	private Thread h1;
	private Thread h2;
	private Font fuente;
	long CONTADOR=1;
	long CONTADOR2=3;
	private boolean parar,parar2;
	private Button b1,b2; //botones del Applet
	
	
	
	public void start(){
		if (h1!=null && h2!=null && h2.isAlive() && h1.isAlive()){ //si el hilo está corriendo y vivo no hago nada
				}
		else
		{
			h1=new Thread(this);
			h1.start();
			h2=new Thread(this);
			h2.start();
		}
		
	}	
	
	
	
	
	//método init
	public void init(){
		setBackground(Color.yellow);//color de fondo
		
		//añado botón 1 y su listener
		add(b1=new Button("Parar contador 1"));
		b1.addActionListener(this);
				
		//añado botón 2 y su listener
		add(b2=new Button("Parar contador 2"));
		b2.addActionListener(this);
		
		fuente=new Font("Verdana",Font.BOLD,26); //tipo de letra
		
		run();
	}
	

	public void run() {
		//inicializo parar a falso
		parar=false;
		
		//recojo hiloActual
		Thread hiloActual=Thread.currentThread();
		
		while (h1==hiloActual || h2==hiloActual){
			try{
				Thread.sleep(1000);
			}catch (InterruptedException e){e.printStackTrace();}
			repaint();
			if(h1!=null){
				CONTADOR++;
			}
			if(h2!=null)
				CONTADOR2++;
		}//fin while
		
	}//fin run
	
	
	public void paint(Graphics g){
		g.setFont(fuente);
		g.drawString(Long.toString((long)CONTADOR), 60, 100);//escribe contador
		g.drawString(Long.toString((long)CONTADOR2), 60, 150);
		
	}
	
	
	//parar controlar pulsación botones	
	public void actionPerformed(ActionEvent e) {
		b1.setLabel("Parar hilo 1");
		
		if (e.getSource()==b1){//comienzo
			h1=null;
		} else if (e.getSource()==b2){ //parada
				h2=null;
		} 
		
		
	}//actionperformed
	
	public void stop(){
		
		h1=null;
	}

	
	





}