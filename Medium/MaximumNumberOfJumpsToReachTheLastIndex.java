class Solution 
{
    public int maximumJumps(int[] nums, int target) 
    {
        int arrayLength = nums.length;

        int[] maximumJumps = new int[arrayLength];

        for(int index = 0; index < arrayLength; index++)
        {
            maximumJumps[index] = -1;
        }

        maximumJumps[0] = 0;

        for(int currentIndex = 0; currentIndex < arrayLength; currentIndex++)
        {
            if(maximumJumps[currentIndex] == -1)
            {
                continue;
            }

            for(int nextIndex = currentIndex + 1; 
                nextIndex < arrayLength; 
                nextIndex++)
            {
                long difference = 
                    (long)nums[nextIndex] - nums[currentIndex];

                if(difference >= -target && difference <= target)
                {
                    maximumJumps[nextIndex] = Math.max(
                        maximumJumps[nextIndex],
                        maximumJumps[currentIndex] + 1
                    );
                }
            }
        }

        return maximumJumps[arrayLength - 1];
    }
}
