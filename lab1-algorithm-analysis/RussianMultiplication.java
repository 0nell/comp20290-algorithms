
public class RussianMultiplication
{

    long  russianMultiply(int n, int m) {
        
        int accumulator = 0;
        while (n!= 0){ 
            if(n%2 != 0){
                accumulator +=  m;
            }
            n= n/ 2;
            m = m *2;
        }
        
        return accumulator;
    }
        
 public static void main(String[] args)
 { long startTime;
    long elapsedTime;
    RussianMultiplication r = new RussianMultiplication();
    startTime = System.nanoTime(); 
    r.russianMultiply(1, 1);
    elapsedTime = System.nanoTime() - startTime;  
    System.out.println(elapsedTime);  
    startTime = System.nanoTime(); 
    r.russianMultiply(100, 100);
    elapsedTime = System.nanoTime() - startTime;  
    System.out.println(elapsedTime); 
    startTime = System.nanoTime(); 
    r.russianMultiply(3586, 7864);
    elapsedTime = System.nanoTime() - startTime;  
    System.out.println(elapsedTime); 
    startTime = System.nanoTime(); 
    r.russianMultiply(7775, 3159);
    elapsedTime = System.nanoTime() - startTime;  
    System.out.println(elapsedTime); 
    startTime = System.nanoTime(); 
    r.russianMultiply(26541, 15445);
    elapsedTime = System.nanoTime() - startTime;  
    System.out.println(elapsedTime); 

 }
}



