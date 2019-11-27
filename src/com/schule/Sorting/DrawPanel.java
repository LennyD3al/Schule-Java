package com.schule.Sorting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

        g.setColor(Color.WHITE);
        for (int i = 0; i < arr.length; ++i) {
            int width = getWidth() / arr.length;
            g.fillRect(i * width, getHeight() - arr[i], width, getHeight());
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        g.drawString("Elements in Array: " + arr.length, 10, 20);
        if (isSorted(arr)) g.drawString("Status: Sorted", 10, 40);
        else g.drawString("Status: Unsorted", 10, 40);
    }


}