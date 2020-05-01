import javafx.scene.control.TreeCell;

//import Trie.TrieNode;

public class Trie{

// Alphabet size (# of symbols) we pick 26 for English alphabet
static final int ALPHABET_SIZE = 26; 


// class for Trie node 
static class TrieNode { 
    TrieNode[] children = new TrieNode[ALPHABET_SIZE]; 
    // isEndOfWord is true if the node represents end of a word i.e. leaf node
    boolean isEndOfWord; 

    TrieNode(){ 
        isEndOfWord = false; 

        for (int i = 0; i < ALPHABET_SIZE; i++) 
            children[i] = null; 
    } 

    void set(int i) {
        if(children[i] == null){
            children[i] = new TrieNode();
        }
;
    }
    
    void set(String key) {
        int len = key.length();

        if(len == 0){
            isEndOfWord = true;
        }
        else{
            int index = key.charAt(0) - 97;
            set(index);
            String newkey = key.substring(1);
            children[index].set(newkey);
        }
    }
    
}

static TrieNode root;
// If not key present, inserts into trie 
// If the key is prefix of Trie node,  
//  marks leaf node
static void insert(String key){ 
    root.set(key);
} 

// Returns true if key presents in trie, else false 
static boolean search(String key) { 
    TrieNode curNode = root;
    for(int i = 0; i < key.length(); i++){
        if(curNode.children[key.charAt(i)-97] != null){
            curNode = curNode.children[key.charAt(i)-97];
        }
        else{
            return curNode.isEndOfWord;
        }
    
        
    }
    return true;
	
} 


// Driver 
public static void main(String args[]) { 

// Input keys (use only 'a' through 'z' and lower case) 
String keys[] = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver"};


String output[] = {"Not present in trie", "Present in trie"}; 

root = new TrieNode(); 

// Construct trie 
int i; 
for (i = 0; i < keys.length ; i++) {
insert(keys[i]); 
}

// Search for different keys 
if(search("book")){
    System.out.println(output[1]);
}
else{
    System.out.println(output[0]);
}
if(search("simple")){
    System.out.println(output[1]);
}
else{
    System.out.println(output[0]);
}
if(search("apple")){
    System.out.println(output[1]);
}
else{
    System.out.println(output[0]);
}
if(search("film")){
    System.out.println(output[1]);
}
else{
    System.out.println(output[0]);
}


} 

//end of class
} 