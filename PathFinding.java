import java.util.*;

public class PathFinding {

    public static class Result {
        int sum;
        List<int[]> path;

        public Result(int sum, List<int[]> path) {
            this.sum = sum;
            this.path = path;
        }
    }

    public static Result findShortestPath(int[][] grid, int rows, int cols) {
        int[][] dp = new int[rows][cols];
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        List<int[]>[][] paths = new ArrayList[rows][cols];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                paths[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < rows; i++) {
            dp[i][0] = grid[i][0];
            paths[i][0].add(new int[]{i, 0});
        }

        for (int j = 1; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                for (int[] dir : directions) {
                    int prevRow = i + dir[0];
                    int prevCol = j + dir[1];

                    if (prevRow >= 0 && prevRow < rows && prevCol >= 0 && prevCol < cols) {
                        if (dp[prevRow][prevCol] != Integer.MAX_VALUE) {
                            int newSum = dp[prevRow][prevCol] + grid[i][j];
                            if (newSum < dp[i][j]) {
                                dp[i][j] = newSum;
                                paths[i][j] = new ArrayList<>(paths[prevRow][prevCol]);
                                paths[i][j].add(new int[]{i, j});
                            }
                        }
                    }
                }
            }
        }

        int minSum = Integer.MAX_VALUE;
        List<int[]> minPath = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            if (dp[i][cols - 1] < minSum) {
                minSum = dp[i][cols - 1];
                minPath = paths[i][cols - 1];
            }
        }

        return new Result(minSum, minPath);
    }
}
