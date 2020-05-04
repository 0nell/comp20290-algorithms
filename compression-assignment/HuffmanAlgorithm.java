import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class HuffmanAlgorithm{
    private class Node{
        int frequency;
        char character;
        Node left;
        Node right;

        public Node(int frequency, char character, Node left, Node right) {
            this.frequency = frequency;
            this.character = character;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }
    }
    private class Table{
        char character;
        String code;

        public Table(char character, String code) {
            this.character = character;
            this.code = code;
        }
        @Override
        public String toString() {
            return character + ":" + code;
        }

       
        
        
    }
    private class FrequencyComparator implements Comparator<Node> {
        @Override
        public int compare(Node x, Node y) {
            return x.frequency - y.frequency;
        }
    }
    
    
    Node root = null;
    ArrayList<Table> table;
    private ArrayList<Table> buildTable(Node root, String
    path) {
        ArrayList<Table> table = new ArrayList<>();
        if(root.left == null && root.right == null && root
        .character != '-'){
            table.add(new Table(root.character, path));
            return table;
        }
        
        table.addAll(buildTable(root.left,path + "0"));
        table.addAll(buildTable(root.right,path + "1"));

        return table;
    }
    void compress(String file){
        int[] characterFrequency = new int[27];
        int size = 0;
        BinaryIn input = new BinaryIn(file);
        StringBuilder text = new StringBuilder();
        
        while(!input.isEmpty()){
            text.append(input.readChar());
        }
        int tempChar;
        String t = text.toString();
        t = t.toUpperCase();
        for(int i = 0; i < t.length(); i++){
            tempChar = t.charAt(i);
            
            if(tempChar == 32){
                characterFrequency[26]++;
            }
            else{
                tempChar -= 65;
                characterFrequency[tempChar]++;
            }
        }
        for(int i =0; i < 27; i++){
            if(characterFrequency[i] !=0){
                size++;
            }
        }
        PriorityQueue<Node> queue = new PriorityQueue<Node>(size, new FrequencyComparator());
        for(int i =0; i < 27; i++){
            if(characterFrequency[i] !=0){
                if(i == 26){
                    Node node = new Node(characterFrequency[i], '_', null, null);
                    queue.add(node);
                }
                else{
                    Node node = new Node(characterFrequency[i], (char)(i+65), null, null);
                    queue.add(node);
                }
                
            }
        }

        while(queue.size() > 1){
            Node left = queue.poll();
            Node right = queue.poll();

            Node newNode = new Node(left.frequency+right.frequency, '-', left, right);
            root = newNode;
            queue.add(newNode);
        }
        table = buildTable(root, "");
        
        for(Table ta : table){
            System.out.println(ta.toString());
        }
        BinaryOut out = new BinaryOut("output.bin");

        writeTrie(root, out);
        out.flush();
        

    }
    public void writeTrie(Node node, BinaryOut out) {
        if (node.isLeaf()) {
            out.write(true);
            out.write(node.character, 8);
            return;
        }
        out.write(false);
        writeTrie(node.left, out);
        writeTrie(node.right, out);
    }

    public static void main(String[] args) {
        HuffmanAlgorithm algo = new HuffmanAlgorithm();
        algo.compress("abra.txt");
    }
}