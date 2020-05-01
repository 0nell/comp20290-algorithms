
public class fibonacci {

	
static int fibonacciIterative(int n){
  if (n<=1)
      return 1 ;

  int fib = 1;
  int prevFib =  1;

  for (int i = 2; i < n; i++) {
   int temp = fib;
   fib = fib + prevFib;
   prevFib = temp;
  }
  return fib;
 }

 static void towersOfHanoi(int disk, char source, char dest, char auxillary){
     if(disk == 1){
        System.out.println("Move disk " + disk + " from " +  source + " to " + dest); 
     }
     else{
        towersOfHanoi(disk-1, source, auxillary, dest);
        System.out.println("Move disk " + disk + " from " +  source + " to " + dest); 
        towersOfHanoi(disk-1,auxillary, dest, source) ;
     }
     
 }

 public static void main (String args[]) 
    { 
    int n = 9; 
    System.out.println(fibonacciIterative(n));
    
    } 
    
 
 
}
