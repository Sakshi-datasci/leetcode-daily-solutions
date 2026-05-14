class Solution 
{
    public boolean isGood(int[] nums) 
    {
        int maximumElement = 0;

        for(int number : nums)
        {
            maximumElement = Math.max(
                maximumElement,
                number
            );
        }

        if(nums.length != maximumElement + 1)
        {
            return false;
        }

        int[] frequency = new int[maximumElement + 1];

        for(int number : nums)
        {
            if(number > maximumElement)
            {
                return false;
            }

            frequency[number]++;
        }

        for(int number = 1; 
            number < maximumElement; 
            number++)
        {
            if(frequency[number] != 1)
            {
                return false;
            }
        }

        return frequency[maximumElement] == 2;
    }
}
