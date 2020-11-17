import java.util.ArrayList;
import java.util.List;



public class SquareRootOperationNumber {
    public static int solution(int A, int B) {
       	List<Integer> list=new ArrayList<>();
         for(int i=A;i<=B;i++) {
	     list.add(i);
	  }
     return nextList( list,0);
      }
    
	private static int nextList(List<Integer> list, int count) {
			List<Integer> list2=new ArrayList<>();
			for(int i:list) {
			double a=Math.sqrt(i);
			if(a == (int)a) {
				list2.add((int)a);
			}
		}
			if (list2.equals(list)||list2.size()==0) {
				return count;
			}else return nextList( list2,count+1);
	}

	public static void main(String ...s) {
		System.out.println(solution(6000, 7000));
	}
}