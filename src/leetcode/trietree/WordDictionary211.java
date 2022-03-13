package leetcode.trietree;

public class WordDictionary211 {
    private Node root = null;

    public WordDictionary211() {
    }

    private static class Node {
        boolean end = false;
        Node[] children = new Node[26];
    }

    private Node getNode(Node node , String key){
        Node p = root;
        for (int i = 0; i < key.length(); i++) {
            if (p == null){
                return null;
            }
            char c = key.charAt(i);
            p = p.children[c-'a'];
        }
        return p;
    }
    public boolean get(String key){
        Node p = getNode(root,key);
        if (p == null || !p.end){
            return false;
        }
        return p.end;
    }


    public void addWord(String word) {
        root = addWord(root,word,0);
    }
    private Node addWord(Node node , String key, int i){
        if (node  == null){
            node = new Node();
        }
        if (i == key.length()){
            node.end = true;
            return node;
        }
        char c= key.charAt(i);
        node.children[c-'a'] = addWord(node.children[c-'a'],key,i+1);
        return node;
    }


    public boolean search(String word) {
        char[] pattern = word.toCharArray();
        return search(root,pattern,0);
    }
    private boolean search(Node node,char[] pattern,int i){
        if(node == null){
            return false;
        }
        if(i == pattern.length){
            return node.end;
        }
        char c = pattern[i];
        if (c == '.'){
            for (int j = 0; j < 26; j++) {
                if (node.children[j] == null){
                    continue;
                }
                Node newNode = node.children[j];
                if (search(newNode,pattern,i+1)){
                    return true;
                }
            }
        }else{
            int k = pattern[i] - 'a';
            if (node.children[k] == null){
                return false;
            }
            node = node.children[k];
            return search(node, pattern, i + 1);
        }
        return false;
    }
}
