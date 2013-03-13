package gui;

import org.newdawn.slick.Input;

public abstract class AbstractSelectableView extends AbstractView {
	
	private boolean selected;
	
	public AbstractSelectableView(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void handleMouseEvent(Input input) {
		selected = (getViewRect().contains(input.getMouseX(), input.getMouseY())) ? true : false;
	}
	
	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
