import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

public class LeetCode2458 {
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

    private static final class NodeMetadata {
        int heightAtRoot;
        int maxHeightOfSubtree;
        int heightWithoutSubtree;

        NodeMetadata() {
        }
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        if (root == null) {
            return null;
        }

        Map<Integer, NodeMetadata> metadataMap = new HashMap<>();
        this.fillInHeights(metadataMap, root, 0);
        this.fillInHeightWithoutSubtree(metadataMap, root, 0);
        // height(root) = max(height(root.left), height(root.right)) + 1
        int[] answer = new int[queries.length];
        for (int index = 0; index < queries.length; index++) {
            answer[index] = metadataMap.get(queries[index]).heightWithoutSubtree;
        }

        return answer;
    }

    private int fillInHeights(
            Map<Integer, NodeMetadata> metadataMap,
            TreeNode root,
            int height
    ) {
        if (root == null) {
            return height - 1;
        }

        NodeMetadata metadata = new NodeMetadata();
        metadata.heightAtRoot = height;
        int leftMaxHeight = fillInHeights(metadataMap, root.left, height + 1);
        int rightMaxHeight = fillInHeights(metadataMap, root.right, height + 1);
        metadata.maxHeightOfSubtree = Math.max(leftMaxHeight, rightMaxHeight);
        metadataMap.put(root.val, metadata);
        return metadata.maxHeightOfSubtree;
    }

    private void fillInHeightWithoutSubtree(
            Map<Integer, NodeMetadata> metadataMap,
            TreeNode root,
            int heightWithoutSubtree
    ) {
        if (root == null) {
            return;
        }

        NodeMetadata metadata = metadataMap.get(root.val);
        metadata.heightWithoutSubtree = heightWithoutSubtree;
        metadataMap.put(root.val, metadata);

        int heightWithoutLeftChild = Math.max(heightWithoutSubtree, metadata.heightAtRoot);
        if (root.right != null) {
            heightWithoutLeftChild = Math.max(heightWithoutLeftChild, metadataMap.get(root.right.val).maxHeightOfSubtree);
        }

        fillInHeightWithoutSubtree(metadataMap, root.left, heightWithoutLeftChild);

        int heightWithoutRightChild = Math.max(heightWithoutSubtree, metadata.heightAtRoot);
        if (root.left != null) {
            heightWithoutRightChild = Math.max(heightWithoutRightChild, metadataMap.get(root.left.val).maxHeightOfSubtree);
        }

        fillInHeightWithoutSubtree(metadataMap, root.right, heightWithoutRightChild);
    }
}
