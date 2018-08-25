package algorithm;
import java.util.Scanner;
 
public class Transport {
	static int minpath = Integer.MAX_VALUE;
 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine().trim());
		Point[] points = new Point[num];
		for (int i = 0; i < num; i++) {
			String[] locations = sc.nextLine().trim().split(",");
			points[i] = new Transport().new Point(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]));
		}
		dfs(new Transport().new Point(0, 0), points, 0, 0);
		System.out.println(minpath);
	}
 
	public static void dfs(Point start, Point[] points, int sum, int count) {
		if (count == points.length) {
			sum += start.getLength(new Transport().new Point(0, 0));// �ص����
			if (sum < minpath) {
				minpath = sum;
			}
			return;
		}
 
		for (int i = 0; i < points.length; i++) {
			if (!points[i].isVisited) {
				points[i].isVisited = true;// ����
				dfs(points[i], points, sum + start.getLength(points[i]), count + 1);
				points[i].isVisited = false;// δ����
			}
		}
 
	}
 
	class Point {
		int x, y;
		boolean isVisited;
 
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.isVisited = false;
		}
 
		public int getLength(Point point) {
			return Math.abs(this.x - point.x) + Math.abs(this.y - point.y);
		}
	}
}
