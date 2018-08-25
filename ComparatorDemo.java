package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 
public class ComparatorDemo {
	public List<Student> mList;
	public List<Comparator<Student>> mCmpList = new ArrayList<Comparator<Student>>();
	public ComparatorDemo(List<Student> list){
		mList = list;
		mCmpList.add(compareAgeASC);
		mCmpList.add(comparePointDESC);
		sort(mList, mCmpList);
	}
	public void sort(List<Student> list, final List<Comparator<Student>> comList) {
		if (comList == null)
			return;
		Comparator<Student> cmp = new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				for (Comparator<Student> comparator : comList) {
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
 
	private Comparator<Student> compareAgeASC = new Comparator<ComparatorDemo.Student>() {
 
		@Override
		public int compare(Student o1, Student o2) {
			return o1.age > o2.age ? 1 : -1;
		}
	};
 
	private Comparator<Student> comparePointDESC = new Comparator<ComparatorDemo.Student>() {
 
		@Override
		public int compare(Student o1, Student o2) {
			return o1.point < o2.point ? 1 : -1;
		}
	};
 
	class Student {
		public int age;
		public String name;
		public int point;
	}
}
