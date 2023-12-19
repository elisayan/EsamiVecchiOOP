package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<Pair<Integer, Integer>,JButton> cells = new HashMap<>();
    private int counter = 0;
    private Logics logics;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50 * size, 50 * size);
        logics = new LogicsImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        // Pair<Integer,Integer> first = logics.move();
        // this.cells.get(first).setText("*");

        ActionListener al = e -> {
            //this.cells.get(counter).setText(String.valueOf(counter++));
        };

        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(" ");
                this.cells.put(new Pair<Integer,Integer>(i, j), jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        for (var entry : cells.entrySet()) {
            
        }

        this.setVisible(true);
    }
    
}
