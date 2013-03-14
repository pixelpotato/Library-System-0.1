package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public abstract class AbstractView {
	
	private Rectangle frame;
	private Rectangle bounds;
	
	public AbstractView(float x, float y, float width, float height) {
		frame = new Rectangle(x, y, width, height);
		bounds = new Rectangle(0, 0, width, height);
	}
	
	abstract public void drawView(Graphics g);
	
	protected void prepareContextForDrawing(Graphics g) {
		g.pushTransform();
		g.translate(frame.getX(), frame.getY());
		g.setWorldClip(bounds);
	}
	
	protected void restoreContext(Graphics g) {
		g.clearWorldClip();
		g.popTransform();
	}
	
	/**
	 * @return the frame
	 */
	public Rectangle getFrame() {
		return frame;
	}
	
	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(Rectangle frame) {
		this.frame = frame;
	}
	
	/**
	 * @return the bounds
	 */
	public Rectangle getBounds() {
		return bounds;
	}
	
	/**
	 * @param bounds
	 *            the bounds to set
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}
