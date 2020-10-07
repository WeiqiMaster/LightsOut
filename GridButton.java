import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import java.awt.Color;

public class GridButton extends JButton {


    private int column;
    private int row;

    //private static final ImageIcon[] icons = new ImageIcon[4];

    /**
     * Constructor used for initializing a GridButton at a specific
     * Board location.
     * 
     * @param column
     *            the column of this Cell
     * @param row
     *            the row of this Cell
     */

    public GridButton(int column, int row) {
        setBackground(Color.WHITE);
        setIcon(new ImageIcon("Icons/Light-1.png"));
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        setBorder(emptyBorder);
        setBorderPainted(false);
        this.column = column;
        this.row = row;
    }

   /**
    * sets the icon of the button to reflect the
    * state specified by the parameters
    * @param isOn true if that location is ON
    * @param isClicked true if that location is
    * tapped in the model's current solution
    */ 
    public void setState(boolean isOn, boolean isClicked) {
        if (isOn) {
            if (isClicked) {
                setIcon(new ImageIcon("Icons/Light-2.png"));
            } else {
                setIcon(new ImageIcon("Icons/Light-0.png"));
            }
        } else {
            if (isClicked) {
                setIcon(new ImageIcon("Icons/Light-3.png"));
            } else {
                setIcon(new ImageIcon("Icons/Light-1.png"));
            }
        }

    }

 

    /**
     * Getter method for the attribute row.
     * 
     * @return the value of the attribute row
     */

    public int getRow() {
        return row;
    }

    /**
     * Getter method for the attribute column.
     * 
     * @return the value of the attribute column
     */

    public int getColumn() {
        return column;
    }

    // YOUR OTHER METHODS HERE
}
