import java.util.*;

public class LeetCode314 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class NodeWithLevel {
        TreeNode node;
        int level;

        NodeWithLevel(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Map<Integer, List<Integer>> columns = new HashMap<>();
        Queue<NodeWithLevel> queue = new LinkedList<>();
        queue.add(new NodeWithLevel(root, 0));
        int minLevel = Integer.MAX_VALUE;
        int maxLevel = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            NodeWithLevel curr = queue.poll();
            minLevel = Math.min(minLevel, curr.level);
            maxLevel = Math.max(maxLevel, curr.level);

            List<Integer> column = columns.getOrDefault(curr.level, new ArrayList<>());
            column.add(curr.node.val);
            columns.put(curr.level, column);

            if (curr.node.left != null) {
                queue.add(new NodeWithLevel(curr.node.left, curr.level - 1));
            }

            if (curr.node.right != null) {
                queue.add(new NodeWithLevel(curr.node.right, curr.level + 1));
            }
        }

        List<List<Integer>> results = new ArrayList<>();
        for (int level = minLevel; level <= maxLevel; level++) {
            results.add(columns.get(level));
        }

        return results;
    }
}
