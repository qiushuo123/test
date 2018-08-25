package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	int val=0;
	TreeNode left=null;
	TreeNode right=null;
	
	public TreeNode(int val){
		this.val=val;
	}
	
	
	public class Solution{
		//二叉树的镜像
		public void Mirror(TreeNode root){
			TreeNode tmp=null;
			if(root!=null){
				tmp=root.left;
				root.left=root.right;
				root.right=tmp;
				if(root.left!=null)
					Mirror(root.left);
				if(root.right!=null)
					Mirror(root.right);
			}
		}
	}

	
	//树的子结构
	public static boolean HasSubtree(TreeNode root1,TreeNode root2){
		boolean result=false;
		if(root2!=null&&root1!=null){
			if(root1.val==root2.val){
				result=doesTree1HaveTree2(root1,root2);
			}
			if(!result){
				result=HasSubtree(root1.left, root2);
			}
			if(!result){
				result=HasSubtree(root1.right, root2);
			}
		}
		return result;
	}
	
	public static boolean doesTree1HaveTree2(TreeNode node1,TreeNode node2){
		if(node2==null)
			return true;
		if(node1==null)
			return false;
		if(node1.val==node2.val)
			return false;
		return doesTree1HaveTree2(node1.left, node2.left)&&doesTree1HaveTree2(node1.right, node2.right);
		
	}
	
	
	//平衡二叉树
	public boolean IsBalanced_Solution(TreeNode root){
		return getDepth(root)!=-1;
	}
	
	private int getDepth(TreeNode root) {
		if(root==null) return 0;
		int left=getDepth(root.left);
		if(left==-1) return -1;
		int right=getDepth(root.right);
		if(right==-1) return -1;
		return Math.abs(left-right)>1?-1:1+Math.max(left, right);
	}
	
	
	//二叉树的深度
	//非递归写法
	public int TreeDepth2(TreeNode pRoot){
		if(pRoot==null){
			return 0;
		}
		Queue<TreeNode> queue=new LinkedList<TreeNode>();
		queue.add(pRoot);
		int depth=0,count=0,nextCount=1;
		while(queue.size()!=0){
			TreeNode top=queue.poll();
			count++;
			if(top.left!=null)
				queue.add(top.left);
			if(top.right!=null)
				queue.add(top.right);
			if(count==nextCount){
				nextCount=queue.size();
				count=0;
				depth++;
			}
		}
		return depth;
	}
	
	//递归写法
	public int TreeDepth(TreeNode pRoot){
		if(pRoot==null)
			return 0;
		int left=TreeDepth(pRoot.left);
		int right=TreeDepth(pRoot.right);
		return Math.max(left, right)+1;
	}
	
	
}
