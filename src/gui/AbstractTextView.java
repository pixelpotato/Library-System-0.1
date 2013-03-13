package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public abstract class AbstractTextView extends AbstractSelectableView implements InputReceivingView {
	
	private static final float BORDER_WIDTH;
	private static final int CURSOR_BLINK_TIME;
	private static final float CURSOR_LEFT_OFFSET;
	private static final float CURSOR_TOP_BOTTOM_OFFSET;
	
	public enum ALIGN_BY {
		TEXT_AREA, OUTLINE
	};
	
	private Rectangle textViewOutlineShape;
	private int timeElapsed;
	private boolean showCursor;
	private Line cursor;
	private String text;
	private UnicodeFont font;
	private static String acceptableCharacters;
	
	static {
		BORDER_WIDTH = 2;
		CURSOR_BLINK_TIME = 500;
		CURSOR_LEFT_OFFSET = 5;
		CURSOR_TOP_BOTTOM_OFFSET = 2;
		acceptableCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()';\"\\/.,<>_+=-`~ :;";
	}
	
	@SuppressWarnings("unchecked")
	public AbstractTextView(float x, float y, float width, float height, ALIGN_BY alignment) throws SlickException {
		super(x, y, width, height);
		boolean alignByText = alignment == ALIGN_BY.TEXT_AREA;
		boolean alignByOutline = !alignByText;
		setViewRect(new Rectangle((alignByText) ? x : x + BORDER_WIDTH, (alignByText) ? y : y + BORDER_WIDTH, (alignByText) ? width : width - 2 * BORDER_WIDTH, (alignByText) ? height : height - 2 * BORDER_WIDTH));
		textViewOutlineShape = new Rectangle((alignByOutline) ? x : x - BORDER_WIDTH, (alignByOutline) ? y : y - BORDER_WIDTH, (alignByOutline) ? width : width + 2 * BORDER_WIDTH, (alignByOutline) ? height : height + 2 * BORDER_WIDTH);
		timeElapsed = 0;
		showCursor = false;
		cursor = new Line(getViewRect().getX() + CURSOR_LEFT_OFFSET, getViewRect().getY() + CURSOR_TOP_BOTTOM_OFFSET, getViewRect().getX() + CURSOR_LEFT_OFFSET, getViewRect().getY() + getViewRect().getHeight() - CURSOR_TOP_BOTTOM_OFFSET);
		text = "";
		font = new UnicodeFont("gui/font/Walkway/Bold.ttf", 16, false, false);
		font.getEffects().add(new ColorEffect(java.awt.Color.black));
		font.addAsciiGlyphs();
		font.loadGlyphs();
	}
	
	public void drawTextBox(Graphics g) {
		g.setColor(new Color(0.65f, 0.2f, 0.2f));
		g.fill(textViewOutlineShape);
		g.setColor((isSelected()) ? Color.white : new Color(1.0f, 0.85f, 0.85f));
		g.fill(getViewRect());
		font.drawString(getViewRect().getX() + CURSOR_LEFT_OFFSET, getViewRect().getY() + CURSOR_TOP_BOTTOM_OFFSET, text);
		if (showCursor && isSelected()) {
			g.setColor(Color.black);
			g.draw(cursor);
		}
	}
	
	public void updateTimeElaped(int delta) {
		timeElapsed += delta;
		if (timeElapsed > CURSOR_BLINK_TIME) {
			updateCursor();
		}
	}
	
	private void updateCursor() {
		showCursor = !showCursor;
		timeElapsed = 0;
	}
	
	public void addToInput(Input input) {
		input.addKeyListener(this);
	}
	
	public void appendToText(char c) {
		text = text + c;
	}
	
	public void keyPressed(int key, char c) {
		if (keyIsSpecial(key)) {
			
		} else if (characterIsAcceptable(c)) {
			appendToText(c);
		}
	}
	
	public boolean characterIsAcceptable(char c) {
		boolean contains = false;
		for (int i = 0; i < acceptableCharacters.length(); i++) {
			if (c == acceptableCharacters.charAt(i)) {
				contains = true;
			}
		}
		return contains;
	}
	
	public void keyReleased(int key, char c) {
		
	}
	
	public void inputEnded() {
		
	}
	
	public void inputStarted() {
		
	}
	
	public boolean isAcceptingInput() {
		return isSelected();
	}
	
	public void setInput(Input input) {
		
	}
	
	/**
	 * @return the timeElapsed
	 */
	public int getTimeElapsed() {
		return timeElapsed;
	}
	
	/**
	 * @param timeElapsed
	 *            the timeElapsed to set
	 */
	public void setTimeElapsed(int timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
}
