package com.lincoln.game.animation;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class ninjastar extends sprite {
	public ninjastar(int x, int speed) {
		this.x = x;
		y = 30;
		w = 100;
		h = 100;
		this.speed = speed;
		im = new ImageIcon(ninjastar.class.getResource("ninjastar.gif"));
	}
	
	public void move() {
		if(y > 900) {
			y = 0;
		}
		y += speed;
	}

	public void draw(Graphics pen) {
		pen.drawImage(im.getImage(), x, y, w, h, null);
	}
}
