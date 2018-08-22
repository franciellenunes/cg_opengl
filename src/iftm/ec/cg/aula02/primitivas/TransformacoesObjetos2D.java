/*
 * Criada em 21 de Agosto de 2018 
 */
package iftm.ec.cg.aula02.primitivas;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author franciellenunes
 */
public class TransformacoesObjetos2D implements GLEventListener, KeyListener {
    private float transX;
    private float transY;
    private float scaleX;
    private float scaleY;
    private float angle;

    public TransformacoesObjetos2D() {
        transX = 0f;
        transY = 0f;
        scaleX = 1f;
        scaleY = 1f;
        angle = 0f;
    }

    public static void main(String[] args) {
        // acelera o rendering
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(profile);
        cap.setHardwareAccelerated(true);

        // cria o painel e adiciona um ouvinte GLEventListener
        TransformacoesObjetos2D render = new TransformacoesObjetos2D();
        GLCanvas canvas = new GLCanvas(cap);
        canvas.addGLEventListener(render);

        // cria uma janela e adiciona o painel
        JFrame frame = new JFrame("Transformações Geomátricas 2D");
        frame.getContentPane().add(canvas);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // inicializa o sistema e chama display() a 30fps
        FPSAnimator animator = new FPSAnimator(canvas, 30);
        frame.setVisible(true);
        frame.addKeyListener(render);
        animator.start();
    }

    public void drawTriangle(GL2 gl) {
        gl.glBegin(GL2.GL_POLYGON);
        gl.glColor3f(0.0f, 0.0f, 1.0f);
        gl.glVertex2f(25f, 50f);
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glVertex2f(50f, 0f);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glVertex2f(0f, 0f);
        gl.glEnd();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 0);
        gl.glClearDepth(1.0f);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU glu = new GLU();
        glu.gluOrtho2D(-100.0, 100.0, -100, 100.0);
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
        gl.glTranslatef(transX, transY, 0);
        gl.glScalef(scaleX, scaleY, 1.0f);
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
        drawTriangle(gl);

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int i, int i1, int i2, int i3) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                transX -= 10;
                break;
            case KeyEvent.VK_RIGHT:
                transX += 10;
                break;
            case KeyEvent.VK_UP:
                transY += 10;
                break;
            case KeyEvent.VK_DOWN:
                transY -= 10;
                break;
            case KeyEvent.VK_ADD:
                scaleY += 0.5;
                scaleX += 0.5;
                break;
            case KeyEvent.VK_SUBTRACT:                
                scaleY -= 0.5;
                scaleX -= 0.5;
                break;
            case KeyEvent.VK_ENTER:
                if(angle < 360){ angle += 45f; } 
                else { angle = 0; }                
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {        
    }

    @Override
    public void keyReleased(KeyEvent ke) {        
    }
    
}
