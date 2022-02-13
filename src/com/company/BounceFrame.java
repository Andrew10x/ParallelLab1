package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;
    public static final int BLUE_PRIORITY = 6;
    public static final int RED_PRIORITY = 7;
    private static final AtomicInteger removedBallsNum = new AtomicInteger(0);
    public static JTextField text = new JTextField("0", 5);

    public static void updateCounter() {
        removedBallsNum.getAndIncrement();
        text.setText("" + removedBallsNum);
    }
    public BounceFrame () {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();

        System.out.println("In frame Thread name = " + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonPriority = new JButton("Priority");
        JButton buttonJoin = new JButton("Join");

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });

        buttonPriority.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 100; i++) {
                    Ball b = new Ball(canvas, BLUE_PRIORITY,100,70);
                    canvas.add(b);
                    BallThread thread = new BallThread(b);
                    thread.setPriority(BLUE_PRIORITY);
                    thread.start();
                    System.out.println("Thread name = " + thread.getName());
                }
                Ball b = new Ball(canvas, RED_PRIORITY, 100, 70);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.setPriority(RED_PRIORITY);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });

        buttonJoin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Ball> balls = new ArrayList<>();
                    Ball b1 = new Ball(canvas, 5);
                    Ball b2 = new Ball(canvas, 5);
                    balls.add(b1);
                    balls.add(b2);
                for (Ball ball : balls) {
                    canvas.add(ball);
                }
                BallThread bs1 = new BallThread(b1);
                BallThread bs2 = new BallThread(b2, bs1);
                bs2.start();
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonPriority);
        buttonPanel.add(buttonJoin);
        buttonPanel.add(text);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void incRemovedBallsCounter() {
        removedBallsNum.getAndIncrement();
    }
}
