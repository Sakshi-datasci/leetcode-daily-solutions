class Solution {
    public int[] separateDigits(int[] nums) {
        
        ArrayList<Integer> result = new ArrayList<>();

        for (int number : nums) {

            String value = String.valueOf(number);

            for (int index = 0; index < value.length(); index++) {

                result.add(value.charAt(index) - '0');
            }
        }

        int[] answer = new int[result.size()];

        for (int index = 0; index < result.size(); index++) {

            answer[index] = result.get(index);
        }

        return answer;
    }
}
