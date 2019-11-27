package com.schule.Sorting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;

import static com.schule.Sorting.Algorithms.Helper.*;

public class DrawPanel extends JPanel {

    int[] arr;
    private JPanel panel;
    private int prevWidth;

    DrawPanel(int[] _arr, int width, int height, boolean resize, Thread thread) {
        this.arr = _arr;
        this.panel = this;
        this.prevWidth = width;
        setPreferredSize(new Dimension(width, height));
        if (resize) {
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    if (thread != null) thread.stop();

                    if (e.getComponent().getWidth() != prevWidth) {
                        int[] oldArr = arr;
                        arr = new int[e.getComponent().getWidth()];
                        System.arraycopy(oldArr, 0, arr, 0, Math.min(arr.length, oldArr.length));

                    }

                    scaleArr(arr, 0, getMax(arr), 0, e.getComponent().getHeight());
                    panel.repaint();
                    // System.out.println(e.getComponent().getWidth());
                    // System.out.println(e.getComponent().getHeight());
                }
            });
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        double[] scaledArr = new double[arr.length];
        for (int i = 0; i < scaledArr.length; ++i) {
            scaledArr[i] = arr[i];
        }
        scaleArr(scaledArr, 0, (int)getMax(scaledArr), 0, 100);

        Color cyan = new Color(64, 224, 208);
        Color orange = new Color(255, 140, 0);
        Color pink = new Color(255, 0, 128);

        Color[] colors = {cyan, orange, pink};
        float[] fractions = {0.0f, 0.5f, 1.0f};

        LinearGradientPaint paint = new LinearGradientPaint(0f, 0f, getWidth(),0f, fractions, colors);

        for (int i = 0; i < arr.length; ++i) {

            /*
            float r = (float) (scaledArr[i] / 100f);
            float gr = (float) ((100 - scaledArr[i]) / 100f);
             */
            int R, G, B;
            if (scaledArr[i] < 50) {
                R = (int) (orange.getRed() * scaledArr[i] / 50 + pink.getRed() * (1 - scaledArr[i] / 50));
                G = (int) (orange.getGreen() * scaledArr[i] / 50 + pink.getGreen() * (1 - scaledArr[i] / 50));
                B = (int) (orange.getBlue() * scaledArr[i] / 50 + pink.getBlue() * (1 - scaledArr[i] / 50));
            } else {
                R = (int) (cyan.getRed() * (scaledArr[i] - 50) / 50 + orange.getRed() * (1 - (scaledArr[i] - 50) / 50));
                G = (int) (cyan.getGreen() * (scaledArr[i] - 50) / 50 + orange.getGreen() * (1 - (scaledArr[i] - 50) / 50));
                B = (int) (cyan.getBlue() * (scaledArr[i] - 50) / 50 + orange.getBlue() * (1 - (scaledArr[i] - 50) / 50));
            }




            // g2d.setPaint(paint);
            g2d.setColor(new Color(R, G, B));
            // g2d.setColor(Color.WHITE);

            int width = getWidth() / arr.length;
            g2d.fillRect(i * width, getHeight() - arr[i], width, getHeight());
        }

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g2d.drawString("Elements in Array: " + arr.length, 10, 20);
        if (isSorted(arr)) g2d.drawString("Status: Sorted", 10, 40);
        else g2d.drawString("Status: Unsorted", 10, 40);
    }


}
