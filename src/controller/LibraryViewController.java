package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class LibraryViewController extends StateBasedGame {
	
	/**
	 * Constructor for {@link LibraryViewController}
	 * 
	 * @param name
	 *            the title of the display
	 * */
	public LibraryViewController(String name) {
		super(name); // call the super class' constructor
	}
	
	/**
	 * Initialize the states of the LibraryController
	 * 
	 * @param container
	 *            the game container
	 * @throws SlickException
	 *             if a state can not be initialized properly
	 * */
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new LoginScreenViewController(0));
	}
	
}
