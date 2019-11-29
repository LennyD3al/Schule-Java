package com.schule.Sorting;

import com.schule.Searching.BinarySearch;
import com.schule.Searching.LinearSearch;
import com.schule.Sorting.Algorithms.*;
import com.schule.Sorting.Visualization;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Objects;

public class Sort extends JFrame {

    // CONSTANTS
    private static final int X_OFFSET = 14;
    private static final int Y_BUTTON_HEIGHT = 40;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 25;

    private static final int SIZE = 15;

    // GLOBAL SWING OBJECTS
    private JTextField[] textFields = new JTextField[SIZE];
    private JComboBox<SortingAlgorithms> sortingAlgoCombo = new JComboBox<>(SortingAlgorithms.values());
    private JTextField inputTF = new JTextField();

    private DrawPanel drawPanel;

    private int[] numbers = new int[SIZE];

    private Sort(String title) {
        super(title);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        int frameWidth = 650;
        int frameHeight = 438;
        setSize(frameWidth, frameHeight);
        JPanel mainPanel = new JPanel(null);
        add(mainPanel);

        for (int i = 0; i < textFields.length; ++i) {
            JTextField tf = new JTextField();
            tf.setBounds(X_OFFSET + i * 40, 10, 40, 25);
            textFields[i] = tf;
            mainPanel.add(tf);
        }

        JButton bNeu = new JButton();
        bNeu.setText("Neu");
        bNeu.setBounds(X_OFFSET, Y_BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        bNeu.addActionListener(e -> bNeu_Action());
        mainPanel.add(bNeu);

        JButton bFuellen = new JButton();
        bFuellen.setText("Füllen");
        bFuellen.setBounds(X_OFFSET + BUTTON_WIDTH, Y_BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        bFuellen.addActionListener(e -> bFullen_Action());
        mainPanel.add(bFuellen);

        JButton bEinfuegen = new JButton();
        bEinfuegen.setText("Einfügen");
        bEinfuegen.setBounds(X_OFFSET + BUTTON_WIDTH * 2, Y_BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        bEinfuegen.addActionListener(e -> bEinfugen_Action());
        mainPanel.add(bEinfuegen);

        JButton bSuchen = new JButton();
        bSuchen.setText("Suchen");
        bSuchen.setBounds(X_OFFSET + BUTTON_WIDTH * 3, Y_BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        bSuchen.addActionListener(e -> bSuchen_Action());
        mainPanel.add(bSuchen);

        JButton bLoschen = new JButton();
        bLoschen.setText("Löschen");
        bLoschen.setBounds(X_OFFSET + BUTTON_WIDTH * 4, Y_BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        bLoschen.addActionListener(e -> bLoschen_Action());
        mainPanel.add(bLoschen);

        JButton bBeenden = new JButton();
        bBeenden.setText("Beenden");
        bBeenden.setBounds(X_OFFSET + BUTTON_WIDTH * 5, Y_BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        bBeenden.addActionListener(e -> bBeenden_Action());
        mainPanel.add(bBeenden);

        inputTF.setBounds(X_OFFSET, 70, 600, BUTTON_HEIGHT);
        mainPanel.add(inputTF);

        JButton bSuchenMin = new JButton();
        bSuchenMin.setText("SuchenMin");
        bSuchenMin.setBounds(X_OFFSET, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        bSuchenMin.addActionListener(e -> bSuchenMin_Action());
        mainPanel.add(bSuchenMin);

        JButton bSuchenMax = new JButton();
        bSuchenMax.setText("SuchenMax");
        bSuchenMax.setBounds(X_OFFSET + BUTTON_WIDTH, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        bSuchenMax.addActionListener(e -> bSuchenMax_Action());
        mainPanel.add(bSuchenMax);

        JButton bSortieren = new JButton();
        bSortieren.setText("Sortieren");
        bSortieren.setBounds(X_OFFSET + BUTTON_WIDTH * 2, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        bSortieren.addActionListener(e -> bSortieren_Action());
        mainPanel.add(bSortieren);

        sortingAlgoCombo.setBounds(X_OFFSET + BUTTON_WIDTH * 3, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        mainPanel.add(sortingAlgoCombo);

        JButton bSuchenBin = new JButton();
        bSuchenBin.setText("Binäre Suche");
        bSuchenBin.setBounds(X_OFFSET + BUTTON_WIDTH * 4, 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        bSuchenBin.addActionListener(e -> bSuchenBin_Action());
        mainPanel.add(bSuchenBin);

        drawPanel = new DrawPanel(numbers, frameWidth, 250, false, null);
        drawPanel.setBounds(0, 150, frameWidth, 250);
        drawPanel.setBackground(Color.GRAY);

        mainPanel.add(drawPanel);

        setVisible(true);

    }

    private void bNeu_Action() {

        for (int i = 0; i < 15; ++i) {
            numbers[i] = 0;
        }
        ausgeben();
    }

    private void bFullen_Action() {

        numbers = new int[SIZE];
        for (int i = 0; i < SIZE - 3; ++i) {
            numbers[i] = (int) (Math.random() * 249 + 1);
        }
        ausgeben();
    }

    private void bEinfugen_Action() {
        try {
            int input = Integer.parseInt(inputTF.getText());

            if (input <= 0 || input > 250) return;

            for (int i = 0; i < 15; ++i) {
                if (numbers[i] == 0) {
                    numbers[i] = input;
                    ausgeben();
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

    }

    private void bSuchen_Action() {
        demarkieren();
        try {
            int input = Integer.parseInt(inputTF.getText());

            if (input <= 0 || input > 250) return;

            for (int i = 0; i < 15; ++i) {
                if (numbers[i] == input) {
                    textFields[i].setBackground(Color.PINK);
                }
            }


        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }


    private void bLoschen_Action() {
        try {
            int input = Integer.parseInt(inputTF.getText());

            if (input <= 0 || input > 250) return;

            for (int i = 0; i < 15; ++i) {
                if (numbers[i] == input) {
                    numbers[i] = 0;
                    ausgeben();
                    break;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }


    private void bBeenden_Action() {

        System.exit(0);
    }

    private void bSuchenMin_Action() {
        demarkieren();
        int lowest = Integer.MAX_VALUE;

        for (int number : numbers) {
            if (number < lowest && number != 0) {
                lowest = number;
            }
        }

        for (int i = 0; i < textFields.length; ++i) {
            if (numbers[i] == lowest) {
                textFields[i].setBackground(Color.GREEN);
            }
        }
    }

    private void bSuchenMax_Action() {
        demarkieren();
        int max = Integer.MIN_VALUE;

        for (int number : numbers) {
            if (number > max && number != 0) {
                max = number;
            }
        }

        for (int i = 0; i < textFields.length; ++i) {
            if (numbers[i] == max) {
                textFields[i].setBackground(Color.RED);
            }
        }
    }

    private void bSortieren_Action() {
        demarkieren();


        int[] toSort = new int[SIZE];
        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] == 0) toSort[i] = Integer.MAX_VALUE;
            else toSort[i] = numbers[i];
        }
        switch ((SortingAlgorithms) Objects.requireNonNull(sortingAlgoCombo.getSelectedItem())) {
            case Bubblesort:
                Bubblesort.sort(toSort);
                break;
            case Insertionsort:
                Insertionsort.sort(toSort);
                break;
            case Selectionsort:
                Selectionsort.sort(toSort);
                break;
            case Heapsort:
                Heapsort.sort(toSort);
                break;
            case Mergesort:
                Mergesort.sort(toSort);
                break;
            case ParallelMergesort:
                ParallelMergesort.sort(toSort);
                break;
            case Quicksort:
                Quicksort.sort(toSort);
                break;
            case Introsort:
                Introsort.sort(toSort);
                break;
            case LSD:
                LSD_Radixsort.sort(toSort);
                break;
            case Bogosort:
                Bogosort.sort(toSort);
                break;
            default:
                Arrays.sort(toSort);
                break;
        }
        for (int i = 0; i < toSort.length; ++i) {
            if (toSort[i] == Integer.MAX_VALUE) numbers[i] = 0;
            else numbers[i] = toSort[i];
        }
        ausgeben();
    }

    private void bSuchenBin_Action() {
        demarkieren();
        try {
            int input = Integer.parseInt(inputTF.getText());

            if (input <= 0 || input > 250) return;

            int[] toSearch = new int[SIZE];
            for (int i = 0; i < numbers.length; ++i) {
                if (numbers[i] == 0) toSearch[i] = Integer.MAX_VALUE;
                else toSearch[i] = numbers[i];
            }
            int index = BinarySearch.search(toSearch, input);
            if (index != -1) textFields[index].setBackground(Color.PINK);

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ausgeben() {
        demarkieren();
        drawPanel.arr = numbers;
        drawPanel.repaint();
        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] == 0) {
                textFields[i].setText("");
                continue;
            }
            textFields[i].setText("" + numbers[i]);
        }
    }

    private void demarkieren() {
        for (JTextField textField : textFields) {
            textField.setBackground(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        new Sort("Sort");
    }
}
