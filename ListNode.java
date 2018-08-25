package algorithm;

public class ListNode {
	int val;
	ListNode next=null;
	
	ListNode(int val) {
		// TODO Auto-generated constructor stub
		this.val=val;
	}

	public class Solution{
		//链表的第k个结点
		public ListNode FindKthtoTail(ListNode head,int k){
			if(head==null||k<=0)
				return null;
			ListNode pre=head;
			ListNode last=head;
			for(int i=1;i<k;i++){
				if(pre.next!=null)
					pre=pre.next;
				else
					return null;
			}
			while(pre.next!=null){
				pre=pre.next;
				last=last.next;
			}
			return last;
		}
	}
}
