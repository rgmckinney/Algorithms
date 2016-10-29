import java.util.*;

public class InOrderTraversal {
    public static LinkedList<Integer> inOrderIterative(TreeNode root) {
        TreeNode curNode = root;
        Stack<TreeNode> nodeStack = new Stack<>();
        LinkedList<Integer> traversal = new LinkedList<>();
        HashSet<TreeNode> visited = new HashSet<>();

        if (root == null) {
            return new LinkedList<>();
        }

        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            // If both sides have been traversed
            if (visited.contains(curNode)) {
                nodeStack.pop();
                if (nodeStack.isEmpty()) {
                    break;
                }
                curNode = nodeStack.peek();
            }
            // If left side is not null
            else if (curNode.left != null && !visited.contains(curNode.left)) {
                curNode = curNode.left;
                nodeStack.push(curNode);
            }
            // If traversed left side already
            else {
                // Traverse this node
                traversal.add(curNode.val);
                visited.add(curNode);

                // If right child is not null
                if (curNode.right != null) {
                    nodeStack.push(curNode.right);
                    curNode = curNode.right;
                }
                // If rightmost child
                else {
                    nodeStack.pop();
                    if (!nodeStack.isEmpty()) {
                        curNode = nodeStack.peek();
                    }
                }
            }
        }

        return traversal;
    }

    public static LinkedList<Integer> inOrderRecursive(TreeNode curNode) {
        // Return empty list if current node is null
        if (curNode == null) {
            return new LinkedList<>();
        }

        // Recrusively call left and right side
        LinkedList<Integer> leftList = inOrderRecursive(curNode.left);
        leftList.add(curNode.val);
        for (int curVal : inOrderRecursive(curNode.right)) {
            leftList.add(curVal);
        }

        return leftList;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(inOrderRecursive(root));
    }
}
