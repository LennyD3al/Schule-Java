package com.schule.Sorting;

import com.schule.Sorting.Algorithms.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

import static com.schule.Sorting.Algorithms.Helper.*;

public class Visualization extends JFrame {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;
    private static final int HEIGHT_TOP = 25;

    private JTextField delayTF = new JTextField();
    private JComboBox<SortingAlgorithms> sortingAlgoCombo = new JComboBox<>(SortingAlgorithms.values());

    private Thread thread;

    private Visualization() {
        initComponents();
    }

    private void initComponents() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT + HEIGHT_TOP));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT_TOP));
        buttonPanel.setLayout(new GridLayout(1, 5));

        int[] arr = new int[WIDTH];
        DrawPanel drawPanel;
        drawPanel = new DrawPanel(arr, WIDTH, HEIGHT, true, thread);
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
                            Bubblesort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Insertionsort:
                    thread = new Thread("Insertionsort") {
                        public void run() {
                            Insertionsort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Selectionsort:
                    thread = new Thread("Selectionsort") {
                        public void run() {
                            Selectionsort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Heapsort:
                    thread = new Thread("Heapsort") {
                        public void run() {
                            Heapsort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Mergesort:
                    thread = new Thread("Mergesort") {
                        public void run() {
                            Mergesort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case ParallelMergesort:
                    thread = new Thread("Parallel Mergesort") {
                        public void run() {
                            ParallelMergesort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Quicksort:
                    thread = new Thread("Quicksort") {
                        public void run() {
                            Quicksort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Introsort:
                    thread = new Thread("Introsort") {
                        public void run() {
                            Introsort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case LSD:
                    thread = new Thread("LSD") {
                        public void run() {
                            LSD_Radixsort.sort(drawPanel.arr, drawPanel, finalDelay);
                        }
                    };
                    break;
                case Bogosort:
                    thread = new Thread("Bogosort") {
                        public void run() {
                            Bogosort.sort(drawPanel.arr, drawPanel, finalDelay);
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
            for (int i = 0; i < drawPanel.arr.length; ++i) {
                double height = (double) i * ((double) drawPanel.getHeight() / (double) drawPanel.getWidth());
                drawPanel.arr[i] = (int) height;
            }
            randomizeArray(drawPanel.arr);
            drawPanel.repaint();
        });

        JButton random2 = new JButton("Random 2");
        random2.addActionListener(e -> {
            if (thread != null) thread.stop();
            for (int i = 0; i < drawPanel.arr.length; ++i) {
                drawPanel.arr[i] = Math.toIntExact(Math.round(Math.random() * drawPanel.getHeight()));
            }
            drawPanel.repaint();
        });

        buttonPanel.add(sort);
        buttonPanel.add(delayPanel);
        buttonPanel.add(random1);
        buttonPanel.add(random2);
        buttonPanel.add(sortingAlgoCombo);

        this.setContentPane(mainPanel);

        mainPanel.add(buttonPanel, BorderLayout.PAGE_START);
        mainPanel.add(drawPanel, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }


    //set ui visible//
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Visualization().setVisible(true));
    }
}
