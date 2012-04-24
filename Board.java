package tetris;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * The game board that holds all the pieces and
 * blocks that have been stacked.
 * 
 * @author dev
 *
 */
@SuppressWarnings("serial")
public class Board extends JPanel {

	public final static int WIDTH = 10;
	public final static int HEIGHT = 24;
	public final static int BLOCKSIZE = 23;
	Block[][] blocks;
	Tetrimino current;

	public Board() {
		blocks = new Block[WIDTH][HEIGHT];
		this.setBackground(Color.WHITE);
		this.setLayout(null);
	}

	public void addToBoard(Tetrimino t) {
		Block[][] conf = t.getConfiguration();
		for (int i = 0; i < conf.length; i++) {
			for (int j = 0; j < conf[i].length; j++) {
				Block b = conf[i][j];
				if (b != null) {
					blocks[current.xPos + i][current.yPos + j] = b;
					this.add(b);
					t.remove(b);
					b.setLocation((current.xPos + i) * BLOCKSIZE,
							(current.yPos + j) * BLOCKSIZE);
				}
			}
		}
		revalidate();
		repaint();
	}

	public boolean setNewTetrimino(Tetrimino t) {
		if (current != null) {
			this.remove(current);
		}
		current = t;
		this.add(current);
		current.setCollisionChecker(new CollisionChecker() {
			public boolean collision(Block[][] conf) {
				for (int i = 0; i < conf.length; i++) {
					for (int j = 0; j < conf[i].length; j++) {
						if (conf[i][j] != null) {
							if (current.xPos + i >= blocks.length
									|| current.yPos + j >= blocks[current.xPos
											+ i].length
									|| blocks[current.xPos + i][current.yPos
											+ j] != null) {
								return true;
							}
						}
					}
				}
				return false;
			}
		});
		if(current.collisionChecker.collision(current.getConfiguration())){
			return false;
		}
		repaint();
		return true;
	}

	@Override
	public void repaint() {
		super.repaint();
	}

	public void shiftDownFrom(int row) {
		for (int i = 0; i < WIDTH; i++) {
			this.remove(blocks[i][row]);
			blocks[i][row] = null;
		}
		for (int r = row; r > 0; r--) {
			for (int c = 0; c < WIDTH; c++) {
				blocks[c][r] = blocks[c][r - 1];
				if (blocks[c][r] != null) {
					blocks[c][r].setLocation(c * BLOCKSIZE,
							r * BLOCKSIZE);
				}
			}
		}
		revalidate();
		repaint();
	}

}
