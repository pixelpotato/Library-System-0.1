package gui;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public abstract class AbstractView {
	
	private Rectangle viewRect;
	
	public AbstractView(float x, float y, float width, float height) {
		viewRect = new Rectangle(x, y, width, height);
	}
	
	abstract public void handleMouseEvent(Input input);
	
	/**
	 * @return the viewRect
	 */
	public Rectangle getViewRect() {
		return viewRect;
	}
	
	/**
	 * @param viewRect
	 *            the viewRect to set
	 */
	public void setViewRect(Rectangle viewRect) {
		this.viewRect = viewRect;
	}
	
}
