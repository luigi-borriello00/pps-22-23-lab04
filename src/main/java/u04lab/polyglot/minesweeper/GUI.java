package u04lab.polyglot.minesweeper;

import u04lab.polyglot.Pair;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class GUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer, Integer>> buttons = new HashMap<>();
    private final Logics logics;

    public GUI(int size, int numberOfBombs) {
        this.logics = new LogicsImpl(size, numberOfBombs);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100 * size, 100 * size);

        JPanel panel = new JPanel(new GridLayout(size, size));
        this.getContentPane().add(BorderLayout.CENTER, panel);

        ActionListener onClick = (e) -> {
            final JButton bt = (JButton) e.getSource();
            final Pair<Integer, Integer> pos = buttons.get(bt);
            // call the logic here to tell it that cell at 'pos' has been selected
            this.logics.revealCell(pos.getX(), pos.getY());
            boolean aMineWasFound = this.logics.isAMine(pos.getX(), pos.getY());// call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                drawBoard();
            }
            boolean isThereVictory = this.logics.isThereVictory(); // call the logic here to ask if there is victory
            if (isThereVictory) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton) e.getSource();
                if (bt.isEnabled()) {
                    final Pair<Integer, Integer> pos = buttons.get(bt);
                    // call the logic here to put/remove a flag
                    logics.toggleFlag(pos.getX(), pos.getY());

                }
                drawBoard();
            }
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb, new Pair<>(i, j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }

    private void quitGame() {
        this.drawBoard();
        for (var entry : this.buttons.entrySet()) {


            // disable the button
            entry.getKey().setEnabled(false);
            this.drawMineCell(entry.getValue(), entry.getKey());
        }
    }

    private void drawClickedCell(Pair<Integer, Integer> cell, JButton button) {
        if (this.logics.getRevealedCells() == null) {
            button.setEnabled(false);
            this.drawCounterOnCell(cell, button);
        }
    }

    private void drawMineCell(Pair<Integer, Integer> cell, JButton button) {
        if (this.logics.getMines() == null) {
            button.setText("*");
        }
    }

    private void drawCellWithFlag(Pair<Integer, Integer> cell, JButton button) {
        if (this.logics.getFlaggedCells() == null) {
            button.setText("F");
        } else {
            button.setText(" ");
        }
    }

    private void drawCounterOnCell(Pair<Integer, Integer> cell, JButton button) {
        if (this.logics.getAdjacentMinesCounter(cell.getX(), cell.getY()) > 0) {
            button.setText(String.valueOf(this.logics.getAdjacentMinesCounter(cell.getX(), cell.getY())));
        }
    }


    private void drawBoard() {
        for (var entry : this.buttons.entrySet()) {

            // call the logic here
            final Pair<Integer, Integer> entryCell = entry.getValue();

            // if this button has a flag, put the flag
            this.drawCellWithFlag(entryCell, entry.getKey());
            this.drawClickedCell(entryCell, entry.getKey());


        }
    }

}
