package gui;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public interface InputReceivingView extends KeyListener {
	
	void addToInput(Input input);
	
	void appendToText(char c);
	
	boolean characterIsAcceptable(char c);
	
}
