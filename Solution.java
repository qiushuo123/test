package algorithm;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;




public class Solution {
	
	//主函数
	public static void main(String[] args){
		Solution p=new Solution();
		System.out.println(p.Permutation("abc").toString());
	}
	
	//替换空格
	public static String replaceSpace(StringBuffer str){
		String sti=str.toString();
		char[] strChar=sti.toCharArray();
		StringBuffer out=new StringBuffer();
		
		for(int i=0;i<strChar.length;i++){
			if(strChar[i]==' ')
				out.append("%20");
			else
				out.append(strChar[i]);
		}
		return out.toString();
	}
	
	//跳台阶
	public static int jumpFloor(int target){
		if(target<=0) return 0;
		return (int)Math.pow(2, target-1);
		
	}
	
	//二进制中1的个数
	public int NumberOf1(int n){
		int count=0;
		while(n!=0){
			count++;
			n=n&(n-1);
		}
		return count;
	}
	
	//数值的整数次方
	public double Power(double base,int exponent){
		int n=Math.abs(exponent);
		if(n==0)
			return 1;
		if(n==1)
			return base;
		double result=Power(base, n>>1);
		result*=result;
		if((n&1)==1)
			result*=base;
		if(exponent<0)
			result=1/result;
		return result;
	}
	
	//机器人的运动范围
	public int movingCount(int threshold,int rows,int cols){
		int flag[][]=new int[rows][cols];//记录是否已经走过
		return helper(0,0,rows,cols,flag,threshold);
	}
	
	private int helper(int i,int j,int rows,int cols,int[][] flag,int threshold){
		if(i<0||i>=rows||j<0||j>=cols||numSum(i)+numSum(j)>threshold||flag[i][j]==1) return 0;
		flag[i][j]=1;
		return helper(i-1, j, rows, cols, flag, threshold)+helper(i+1, j, rows, cols, flag, threshold)
				+helper(i, j-1, rows, cols, flag, threshold)+helper(i, j+1, rows, cols, flag, threshold)+1;
	}
	
	private int numSum(int i){
		int sum=0;
		do{
			sum+=i%10;
		}while((i=i/10)>0);
		return sum;
	}
	
	//用两个栈实现队列
	Stack<Integer> stack1=new Stack<Integer>();
	Stack<Integer> stack2=new Stack<Integer>();
	
	public void push(int node){
		stack1.push(node);
	}
	
	public int pop(){
		if(stack1.empty()&&stack2.empty()){
			throw new RuntimeException("Queue is empty");
		}
		if(stack2.empty()){
			while(!stack1.empty()){
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
	
	//字符串的排列
	public ArrayList<String> Permutation(String str){
		ArrayList<String> re=new ArrayList<String>();
		if(str==null||str.length()==0)
			return re;
		HashSet<String> set=new HashSet<String>();
		fun(set,str.toCharArray(),0);
		re.addAll(set);
		Collections.sort(re);
		return re;
	}
	
	void fun(HashSet<String> re,char[] str,int k){
		if(k==str.length){
			re.add(new String(str));
			return;
		}
		for(int i=k;i<str.length;i++){
			swap(str,i,k);
			fun(re,str,k+1);
			swap(str,i,k);
		}
	}
	
	
	void swap(char[] str,int i,int j){
		if(i!=j){
			char t=str[i];
			str[i]=str[j];
			str[j]=t;
		}
	}
	
	
	//二叉搜索树的后序遍历序列
	public boolean VerifySquenceOfBST(int[] sequence){
		if(sequence.length==0)
			return false;
		if(sequence.length==1)
			return true;
		return ju(sequence,0,sequence.length-1);
	}
	
	public boolean ju(int[] a,int star,int root){
		if(star>=root)
			return true;
		int i=root;
		while(i>star&&a[i-1]>a[root])
			i--;
		for(int j=star;j<i-1;j++)
			if(a[j]>a[root])
				return false;
		return ju(a, star, i-1)&&ju(a, star, root-1);
				
	}
	
	//吧数组排成最小的数
	public String PrintMinNumber(int[] numbers){
		if(numbers==null||numbers.length==0) return "";
		int len=numbers.length;
		String[] str=new String[len];
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<len;i++){
			str[i]=String.valueOf(numbers[i]);
		}
		Arrays.sort(str,new Comparator<String>() {
			public int compare(String s1, String s2){
				String c1=s1+s2;
				String c2=s2+s1;
				return c1.compareTo(c2);
			}
		});
		for(int i=0;i<len;i++){
			sb.append(str[1]);
		}
		return sb.toString();
	}
	
	
	//滑动窗口的最大值
	public ArrayList<Integer> maxInWindows(int[] num,int size){
		ArrayList<Integer> res=new ArrayList<>();
		if(size==0) return res;
		int begin;
		ArrayDeque<Integer> q=new ArrayDeque<>();
		for(int i=0;i<num.length;i++){
			begin=i-size+1;
			if(q.isEmpty())
				q.add(i);
			else if(begin>q.peekFirst())
				q.pollFirst();
			
			while((!q.isEmpty())&&num[q.peekLast()]<=num[i])
				q.pollLast();
			q.add(i);
			if(begin>=0)
				res.add(num[q.peekFirst()]);
		}
		return res;
	}
	
	//矩阵中的路径
	public boolean hasPath(char[] matrix,int rows,int cols,char[] str){
		int flag[]=new int[matrix.length];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++){
			if(helper1(matrix,rows,cols,i,j,str,0,flag))
				return true;
		}
		return false;
	}
	
	private boolean helper1(char[] matrix,int rows,int cols,int i,int j,char[] str,int k,int[] flag){
		int index=1*cols+j;
		if(i<0||i>=rows||j<0||j>=cols||matrix[index]!=str[k]||flag[index]==1)
			return false;
		if(k==str.length-1) return true;
		flag[index]=1;
		if(helper1(matrix, rows, cols, i-1, j, str, k+1, flag)
				||helper1(matrix, rows, cols, i+1, j, str, k+1, flag)
				||helper1(matrix, rows, cols, i, j-1, str, k+1, flag)
				||helper1(matrix, rows, cols, i, j+1, str, k+1, flag))
			return true;
		flag[index]=0;
		return false;
	}
	
	
	//数组中出现次数超过一半
	public int MOreThanHalfNum_Soluction(int[]  array){
		HashMap<Integer, Integer> hashmap=new HashMap<>();
		for(int i=0;i<array.length;i++){
			Integer tmp=hashmap.get(array[i]);
			if(tmp==null){
				hashmap.put(array[i], 1);
				tmp=1;
			}else{
				hashmap.put(array[i], tmp+1);
			}
			if(tmp+1>array.length/2) return array[i];
		}
		return 0;
	}
	
	
}
