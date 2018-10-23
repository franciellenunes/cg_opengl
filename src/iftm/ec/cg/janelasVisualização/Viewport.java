/*
 * MIT License - Copyright (c) 2018 Francielle da Silva Nunes
 * Criada em 05 set 2018 
 */
package iftm.ec.cg.janelasVisualização;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.JFrame;

/**
 * Classe criada para estudo de viewport 
 * Desenho de estrelas em janelas separadas
 * @author franciellenunes
 */
public class Viewport implements GLEventListener {

    public static void main(String[] args) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(profile);

        Viewport star = new Viewport();
        GLCanvas canvas = new GLCanvas(cap);
        canvas.addGLEventListener(star);

        JFrame frame = new JFrame("Estrela");
        frame.getContentPane().add(canvas);
        frame.setSize(640, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        FPSAnimator animator = new FPSAnimator(canvas, 60);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D(-300, 300, -300, 300);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glViewport(0, 0, 128, 128);
        desenhaEstrela(gl);

        gl.glViewport(50, 10, 128, 128);
        desenhaEstrela(gl);

        gl.glViewport(100, 20, 128, 128);
        desenhaEstrela(gl);

        gl.glViewport(150, 10, 128, 128);
        desenhaEstrela(gl);

        gl.glViewport(200, 0, 128, 128);
        desenhaEstrela(gl);

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    public void desenhaEstrela(GL2 gl) {
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2i(30, 60);
        gl.glVertex2i(0, 150);
        gl.glVertex2i(-30, 60);
        gl.glVertex2i(-120, 60);
        gl.glVertex2i(-60, 0);
        gl.glVertex2i(-90, -90);
        gl.glVertex2i(0, -30);
        gl.glVertex2i(90, -90);
        gl.glVertex2i(60, 0);
        gl.glVertex2i(120, 60);
        gl.glEnd();
    }
}
