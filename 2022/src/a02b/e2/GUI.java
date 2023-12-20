package a02b.e2;

import javax.swing.*;

import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logics logics;

    public GUI(int size) {
        this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton check = new JButton("Check > Restart");
        main.add(BorderLayout.SOUTH, check);
        
        check.addActionListener(e->{
            if(logics.checkDiagonal()){
                var button = (JButton) e.getSource();
                var position = cells.get(button);
                for (var entry : cells.entrySet()) {
                    
                }
            }
        });

        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
                Pair<Integer, Integer> position = cells.get(button);
                button.setText(logics.hit(position.getX(), position.getY()) ? "*" : " ");                
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<Integer,Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    
}
