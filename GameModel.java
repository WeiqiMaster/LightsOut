import java.util.Random;

public class GameModel {

	private int height;
	private int width;
	private boolean[][] board;
	private int numberOfSteps;
	private Solution solution;

	public GameModel(int width, int height) {
		this.width = width;
        this.height = height;
        numberOfSteps = 0;
		board = new boolean[height][width];
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public boolean isON(int i, int j) {
		return board[i][j];
	}

	public void reset() {
		numberOfSteps = 0;
		board = new boolean[height][width];
	}

	public void set(int i, int j, boolean value) {
		// column i, row j
		board[j][i] = value;
	}

	public void click(int i, int j) {
		set(j, i, !isON(i, j));
		if (i > 0) {
			set(j, i - 1, !isON(i - 1, j));
		}
		if (i < (height - 1)) {
			set(j, i + 1, !isON(i + 1, j));	
		} 
		if (j > 0) {
			set(j - 1, i, !isON(i, j - 1));
		}
		if (j < (width - 1)) {
			set(j + 1, i, !isON(i, j + 1));
		}
		numberOfSteps++;
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}

	public boolean isFinished() {
		for (boolean[] bb: board) {
			for (boolean b: bb) {
				if (!b) {
					return false;
				}
			}
		}
		return true;
	}

	public void randomize() {
		numberOfSteps = 0;
		Random random = new Random();
		do {
			for(int i = 0; i < height; i++){
	            for(int j = 0; j < width ; j++) {
	                board[i][j] = random.nextBoolean();
	            }
	        }
		} while (LightsOut.solve(this).isEmpty());
		
	}

	public void setSolution() {
		solution = LightsOut.solveShortest(this);
	}

	public void resetSolution() {
		solution = null;
	}

	public boolean solutionSelects(int i, int j) {
		if (solution == null) {
			return false;
		}
		return solution.get(i, j);
	}

	public String toString() {
		StringBuffer out = new StringBuffer();
        out.append("[");
        for(int i = 0; i < height; i++){
            out.append("[");
            for(int j = 0; j < width ; j++) {
                if (j>0) {
                    out.append(",");
                }
                out.append(board[i][j]);
            }
            out.append("]"+(i < height -1 ? ",\n" :""));
        }
        out.append("]");
        return out.toString();
	}

}
