import java.util.*;

class Solution {
    
    public int minimumEffort(int[][] tasks) {

        Arrays.sort(tasks, (task1, task2) -> {
            
            return (task2[1] - task2[0]) - 
                   (task1[1] - task1[0]);
        });

        int currentEnergy = 0;
        int answer = 0;

        for (int[] task : tasks) {

            int actual = task[0];
            int minimum = task[1];

            if (currentEnergy < minimum) {

                answer += (minimum - currentEnergy);

                currentEnergy = minimum;
            }

            currentEnergy -= actual;
        }

        return answer;
    }
}
