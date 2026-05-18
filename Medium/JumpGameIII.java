class Solution {

    public boolean dfs(int[] arr, int index, boolean[] visited) {

        // Out of bounds
        if(index < 0 || index >= arr.length)
            return false;

        // Already visited
        if(visited[index])
            return false;

        // Found 0
        if(arr[index] == 0)
            return true;

        visited[index] = true;

        return dfs(arr, index + arr[index], visited) ||
               dfs(arr, index - arr[index], visited);
    }

    public boolean canReach(int[] arr, int start) {

        boolean[] visited = new boolean[arr.length];

        return dfs(arr, start, visited);
    }
}
