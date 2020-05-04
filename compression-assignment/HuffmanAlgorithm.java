

/******************************************************************************
 *  Compilation:  javac HuffmanAlgorithm.java
 *  To run java HuffmanAlgorithm [compress/decompress] [Input file] [Output file]
 * 
 *  Compress or expand a binary input stream using the Huffman algorithm.
 *
 * 
 *
 ******************************************************************************/


/**
 *   *
 *  *  @author Lleno Anya
 */
public class HuffmanAlgorithm {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // Do not instantiate.
    private HuffmanAlgorithm() { }

    // Huffman trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch,  int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }


    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */

    public static void compress(String file, String fileOut) {
        Node root = null;
        BinaryIn input = new BinaryIn(file);
        StringBuilder text = new StringBuilder();

        while (!input.isEmpty()) {
            text.append(input.readChar());
        }
        char[] c = text.toString().toCharArray();
        int[] characterFrequency = new int[R];
        for (int i = 0; i < c.length; i++)
            characterFrequency[c[i]]++;
 
        root = buildTrie(characterFrequency);
        
        String[] table = new String[R];
        buildCode(table, root, "");
        

        BinaryOut out = new BinaryOut(fileOut);
        writeTrie(root, out);
        out.write(c.length);
        // use Huffman code to encode input
        for (int i = 0; i < c.length; i++) {
            String code = table[c[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    out.write(false);
                }
                else if (code.charAt(j) == '1') {
                    out.write(true);
                }
                else throw new IllegalStateException("Illegal state");
            }
        }

        // close output stream
       out.flush();

    }


    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void decompress(String file, String fileOut) {
      
        // read in Huffman trie from input stream
        BinaryOut out = new BinaryOut(fileOut);
        BinaryIn in = new BinaryIn(file);
        Node root = readTrie(in);
        // number of bytes to write
        int length = in.readInt();

        // decode using the Huffman trie
        
        for (int i = 0; i < length; i++) {
            Node trie = root;
            while (!trie.isLeaf()) {
                boolean bit = in.readBoolean();
                if (bit) 
                    trie = trie.right;
                else     
                    trie = trie.left;
            }
            out.write(trie.ch, 8);
        }
        out.close();
    }

    // build the Huffman trie given frequencies
    private static Node buildTrie(int[] freq) {
        // initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));

        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
            else                 pq.insert(new Node('\1', 0, null, null));
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
        
    }


    // write bitstring-encoded trie to specified file
    private static void writeTrie(Node x, BinaryOut out) {
        if (x.isLeaf()) {
            out.write(true);
            out.write(x.ch, 8);
            return;
        }
        out.write(false);
        writeTrie(x.left, out);
        writeTrie(x.right, out);
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] table, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(table, x.left,  s + '0');
            buildCode(table, x.right, s + '1');
        }
        else {
            table[x.ch] = s;
        }
    }


    // read bitstring-encoded trie from specified file
    private static Node readTrie(BinaryIn in) {
        boolean isLeaf = in.readBoolean();
        if (isLeaf) {
            return new Node(in.readChar(), -1, null, null);
        }
        else {
            return new Node('\0', -1, readTrie(in), readTrie(in));
        }
    }

    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "compress" an {@code decompress()} if it is "decompress".
     *  ‘java HuffmanCompression compress filename output filename’  

     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String instruction = args[0];
        String file1 = args[1];
        String file2 = args[2];
        
        if(instruction.equals("compress")){
            long startTime = System.nanoTime();
			compress(file1, file2); 
            long elapsedTime = System.nanoTime() - startTime; 
			System.out.println(elapsedTime);
            
        }
        else if(instruction.equals("decompress")){
            long startTime = System.nanoTime();
			decompress(file1, file2); 
            long elapsedTime = System.nanoTime() - startTime; 
			System.out.println(elapsedTime);
        }  
        
    }   

}

