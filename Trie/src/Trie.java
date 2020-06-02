public class Trie {
    private TrieNode root;

    Trie(){
        root = new TrieNode();
    }

    public boolean addWord(String word){
        if(word.length() != 0)
            return addWord(word, 0, root);

        return false;
    }

    private boolean addWord(String word, int index, TrieNode root){
        if(index == word.length()-1){
            if(root.letters.containsKey(word.charAt(index))){
                return true;
            }

            TrieNode tempNode = new TrieNode(word.charAt(index));
            tempNode.isWord = true;
            root.letters.put(word.charAt(index), tempNode);
            return true;
        }

        if(root.letters.containsKey(word.charAt(index))){
            addWord(word, index+1, root.letters.get(word.charAt(index)));
        } else {
            TrieNode tempNode = new TrieNode(word.charAt(index));
            root.letters.put(word.charAt(index), tempNode);
            addWord(word, index+1, root.letters.get(word.charAt(index)));
        }

        return false;
    }

    public boolean removeWord(String word){
        return removeWord(word, 0, root);
    }

    private boolean removeWord(String word, int index, TrieNode root){
        if(index == word.length()){
            root.isWord = false;

            return root.letters.size() == 0;
        }

        if(!root.letters.containsKey(word.charAt(index)))
            return false;

        boolean ans = removeWord(word, index+1, root.letters.get(word.charAt(index)));

        if(ans){
            if(!root.letters.get(word.charAt(index)).isWord && root.letters.get(word.charAt(index)).letters.size() == 0){
                root.letters.put(word.charAt(index), null);
                return true;
            }
        }
    }

    public boolean hasWord(String word){
        return hasWord(word, 0, root);
    }

    private boolean hasWord(String word, int index, TrieNode root){
        if(word.length() == 0)
            return true;

        if(root.letters.containsKey(word.charAt(index)))
            return hasWord(word, index+1, root.letters.get(word.charAt(index)));

        return false;
    }
}
