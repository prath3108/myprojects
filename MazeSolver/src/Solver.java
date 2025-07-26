// import java.awt.*;
// import java.util.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Collections;


public class Solver {
    private int[][] maze;
    private int rows, cols;
    private List<Point> visitedOrder = new ArrayList<>();

    public Solver(int[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
    }

    public List<Point> getVisitedOrder() {
        return visitedOrder;
    }

    public List<Point> bfs(Point start, Point end) {
        boolean[][] visited = new boolean[rows][cols];
        Map<Point, Point> parent = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);
        visited[start.x][start.y] = true;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            visitedOrder.add(current);

            if (current.equals(end)) {
                return reconstructPath(parent, start, end);
            }

            for (int[] dir : directions) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols &&
                        !visited[nx][ny] && maze[nx][ny] == 0) {
                    Point next = new Point(nx, ny);
                    queue.add(next);
                    visited[nx][ny] = true;
                    parent.put(next, current);
                }
            }
        }
        return Collections.emptyList(); // No path found
    }

    private List<Point> reconstructPath(Map<Point, Point> parent, Point start, Point end) {
        List<Point> path = new ArrayList<>();
        Point current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = parent.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
