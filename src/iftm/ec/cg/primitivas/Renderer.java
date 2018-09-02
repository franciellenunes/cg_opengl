/*
 * MIT License - Copyright (c) 2018 Francielle da Silva Nunes
 * Criada em 14 de Agosto de 2018 
 */
package iftm.ec.cg.primitivas;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import java.awt.Color;
import javax.swing.JFrame;

/**
 * Classe criada para aplicação de diferentes padrões de polígonos.
 * Permite o desenho de um casinha com diferentes padrões de preenchimento.
 * @author Francielle da Silva Nunes
 */
public class Renderer implements GLEventListener {

    public static void main(String[] args) {
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        GLCanvas canvas = new GLCanvas(caps);
        canvas.setSize(900, 500);
        canvas.addGLEventListener(new Renderer());

        JFrame frame = new JFrame("Aplicacao JOGL");
        frame.getContentPane().add(canvas);
        frame.setSize(900, 500);
        frame.setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 0);
        gl.glClearDepth(1.0f);
        gl.glMatrixMode(GL2.GL_MATRIX_MODE);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable glad) { }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        desenhaCasinha(gl);
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) { }

    /**
     * Desenha uma casinha
     *
     * @param gl
     */
    public void desenhaCasinha(GL2 gl) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // Parte da frente da casa
        gl.glLineWidth(1f);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glPolygonMode(GL.GL_FRONT, GL2.GL_FILL);
        gl.glRectf(-0.8f, -0.6f, -0.2f, 0.2f);
        // Borda direita
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glLineWidth(3f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(-0.2f, -0.6f);   
        gl.glVertex2f(-0.2f, 0.2f);
        gl.glEnd();

        // Porta da frente
        gl.glLineWidth(1f);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glRectf(-0.6f, -0.6f, -0.4f, -0.3f);

        // Telhado da frente
        gl.glLineWidth(3f);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glEnable(GL2.GL_POLYGON_STIPPLE);
        gl.glPolygonStipple(getMaskHalftone(), 0);
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex2f(-0.9f, 0.2f);
        gl.glVertex2f(-0.5f, 0.6f);
        gl.glVertex2f(-0.1f, 0.2f);
        gl.glEnd();
        gl.glDisable(GL2.GL_POLYGON_STIPPLE);
        // Borda do telhado
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_LINE_LOOP);
        gl.glVertex2f(-0.9f, 0.2f);   
        gl.glVertex2f(-0.5f, 0.6f);   
        gl.glVertex2f(-0.1f, 0.2f); 
        gl.glEnd();

        // Parte lateral da casa
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glBegin(GL2.GL_POLYGON);
        gl.glVertex2f(-0.2f, -0.6f);
        gl.glVertex2f(0.8f, -0.4f);
        gl.glVertex2f(0.8f, 0.4f);
        gl.glVertex2f(-0.2f, 0.2f);
        gl.glEnd();

        // Janela da lateral
        gl.glLineWidth(1f);
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(0.1f, -0.25f);
        gl.glVertex2f(0.5f, -0.19f);
        gl.glVertex2f(0.5f, 0.1f);
        gl.glVertex2f(0.1f, 0.05f);
        gl.glEnd();

        // Telhado da lateral
        gl.glLineWidth(3f);
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glEnable(GL2.GL_POLYGON_STIPPLE);
        gl.glPolygonStipple(getMaskHalftone(), 0);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-0.1f, 0.2f);
        gl.glVertex2f(0.9f, 0.4f);
        gl.glVertex2f(0.5f, 0.8f);
        gl.glVertex2f(-0.5f, 0.6f);
        gl.glEnd();
        gl.glDisable(GL2.GL_POLYGON_STIPPLE);
    }

    private byte[] getMaskHalftone() {
        byte[] mask = new byte[128];
        byte m = (byte) 0xAA;
        for (int i = 0; i < 128; i++) {
            if (i % 4 == 0) {
                if (m == (byte) 0xAA) {
                    m = (byte) 0x55;
                } else {
                    m = (byte) 0xAA;
                }
            }
            mask[i] = m;
        }
        return mask;
    }

    private byte[] getMaskFly() {
        byte mask0[] = new byte[128];
        mask0[0] = (byte) 0x00;
        mask0[1] = (byte) 0x00;
        mask0[2] = (byte) 0x00;
        mask0[3] = (byte) 0x00;
        mask0[4] = (byte) 0x00;
        mask0[5] = (byte) 0x00;
        mask0[6] = (byte) 0x00;
        mask0[7] = (byte) 0x00;

        mask0[8] = (byte) 0x03;
        mask0[9] = (byte) 0x80;
        mask0[10] = (byte) 0x01;
        mask0[11] = (byte) 0xC0;
        mask0[12] = (byte) 0x06;
        mask0[13] = (byte) 0xC0;
        mask0[14] = (byte) 0x03;
        mask0[15] = (byte) 0x60;

        mask0[16] = (byte) 0x04;
        mask0[17] = (byte) 0x60;
        mask0[18] = (byte) 0x06;
        mask0[19] = (byte) 0x20;
        mask0[20] = (byte) 0x04;
        mask0[21] = (byte) 0x30;
        mask0[22] = (byte) 0x0C;
        mask0[23] = (byte) 0x20;

        mask0[24] = (byte) 0x04;
        mask0[25] = (byte) 0x18;
        mask0[26] = (byte) 0x18;
        mask0[27] = (byte) 0x20;
        mask0[28] = (byte) 0x04;
        mask0[29] = (byte) 0x0C;
        mask0[30] = (byte) 0x30;
        mask0[31] = (byte) 0x20;

        mask0[32] = (byte) 0x04;
        mask0[33] = (byte) 0x06;
        mask0[34] = (byte) 0x60;
        mask0[35] = (byte) 0x20;
        mask0[36] = (byte) 0x44;
        mask0[37] = (byte) 0x03;
        mask0[38] = (byte) 0xC0;
        mask0[39] = (byte) 0x22;

        mask0[40] = (byte) 0x44;
        mask0[41] = (byte) 0x01;
        mask0[42] = (byte) 0x80;
        mask0[43] = (byte) 0x22;
        mask0[44] = (byte) 0x44;
        mask0[45] = (byte) 0x01;
        mask0[46] = (byte) 0x80;
        mask0[47] = (byte) 0x22;

        mask0[48] = (byte) 0x44;
        mask0[49] = (byte) 0x01;
        mask0[50] = (byte) 0x80;
        mask0[51] = (byte) 0x22;
        mask0[52] = (byte) 0x44;
        mask0[53] = (byte) 0x01;
        mask0[54] = (byte) 0x80;
        mask0[55] = (byte) 0x22;

        mask0[56] = (byte) 0x44;
        mask0[57] = (byte) 0x01;
        mask0[58] = (byte) 0x80;
        mask0[59] = (byte) 0x22;
        mask0[60] = (byte) 0x44;
        mask0[61] = (byte) 0x01;
        mask0[62] = (byte) 0x80;
        mask0[63] = (byte) 0x22;

        mask0[64] = (byte) 0x66;
        mask0[65] = (byte) 0x01;
        mask0[66] = (byte) 0x80;
        mask0[67] = (byte) 0x66;
        mask0[68] = (byte) 0x33;
        mask0[69] = (byte) 0x01;
        mask0[70] = (byte) 0x80;
        mask0[71] = (byte) 0xCC;

        mask0[72] = (byte) 0x19;
        mask0[73] = (byte) 0x81;
        mask0[74] = (byte) 0x81;
        mask0[75] = (byte) 0x98;
        mask0[76] = (byte) 0x0C;
        mask0[77] = (byte) 0xC1;
        mask0[78] = (byte) 0x83;
        mask0[79] = (byte) 0x30;

        mask0[80] = (byte) 0x07;
        mask0[81] = (byte) 0xe1;
        mask0[82] = (byte) 0x87;
        mask0[83] = (byte) 0xe0;
        mask0[84] = (byte) 0x03;
        mask0[85] = (byte) 0x3f;
        mask0[86] = (byte) 0xfc;
        mask0[87] = (byte) 0xc0;

        mask0[88] = (byte) 0x03;
        mask0[89] = (byte) 0x31;
        mask0[90] = (byte) 0x8C;
        mask0[91] = (byte) 0xC0;
        mask0[92] = (byte) 0x03;
        mask0[93] = (byte) 0x33;
        mask0[94] = (byte) 0xCC;
        mask0[95] = (byte) 0xC0;

        mask0[96] = (byte) 0x06;
        mask0[97] = (byte) 0x64;
        mask0[98] = (byte) 0x26;
        mask0[99] = (byte) 0x60;
        mask0[100] = (byte) 0x0C;
        mask0[101] = (byte) 0xCC;
        mask0[102] = (byte) 0x33;
        mask0[103] = (byte) 0x30;

        mask0[104] = (byte) 0x18;
        mask0[105] = (byte) 0xCC;
        mask0[106] = (byte) 0x33;
        mask0[107] = (byte) 0x18;
        mask0[108] = (byte) 0x10;
        mask0[109] = (byte) 0xC4;
        mask0[110] = (byte) 0x23;
        mask0[111] = (byte) 0x08;

        mask0[112] = (byte) 0x10;
        mask0[113] = (byte) 0x63;
        mask0[114] = (byte) 0xC6;
        mask0[115] = (byte) 0x08;
        mask0[116] = (byte) 0x10;
        mask0[117] = (byte) 0x30;
        mask0[118] = (byte) 0x0C;
        mask0[119] = (byte) 0x08;

        mask0[120] = (byte) 0x10;
        mask0[121] = (byte) 0x18;
        mask0[122] = (byte) 0x18;
        mask0[123] = (byte) 0x08;
        mask0[124] = (byte) 0x10;
        mask0[125] = (byte) 0x00;
        mask0[126] = (byte) 0x00;
        mask0[127] = (byte) 0x08;

        return mask0;
    }
}
