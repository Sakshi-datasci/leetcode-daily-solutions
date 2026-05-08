import java.util.*;

class Solution {

    // Sieve for smallest prime factor
    private int[] smallestPrimeFactor(int limit) {
        int[] spf = new int[limit + 1];

        for (int i = 0; i <= limit; i++) {
            spf[i] = i;
        }

        for (int i = 2; i * i <= limit; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= limit; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }

        return spf;
    }

    // Check if a number is prime
    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public int minJumps(int[] nums) {

        int n = nums.length;

        if (n == 1) {
            return 0;
        }

        int maxValue = 0;

        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }

        int[] spf = smallestPrimeFactor(maxValue);

        // prime -> list of indices divisible by that prime
        Map<Integer, List<Integer>> divisibleIndices = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int value = nums[i];

            Set<Integer> primeFactors = new HashSet<>();

            while (value > 1) {

                int prime = spf[value];

                primeFactors.add(prime);

                while (value % prime == 0) {
                    value /= prime;
                }
            }

            for (int prime : primeFactors) {
                divisibleIndices
                    .computeIfAbsent(prime, k -> new ArrayList<>())
                    .add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[n];

        Arrays.fill(distance, -1);

        queue.offer(0);
        distance[0] = 0;

        Set<Integer> usedPrime = new HashSet<>();

        while (!queue.isEmpty()) {

            int currentIndex = queue.poll();

            int currentDistance = distance[currentIndex];

            if (currentIndex == n - 1) {
                return currentDistance;
            }

            // Move left
            if (currentIndex - 1 >= 0 &&
                distance[currentIndex - 1] == -1) {

                distance[currentIndex - 1] = currentDistance + 1;
                queue.offer(currentIndex - 1);
            }

            // Move right
            if (currentIndex + 1 < n &&
                distance[currentIndex + 1] == -1) {

                distance[currentIndex + 1] = currentDistance + 1;
                queue.offer(currentIndex + 1);
            }

            int value = nums[currentIndex];

            // Teleport only if current value is prime
            if (isPrime(value) && !usedPrime.contains(value)) {

                usedPrime.add(value);

                List<Integer> nextIndices =
                    divisibleIndices.getOrDefault(value, new ArrayList<>());

                for (int nextIndex : nextIndices) {

                    if (distance[nextIndex] == -1) {

                        distance[nextIndex] = currentDistance + 1;
                        queue.offer(nextIndex);
                    }
                }
            }
        }

        return -1;
    }
}
