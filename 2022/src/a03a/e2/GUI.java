package a03a.e2;

import javax.swing.*;


import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private Logics logic;
    private Random random = new Random();
    private Pair<Integer,Integer> robot;
    private Pair<Integer,Integer> uman;
    private int c=0;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicsImpl();
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
                var position = cells.keySet().iterator().next();
                
                System.out.println(position);
                if (logic.umanMove(position.getX(), position.getY(), uman)) {
                    button.setText("o");
                    uman=new Pair<Integer,Integer>(position.getX(), position.getY());
                }
        	    
                //button.setText("");
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
        
        setRandomRobotAndUman(size); 

        this.setVisible(true);
    } 
    
    private void setRandomRobotAndUman(int size){
        System.out.println("count: "+c++);
        robot = new Pair<Integer, Integer>(this.random.nextInt(size), this.random.nextInt(size));
        uman = new Pair<Integer, Integer>(this.random.nextInt(size), this.random.nextInt(size));
        if (robot.equals(uman)) {
            robot = new Pair<Integer, Integer>(this.random.nextInt(size), this.random.nextInt(size));
        }
        cells.get(robot).setText("*");
        cells.get(uman).setText("o");
    }
}
