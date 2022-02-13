package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GoalPoint {
    private static final int X_SIZE = 50;
    private static final int Y_SIZE = 50;
    private static final int RADIUS = X_SIZE / 2;

    private static final int X = 570;
    private static final int Y = 210;

    private static final int X_CENTRE = ((X_SIZE / 2) - 10) + X;
    private static final int Y_CENTRE = ((Y_SIZE / 2) - 10) + Y;

    public void draw(Graphics2D g2) {
        g2.fill(new Ellipse2D.Double(X, Y, X_SIZE, Y_SIZE));
    }

    public static boolean isGoal(Ball ball) {
        return Math.pow(ball.getX() - X_CENTRE,2)  + Math.pow(ball.getY() - Y_CENTRE,2) <= Math.pow(RADIUS,2);
    }


}