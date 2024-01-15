package a03a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logics logics;
    private Pair<Integer, Integer> human;
    private Pair<Integer, Integer> computer;
    private Random random = new Random();

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        this.logics = new LogicsImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        resetGame(size);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var position = cells.get(button);

                if (logics.humanMove(position.getX(), position.getY(), human)) {
                    cells.entrySet().stream()
                            .filter(entry -> entry.getValue().equals(human))
                            .forEach(entry -> entry.getKey().setText(""));
                    button.setText("o");

                    human = new Pair<Integer, Integer>(position.getX(), position.getY());
                    Pair<Integer, Integer> temp = logics.computerMove(computer);
                    for (var entry : cells.entrySet()) {
                        if (temp.equals(entry.getValue())) {
                            cells.entrySet().stream()
                                    .filter(e1 -> e1.getValue().equals(computer))
                                    .forEach(e1 -> e1.getKey().setText(""));
                            System.out.println("befor update: "+computer);
                            entry.getKey().setText("*");
                            computer = temp;
                            System.out.println("pair: "+entry.getValue());
                            System.out.println("update computer: "+computer);
                            if (logics.computerWins(computer, human) || logics.humanWins(computer)) {

                                resetGame(size);
                            }
                        }
                    }
                }

            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<Integer, Integer>(i, j));
                if (this.human.getX() == i && this.human.getY() == j) {
                    jb.setText("o");
                }

                if (this.computer.getX() == i && this.computer.getY() == j) {
                    jb.setText("*");
                }

                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        this.setVisible(true);
    }

    private void resetGame(int size) {
        System.out.println("reset game");
        cells.keySet().stream().forEach(b -> b.setText(""));
        human = new Pair<Integer, Integer>(this.random.nextInt(size), this.random.nextInt(size));
        computer = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));

        while (human.equals(computer)) {
            computer = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
        }

        cells.entrySet().stream()
                .filter(entry -> entry.getValue().equals(human))
                .forEach(entry -> entry.getKey().setText("o"));

        cells.entrySet().stream()
                .filter(entry -> entry.getValue().equals(computer))
                .forEach(entry -> entry.getKey().setText("*"));
    }
}