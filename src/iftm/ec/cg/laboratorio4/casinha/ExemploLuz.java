package iftm.ec.cg.laboratorio4.casinha;

/**
 * Classe ExemploLuz, junto com a classe Renderer, mostra um exemplo de 
 * como trabalhar com Java e OpenGL utilizando a API JOGL. � aberta uma 
 * janela na qual � desenhada uma casinha com ilumina��o. � poss�vel fazer 
 * zoom in e zoom out usando o mouse, e mover a posi��o do observador 
 * virtual com as teclas de setas, HOME e END.
 * 
 * @author Marcelo Cohen
 * @version 1.0
 */

import java.awt.BorderLayout;
import java.awt.Container;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ExemploLuz
{
	private Renderer renderer;

	/**
	 * Construtor da classe ExemploLuz que n�o recebe par�metros. Cria uma janela e insere  
	 * um componente canvas OpenGL.
	 */
	public ExemploLuz()
	{
		// Cria janela
		JFrame janela = new JFrame("Iluminacao em OpenGL");   
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
	}

	/**
	 * M�todo main que apenas cria um objeto ExemploLuz
	 */
	public static void main(String args[])
	{
		new ExemploLuz();
	}
}
