package a01d.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> cells = new HashMap<>();
    private Logics logic;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicsImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            var position = cells.get(jb);

            logic.hit(position.getX(), position.getY());

            for (var entry : cells.entrySet()) {                
                entry.getKey().setText(logic.isSelected(entry.getValue().getX(), entry.getValue().getY()) ? "*" : "");
            }
            if (logic.isOver()) {
                System.exit(0);
            }            
        	
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton();
                this.cells.put(jb, new Pair<Integer,Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}
