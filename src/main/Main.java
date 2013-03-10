package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import core.LibraryController;

public class Main {
	
	private static final int DISPLAY_WIDTH; // width of the window
	private static final int DISPLAY_HEIGHT; // height of the window
	private static final boolean DISPLAY_IS_FULLSCREEN; // whether the program runs in full screen mode or not
	private static final int DISPLAY_TARGET_FRAME_RATE; // the frame rate of the program
	
	static {
		DISPLAY_WIDTH = 1000;
		DISPLAY_HEIGHT = 562;
		DISPLAY_IS_FULLSCREEN = false;
		DISPLAY_TARGET_FRAME_RATE = 60;
	}
	
	/**
	 * The main method creates an AppGameContainer that holds the library program. A new instance of
	 * core.LibraryController is created. The display mode of the AppGameContainer is set, as well as
	 * the target frame rate. Finally, the library system is started and the program proceeds to the
	 * core.LibraryController instance's initStatesList() method.
	 * @param args the command line arguments
	 * */
	public static void main(String[] args) throws SlickException {
		// create new AppGameContainer, call the title of the window "Library System 0.1"
		AppGameContainer gameContainer = new AppGameContainer(new LibraryController("Library System 0.1"));
		// set the display mode of the window (width, height and full screen)
		gameContainer.setDisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT, DISPLAY_IS_FULLSCREEN);
		// set the frame rate
		gameContainer.setTargetFrameRate(DISPLAY_TARGET_FRAME_RATE);
		// start the program
		gameContainer.start();
	}

}
