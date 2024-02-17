package ru.vsu.cs.course1.model;

import ru.vsu.cs.course1.SortUtils;
import ru.vsu.cs.course1.model.Point;
import ru.vsu.cs.course1.model.Triangle;

import java.util.List;

public class TriangleUtils {
	private static final double EPS = 1E-12;
	public static boolean areSimilarTriangles(Triangle a, Triangle b){
		List<Double> aSortedSL = getSortedSquaredLengths(a);
		List<Double> bSortedSL = getSortedSquaredLengths(b);
		if (Math.abs(aSortedSL.get(0) * bSortedSL.get(1) - aSortedSL.get(1) * bSortedSL.get(0)) > EPS) return false;
		if (Math.abs(aSortedSL.get(0) * bSortedSL.get(2) - aSortedSL.get(2) * bSortedSL.get(0)) > EPS) return false;
		//if (Math.abs(aSortedSL.get(1) * bSortedSL.get(2) - aSortedSL.get(2) * bSortedSL.get(1)) > EPS) return false;
		return true;
	}
	private static List<Double> getSortedSquaredLengths(Triangle t){
		double ab = TriangleUtils.getSideLengthSquared(t.getA(), t.getB());
		double ac = TriangleUtils.getSideLengthSquared(t.getA(), t.getC());
		double bc = TriangleUtils.getSideLengthSquared(t.getB(), t.getC());

		return getSortedSquaredLengths(ab, ac, bc);
	}
	private static List<Double> getSortedSquaredLengths(double abLengthSquared, double bcLengthSquared, double acLengthSquared){
		return SortUtils.sortThreeNumsAscending(abLengthSquared, acLengthSquared, bcLengthSquared);
	}
	public static double getSideLengthSquared(Point a, Point b){
		return (b.getX() - a.getX()) * (b.getX() - a.getX()) + (b.getY() - a.getY()) * (b.getY() - a.getY());
	}
}
