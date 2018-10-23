package iftm.ec.cg.laboratorio4.casinha;

/**
 * Classe Renderer, junto com a classe ExemploJava, mostra um exemplo de como
 * trabalhar com Java e OpenGL utilizando a API JOGL. � aberta uma janela na
 * qual � desenhada uma casinha. � poss�vel fazer zoom in e zoom out usando o
 * mouse, e mover a posi��o do observador virtual com as teclas de setas, HOME e
 * END.
 *
 * @author Marcelo Cohen
 * @version 1.0
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jogamp.opengl.DebugGL2;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

public class Renderer extends MouseAdapter implements GLEventListener, KeyListener {
    // Atributos

    private GL2 gl;
    private GLU glu;
    private GLUT glut;
    private GLAutoDrawable glDrawable;
    private double angle, fAspect;
    private float rotX, rotY, obsZ;

    /**
     * Construtor da classe Renderer que n�o recebe par�metros.
     */
    public Renderer() {
        // Especifica o �ngulo da proje��o perspectiva  
        angle = 60;
        // Posi��o do observador virtual
        rotX = 5;
        rotY = 0;
        obsZ = 10;
        // Inicializa o valor para corre��o de aspecto   
        fAspect = 1;
    }

    /**
     * M�todo definido na interface GLEventListener e chamado pelo objeto no
     * qual ser� feito o desenho logo ap�s a inicializa��o do contexto OpenGL.
     */
    public void init(GLAutoDrawable drawable) {
        glDrawable = drawable;
        gl = drawable.getGL().getGL2();
        // glu = drawable.getGLU();       
        glu = new GLU();
        glut = new GLUT();

        drawable.setGL(new DebugGL2(gl));

        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        // gl.glLightModeli(GL2.GL_LIGHT_MODEL_LOCAL_VIEWER, GL2.GL_TRUE);
        
        // Habilita Z-Buffer
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHTING);
    }

    /**
     * M�todo definido na interface GLEventListener e chamado pelo objeto no
     * qual ser� feito o desenho para come�ar a fazer o desenho OpenGL pelo
     * cliente.
     */
    public void display(GLAutoDrawable drawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        defineIluminacao();
        especificaParametrosVisualizacao();

        gl.glColor3f(0.0f, 0.0f, 1.0f);

        // Desenha uma casinha
        gl.glBegin(GL2.GL_QUADS);
        // Frente        
        float p1FC[] = {-1, 0, 1};
        float p2FC[] = {-1, 2, 1};
        float p3FC[] = {1, 2, 1};    
        float[] vetorNormalFC = getVetorNormal(p1FC,p2FC,p3FC);
        gl.glNormal3f(vetorNormalFC[0], vetorNormalFC[1], vetorNormalFC[2]);
        gl.glColor3f(0.3f, 0.5f, 0.4f);
        gl.glVertex3f(-1, 0, 1);
        gl.glVertex3f(-1, 2, 1);
        gl.glVertex3f(1, 2, 1);
        gl.glVertex3f(1, 0, 1);

        // Fundos 
        float p1TC[] = {-1, 0, -1};
        float p2TC[] = {1, 0, -1};
        float p3TC[] = {1, 2, -1};    
        float[] vetorNormalTC = getVetorNormal(p1TC,p2TC,p3TC);
        gl.glNormal3f(vetorNormalTC[0], vetorNormalTC[1], vetorNormalTC[2]);
        gl.glVertex3f(-1, 0, -1);
        gl.glVertex3f(1, 0, -1);
        gl.glVertex3f(1, 2, -1);
        gl.glVertex3f(-1, 2, -1);

        // Lado esquerdo        
        float p1EC[] = {-1, 0, 1};
        float p2EC[] = {-1, 2, 1};
        float p3EC[] = {-1, 2, -1};    
        float[] vetorNormalEC = getVetorNormal(p1EC,p2EC,p3EC);
        gl.glNormal3f(vetorNormalEC[0], vetorNormalEC[1], vetorNormalEC[2]);
        gl.glVertex3f(-1, 0, 1);
        gl.glVertex3f(-1, 2, 1);
        gl.glVertex3f(-1, 2, -1);
        gl.glVertex3f(-1, 0, -1);

        // Lado direito        
        float p1DC[] = {1, 0, 1};
        float p2DC[] = {1, 0, -1};
        float p3DC[] = {1, 2, -1};    
        float[] vetorNormalDC = getVetorNormal(p1DC,p2DC,p3DC);
        gl.glNormal3f(vetorNormalDC[0], vetorNormalDC[1], vetorNormalDC[2]);
        gl.glVertex3f(1, 0, 1);
        gl.glVertex3f(1, 0, -1);
        gl.glVertex3f(1, 2, -1);
        gl.glVertex3f(1, 2, 1);

        // Finaliza os 4 quads
        gl.glEnd();

        // Teto � uma pir�mide de base quadrangular
        gl.glBegin(GL2.GL_TRIANGLES);

        // Frente
        gl.glColor3f(0.8f, 0.2f, 0);
        float p1F[] = {-1, 2, 1};
        float p2F[] = {0, 4, 0};
        float p3F[] = {1, 2, 1};    
        float[] vetorNormalF = getVetorNormal(p1F,p2F,p3F);
        gl.glNormal3f(vetorNormalF[0], vetorNormalF[1], vetorNormalF[2]);
        gl.glVertex3f(-1, 2, 1);
        gl.glVertex3f(0, 4, 0);
        gl.glVertex3f(1, 2, 1);

        // Lado direito
        float p1D[] = {1, 2, 1};
        float p2D[] = {1, 2, -1};
        float p3D[] = {0, 4, 0};    
        float[] vetorNormalD = getVetorNormal(p1D,p2D,p3D);
        gl.glNormal3f(vetorNormalD[0], vetorNormalD[1], vetorNormalD[2]);
        gl.glVertex3f(1, 2, 1);
        gl.glVertex3f(1, 2, -1);
        gl.glVertex3f(0, 4, 0);

        // Traseira
        float p1T[] = {1, 2, 1};
        float p2T[] = {-1, 2, -1};
        float p3T[] = {0, 4, 0};    
        float[] vetorNormalT = getVetorNormal(p1T,p2T,p3T);
        gl.glNormal3f(vetorNormalT[0], vetorNormalT[1], vetorNormalT[2]);
        gl.glVertex3f(1, 2, -1);
        gl.glVertex3f(-1, 2, -1);
        gl.glVertex3f(0, 4, 0);

        // Lado esquerdo
        float p1E[] = {-1, 2, 1};
        float p2E[] = {0, 4, 0};
        float p3E[] = {-1, 2, -1};    
        float[] vetorNormalE = getVetorNormal(p1E,p2E,p3E);
        gl.glNormal3f(vetorNormalE[0], vetorNormalE[1], vetorNormalE[2]);
        gl.glVertex3f(-1, 2, 1);
        gl.glVertex3f(0, 4, 0);
        gl.glVertex3f(-1, 2, -1);

        // Finaliza os 4 tri�ngulos
        gl.glEnd();

        // Ch�o embaixo da casinha
        gl.glBegin(GL2.GL_QUADS);
        float p1CH[] = {-10, 0, -10};
        float p2CH[] = {10, 0, -10};
        float p3CH[] = {10, 0, 10};    
        float[] vetorNormalCH = getVetorNormal(p1CH,p2CH,p3CH);
        gl.glNormal3f(vetorNormalCH[0], vetorNormalCH[1], vetorNormalCH[2]);
        gl.glColor3f(0.2f, 0.7f, 0.3f);
        gl.glVertex3f(-10, 0, -10);
        gl.glVertex3f(10, 0, -10);
        gl.glVertex3f(10, 0, 10);
        gl.glVertex3f(-10, 0, 10);
        gl.glEnd();

    }

    /**
     * M�todo definido na interface GLEventListener e chamado pelo objeto no
     * qual ser� feito o desenho depois que a janela foi redimensionada.
     */
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        gl.glViewport(0, 0, width, height);
        fAspect = (float) width / (float) height;
    }

    /**
     * M�todo definido na interface GLEventListener e chamado pelo objeto no
     * qual ser� feito o desenho quando o modo de exibi��o ou o dispositivo de
     * exibi��o associado foi alterado.
     */
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    /**
     * Método usado para ajustar as propriedades da(s) fonte(s) de luz
     */
    public void defineIluminacao() {

        // Define os parâmetros através de vetores RGBA - o último valor deve ser sempre 1.0f
        float luzAmbiente[] = {0.2f, 0.2f, 0.2f, 1.0f};
        float luzDifusa[] = {0.7f, 0.7f, 0.7f, 1.0f};
        float luzEspecular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float posicaoLuz[] = {0.0f, 50.0f, 50.0f, 1.0f};

        // Brilho do material
        float especularidade[] = {1.0f, 1.0f, 1.0f, 1.0f};
        int especMaterial = 10;

        // Define a reflectância do material
        // gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, especularidade, 0);
        // Define a concentração do brilho
        gl.glMateriali(GL2.GL_FRONT, GL2.GL_SHININESS, especMaterial);

        // Ativa o uso da luz ambiente
        gl.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, luzAmbiente, 0);

        // Define os parâmetros da luz de número 0
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, luzAmbiente, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, luzDifusa, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, luzEspecular, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posicaoLuz, 0);

    }

    /**
     * M�todo usado para especificar a posi��o do observador virtual.
     */
    public void posicionaObservador() {
        // Especifica sistema de coordenadas do modelo
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        // Inicializa sistema de coordenadas do modelo  
        gl.glLoadIdentity();
        // Especifica posi��o do observador e do alvo   
        gl.glTranslatef(0.0f, 0.0f, -obsZ);
        gl.glRotatef(rotX, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(rotY, 0.0f, 1.0f, 0.0f);
    }

    /**
     * M�todo usado para especificar o volume de visualiza��o.
     */
    public void especificaParametrosVisualizacao() {
        // Especifica sistema de coordenadas de proje��o
        gl.glMatrixMode(GL2.GL_PROJECTION);
        // Inicializa sistema de coordenadas de proje��o
        gl.glLoadIdentity();

        // Especifica a proje��o perspectiva(angulo,aspecto,zMin,zMax)
        glu.gluPerspective(angle, fAspect, 0.5, 500);

        posicionaObservador();
    }

    /**
     * M�todo da classe MouseAdapter que est� sendo sobrescrito para gerenciar
     * os eventos de clique de mouse, de maneira que sej� feito zoom in e zoom
     * out.
     */
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) // Zoom in
        {
            if (angle >= 4) {
                angle -= 4;
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) // Zoom out
        {
            if (angle <= 72) {
                angle += 4;
            }
        }
        glDrawable.display();
    }

    /**
     * M�todo definido na interface KeyListener que est� sendo implementado
     * para, de acordo com as teclas pressionadas, permitir mover a posi��o do
     * observador virtual.
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                rotY++;
                break;
            case KeyEvent.VK_LEFT:
                rotY--;
                break;
            case KeyEvent.VK_UP:
                rotX++;
                break;
            case KeyEvent.VK_DOWN:
                rotX--;
                break;
            case KeyEvent.VK_HOME:
                obsZ++;
                break;
            case KeyEvent.VK_END:
                obsZ--;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
        glDrawable.display();
    }

    /**
     * M�todo definido na interface KeyListener.
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * M�todo definido na interface KeyListener.
     */
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void dispose(GLAutoDrawable arg0) {
        // TODO Auto-generated method stub
    }

    private float[] getVetorNormal(float[] p1, float[] p2, float[] p3) {
        float[] vet1 = new float[3];
        float[] vet2 = new float[3];
        vet1[0] = p1[0] - p2[0];
        vet1[1] = p1[1] - p2[1];
        vet2[2] = p1[2] - p2[2];
        vet2[0] = p3[0] - p2[0];
        vet2[1] = p3[1] - p2[1];
        vet2[2] = p3[2] - p2[2];
        float[] n = calcProdVetorial(vet1, vet2);
        getNormaliza(n);
        return n;
    }

    private void getNormaliza(float[] vet) {
        float length = (float) Math.sqrt(vet[0] * vet[0] + vet[1] * vet[1] + vet[2]
                * vet[2]);
        if (length > 0) {
            vet[0] /= length;
            vet[1] /= length;
            vet[2] /= length;
        }
    }

    private float[] calcProdVetorial(float[] p1, float[] p2) {
        float[] normal = new float[3];
        normal[0] = (p1[1] * p2[2]) - (p1[2] * p2[1]);
        normal[1] = (p1[2] * p2[0]) - (p1[0] * p2[2]);
        normal[2] = (p1[0] * p2[1]) - (p1[1] * p2[0]);
        return normal;
    }
}
