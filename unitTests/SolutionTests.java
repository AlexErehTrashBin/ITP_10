import org.junit.Test;
import ru.vsu.cs.course1.FileUtils;
import ru.vsu.cs.course1.Task;
import ru.vsu.cs.course1.TaskUtils;
import ru.vsu.cs.course1.model.Triangle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SolutionTests {
	@Test
	public void test1() {
		Triangle a = new Triangle(0,0,2,0,0,2);
		Triangle b = new Triangle(0,0,4,0,0,4);
		boolean areSimilar = a.isSimilarTo(b);
		assertTrue(areSimilar);
	}
	@Test
	public void test3() {
		List<Triangle> triangles = FileUtils.getTrianglesFromFile("files/testIn/input01.txt");

		for (Triangle t: triangles) {
			System.out.println(t);
		}
		//assertTrue(areSimilar);
	}
	@Test
	public void test2(){
		List<Triangle> triangles = new ArrayList<>();
		triangles.add(new Triangle(3,3,0,0,0,0));
		triangles.add(new Triangle(2,0,1,3,2,1));
		triangles.add(new Triangle(3,1,2,0,1,3));
		triangles.add(new Triangle(0,2,3,3,1,2));
		List<List<Triangle>> subsets = Task.getSubsets(triangles);
		for (List<Triangle> subset: subsets) {
			for (Triangle t: subset) {
				System.out.println(t);
			}
			System.out.println();
		}
		//System.out.println();
	}
}
