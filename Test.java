package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import algorithm.ComparatorDemo.Student;

public class Test {
	
	static class point {
		public int a;
		public int b;
	}
	
	public static void main(String[] args){
		Scanner scanner=new Scanner(System.in);
		int nums=scanner.nextInt();
		List<point> list=new ArrayList<point>();
		for(int i=0;i<nums;i++){
			point tmp=new point();
			tmp.a=scanner.nextInt();
			tmp.b=scanner.nextInt();
			list.add(tmp);
		}
		Test test=new Test(list);
		
		/*
		for(point listtmp:list){
			System.out.print(listtmp.a);
			System.out.print(listtmp.b);
			System.out.println();
		}
		*/
	}
	
	public List<point> mList;
	public List<Comparator<point>> mCmpList = new ArrayList<Comparator<point>>();
	public Test(List<point> list){
		mList = list;
		mCmpList.add(compareAgeASC);
		mCmpList.add(comparePointDESC);
		sort(mList, mCmpList);
	}
	
	
	public void sort(List<point> list, final List<Comparator<point>> comList) {
		if (comList == null)
			return;
		Comparator<point> cmp = new Comparator<point>() {
			@Override
			public int compare(point o1, point o2) {
				for (Comparator<point> comparator : comList) {
					if (comparator.compare(o1, o2) > 0) {
						return 1;
					} else if (comparator.compare(o1, o2) < 0) {
						return -1;
					}
				}
				return 0;
			}
		};
		Collections.sort(list, cmp);
	}
 
	private Comparator<point> compareAgeASC = new Comparator<Test.point>() {
 
		@Override
		public int compare(point o1, point o2) {
			return o1.a > o2.a ? 1 : -1;
		}
	};
 
	private Comparator<point> comparePointDESC = new Comparator<Test.point>() {
 
		@Override
		public int compare(point o1, point o2) {
			return o1.b < o2.b ? 1 : -1;
		}
	};
 

}
