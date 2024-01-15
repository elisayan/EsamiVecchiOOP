package a03a.e2;

import javax.swing.*;


import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<Pair<Integer,Integer>, JButton> cells = new HashMap<>();
    private Random random = new Random();

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    //var position = cells.get(button);
                button.setText("");
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(new Pair<Integer,Integer>(i, j), jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        Pair<Integer,Integer> robot = new Pair<Integer, Integer>(this.random.nextInt(size), this.random.nextInt(size));
        Pair<Integer,Integer> uman = new Pair<Integer, Integer>(this.random.nextInt(size), this.random.nextInt(size));
        System.out.println(robot);
        System.out.println(uman);
        System.out.println(cells);
        cells.get(robot).setText("*");
        cells.get(uman).setText("o");
        
        this.setVisible(true);
    }    
}
