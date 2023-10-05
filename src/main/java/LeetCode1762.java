import java.util.ArrayList;
import java.util.List;

public class LeetCode1762 {
    public int[] findBuildings(int[] heights) {
        if (heights == null || heights.length < 1) {
            return new int[0];
        }

        List<Integer> seaViewPositions = new ArrayList<>();
        seaViewPositions.add(heights.length - 1);

        int maxToTheRight = heights[heights.length -1];
        for (int index = heights.length - 2; index >= 0; index--) {
            if (heights[index] > maxToTheRight) {
                seaViewPositions.add(index);
            }

            maxToTheRight = Math.max(maxToTheRight, heights[index]);
        }

        int[] ascSeaViewPos = new int[seaViewPositions.size()];
        for (int index = 0; index < ascSeaViewPos.length; index++) {
            ascSeaViewPos[index] = seaViewPositions.get(ascSeaViewPos.length - 1 - index);
        }

        return ascSeaViewPos;
    }
}
