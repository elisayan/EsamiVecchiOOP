package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();
    private final Logics logics;

    public GUI(int size) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50 * size, 50 * size);
        this.logics = new LogicsImpl(size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(panel);

        ActionListener al = e -> {
            var button = (JButton) e.getSource();
            int x = button.getLocation().x;
            int y = button.getLocation().y;
            switch (logics.hit(x, y)) {
                case FIRST:
                    button.setText("1");
                    break;
                case SECOND:
                    for (JButton asterisk : cells) {
                        if (logics.isHitted(asterisk.getX(), asterisk.getY())) {
                            asterisk.setText("*");
                        }
                    }
                    break;
                default:
                    break;
            }
            button.setEnabled(false);
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                this.cells.add(jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

}
