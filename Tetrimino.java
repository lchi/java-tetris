package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Represents one tetris 'piece', which consists of four blocks.
 * 
 * @author dev
 *
 */
public class Tetrimino extends JPanel {
	private int active;
	int xPos, yPos;
	private ArrayList<Block[][]> configurations;
	protected CollisionChecker collisionChecker;

	public static Tetrimino getNext() {
		switch ((int) (Math.random() * 7)) {
		case 0:
			return new Tetrimino('i');
		case 1:
			return new Tetrimino('l');
		case 2:
			return new Tetrimino('j');
		case 3:
			return new Tetrimino('o');
		case 4:
			return new Tetrimino('s');
		case 5:
			return new Tetrimino('z');
		case 6:
			return new Tetrimino('t');
		default:
			return null;
		}
	}

	private Tetrimino(char type) {
		active = 0;
		xPos = (Board.WIDTH / 2) - 1;
		yPos = 0;
		configurations = BlockConfiguration.getForType(type);
		for (Block[][] conf : configurations) {
			setBlockColors(conf, getColor(type));
		}
		// this.setBackground(Color.BLACK);
		this.setOpaque(false);
		this.setLayout(null);

		updatePosition(0, 0);
		updateContained();
		validate();
		repaint();
	}

	public void setCollisionChecker(CollisionChecker c) {
		this.collisionChecker = c;
	}

	private void updateContained() {
		Block[][] conf = getConfiguration();
		this.setBounds(Board.BLOCKSIZE * xPos, Board.BLOCKSIZE * yPos,
				conf.length * Board.BLOCKSIZE, conf[0].length * Board.BLOCKSIZE);
		this.setPreferredSize(new Dimension(conf.length * Board.BLOCKSIZE,
				conf[0].length * Board.BLOCKSIZE));
		this.removeAll();
		updateBlocks();
		validate();
		repaint();
	}

	private void setBlockColors(Block[][] conf, Color c) {
		for (int i = 0; i < conf.length; i++) {
			for (int j = 0; j < conf[i].length; j++) {
				if (conf[i][j] != null) {
					conf[i][j].setColor(c);
				}
			}
		}
	}

	private Color getColor(char type) {
		switch (type) {
		case 'i':
			return Color.CYAN;
		case 'j':
			return Color.MAGENTA;
		case 'l':
			return Color.ORANGE;
		case 's':
			return Color.BLUE;
		case 'z':
			return Color.GREEN;
		case 'o':
			return Color.RED;
		case 't':
			return Color.YELLOW;
		default:
			return Color.BLACK;
		}
	}

	public Block[][] getConfiguration() {
		return configurations.get(active);
	}

	public Block[][] getNextLeftRotation(){
		if (active - 1 >= 0) {
			return configurations.get(active - 1);
		} else {
			return configurations.get(configurations.size() - 1);
		}
	}
	
	public Block[][] getNextRightRotation(){
		if (1 + active < configurations.size()) {
			return configurations.get(1 + active);
		} else {
			return configurations.get(0);
		}
	}
	
	public void rotateRight() {
		Block[][] conf = getNextRightRotation();
		if (withinBoundaries(conf) && !collisionChecker.collision(conf)) {
			active = ++active < configurations.size() ? active : 0;
			updateContained();
		}
	}

	public void rotateLeft() {
		Block[][] conf = getNextLeftRotation();
		if (withinBoundaries(conf) && !collisionChecker.collision(conf)) {
			active = --active >= 0 ? active : configurations.size() - 1;
			updateContained();
		}
	}

	public void shiftRight() {
		Block[][] conf = getConfiguration();
		xPos++;
		if (canShiftRight(conf) && !collisionChecker.collision(conf)) {
			updatePosition(1, 0);
		} else {
			xPos--;
		}
	}

	public void shiftLeft() {
		Block[][] conf = getConfiguration();
		xPos--;
		if (canShiftLeft(conf) && !collisionChecker.collision(conf)) {
			updatePosition(-1, 0);
		} else {
			xPos++;
		}
	}

	public void shiftDown() {
		yPos++;
		updatePosition(0, 1);
	}

	public void updatePosition(int dx, int dy) {
		this.setLocation(0 + (Board.BLOCKSIZE * xPos),
				0 + (Board.BLOCKSIZE * yPos));
	}

	public void updateBlocks() {
		Block[][] conf = getConfiguration();
		for (int i = 0; i < conf.length; i++) {
			for (int j = 0; j < conf[i].length; j++) {
				if (conf[i][j] != null) {
					conf[i][j].setLocation(Board.BLOCKSIZE * i, Board.BLOCKSIZE
							* j);
					this.add(conf[i][j]);
				}
			}
		}
	}

	public boolean withinBoundaries(Block[][] conf) {
		return xPos + conf.length - 1 < Board.WIDTH && xPos >= 0;
	}

	public boolean canShiftRight(Block[][] conf) {
		return xPos + conf.length - 1 < Board.WIDTH;
	}

	public boolean canShiftLeft(Block[][] conf) {
		return xPos >= 0;
	}

	public boolean canShiftDown() {
		yPos++;
		boolean canShift = yPos < Board.HEIGHT
				&& !collisionChecker.collision(getConfiguration());
		yPos--;
		return canShift;
	}

}
