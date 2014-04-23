package gameUI;

import javax.swing.JFrame;

public class MainWindow {

	private static final String SNAKE_GAME_TITLE = "Snake Game";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame mainWindow = new JFrame(SNAKE_GAME_TITLE);
		mainWindow.add(new MainPanel());
		
		mainWindow.pack();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

}
