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
 * Desenho de esferas e aplicação de rotação em cada uma delas
 *
 * @author franciellenunes
 */
public class Esferas implements GLEventListener, KeyListener {

    private float alpha;
    private float beta;

    float[] m = {1, 0.5f, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};

    public Esferas() {
        alpha = 0;
        beta = 0;
    }

    public static void main(String[] args) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);

        Esferas render = new Esferas();
        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(render);

        JFrame frame = new JFrame("Esferas");
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
        GLUT glut = new GLUT();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        drawSpheres(gl, glut);
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
                alpha = alpha - 1;
                break;
            case KeyEvent.VK_DOWN:
                alpha = alpha + 1;
                break;
            case KeyEvent.VK_LEFT:
                beta = beta + 1;
                break;
            case KeyEvent.VK_RIGHT:
                beta = beta - 1;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public void drawSpheres(GL2 gl, GLUT glut) {

        // Esfera 1
        gl.glPushMatrix();
        gl.glColor3f(1f, 1f, 0);
        gl.glTranslatef(-0.5f, 0, 0);
        gl.glRotatef(beta, 0, 1f, 0);
        gl.glRotatef(alpha, 1f, 0, 0);
        glut.glutWireSphere(0.25f, 20, 20);
        gl.glPopMatrix();

        // Esfera 2
        gl.glPushMatrix();
        gl.glColor3f(1f, 0, 0);
        gl.glTranslatef(0.5f, 0, 0);
        gl.glRotatef(beta, 0, 1f, 0);
        gl.glRotatef(alpha, 1f, 0, 0);
        glut.glutWireSphere(0.25f, 20, 20);
        gl.glPopMatrix();

        gl.glFlush();
    }
}
