import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.BorderLayout;

public class GameView extends JFrame {

    private GridButton[][] board;
    private GameModel model;
    private JLabel numberOfSteps;
    private JCheckBox showSolution;

    private int rows;
    private int columns;


    /**
     * Constructor used for initializing the Frame
     * 
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */
    public GameView(GameModel gameModel, GameController gameController) {
        super("Lights Out");

        this.model = gameModel;
        rows = gameController.getHeight();
        columns = gameController.getWidth();

        JPanel canvas = new JPanel();

        canvas.setBackground(Color.WHITE);
        canvas.setLayout(new GridLayout(rows, columns));
        canvas.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        canvas.setSize(800, 1000);

        board = new GridButton[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                GridButton gridButton = new GridButton(column, row);
                gridButton.addActionListener(gameController);
                board[row][column] = gridButton;
                canvas.add(gridButton);
            }
        }

        JPanel control = new JPanel();
        control.setLayout(new GridLayout(4, 1));
        control.setBackground(Color.WHITE);
        control.setSize(400, 200);

        numberOfSteps = new JLabel();
        numberOfSteps.setText("Number of steps: " + model.getNumberOfSteps());

        JButton reset, random, quit;
        showSolution = new JCheckBox("Solution");
        reset = new JButton("Reset");
        random = new JButton("Random");
        quit = new JButton("Quit");
        reset.addActionListener(gameController);
        random.addActionListener(gameController);
        showSolution.addItemListener(gameController);
        quit.addActionListener(gameController);
        reset.setBackground(Color.WHITE);
        random.setBackground(Color.WHITE);
        showSolution.setBackground(Color.WHITE);
        quit.setBackground(Color.WHITE);

        control.add(reset);
        control.add(random);
        control.add(showSolution);
        control.add(quit);

        add(canvas, BorderLayout.WEST);
        add(control, BorderLayout.EAST);
        add(numberOfSteps, BorderLayout.SOUTH);

        //setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        pack();
        setResizable(false);

        //display the window
        setVisible(true);
    }

    /**
     * updates the status of the board's GridButton instances based 
     * on the current game model, then redraws the view
     */
    public void update(){
        numberOfSteps.setText("Number of steps: " + model.getNumberOfSteps());
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (model.isON(row, column)) {
                    if (model.solutionSelects(row, column)) {
                        board[row][column].setState(true, true);
                    } else {
                        board[row][column].setState(true, false);
                    }
                } else {
                    if (model.solutionSelects(row, column)) {
                        board[row][column].setState(false, true);
                    } else {
                        board[row][column].setState(false,false);
                    }
                }
            }
        }

    }

    /**
     * returns true if the ``solution'' checkbox
     * is checked
     *
     * @return the status of the ``solution'' checkbox
     */
    public boolean solutionShown(){
        return showSolution.isSelected();
    }

}
