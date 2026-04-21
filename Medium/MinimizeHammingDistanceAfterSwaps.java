import java.util.*;

class Solution {

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {

        int n = source.length;

        // Step 1: DSU
        UnionFind uf = new UnionFind(n);

        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        // Step 2: group indices by parent
        Map<Integer, List<Integer>> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int parent = uf.find(i);
            groups.computeIfAbsent(parent, k -> new ArrayList<>()).add(i);
        }

        int hamming = 0;

        // Step 3: process each group
        for (List<Integer> group : groups.values()) {

            Map<Integer, Integer> freq = new HashMap<>();

            // count source values
            for (int idx : group) {
                freq.put(source[idx], freq.getOrDefault(source[idx], 0) + 1);
            }

            // match with target
            for (int idx : group) {
                int val = target[idx];

                if (freq.getOrDefault(val, 0) > 0) {
                    freq.put(val, freq.get(val) - 1); // matched
                } else {
                    hamming++; // unmatched
                }
            }
        }

        return hamming;
    }

    // DSU Class
    class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // path compression
            }
            return parent[x];
        }

        void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) {
                parent[pa] = pb;
            }
        }
    }
}
