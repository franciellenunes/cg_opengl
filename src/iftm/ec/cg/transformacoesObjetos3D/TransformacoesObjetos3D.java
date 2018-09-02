/*
 * MIT License - Copyright (c) 2018 Francielle da Silva Nunes
 * Criada em 01 set 2018 
 */
package iftm.ec.cg.transformacoesObjetos3D;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import iftm.ec.cg.transformacoesObjetos2D.TransformacoesObjetos2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 * Classe criada para utilização de transformações em Objetos 3D
 * 
 * @author franciellenunes
 */
public class TransformacoesObjetos3D implements GLEventListener, KeyListener {

    private float alpha;
    private float beta;
    private float delta;

    public TransformacoesObjetos3D() {
        alpha = 0;
        beta = 0;
        delta = 1.f;
    }
    
    public static void main(String[] args) {
        
        GLProfile profile = GLProfile.get(GLProfile.GL2); // specifies the the OpenGL profile
        GLCapabilities caps = new GLCapabilities(profile); // describing the desired capabilities that a rendering context must support
        caps.setDoubleBuffered(true);
        caps.setHardwareAccelerated(true);
        
        TransformacoesObjetos3D render = new TransformacoesObjetos3D();
        GLCanvas canvas = new GLCanvas(caps); // is a class for displaying OpenGL graphics
        canvas.addGLEventListener(render);
        
        JFrame frame = new JFrame("Transformações Geométricas 3D");
        frame.addKeyListener(render);
        frame.getContentPane().add(canvas);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        FPSAnimator animator = new FPSAnimator(canvas, 30);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);        
        animator.start();
    }

    /**
     * This method is called immediately after the OpenGL context is initialized
     * for the  first time, which is a system event. It can be used to perform
     * one-time OpenGL initialization
     * @param drawable 
     */
    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0);
        gl.glEnable(GL2.GL_DEPTH_TEST);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-5f, 5f, -5f, 5f, -5f, 5f);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    /**
     * This method is called to initiate OpenGL rendering when program starts.
     * It is called afterwards when reshape event happens
     * @param drawable 
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2(); // interface to OpenGL
        GLUT glut = new GLUT(); //  It is used to create windows that serve as simple frames for OpenGL drawing surfaces.

        /* glClear: sets the bitplane area of the window to values previously selected by glClearColor, glClearIndex, glClearDepth, glClearStencil, and glClearAccum. 
            GL_COLOR_BUFFER_BIT: Indicates the buffers currently enabled for color writing.
            GL_DEPTH_BUFFER_BIT: Indicates the depth buffer.
         */
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        /* glMatrixMode: Specifies which matrix stack is the target for subsequent matrix operations.
        GL_MODELVIEW: SApplies subsequent matrix operations to the modelview matrix stack.
         */
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        /* glLoadIdentity: replaces the current matrix with the identity matrix. */
        gl.glLoadIdentity();

        /* glRotate: produces a rotation of angle degrees around the vector x y z */
        gl.glRotatef(beta, 0, 1f, 0);
        gl.glRotatef(alpha, 1f, 0, 0);
        
        /* glScale: produces a nonuniform scaling along the x, y, and z axes. */
        gl.glScalef(delta, delta, delta); 
        
        /* glColor: set the current color */
        gl.glColor3f(1f, 1f, 0);
        
        /* glutWireSphere: render a solid or wireframe sphere respectively */
        glut.glutWireSphere(1.0f, 20, 20);

        /* glTranslate: produces a translation by x y z */
        gl.glTranslatef(0, -1f, 0);
        gl.glScalef(4, 0.1f, 4);
        gl.glColor3f(0, 0, 1f);
        
        /* glutSolidCube: render a solid or wireframe cube respectively */
        glut.glutSolidCube(1.0f);

        /* Different GL implementations buffer commands in several different locations, 
        including network buffers and the graphics accelerator itself. 
        glFlush empties all of these buffers */
        gl.glFlush();
    }

    /**
     * This method is called if canvas has been resized, which happens when the user
     * changes the size of the window.
     * @param drawable
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_PAGE_UP:
                delta = delta * 1.1f;
                break;
            case KeyEvent.VK_PAGE_DOWN:
                delta = delta * 0.5f;
                break;
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

}
