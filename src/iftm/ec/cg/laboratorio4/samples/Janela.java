package iftm.ec.cg.laboratorio4.samples;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.jogamp.opengl.util.FPSAnimator;

public class Janela
{
	private Renderer renderer;

	/**
	 * Construtor da classe ExemploJava que n�o recebe par�metros. Cria uma janela e insere  
	 * um componente canvas OpenGL.
	 */
	public Janela()
	{
		// Cria janela
		JFrame janela = new JFrame("Anima��o do Sistema Solar");   
		janela.setBounds(50,100,500,500); 
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		BorderLayout layout = new BorderLayout(); 
		Container caixa=janela.getContentPane();
		caixa.setLayout(layout); 

		// Cria um objeto GLCapabilities para especificar o n�mero de bits 
		// por pixel para RGBA
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities c = new GLCapabilities(profile);
		c.setRedBits(8);
		c.setBlueBits(8);
		c.setGreenBits(8);
		c.setAlphaBits(8); 

		// Cria o objeto que ir� gerenciar os eventos
		renderer = new Renderer();

		// Cria um canvas, adiciona na janela, e especifica o objeto "ouvinte" 
		// para os eventos Gl, de mouse e teclado
		// GLCanvas canvas = GLDrawableFactory.getFactory().createGLCanvas(c);
		GLCanvas canvas = new GLCanvas(c);
		janela.add(canvas,BorderLayout.CENTER);
		canvas.addGLEventListener(renderer);        
		canvas.addMouseListener(renderer);
		canvas.addKeyListener(renderer);
		janela.setVisible(true);
		canvas.requestFocus();
		
		FPSAnimator anim = new FPSAnimator(canvas,5);

		janela.addWindowListener(new WindowAdapter() {
	           public void windowClosing(WindowEvent e) {
	             anim.stop();
	             System.exit(0);
	           }
	         });		
		
	    anim.start();		
	}

	public static void main(String[] args) {
		new Janela();
	}	
}
