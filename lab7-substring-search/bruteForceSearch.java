
// Java program for Naive Pattern Searching 

public class bruteForceSearch {
	  
	    public static void search(String txt, String pat) 
	    { 
			int n = txt.length();
			int m = pat.length();
			for (int pos = 0; pos < n-m+1; pos++ )             
			   { 
				if (txt.substring(pos, pos+m).equals(pat))
				  {
					 System.out.println("Found at pos " + pos);
					 return;      
				  }
			   }
			   System.out.println("Not found");
	    } 
	  
	    public static void main(String[] args) 
	    { 
	        //alter to take text file in..
			String txt = "ABACDABABCABAB"; 
			String pat = "ABAB"; 
			String txt1 = "SUFHUISHFIHSDDONKEYFACEASDAAS"; 
			String pat1 = "DONKEYFACE";
			String txt2 = "ABABDSDGDSGSABACDASDFSGBABCSFDABABEFGGG"; 
			String pat2 = "EFGGG";
			String txt3= "ABABDABACDABABCABABAISSDIAMXLNCSUASIDASDAUSODNASKDOANSD"; 
			String pat3 = "EFG";
			//First method calls are usually slower, this is to pass that bump
			search(txt, pat);

            long startTime = System.nanoTime();
            search(txt, pat);
            long elapsedTime = System.nanoTime() - startTime; 
			System.out.println(elapsedTime);
			startTime = System.nanoTime();
            search(txt1, pat1);
            elapsedTime = System.nanoTime() - startTime; 
			System.out.println(elapsedTime);
			
			startTime = System.nanoTime();
            search(txt2, pat2);
            elapsedTime = System.nanoTime() - startTime; 
			System.out.println(elapsedTime);
			
			startTime = System.nanoTime();
            search(txt3, pat3);
            elapsedTime = System.nanoTime() - startTime; 
            System.out.println(elapsedTime);
			
	    } 
	} 
