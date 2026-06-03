import java.util.*;

class Solution {

    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        long answer = Long.MAX_VALUE;

        RideHelper waterHelper = new RideHelper(waterStartTime, waterDuration);
        RideHelper landHelper = new RideHelper(landStartTime, landDuration);

        // Land -> Water
        for (int i = 0; i < landStartTime.length; i++) {
            long landFinish = (long) landStartTime[i] + landDuration[i];
            answer = Math.min(answer, waterHelper.getEarliestFinish(landFinish));
        }

        // Water -> Land
        for (int i = 0; i < waterStartTime.length; i++) {
            long waterFinish = (long) waterStartTime[i] + waterDuration[i];
            answer = Math.min(answer, landHelper.getEarliestFinish(waterFinish));
        }

        return (int) answer;
    }

    static class RideHelper {
        int n;
        int[] start;
        long[] prefixMinDuration;
        long[] suffixMinStartPlusDuration;

        RideHelper(int[] startTime, int[] duration) {
            n = startTime.length;

            int[][] rides = new int[n][2];
            for (int i = 0; i < n; i++) {
                rides[i][0] = startTime[i];
                rides[i][1] = duration[i];
            }

            Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));

            start = new int[n];
            prefixMinDuration = new long[n];
            suffixMinStartPlusDuration = new long[n];

            for (int i = 0; i < n; i++) {
                start[i] = rides[i][0];
            }

            prefixMinDuration[0] = rides[0][1];
            for (int i = 1; i < n; i++) {
                prefixMinDuration[i] =
                        Math.min(prefixMinDuration[i - 1], rides[i][1]);
            }

            suffixMinStartPlusDuration[n - 1] =
                    (long) rides[n - 1][0] + rides[n - 1][1];

            for (int i = n - 2; i >= 0; i--) {
                long value = (long) rides[i][0] + rides[i][1];
                suffixMinStartPlusDuration[i] =
                        Math.min(suffixMinStartPlusDuration[i + 1], value);
            }
        }

        long getEarliestFinish(long previousRideFinish) {
            int idx = upperBound(start, previousRideFinish);

            long result = Long.MAX_VALUE;

            // Rides already open when previous ride finishes
            if (idx > 0) {
                result = Math.min(
                        result,
                        previousRideFinish + prefixMinDuration[idx - 1]
                );
            }

            // Rides not yet open
            if (idx < n) {
                result = Math.min(
                        result,
                        suffixMinStartPlusDuration[idx]
                );
            }

            return result;
        }

        private int upperBound(int[] arr, long target) {
            int left = 0, right = arr.length;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (arr[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }
    }
}
