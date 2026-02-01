// Solution 
// TC - O(m*n) m - number of words in sentence, n - average length of word
// SC - O(m*n) for trie space
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

        String getLongestWord(String word, String result) { // Returns the longest Word
            TrieNode current = root;
            int resLen = result.length();
            int wordLen = word.length();
            boolean find = true; // Checks if the word can be built or not
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (current.children[ch - 'a'] == null || current.children[ch - 'a'].isEnd == false) {
                    find = false; // returns if the word cannot be built
                    break;
                }
                current = current.children[ch - 'a'];
            }
            if (find) { // if the word is built.
                if (resLen < wordLen) { // if the new word length is more the result length
                    result = word;
                } else if (resLen == wordLen) { // if the new word length is same of result length
                    for (int i = 0; i < resLen; i++) { // Iterate over each word and check its lexicographical value
                        if (word.charAt(i) < result.charAt(i)) {
                            result = word; // update result if current word is less lexicographically
                            break;
                        } else if (word.charAt(i) > result.charAt(i)) { // don't update result
                            break;
                        }
                    }
                }
            }
            return result;
        }
    }

    public String longestWord(String[] words) {

        Trie trie = new Trie();
        String result = "";
        for (String word : words) {
            trie.insert(word);
        }

        for (String word : words) {
            result = trie.getLongestWord(word, result);
        }

        return result;
    }
}