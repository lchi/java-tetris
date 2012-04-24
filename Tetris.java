package tetris;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Swing frame and main class, mostly used to setup
 * the UI.
 * @author dev
 *
 */
@SuppressWarnings("serial")
public class Tetris extends JFrame {

	JPanel panel;
	GameOrchestrator orchestrator;

	public Tetris() {
		setAndSizeComponents();
		initKeyListeners();
	}

	private void setAndSizeComponents() {
		this.setLayout(null);
		Insets insets = this.getInsets();

		Board gameBoard = new Board();
		gameBoard.setBounds(insets.left + 5, insets.top + 5, Board.BLOCKSIZE
				* Board.WIDTH, Board.BLOCKSIZE * Board.HEIGHT);
		gameBoard.setVisible(true);
		gameBoard.setBorder(new BevelBorder(BevelBorder.LOWERED));
		gameBoard.setOpaque(false);

		JPanel infoPanel = new JPanel();
		infoPanel.setName("Info");
		infoPanel.setLayout(null);
		infoPanel.setBounds(35 + Board.BLOCKSIZE * Board.WIDTH,
				insets.top + 35, 100, 100);
		infoPanel.setVisible(true);

		orchestrator = new GameOrchestrator(1, 1000, gameBoard, infoPanel, this);

		this.add(gameBoard);
		this.add(infoPanel);

		this.setBounds(50, 50, 400, 593);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Score: 0  Level: 1");
	}

	private void initKeyListeners() {
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// nothing, handle in keyPressed()
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 27: // esc
					quit();
					break;
				case 32: // space
					orchestrator.hardDrop();
					break;
				case 37: // <-
					orchestrator.shiftLeft();
					break;
				case 38: // up
					orchestrator.rotateRight();
					break;
				case 39: // ->
					orchestrator.shiftRight();
					break;
				case 40: // dwn arrow
					orchestrator.softDrop();
					break;
				case 65: // a
					orchestrator.rotateLeft();
					break;
				case 68: // d
					orchestrator.rotateRight();
					break;
				case 80: // p
					togglePause();
					break;
				default:
					e.consume();
					return;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

		});
	}

	public static void main(String[] args) {
		Tetris tetris = new Tetris();
		int score = tetris.play();
		JOptionPane.showMessageDialog(tetris, "You lose! Score=" + score);
		System.exit(0);
		
	}

	public int play() {
		return orchestrator.play();
	}

	public void quit() {
		System.exit(0);
	}

	private void togglePause() {
		orchestrator.togglePause();
	}
}
