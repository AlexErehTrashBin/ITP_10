package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortUtils {
	public static List<Double> sortThreeNumsAscending(double first, double second, double third){
		double a = first;
		double b = second;
		double c = third;
		if (a > b){
			double temp = a;
			a = b;
			b = temp;
		}
		if (a > c){
			double temp = a;
			a = c;
			c = temp;
		}
		if (b > c){
			double temp = b;
			b = c;
			c = temp;
		}
		return new ArrayList<>(Arrays.asList(a, b, c));
	}
	// Сортировка пузырьком в обратном порядке (как делает Comparator.reverseOrder())
	public static void sortDescending(List<Integer> list){
		if (list == null || list.size() <= 1) return;
		for (int i = list.size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (list.get(j).compareTo(list.get(j + 1)) < 0) {
					int temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}
	}
}
