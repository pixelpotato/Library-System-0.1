package gui;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class TextView extends AbstractTextView {
	
	public TextView(float x, float y, float width, float height, ALIGN_BY alignment, Input input) throws SlickException {
		super(x, y, width, height, alignment);
		addToInput(input);
	}
	
}
