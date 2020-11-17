import java.util.ArrayList;
import java.util.List;


public class OpenClosedBrackets{
public   static void main(String ...strings )
{
	
	System.out.println(	solution("(())))("));//4

	System.out.println(	solution("))"));//2
	System.out.println(	solution("(())"));//2
		System.out.println(	solution("(()))(()()())))"));
}

	
	 static int solution(String S) 
	    { 
		        		
	        		
	        int length = S.length(); 
	        int openbrkt[] = new int[length+1]; 
	        int    closebrkt[] = new int[length+1]; 
	        int pos = -1; 
	       
	        openbrkt[0] = 0; 
	        closebrkt[length] = 0; 
	        if (S.charAt(0)=='(') 
	            openbrkt[1] = 1; 
	        if (S.charAt(length-1) == ')') 
	            closebrkt[length-1] = 1; 
	       
	      
	        for (int i = 1; i < length; i++) 
	        { 
	            if ( S.charAt(i) == '(' ) 
	                openbrkt[i+1] = openbrkt[i] + 1; 
	            else
	                openbrkt[i+1] = openbrkt[i]; 
	        } 
	       
	     ///brackets in each position
	        for (int i = length-2; i >= 0; i--) 
	        { 
	            if ( S.charAt(i) == ')' ) 
	                closebrkt[i] = closebrkt[i+1] + 1; 
	            else
	                closebrkt[i] = closebrkt[i+1]; 
	        } 
	       
	       //Results
	        if (closebrkt[0] == 0) 
	            return 0; 
	        if (openbrkt[length] == 0) 
	            return length; 
	      
	                for (int i=0; i<=length; i++) 
	            if (openbrkt[i] == closebrkt[i]) 
	                pos = i; 
	       
	        return pos; 
	    } 
	      
}	
	
