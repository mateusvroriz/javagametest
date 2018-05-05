package game;

import jplay.Sprite;

public class gameObject extends Sprite {
	
	public gameObject(int x, int y){
		super("src//recursos//sprite//tiletest.png",1); // img and frame number
		this.x = x;
		this.y = y;	
		}

}
