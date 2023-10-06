import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode2101 {
    public int maximumDetonation(int[][] bombs) {
        if (bombs == null) {
            return 0;
        }

        List<List<Integer>> detonatedBombsList = new ArrayList<>();
        for (int index = 0; index < bombs.length; index++) {
            List<Integer> directlyDetonatedBombs = this.getDirectlyDetonatedBombs(bombs, index);
            detonatedBombsList.add(directlyDetonatedBombs);
        }

        int maximumDetonatedBombs = 1;
        for (int index = 0; index < bombs.length; index++) {
            int detonatedBombs = denoatedcountDetonatedBombs(detonatedBombsList, index);
            maximumDetonatedBombs = Math.max(maximumDetonatedBombs, detonatedBombs);
        }

        return maximumDetonatedBombs;
    }

    private List<Integer> getDirectlyDetonatedBombs(int[][] bombs, int centerIndex) {
        List<Integer> directlyDetonatedBombs = new ArrayList<>();
        for (int index = 0; index < bombs.length; index++) {
            if (index == centerIndex) {
                continue;
            }

            int bombX = bombs[index][0];
            int bombY = bombs[index][1];
            int centerX = bombs[centerIndex][0];
            int centerY = bombs[centerIndex][1];
            double centerRadius = bombs[centerIndex][2];
            if (distance(bombX, bombY, centerX, centerY) <= centerRadius) {
                directlyDetonatedBombs.add(index);
            }
        }

        return directlyDetonatedBombs;
    }

    private int denoatedcountDetonatedBombs(List<List<Integer>> detonatedBombsList, int detonateBombIndex) {
        boolean[] isDetonated = new boolean[detonatedBombsList.size()];
        Queue<Integer> queue = new LinkedList<>();

        isDetonated[detonateBombIndex] = true;
        queue.add(detonateBombIndex);

        int detonateCount = 0;
        while (!queue.isEmpty()) {
            int index = queue.poll();
            detonateCount++;

            for (int directlyDetonatedBomb : detonatedBombsList.get(index)) {
                if (!isDetonated[directlyDetonatedBomb]) {
                    isDetonated[directlyDetonatedBomb] = true;
                    queue.add(directlyDetonatedBomb);
                }
            }
        }

        return detonateCount;
    }

    private double distance(int x1, int y1, int x2, int y2) {
        double xDiff = x1 - x2;
        double yDiff = y1 - y2;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
