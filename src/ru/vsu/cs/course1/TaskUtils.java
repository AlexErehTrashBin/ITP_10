package ru.vsu.cs.course1;

import ru.vsu.cs.course1.model.Point;
import ru.vsu.cs.course1.model.Triangle;

import java.util.ArrayList;
import java.util.List;

public class TaskUtils {
	public static double[][] convertMatrix(List<List<Double>> arrayList) {
		if (arrayList.isEmpty()) return new double[][]{null};
		double[][] arr = new double[arrayList.size() - 1][6];
		for (int i = 0; i < arrayList.size() - 1; i++) {
			if (arrayList.get(i).isEmpty()) {
				arr[i] = null;
				continue;
			}
			for (int j = 0; j <= 5; j++) {
				arr[i][j] = arrayList.get(i).get(j);
			}
		}
		return arr;
	}

	public static void convertTrianglesToDoubleMatrix(List<Triangle> triangles, List<List<Double>> matrix) {
		for (Triangle t : triangles) {
			Point a = t.getA();
			Point b = t.getB();
			Point c = t.getC();
			matrix.add(List.of(a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY()));
		}
	}

	public static List<Triangle> getTrianglesFromMatrix(double[][] input) {
		List<Triangle> triangles = new ArrayList<>();
		for (double[] triangleCoordinates : input) {
			triangles.add(getTriangleByCoordinatesArray(triangleCoordinates));
		}
		return triangles;
	}

	public static double[][] convertSubsetsToMatrix(List<List<Triangle>> subsets) {
		List<List<Double>> matrix = new ArrayList<>();
		for (List<Triangle> subset : subsets) {
			convertTrianglesToDoubleMatrix(subset, matrix);
			matrix.add(new ArrayList<>());
		}

		return convertMatrix(matrix);
	}

	public static Triangle getTriangleByCoordinatesArray(double[] coordinates) {
		double x1 = coordinates[0];
		double y1 = coordinates[1];
		double x2 = coordinates[2];
		double y2 = coordinates[3];
		double x3 = coordinates[4];
		double y3 = coordinates[5];
		return new Triangle(x1, y1, x2, y2, x3, y3);
	}

}