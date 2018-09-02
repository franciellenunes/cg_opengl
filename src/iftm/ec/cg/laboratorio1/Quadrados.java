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
public class Quadrados implements GLEventListener, KeyListener {

    private float scale;
    private float angle;
    private float alpha;
    private float beta;
    private float delta;

    float[] m = {1, 0.5f, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};

    public Quadrados() {
        scale = 1.0f;
        angle = 0.0f;
        alpha = 0;
        beta = 0;
        delta = 1.f;
    }

    public static void main(String[] args) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);

        Quadrados render = new Quadrados();
        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(render);

        JFrame frame = new JFrame("Quadrados");
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

        drawSquares(gl);
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
            case KeyEvent.VK_ADD:
                scale = scale + 0.1f;
                break;
            case KeyEvent.VK_MINUS:
                if (scale > 0.1) {
                    scale = scale - 0.1f;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (angle > -360) {
                    angle = angle - 10.0f;
                } else {
                    angle = 0;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (angle < 360) {
                    angle = angle + 10.0f;
                } else {
                    angle = 0;
                }
                break;           
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    public void drawSquares(GL2 gl) {
        gl.glScalef(scale, scale, 1.0f);
        gl.glRotatef(angle, 1.0f, 0.0f, 0.0f);
        gl.glMultMatrixf(m, 0);
        gl.glColor3f(1.0f, 1.0f, 1.0f);

        // Quadrado 1
        gl.glRectf(-0.5f, -0.5f, 0.5f, 0.5f);

        // Quadrado 2
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glRectf(0.25f, 0.25f, 0.75f, 0.75f);

        gl.glFlush();
    }   
}
