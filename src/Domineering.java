import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Domineering extends JFrame {
    private JPanel panel;
    private JPanel lowerPanel;
    private JLabel status;
    private JButton reset;
    private JPanel buttonPanel;
    public char player = 'H';
    public char state = ' ';
    private final int WINDOW_WIDTH = 250;
    private final int WINDOW_HEIGHT = 250;
    private DomineeringButton buttons[][] = new DomineeringButton[6][6];

    public Domineering() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Domineering");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        panel.setLayout(new BorderLayout());

        buildPanel();
        buildButtonPanel();

        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(lowerPanel, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    public boolean isValidMove(int row1, int col1, char player) {
        int row2 = row1;
        int col2 = col1;

        if (player == 'H') {
            col2++;
            return col2 < buttons[row2].length && buttons[row1][col1].getState() == ' ' && buttons[row2][col2].getState() == ' ';
        } else {
            row2++;
            return row2 < buttons.length && buttons[row1][col1].getState() == ' ' && buttons[row2][col2].getState() == ' ';
        }
    }

    public void buildPanel() {
        lowerPanel = new JPanel();

        status = new JLabel(player + "'s turn");
        reset = new JButton("Reset");

        lowerPanel.add(status);
        lowerPanel.add(reset);
        reset.addActionListener(new ResetButtonListener());
    }

    public void buildButtonPanel() {
        buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(6, 6));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                buttons[i][j] = new DomineeringButton(i, j, state);
                buttonPanel.add(buttons[i][j]);
                buttons[i][j].addActionListener(new ButtonListener());
            }
        }
    }

    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int a;
            int b;
            DomineeringButton clicked = (DomineeringButton) e.getSource();
            a = clicked.getRow();
            b = clicked.getCol();

            if (player == 'H' && isValidMove(a, b, 'H') && !gameOver('H')) {
                buttons[a][b].setBackground(Color.BLUE);
                buttons[a][b + 1].setBackground(Color.BLUE);
                buttons[a][b].setState();
                buttons[a][b + 1].setState();
                player = 'V';
                if (gameOver(player)) {
                    status.setText("No more moves...\n" +
                            "H wins!");
                } else {
                    status.setText(player + "'s turn");
                }
            } else if (player == 'V' && isValidMove(a, b, 'V') && !gameOver('V')) {
                buttons[a][b].setBackground(Color.ORANGE);
                buttons[a + 1][b].setBackground(Color.ORANGE);
                buttons[a][b].setState();
                buttons[a + 1][b].setState();
                player = 'H';
                if (gameOver(player)) {
                    status.setText("No more moves...\n" +
                            "V wins!");
                } else {
                    status.setText(player + "'s turn");
                }
            } else {
                status.setText("Invalid move!\n" +
                        "Try again " + player);
            }
        }
    }

    public boolean gameOver(char player) {
        int row = 0;
        boolean foundMove = false;
        while (row < buttons.length && !foundMove) {
            int col = 0;
            while (col < buttons[row].length & !foundMove) {
                foundMove = isValidMove(row, col, player);
                col++;
            }
            row++;
        }
        return !foundMove;
    }

    public class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            player = 'H';
            status.setText(player + "'s turn");
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    buttons[i][j].setBackground(null);
                    buttons[i][j].setState2();
                }
            }
        }
    }
}





