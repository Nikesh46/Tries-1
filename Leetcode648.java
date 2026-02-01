// Solution
// TC O(m * n) where m is number of words in dictionary and n is average length of word
// SC O(m * n) for trie space
class Solution {

    class TrieNode {

        private TrieNode[] children;
        private boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        }

    }

    class Trie {

        private TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        // insert
        void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.children[ch - 'a'] == null) {
                    current.children[ch - 'a'] = new TrieNode();
                }
                current = current.children[ch - 'a'];
            }
            current.isEnd = true;
        }

        String replace(String word) {
            TrieNode current = root;
            StringBuilder currentString = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.children[ch - 'a'] == null || current.isEnd)
                    break;
                current = current.children[ch - 'a'];
                currentString.append(ch);
            }
            if (current.isEnd) {
                return currentString.toString();
            }
            return word;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {

        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }

        StringBuilder res = new StringBuilder();
        String[] words = sentence.split(" ");
        boolean first = true;

        for (String word : words) {
            if (!first) {
                res.append(" ");
            }
            res.append(trie.replace(word));
            first = false;
        }

        return res.toString();

    }
}