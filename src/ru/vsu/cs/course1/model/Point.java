package ru.vsu.cs.course1.model;

import java.text.NumberFormat;

public class Point {
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	private final double x;
	private final double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(13);
		nf.setMinimumFractionDigits(0);
		return "(%s; %s)".formatted(nf.format(x), nf.format(y));
	}
}
