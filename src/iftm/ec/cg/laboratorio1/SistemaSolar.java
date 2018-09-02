/*
 * MIT License - Copyright (c) 2018 Francielle da Silva Nunes
 * Criada em 02 set 2018 
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
 * Classe criada para realização de exercício prático intitulado Laboratório 1 
 * O objetivo é aplicar os conceitos apresentados até o momento na disciplina
 * Desenho de esferas formando um sistema solar
 *
 * @author franciellenunes
 */
public class SistemaSolar implements GLEventListener, KeyListener {

    private float alpha;
    private float beta;

    float[] m = {1, 0.5f, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};

    public SistemaSolar() {
        alpha = 0;
        beta = 0;
    }

    public static void main(String[] args) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);

        GLCapabilities caps = new GLCapabilities(profile);
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);

        SistemaSolar render = new SistemaSolar();
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

        // Sol
        gl.glPushMatrix();
        gl.glColor3f(1f, 1f, 0);
        glut.glutWireSphere(0.25f, 20, 20);
        gl.glPopMatrix();
        
        // Mercúrio
        gl.glPushMatrix();
        gl.glColor3f(0.230f, 0.184f, 0.117f);
        gl.glTranslatef(0.3f, 0.25f, 0);
        glut.glutWireSphere(0.03f, 20, 20);
        gl.glPopMatrix();
        
        // Vênus
        gl.glPushMatrix();        
        gl.glColor3f(0.239f, 0.171f, 0.074f);
        gl.glTranslatef(0.45f, 0.3f, 0);
        glut.glutWireSphere(0.06f, 20, 20);
        gl.glPopMatrix();
        
        // Terra
        gl.glPushMatrix();                
        gl.glColor3f(0.0f, 1f, 0.0f);
        gl.glTranslatef(0.6f, 0.35f, 0);
        glut.glutWireSphere(0.06f, 20, 20);
        gl.glPopMatrix();
        
        // Lua
        gl.glPushMatrix();                
        gl.glColor3f(1f, 1f, 1f);
        gl.glTranslatef(0.68f, 0.35f, 0);
        glut.glutWireSphere(0.01f, 20, 20);
        gl.glPopMatrix();
        
        // Marte
        gl.glPushMatrix();           
        gl.glColor3f(0.222f, 0.073f, 0.028f);
        gl.glTranslatef(0.80f, 0.40f, 0);
        glut.glutWireSphere(0.05f, 20, 20);
        gl.glPopMatrix();
        
        // Jupiter
        gl.glPushMatrix();           
        gl.glColor3f(0.222f, 0.073f, 0.028f);
        gl.glTranslatef(0.80f, 0.40f, 0);
        glut.glutWireSphere(0.05f, 20, 20);
        gl.glPopMatrix();
        
        // Saturno
        gl.glPushMatrix();           
        gl.glColor3f(1f, 1f, 1f);
        gl.glTranslatef(-0.80f, 0.40f, 0);
        
        glut.glutWireTorus(0.05f, 0.10f, 30, 35);
        
        gl.glColor3f(1f, 0f, 0f);
        glut.glutWireSphere(0.05f, 20, 20);
        gl.glPopMatrix();
         
        // Urano
        gl.glPushMatrix();           
        gl.glColor3f(0.222f, 0.073f, 0.028f);
        gl.glTranslatef(0.80f, 0.40f, 0);
        glut.glutWireSphere(0.05f, 20, 20);
        gl.glPopMatrix();
        
        // Netuno
        gl.glPushMatrix();           
        gl.glColor3f(0.222f, 0.073f, 0.028f);
        gl.glTranslatef(0.80f, 0.40f, 0);
        glut.glutWireSphere(0.05f, 20, 20);
        gl.glPopMatrix();
                
        gl.glFlush();
    }
}
