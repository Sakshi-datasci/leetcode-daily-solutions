class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];

        // Best index for this suffix
        int index = -1;
        int length = Integer.MAX_VALUE;
    }

    TrieNode root = new TrieNode();

    // Update best candidate
    private void updateNode(TrieNode node, int wordLen, int idx) {
        if (wordLen < node.length) {
            node.length = wordLen;
            node.index = idx;
        }
    }

    // Insert reversed word into trie
    private void insert(String word, int idx) {
        TrieNode node = root;

        updateNode(node, word.length(), idx);

        for (int i = word.length() - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'a';

            if (node.children[ch] == null) {
                node.children[ch] = new TrieNode();
            }

            node = node.children[ch];

            updateNode(node, word.length(), idx);
        }
    }

    // Search longest matching suffix
    private int search(String word) {
        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'a';

            if (node.children[ch] == null) {
                break;
            }

            node = node.children[ch];
        }

        return node.index;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        // Build Trie
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        // Process Queries
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }
}
