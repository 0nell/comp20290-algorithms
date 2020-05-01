public class TowerOfHanoi {
    public static void towersOfHanoi(int disk, char source, char dest, char auxillary){
        if(disk == 1){
           System.out.println("Move disk " + disk + " from " +  source + " to " + dest); 
        }
        else{
           towersOfHanoi(disk-1, source, auxillary, dest);
           System.out.println("Move disk " + disk + " from " +  source + " to " + dest); 
           towersOfHanoi(disk-1,auxillary, dest, source) ;
        }
        
    }
   
    public static void main(String[] args) {
        towersOfHanoi(3, 'A', 'C', 'B'); 
        towersOfHanoi(4, 'A', 'C', 'B'); 
        
    }
}