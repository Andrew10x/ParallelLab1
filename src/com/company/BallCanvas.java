package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;



public class BallCanvas extends JPanel {
    private static ArrayList<Ball> balls = new ArrayList<>();

    public void add(Ball b) {
        balls.add(b);
    }

    public static void removeFromBalls(Ball b) {
        balls.remove(b);
    }

    public static int ballsNum(){
        return balls.size();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        for (Ball b : balls) {
            if (b.getPriorityOfBall() > 5){
                if (b.getPriorityOfBall() == BounceFrame.RED_PRIORITY) {
                    b.drawRed(g2);
                } else {
                    b.drawBlue(g2);
                }
            }else
                b.draw(g2);
        }

        drawBlack(g2, 1, 1);
        drawBlack(g2, 415, 1);
        drawBlack(g2, 1, 255);
        drawBlack(g2, 415, 255);

    }

    public void drawBlack(Graphics2D g2, int x,int y) {
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(x, y, 20, 20));
    }
}
