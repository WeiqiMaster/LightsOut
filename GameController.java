import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;

public class GameController implements ActionListener, ItemListener {

    private GameModel model;
    private int width;
    private int height;
    private GameView view;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param width
     *            the width of the board on which the game will be played
     * @param height
     *            the height of the board on which the game will be played
     */
    public GameController(int width, int height) {
        this.width = width;
        this.height = height;
        model = new GameModel(width, height);
        view = new GameView(model, this);
    }


    /**
     * Callback used when the user clicks a button (reset, 
     * random or quit)
     *
     * @param e
     *            the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof GridButton) {
            GridButton src = (GridButton) e.getSource();
            int row = src.getRow();
            int column = src.getColumn();
            model.click(row, column);

            if (model.isFinished()) {
                view.update();
                Object[] options = {"Quit", "Play Again"};
                int choice = JOptionPane.showOptionDialog(null, //Component parentComponent
                               "Congratulations, you won in " + model.getNumberOfSteps() + " steps! Would you like to play again?", //Object message,
                               "Won", //String title
                               JOptionPane.YES_NO_OPTION, //int optionType
                               JOptionPane.INFORMATION_MESSAGE, //int messageType
                               null, //Icon icon,
                               options, options[1]); //Object[] options,
                if(choice == 0 ){
                   System.exit(0);
                }else{    
                    model.reset();
                }
            }
        } else if (e.getActionCommand().equals("Reset")) {
            model.reset();
        } else if (e.getActionCommand().equals("Random")) {
            model.randomize();
        } else if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        }
        

        if (view.solutionShown()) {
            model.setSolution();
        }
        view.update();
    }

    /**
     * Callback used when the user select/unselects
     * a checkbox
     *
     * @param e
     *            the ItemEvent
     */
    public void itemStateChanged(ItemEvent e){
        if (e.getStateChange() == e.SELECTED) {
            model.setSolution();
        } else {
            model.resetSolution();
        }
        view.update();
    }

  

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
