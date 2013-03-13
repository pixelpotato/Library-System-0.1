package controller;

import gui.AbstractView;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AbstractViewController extends BasicGameState {
	
	private final int stateID; // the ID associated with this view controller
	
	private ArrayList<AbstractView> guiElements;
	
	/**
	 * Constructor for {@link AbstractViewController}
	 * 
	 * @param stateID
	 *            the ID associated with this view controller
	 * */
	public AbstractViewController(int stateID) {
		this.stateID = stateID; // set stateID to the parameter passed in
		guiElements = new ArrayList<AbstractView>();
	}
	
	/**
	 * Needed by the Slick engine to differentiate between view controllers
	 * 
	 * @return the ID associate d with this view controller
	 * */
	public int getID() {
		return this.getStateID();
	}
	
	/**
	 * @return the stateID
	 */
	public int getStateID() {
		return stateID;
	}
	
	/**
	 * The initialization method of this view controller
	 * 
	 * @param container
	 *            the game container
	 * @param game
	 *            the game
	 * */
	abstract public void init(GameContainer container, StateBasedGame game) throws SlickException;
	
	/**
	 * The render method of this view controller, this method renders to the
	 * screen
	 * 
	 * @param container
	 *            the game container
	 * @param game
	 *            the game
	 * @param g
	 *            the graphics context
	 * */
	abstract public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException;
	
	/**
	 * The update method, this method updates the model of the view controller
	 * 
	 * @param container
	 *            the game container
	 * @param game
	 *            the game
	 * @param delta
	 *            the delta value
	 * */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		pollInput(container.getInput());
	}
	
	private void pollInput(Input input) {
		pollKeyboard(input);
		pollMouse(input);
	}
	
	protected void pollKeyboard(Input input) {
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
	}
	
	protected void pollMouse(Input input) {
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			for (AbstractView view : guiElements) {
				view.handleMouseEvent(input);
			}
		}
	}
	
	/**
	 * @return the guiElements
	 */
	public ArrayList<AbstractView> getGuiElements() {
		return guiElements;
	}
	
	/**
	 * @param guiElements
	 *            the guiElements to set
	 */
	public void setGuiElements(ArrayList<AbstractView> guiElements) {
		this.guiElements = guiElements;
	}
	
}
