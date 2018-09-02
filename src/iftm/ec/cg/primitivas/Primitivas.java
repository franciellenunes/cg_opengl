/*
 * MIT License - Copyright (c) 2018 Francielle da Silva Nunes
 * Criada em 07 de Agosto de 2018 
 */
package iftm.ec.cg.primitivas;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;

/**
 * Classe criada para desenho de primitivas
 *
 * @author Francielle da Silva Nunes
 */
public class Primitivas implements GLEventListener {

    public static void main(String[] args) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities capabilities = new GLCapabilities(profile);

        GLCanvas canvas = new GLCanvas(capabilities);
        canvas.addGLEventListener(new Primitivas());

        JFrame frame = new JFrame("Primitivas");
        frame.getContentPane().add(canvas);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0f);
        gl.glMatrixMode(GL2.GL_MATRIX_MODE);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        // desenhaTresPontos(drawable);
        // desenhaSequenciaPontos(drawable);
        // desenhaLinhasParalelas(gl);
        // desenhaPoligono(gl);
        // desenhaBandeiraMinas(gl);
        desenhaCasinha(gl);
        
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    /**
     * Desenha três pontos na tela, com as diferentes cores do RGB
     *
     * @param drawable
     */
    public void desenhaTresPontos(GL2 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glPointSize(5f);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex2f(0.25f, 0.5f);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(0.5f, 0.0f);
        gl.glEnd();

        gl.glFlush();
    }

    /**
     * Desenha uma sequência de pontos na tela, com diferentes tamanhos
     *
     * @param drawable
     */
    public void desenhaSequenciaPontos(GL2 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glColor3f(1.0f, 1.0f, 1.0f);
        float x = -1.0f;
        for (int i = 1; i < 25; i++) {
            gl.glPointSize((float) (i / 2));
            gl.glBegin(GL.GL_POINTS);
            gl.glVertex2f(x, 0);
            gl.glEnd();
            x += 0.1;
        }

        gl.glFlush();
    }

    /**
     * Desenha linhas pararelas com padrões e fatores do LineStipple
     *
     * @param gl
     */
    public void desenhaLinhasParalelas(GL2 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        int patterns[] = {0x00FF, 0x00FF, 0x0C0F, 0x0C0F, 0xAAAA, 0xAAAA, 0xAAAA, 0xAAAA};
        int factors[] = {1, 2, 1, 3, 1, 2, 3, 4};

        gl.glLineWidth(1f);
        gl.glEnable(GL2.GL_LINE_STIPPLE);
        double f = -0.9;
        for (int i = 0; i < patterns.length; i++, f += 0.2) {
            gl.glLineStipple(factors[i], (short) patterns[i]);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(-1f, (float) f);
            gl.glVertex2f(1f, (float) f);
            gl.glEnd();
        }
        gl.glFlush();
    }

    /**
     * Desenha triângulo com cores diferentes em suas extremidades
     *
     * @param gl
     */
    public void desenhaPoligono(GL2 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex2f(0.0f, 0.0f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex2f(0.25f, 0.5f);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(0.5f, 0.0f);
        gl.glEnd();
    }

    /**
     * Desenha a bandeira do estado de Minas Gerais
     *
     * @param gl
     */
    public void desenhaBandeiraMinas(GL2 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glVertex2f(-0.7f, -0.6f);
        gl.glVertex2f(0.7f, -0.6f);
        gl.glVertex2f(0.7f, 0.6f);
        gl.glVertex2f(-0.7f, 0.6f);
        gl.glEnd();

        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex2f(-0.2f, -0.2f);
        gl.glVertex2f(0f, 0.2f);
        gl.glVertex2f(0.2f, -0.2f);
        gl.glEnd();
    }
    
    /**
     * Desenha uma casinha
     *
     * @param gl
     */
    public void desenhaCasinha(GL2 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glColor3f(1.0f, 1.0f, 1.0f);

        // Parte da frente da casa
        gl.glLineWidth(1f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(-0.8f, -0.6f);
        gl.glVertex2f(-0.2f, -0.6f);
        gl.glVertex2f(-0.2f, 0.2f);
        gl.glVertex2f(-0.8f, 0.2f);        
        gl.glEnd();
        
        // Porta
        gl.glLineWidth(1f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-0.6f, -0.6f);
        gl.glVertex2f(-0.4f, -0.6f);
        gl.glVertex2f(-0.4f, -0.3f);
        gl.glVertex2f(-0.6f, -0.3f);        
        gl.glEnd();
        
        // Telhado
        gl.glLineWidth(3f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(-0.9f, 0.2f);   
        gl.glVertex2f(-0.5f, 0.6f);   
        gl.glVertex2f(-0.1f, 0.2f); 
        gl.glEnd();
        
        // Parte lateral da casa
        gl.glLineWidth(1f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(-0.2f, -0.6f);
        gl.glVertex2f(0.8f, -0.4f);
        gl.glVertex2f(0.8f, 0.4f);
        gl.glVertex2f(-0.2f, 0.2f);
        gl.glEnd();
        
        // Janela
        gl.glLineWidth(1f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0.1f, -0.25f);
        gl.glVertex2f(0.5f, -0.20f);
        gl.glVertex2f(0.5f, 0.1f);
        gl.glVertex2f(0.1f, 0.05f);        
        gl.glEnd();
        
        // Telhado
        gl.glLineWidth(3f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-0.1f, 0.2f);   
        gl.glVertex2f(0.9f, 0.4f);
        gl.glVertex2f(0.5f, 0.8f);
        gl.glVertex2f(-0.5f, 0.6f);        
        gl.glEnd();

    }
}
