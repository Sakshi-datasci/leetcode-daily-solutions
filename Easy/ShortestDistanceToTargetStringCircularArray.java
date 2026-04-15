class Solution {

    public int closestTarget(String[] words, String target, int startIndex) {

        int n = words.length;
        int minDist = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (words[i].equals(target)) {

                int forward = (i - startIndex + n) % n;
                int backward = (startIndex - i + n) % n;

                int dist = Math.min(forward, backward);

                minDist = Math.min(minDist, dist);
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
