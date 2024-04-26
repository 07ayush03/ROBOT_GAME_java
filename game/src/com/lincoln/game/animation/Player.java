package com.lincoln.game.animation;

import javax.swing.ImageIcon;

public class Player extends sprite {
	public Player() {
		x = 50;
		y = 450;
		w = 200;
		h = 100;
		im = new ImageIcon(Player.class.getResource("pluuu.gif"));
	}

	public void move() {
		x += speed;
	}

	public boolean outOfScreen() {
		return x > 1500;
	}
}