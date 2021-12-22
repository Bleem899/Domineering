import javax.swing.*;

public class DomineeringButton extends JButton{
    private int row;
    private int col;
    private char state;

    public DomineeringButton(int row, int col, char state) {
        this.row = row;
        this.col = col;
        this.state = state;
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public char getState(){ return state;}
    public void setState(){state = '*';}
    public void setState2(){state = ' ';}
}
