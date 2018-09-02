/*
 * MIT License - Copyright (c) 2018 Francielle da Silva Nunes
 * Criada em 01 set 2018 
 */
package iftm.ec.cg.laboratorio1;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 * Classe criada para realização de exercício prático intitulado Laboratório 1 O
 * objetivo é aplicar os conceitos apresentados até o momento na disciplina
 * Desenho de quadrados e aplicação de rotações e escala
 *
 * @author franciellenunes
 */
public class Triangulo implements GLEventListener, KeyListener {

    public static float[] m = {1, 0, 0, 0, -1, 0, 0, 0, 1};

    public static void main(String[] args) {

        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);

        Triangulo render = new Triangulo();
        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(render);

        JFrame frame = new JFrame("Triângulo");
        frame.addKeyListener(render);
        frame.getContentPane().add(canvas);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FPSAnimator animator = new FPSAnimator(canvas, 30);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0);
        gl.glClearDepth(1.0f);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        drawTriangle(gl);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int heigth) {

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public void drawTriangle(GL2 gl) {
        // gl.glMultMatrixf(m, 0);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(-0.25f, -0.25f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex2f(0, 0.25f);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex2f(0.25f, -0.25f);
        gl.glEnd();

        gl.glFlush();
    }
}
