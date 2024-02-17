package ru.vsu.cs.course1.model;

import static ru.vsu.cs.course1.model.TriangleUtils.areSimilarTriangles;

public class Triangle {

	private final Point a;
	private final Point b;
	private final Point c;

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	public Point getC() {
		return c;
	}

	public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		Point a = new Point(x1, y1);
		Point b = new Point(x2, y2);
		Point c = new Point(x3, y3);
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public boolean isSimilarTo(Triangle other) {
		return areSimilarTriangles(this, other);
	}

	@Override
	public String toString() {
		return "{ %s, %s, %s }".formatted(a, b, c);
	}
}
