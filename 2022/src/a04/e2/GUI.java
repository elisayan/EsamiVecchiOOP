package a04.e2;

import javax.swing.*;

import a04.e2.LogicsImpl.Player;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logics logics;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        this.logics = new LogicsImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var button = (JButton) e.getSource();
                var position = cells.get(button);

                if (logics.moveRook(position.getX(), position.getY())) {
                    if (logics.isRookWin(position.getX(), position.getY())) {
                        System.out.println("Vittoria");
                        logics.reset();
                    } else {
                        logics.moveKing();
                    }
                    redraw();
                }

                if (logics.isKingWin()) {
                    System.out.println("Sconfitta");
                    System.exit(0);
                }
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<Integer, Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        redraw();

        this.setVisible(true);
    }

    private void redraw() {
        Pair<Integer, Integer> rook = logics.getPosition(Player.ROOK);
        Pair<Integer, Integer> king = logics.getPosition(Player.KING);

        for (var entry : cells.entrySet()) {
            entry.getKey().setText(rook.equals(entry.getValue()) ? "R" : king.equals(entry.getValue()) ? "K" : "");
        }
    }
}
