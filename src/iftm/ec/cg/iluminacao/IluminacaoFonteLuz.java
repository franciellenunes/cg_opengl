package iftm.ec.cg.iluminacao;

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
import javax.swing.JFrame;

/**
 *
 * @author franciellenunes
 */
public class IluminacaoFonteLuz implements GLEventListener {
    
    public static void main(String[] args) {
        IluminacaoFonteLuz ifl = new IluminacaoFonteLuz();
        
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities cap = new GLCapabilities(profile);
        
        GLCanvas canvas = new GLCanvas(cap);
        canvas.addGLEventListener(ifl);
        
        JFrame frame = new JFrame("Iluminação");
        frame.add(canvas);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();
        
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // define a cor de fundo
        gl.glEnable(GL.GL_DEPTH_TEST); // habilita o teste de profundidade
        
        gl.glMatrixMode(GL2.GL_MODELVIEW); // define que a matriz é a model view        
        gl.glLoadIdentity(); // carrega a matriz identidade
        glu.gluLookAt(0.0, 0.0, 1.0, // posição da câmera
                0.0, 0.0, 0.0, // para onde a câmera aponta
                0.0, 1.0, 0.0); // vetor view-up
        gl.glMatrixMode(GL2.GL_PROJECTION); // define que a matriz é a de projeção
        gl.glLoadIdentity();
        gl.glOrtho(-2.0, 2.0, -2.0, 2.0, -2.0, 2.0);
        
        lighting(drawable);        
    }    

    @Override
    public void dispose(GLAutoDrawable glad) {        
    }

    @Override
    public void display(GLAutoDrawable drawable) {        
        GL2 gl = drawable.getGL().getGL2();
        GLUT glut = new GLUT();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT); // limpa o buffer
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        glut.glutSolidSphere(1.5, 40, 40);
        
        gl.glFlush();        
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {        
    }
    
    public void lighting(GLAutoDrawable drawable){
        
        GL2 gl = drawable.getGL().getGL2();
        
        float position[] = {2.0f, 2.0f, 2.0f, 1.0f}; // fonte local
        float white[] = {1.0f, 1.0f, 1.0f, 1.0f}; // fonte local
        float black[] = {1.0f, 1.0f, 1.0f, 1.0f}; // fonte local
        
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, black, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, white, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, white, 0);
        
        // Holofótes
        /* float position1[] = {-2.0f, 0.0f, 0.0f, 1.0f};
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position1, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, white, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, white, 0);     
        
        float direction[] = {1.0f, 0.0f, 0.0f};
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPOT_DIRECTION, direction, 0); // vetor direção
        gl.glLightf(GL2.GL_LIGHT1, GL2.GL_SPOT_CUTOFF, 45.0f); // espalhamento angular
        gl.glLightf(GL2.GL_LIGHT1, GL2.GL_SPOT_EXPONENT, 0.5f); // atenuação angular */
        
        // Atenuação Radial
        /* gl.glLightf(GL2.GL_LIGHT0, GL2.GL_CONSTANT_ATTENUATION, 0.5f); 
        gl.glLightf(GL2.GL_LIGHT0, GL2.GL_LINEAR_ATTENUATION, 0.5f);
        gl.glLightf(GL2.GL_LIGHT0, GL2.GL_QUADRATIC_ATTENUATION, 0.1f); */   
        
        // ativa a iluminação
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);
    }
    
}
