package com.schule.Sorting;

import com.schule.Sorting.Algorithms.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Objects;

import static com.schule.Sorting.Algorithms.Helper.randomizeArray;

public class Visualization extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 500;
    private static final int HEIGHT_TOP = 25;

    private JTextField delayTF = new JTextField();
    private JComboBox<SortingAlgorithms> sortingAlgoCombo = new JComboBox<>(SortingAlgorithms.values());

    private int[] arr = new int[WIDTH];

    private Thread thread;

    public Visualization() {
        initComponents();
    }

    private void initComponents() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT + HEIGHT_TOP));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT_TOP));
        buttonPanel.setLayout(new GridLayout(1, 5));

        JPanel drawPanel;
        drawPanel = new DrawPanel(arr, WIDTH, HEIGHT);
        drawPanel.setBackground(Color.GRAY);
        drawPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JButton sort = new JButton("Sort");
        sort.addActionListener(e -> {
            if (thread != null) thread.stop();
            long delay = 1;
            try {
                delay = Long.parseLong(delayTF.getText());
            } catch (NumberFormatException err) {
                err.printStackTrace();
            }

            long finalDelay = delay;

            switch ((SortingAlgorithms) Objects.requireNonNull(sortingAlgoCombo.getSelectedItem())) {
                case Bubblesort:
                    thread = new Thread("Bubblesort") {
                        public void run() {
                            Bubblesort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Insertionsort:
                    thread = new Thread("Insertionsort") {
                        public void run() {
                            Insertionsort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Heapsort:
                    thread = new Thread("Heapsort") {
                        public void run() {
                            Heapsort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Mergesort:
                    thread = new Thread("Mergesort") {
                        public void run() {
                            Mergesort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case ParallelMergesort:
                    thread = new Thread("Parallel Mergesort") {
                        public void run() {
                            ParallelMergesort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Quicksort:
                    thread = new Thread("Quicksort") {
                        public void run() {
                            Quicksort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Introsort:
                    thread = new Thread("Introsort") {
                        public void run() {
                            Introsort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case LSD:
                    thread = new Thread("LSD") {
                        public void run() {
                            LSD_Radixsort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Bogosort:
                    thread = new Thread("Bogosort") {
                        public void run() {
                            Bogosort.sort(arr, drawPanel, finalDelay);
                        }
                    };
                    break;

            }
            thread.start();
            System.out.println(thread.getName());
        });

        JPanel delayPanel = new JPanel();
        delayPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel();
        label.setText("Delay[ms]: ");
        delayPanel.add(label);

        delayTF.setText("1");
        Dimension d = delayTF.getPreferredSize();
        d.width = 50;
        delayTF.setPreferredSize(d);
        delayPanel.add(delayTF);

        JButton random1 = new JButton("Random 1");
        random1.addActionListener(e -> {
            if (thread != null) thread.stop();
            for (int i = 0; i < arr.length; ++i) {
                double height = (double) i * ((double) drawPanel.getHeight() / (double) drawPanel.getWidth());
                arr[i] = (int) height;
            }
            randomizeArray(arr);
            drawPanel.repaint();
        });

        JButton random2 = new JButton("Random 2");
        random2.addActionListener(e -> {
            if (thread != null) thread.stop();
            for (int i = 0; i < arr.length; ++i) {
                arr[i] = Math.toIntExact(Math.round(Math.random() * drawPanel.getHeight()));
            }
            drawPanel.repaint();
        });

        buttonPanel.add(sort);
        buttonPanel.add(delayPanel);
        buttonPanel.add(random1);
        buttonPanel.add(random2);
        buttonPanel.add(sortingAlgoCombo);

        // add the component to the frame to see it!
        this.setContentPane(mainPanel);

        mainPanel.add(buttonPanel);
        mainPanel.add(drawPanel);

        // be nice to testers..
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }// </editor-fold>


    //set ui visible//
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Visualization().setVisible(true));
    }

    // This class name is very confusing, since it is also used as the
    // name of an attribute!
    //class jPanel2 extends JPanel {
    class DrawPanel extends JPanel {

        private int[] arr;

        DrawPanel(int[] arr, int width, int height) {
            this.arr = arr;
            setPreferredSize(new Dimension(width, height));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            for (int i = 0; i < arr.length; ++i) {
                // System.out.println("Test");
                g.fillRect(i, getHeight(), 1, -arr[i]);
            }
        }
    }
}
