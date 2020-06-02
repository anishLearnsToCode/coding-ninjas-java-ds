import java.util.HashMap;

public class TrieNode {
    public char ch;
    boolean isWord;
    HashMap<Character, TrieNode> letters;

    TrieNode(){
        isWord = false;
        letters = new HashMap<>();
    }
    TrieNode(Character ch){
        this.ch = ch;
        letters = new HashMap<>();
    }
}
