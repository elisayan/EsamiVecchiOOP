package a01c.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new HashMap<>();
    private Logic logic;
    private int c=0;

    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);
        this.logic = new LogicImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        

        ActionListener al = e -> {
            var jb = (JButton) e.getSource();
            var position = cells.get(jb);

            switch (logic.click(position.getX(), position.getY())) {
                case FIRST:
                    jb.setText("1");
                    break;

                case SECOND:
                    jb.setText("2");
                    break;
                
                case THIRD:
                    for(var entry: cells.entrySet()){
                        if(logic.isZero(entry.getValue().getX(),entry.getValue().getY())){
                            entry.getKey().setText("0");
                        }
                    }
                    break;
                default:
                    System.out.println("other: "+ ++c);
                    for (var entry : cells.entrySet()) {
                        if (logic.isZero(entry.getValue().getX(), entry.getValue().getY())) {
                            entry.getKey().setText("0");
                        }
                    }
                    break;
            }
            
            if (logic.isOver()) {
                System.exit(0);
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton();
                this.cells.put(jb, new Pair<Integer, Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

}
