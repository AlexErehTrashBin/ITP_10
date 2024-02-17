package ru.vsu.cs.course1;


import ru.vsu.cs.course1.model.Triangle;

import java.util.*;

public class Task {
	public static List<List<Triangle>> getSubsetsExperimental(List<Triangle> input){
		if (input == null || input.isEmpty()) return new ArrayList<>();
		List<List<Triangle>> resultingList = new ArrayList<>();
		for (Triangle t: input) {
			boolean found = false;
			for (List<Triangle> subset: resultingList) {
				if (t.isSimilarTo(subset.get(0))) {
					found = true;
					subset.add(t);
					break;
				}
			}
			if (!found){
				List<Triangle> newSubset = new ArrayList<>();
				newSubset.add(t);
				resultingList.add(newSubset);
			}
		}
		return resultingList;
	}
	public static List<List<Triangle>> getSubsetsOld(List<Triangle> input) {
		if (input == null || input.isEmpty()) return new ArrayList<>();
		List<Triangle> triangles = new ArrayList<>(input);
		List<List<Triangle>> resultingList = new ArrayList<>();
		List<Integer> tempIndices = new ArrayList<>();
		List<Triangle> tempTriangles = new ArrayList<>();
		// TODO(Глянуть на эффективность алгоритма)
		while (!triangles.isEmpty()) {
			tempIndices.add(0);
			tempTriangles.add(triangles.get(0));
			for (int i = 1; i < triangles.size(); i++) {
				Triangle ithTriangle = triangles.get(i);
				if (tempTriangles.get(0).isSimilarTo(ithTriangle)) {
					tempTriangles.add(ithTriangle);
					tempIndices.add(i);
				}
			}
			SortUtils.sortDescending(tempIndices);
			for (int index : tempIndices) {
				triangles.remove(index);
			}
			resultingList.add(new ArrayList<>(tempTriangles));
			tempIndices.clear();
			tempTriangles.clear();
		}
		return resultingList;
	}
	public static List<List<Triangle>> getSubsets(List<Triangle> input) {
		return getSubsetsExperimental(input);
	}
}