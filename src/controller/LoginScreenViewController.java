package controller;

import gui.TextView;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class LoginScreenViewController extends AbstractViewController {
	
	private TextView textView;
	
	/**
	 * Constructor for {@link LoginScreenViewController}
	 * 
	 * @param stateID
	 *            the ID associated with this view controller
	 * */
	public LoginScreenViewController(int stateID) {
		// set instance variable - stateID - to the parameter passed in
		super(stateID); // call the super constructor
	}
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		int width = 400;
		int height = 30;
		int x = container.getWidth() / 2 - width / 2;
		int y = container.getHeight() / 2 - height / 2;
		textView = new TextView(x, y, width, height, container.getInput());
		getGuiElements().add(textView);
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		textView.drawView(g);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		textView.updateTimeElaped(delta);
	}
	
}
