package javaapplication1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JApplet;

public class Gyulaifraktalen extends JApplet {

    Image osi = null;

    @Override
    public void start() { // set standard size (pixels)
        setSize(802, 802);
        setMinimumSize(new Dimension(129, 49));
        setMaximumSize(new Dimension(4096, 2160));
        super.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D osi_g2 = (Graphics2D) osi.getGraphics();
        osi_g2.setColor(Color.DARK_GRAY);
        osi_g2.fillRect(0, 0, getWidth(), getHeight()); // paint a backround to decrease the number of re-draws
        osi_g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // apply anti-alising
        osi_g2.setStroke(new BasicStroke(0.2f)); // change line px size to 0.2px
        // draw the fractal with depth 8
        int sidelen = Math.min(getWidth(), getHeight()) - 20;

        if (getHeight() <= 49) {
            sidelen = 30;
            setSize(getWidth(), 49);
        }
        int nDjup = 7;

        if (sidelen > 4076) {
            nDjup = 11;
        } else if (sidelen > 3052) {
            nDjup = 10;
        } else if (sidelen > 2028) {
            nDjup = 9;
        } else if (sidelen > 700) {
            nDjup = 8;
        } else if (sidelen > 400) {
            nDjup = 7;
        } else if (sidelen > 300) {
            nDjup = 6;
        } else if (sidelen > 130) {
            nDjup = 5;
        } else if (sidelen > 80){
            nDjup = 4;
        } else {
            nDjup = 3; 
        }

        int dx = 20, dy = 20;
        int ex = sidelen, ey = 20;
        int fx = (ex - dx) / 2 + dx, fy = sidelen;

        ritaTriangel(osi_g2, dx, dy, ex, ey, fx, fy, nDjup);
        g.drawImage(osi, 0, 0, this);
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex1) {
        }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void init() {
        osi = createImage(4096, 2160);
    }

    private void ritaTriangel(Graphics2D osi_g2, int ax, int ay, int bx, int by, int cx, int cy, int djup) {
        osi_g2.setColor(Color.cyan);

        if (djup == 0) {
            osi_g2.drawLine(ax, ay, bx, by);
            osi_g2.drawLine(bx, by, cx, cy);
            osi_g2.drawLine(cx, cy, ax, ay);

        } else {
            int mx = (ax + bx + cx) / 3;
            int my = (ay + by + cy) / 3;

            ritaTriangel(osi_g2, ax, ay, bx, by, mx, my, djup - 1);
            ritaTriangel(osi_g2, ax, ay, cx, cy, mx, my, djup - 1);
            ritaTriangel(osi_g2, bx, by, cx, cy, mx, my, djup - 1);
        }
    }

}