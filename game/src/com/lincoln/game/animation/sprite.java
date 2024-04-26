package com.lincoln.game.animation;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public abstract class sprite {
	public int x, y, w, h, speed;
	ImageIcon im;

	public void draw(Graphics pen) {
		pen.drawImage(im.getImage(), x, y, w, h, null);
	}

	public void move() {
		y += speed;
	}
}
