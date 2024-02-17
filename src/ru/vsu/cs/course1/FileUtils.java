package ru.vsu.cs.course1;

import ru.vsu.cs.course1.model.Triangle;
import ru.vsu.cs.util.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	private static double[] getCoordinateArrayFromLine(String line){
		String[] coordinatesStringArray = line.split("\\s+");
		double[] coordinates = new double[6];
		for (int j = 0; j < coordinatesStringArray.length; j++) {
			coordinates[j] = Double.parseDouble(coordinatesStringArray[j]);
		}
		return coordinates;
	}
	public static List<Triangle> getTrianglesFromFile(String path){
		List<Triangle> triangles = new ArrayList<>();
		List<String> lines;
		try {
			lines = List.of(ArrayUtils.readLinesFromFile(path));
		} catch (FileNotFoundException e) {
			System.err.printf("Файл %s не был найден", path);
			return triangles;
		}
		for (String line : lines) {
			double[] coordinates = getCoordinateArrayFromLine(line);
			triangles.add(TaskUtils.getTriangleByCoordinatesArray(coordinates));
		}
		return triangles;
	}

	public static void writeSubsetsToFile(List<List<Triangle>> subsets, String path){
		PrintStream ps;
		try {
			ps = new PrintStream(path);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		for (List<Triangle> subset: subsets) {
			for (Triangle t: subset) {
				ps.println(t);
			}
			ps.println();
		}
	}
}
