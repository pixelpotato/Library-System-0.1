package gui;

import org.newdawn.slick.Input;

public abstract class AbstractSelectableView extends AbstractView implements MouseInputReveivingView {
	
	private boolean selected;
	
	public AbstractSelectableView(float x, float y, float width, float height) {
		super(x, y, width, height);
		
	}
	
	public void mouseClicked(int button, int x, int y, int clickCount) {
		selected = (getFrame().contains(x, y)) ? true : false;
	}
	
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		
	}
	
	public void mousePressed(int button, int x, int y) {
		
	}
	
	public void mouseReleased(int button, int x, int y) {
		
	}
	
	public void mouseWheelMoved(int change) {
		
	}
	
	public boolean isAcceptingInput() {
		return true;
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
	
	public void addMouseListener(Input input) {
		input.addMouseListener(this);
	}
	
}
