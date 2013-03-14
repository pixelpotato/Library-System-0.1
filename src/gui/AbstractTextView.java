package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

public abstract class AbstractTextView extends AbstractSelectableView implements KeyboardReceivingView {
	
	private static final float BORDER_WIDTH;
	private static final int CURSOR_BLINK_TIME;
	private static final float CURSOR_LEFT_OFFSET;
	private static final float CURSOR_TOP_BOTTOM_OFFSET;
	
	public enum ALIGN_BY {
		TEXT_AREA, OUTLINE
	};
	
	private Rectangle textViewShape;
	private int timeElapsed;
	private boolean showCursor;
	private Line cursor;
	private String text;
	private UnicodeFont font;
	private static String acceptableCharacters;
	private int cursorIndex;
	private int scrollOffset;
	
	static {
		BORDER_WIDTH = 2;
		CURSOR_BLINK_TIME = 530;
		CURSOR_LEFT_OFFSET = 5 + BORDER_WIDTH;
		CURSOR_TOP_BOTTOM_OFFSET = 2 + BORDER_WIDTH;
		acceptableCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()';\"\\/.,<>_+=-`~ :;?|";
	}
	
	@SuppressWarnings("unchecked")
	public AbstractTextView(float x, float y, float width, float height) throws SlickException {
		super(x, y, width, height);
		setBounds(new Rectangle(0, 0, width, height));
		textViewShape = new Rectangle(BORDER_WIDTH, BORDER_WIDTH, getBounds().getWidth() - BORDER_WIDTH * 2, getBounds().getHeight() - BORDER_WIDTH * 2);
		timeElapsed = 0;
		showCursor = false;
		cursor = new Line(CURSOR_LEFT_OFFSET, CURSOR_TOP_BOTTOM_OFFSET, CURSOR_LEFT_OFFSET, getBounds().getHeight() - CURSOR_TOP_BOTTOM_OFFSET);
		text = "";
		font = new UnicodeFont("gui/font/Walkway/Bold.ttf", findFontSizeForHeight(getBounds().getHeight() - 2 * CURSOR_TOP_BOTTOM_OFFSET), false, false);
		font.getEffects().add(new ColorEffect(java.awt.Color.black));
		font.addAsciiGlyphs();
		font.loadGlyphs();
		cursorIndex = 0;
		scrollOffset = 0;
	}
	
	private int findFontSizeForHeight(float height) throws SlickException {
		int currentFontSize = 1;
		int currentHeight = 0;
		while (currentHeight <= height) {
			UnicodeFont f = new UnicodeFont("gui/font/Walkway/Bold.ttf", currentFontSize, false, false);
			currentHeight = f.getAscent() + f.getDescent();
			if (currentHeight <= height)
				currentFontSize++;
		}
		return currentFontSize;
	}
	
	public void drawView(Graphics g) {
		super.prepareContextForDrawing(g);
		g.setColor(new Color(0.65f, 0.2f, 0.2f));
		g.fill(getBounds());
		g.setColor((isSelected()) ? Color.white : Color.lightGray /* new
																 * Color(1.0f,
																 * 0.85f, 0.85f) */);
		g.fill(textViewShape);
		font.drawString(CURSOR_LEFT_OFFSET,
				CURSOR_TOP_BOTTOM_OFFSET, text);
		if (showCursor && isSelected()) {
			g.setColor(Color.black);
			g.draw(cursor);
		}
		super.restoreContext(g);
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
		super.addMouseListener(input);
	}
	
	public void appendToText(char c) {
		text = text + c;
		moveCursorPositionToCharacter(text.length());
	}
	
	private void moveCursorPositionToCharacter(int c) {
		cursorIndex = c;
		updateCursorPosition();
	}
	
	private void updateCursorPosition() {
		float width = font.getWidth(text.substring(0, cursorIndex)) + 1;
		cursor.setX(width);
	}
	
	public void keyPressed(int key, char c) {
		if (isSelected()) {
			if (!text.equals("") && key == 14) {
				text = text.substring(0, text.length() - 1);
				moveCursorPositionToCharacter(text.length());
			} else if (characterIsAcceptable(c)) {
				appendToText(c);
			}
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
	
	/**
	 * @return the cursorIndex
	 */
	public int getCursorIndex() {
		return cursorIndex;
	}
	
	/**
	 * @param cursorIndex
	 *            the cursorIndex to set
	 */
	public void setCursorIndex(int cursorIndex) {
		this.cursorIndex = cursorIndex;
	}
	
	/**
	 * @return the scrollOffset
	 */
	public int getScrollOffset() {
		return scrollOffset;
	}
	
	/**
	 * @param scrollOffset
	 *            the scrollOffset to set
	 */
	public void setScrollOffset(int scrollOffset) {
		this.scrollOffset = scrollOffset;
	}
	
}
