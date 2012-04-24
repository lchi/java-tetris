package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * One block, the atomic unit.
 * @author dev
 *
 */
@SuppressWarnings("serial")
public class Block extends JPanel {

	Rectangle r;

	public Block() {
		super();
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		this.setSize(Board.BLOCKSIZE, Board.BLOCKSIZE);
		this.setBorder(new BevelBorder(BevelBorder.RAISED));
	}

	public void setColor(Color color) {
		this.setBackground(color);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(getBackground());
		g.fillRect(getX(), getY(), Board.BLOCKSIZE, Board.BLOCKSIZE);

	}

}
