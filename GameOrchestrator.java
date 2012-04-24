package tetris;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Orchestrator to run and keep track of progress in 
 * the game.
 * 
 * @author dev
 *
 */
public class GameOrchestrator {
	int level, score;
	Tetrimino current, next;
	boolean lost, paused;
	Board gameBoard;
	int clockTick;
	JPanel infoPanel;
	JTextField infoBox;
	JFrame parent;
	int rows;

	public GameOrchestrator(int level, int clockTick, Board gameBoard,
			JPanel infoPanel, JFrame parent) {
		this.gameBoard = gameBoard;
		this.infoPanel = infoPanel;
		this.parent = parent;

		lost = false;
		paused = false;
		score = 0;
		rows = 0;
		this.level = level;

		this.clockTick = clockTick;
	}

	public int play() {
		long lastClockTick = System.currentTimeMillis();
		current = Tetrimino.getNext();
		next = Tetrimino.getNext();
		gameBoard.setNewTetrimino(current);
		addNextToInfo();
		while (!lost) {
			while (paused);
			if (lastClockTick + clockTick < System.currentTimeMillis()) {
				if (current.canShiftDown()) {
					current.shiftDown();
				} else {
					updateTetrimino();
				}
				lastClockTick = System.currentTimeMillis();
			}
		}
		return score;
	}

	private void increaseLevel() {
		this.level++;
		clockTick *= .85;
	}

	public void togglePause() {
		paused = !paused;
	}

	public void shiftRight() {
		current.shiftRight();
	}

	public void shiftLeft() {
		current.shiftLeft();
	}

	public void rotateRight() {
		current.rotateRight();
	}

	public void rotateLeft() {
		current.rotateLeft();
	}

	public void softDrop() {
		current.shiftDown();
	}

	public void hardDrop() {
		while (current.canShiftDown())
			current.shiftDown();
		updateTetrimino();
	}

	public void updateTetrimino() {
		infoPanel.remove(next);
		gameBoard.addToBoard(current);
		eliminateLines();
		current = next;
		current.setLocation(((Board.WIDTH/2)-1)*Board.BLOCKSIZE, 0);
		if(!gameBoard.setNewTetrimino(current)){
			lost = true;
		}
		next = Tetrimino.getNext();
		addNextToInfo();
		gameBoard.revalidate();
		gameBoard.repaint();
	}

	public void addNextToInfo(){
		infoPanel.add(next);
		next.setLocation(0, 0);
		infoPanel.revalidate();
		infoPanel.repaint();
	}
	
	public void eliminateLines() {
		Block[][] blocks = gameBoard.blocks;
		int numRows = 0;
		for (int i = 0; i < Board.HEIGHT; i++) {
			int count = 0;
			for (int j = 0; j < Board.WIDTH; j++) {
				if (blocks[j][i] != null)
					count++;
			}
			if (count == 10) {
				numRows++;
				gameBoard.shiftDownFrom(i--);
			}
		}
		updateScore(numRows);
	}

	public void updateScore(int numRows) {
		rows += numRows;
		switch (numRows) {
		case 0:
			return;
		case 1:
			score += level*100;
			break;
		case 2:
			score += level*300;
			break;
		case 3:
			score += level*800;
			break;
		case 4:
			score += level*2000;
			break;
		}
		if(rows >= 10){
			rows %= 10;
			increaseLevel();
		}
		updateInfo();
	}
	
	public void updateInfo(){
		parent.setTitle("Score: " + score + "  Level: " + level);
	}
	
}
