package a02a.e2;

import javax.swing.*;

import java.util.*;
import java.util.random.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer, Integer>> cells = new HashMap<>();
    private int counter = 0;
    private Logics logics;
    private Random random = new Random();    
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        logics = new LogicsImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        int number = random.nextInt(size + 1);
        
        ActionListener al = e -> {
            JButton button = (JButton) e.getSource();
            Pair<Integer,Integer> position = new Pair<Integer,Integer>(number, number);
            
        	this.cells.replace(button, position);
            if(logics.hit(number, number)){
                cells.keySet().iterator().next().setText(String.valueOf(counter++));
            }
            
            //for (var entry : cells.entrySet()) {
                
            //}
            
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<Integer,Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}
