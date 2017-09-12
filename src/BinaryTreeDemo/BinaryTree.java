package BinaryTreeDemo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class TreeNode
{
    public enum  Tags {left, right};
    public int key = 0;
    public String data = null;
    public boolean isVisited = false;
    public TreeNode leftChild = null;
    public TreeNode rightChild = null;
    public Tags tag;
    //无惨构造器
    public TreeNode() {}

    public TreeNode(int key, String data)
    {
        this.key = key;
        this.data = data;
    }

    public void displayTreeNode()
    {
        System.out.println("key="+ key + ",data=" + data + ",leftChild=" + leftChild + ",rightChild=" + rightChild);
    }
}

public class BinaryTree
{
    private TreeNode root = null;

    public BinaryTree ()
    {
        root = new TreeNode(1, "rootNode(A)");
    }

    // create binary tree
    public void createBinaryTree()
    {
        TreeNode newNodeB = new TreeNode(2, "B");
        TreeNode newNodeC = new TreeNode(3, "C");
        TreeNode newNodeD = new TreeNode(4, "D");
        TreeNode newNodeE = new TreeNode(5, "E");
        TreeNode newNodeF = new TreeNode(6, "F");
        TreeNode newNodeG = new TreeNode(7, "G");
//        root.leftChild = newNodeB;
        root.rightChild = newNodeC;
//        newNodeB.leftChild = newNodeD;
//        newNodeB.rightChild = newNodeE;
        newNodeC.leftChild = newNodeF;
        newNodeC.rightChild = newNodeG;
    }

    // 求二叉树高度
    public int getHeight()
    {
        return getHeight(root);
    }
    // 求节点的高度
    public int getHeight(TreeNode node)
    {
        if (node == null)
            return 0;
        int leftHeight = getHeight(node.leftChild);
        int rightHeight = getHeight(node.rightChild);
        // return leftHeight > rightHeight ? (leftHeight + 1) : (rightHeight + 1);
        //改为更简洁的代码
        return Math.max(leftHeight, rightHeight) + 1;
    }

    //求二叉树最小深度
    public int getMinDepth(TreeNode node)
    {
        if (node == null)
            return 0;
        else
        {
            int left = getMinDepth(node.leftChild);
            int right = getMinDepth(node.rightChild);
            return Math.min(left, right) + 1;
        }
    }

    //二叉树中叶子节点个数
    public int numsOfNoChildNode(TreeNode node)
    {
        if (node == null)
            return 0;
        if (node.leftChild == null && node.rightChild == null)
            return 1;
        return numsOfNoChildNode(node.leftChild) + numsOfNoChildNode(node.rightChild);
    }

    //二叉树中第k层节点的个数
    public int numsOfkLevelTreeNode(TreeNode node, int k)
    {
        if (k < 1)
        {
            System.out.println("k值不合法！");
            return -1;
        }
        if (node == null)
            return 0;
        if (k == 1)
            return 1;
        int numsLeft = numsOfkLevelTreeNode(node.leftChild, k-1);
        int numsRight = numsOfkLevelTreeNode(node.rightChild, k-1);
        return numsLeft + numsRight;
    }

    // 求二叉树规模
    public int getSize()
    {
        return getSize(root);
    }

    public int getSize(TreeNode node)
    {
        if (node == null)
            return 0;
        int leftSize = getSize(node.leftChild);
        int rightSize = getSize(node.rightChild);
        return leftSize + rightSize + 1;
    }

    //二叉树遍历

    //先序遍历
    public void beforeOrder(TreeNode node)
    {
        if (node == null)
            return;
        System.out.println(node.data);
        beforeOrder(node.leftChild);
        beforeOrder(node.rightChild);
    }

    // 中序遍历
    public void midOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        midOrder(node.leftChild);
        System.out.print(node.data);
        midOrder(node.rightChild);
    }

    // 后序遍历
    public void afterOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        afterOrder(node.leftChild);
        afterOrder(node.rightChild);
        System.out.print(node.data);
    }
    // 后序遍历的非递归实现，需要借助栈stack
    // 后序在栈里保存着其祖先路径
    public void afterOrder()
    {
        Stack<TreeNode> stack = new Stack();
        if (root == null)
            return;
        TreeNode pointer = root;
        TreeNode element;
        while (true)
        {
            // 沿左路分支一直下降（进入左子树）
            while (pointer != null)
            {
                pointer.tag = TreeNode.Tags.left;
                stack.push(pointer);
                pointer = pointer.leftChild;
            }
            // 弹出栈顶元素
             element = stack.pop();
            // 如果从右子树返回
            while (element.tag == TreeNode.Tags.right)
            {
                System.out.println(element.data);
                if (stack.isEmpty())
                    return;
                else
                {
                    // 如果栈非空，继续弹栈，直至栈为空或者出栈元素
                    // 的标签是left为止。
                    element = stack.pop();
                }
            }
            // 如果从左子树返回
            element.tag = TreeNode.Tags.right;
            stack.push(element);
            // 转向右子树
            pointer = element.rightChild;
        }
    }

    // 6.判断二叉树是否是平衡二叉树
    public boolean isAVL(TreeNode node)
    {
        return maxDepth2(node) != -1;
    }
    int maxDepth2(TreeNode node)
    {
        if (node == null) // 如果是空树，返回 0 ，结果为true
            return 0;
        // 左子树高度
        int left = maxDepth2(node.leftChild);
        // 右子树高度
        int right = maxDepth2(node.rightChild);
        // 左或右子树高度为 -1，或左右子树高度相差大于1
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }

    // 7.判断二叉树是否是完全二叉树
    boolean isCompleteTreeNode(TreeNode node)
    {
        // 根节点为空，则不是完全二叉树
        if (node == null)
            return false;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        // 队列中加入根节点
        queue.add(node);
        boolean result = true; // 返回结果默认设置为true
        boolean hasNoChild = false; // 没有孩子节点（当一个节点只有左孩子没有右孩子时，后续节点都不能有孩子，如果有则不是完全二叉树）
        while (!queue.isEmpty())
        {
            TreeNode current = queue.remove();
            if (hasNoChild) //如果不能有孩子节点（遇到上面那种情况下后续节点是不能有孩子节点，否则不是完全二叉树）
            {
                if (current.leftChild != null || current.rightChild != null)
                {
                    result = false;
                    break;
                }
            }
            else    // 如果还可以有孩子节点（没有出现上面那种情况）
            {
                // 左右孩子都不为空则全部进入队列
                if (current.leftChild != null && current.rightChild != null)
                {
                    queue.add(current.leftChild);
                    queue.add(current.rightChild);
                }
                // 左孩子不为空，右孩子为空，则左孩子入队，并且后续节点都不能有孩子节点
                else if (current.leftChild != null && current.rightChild == null)
                {
                    queue.add(current.leftChild);
                    hasNoChild = true;
                }
                // 左孩子为空，右孩子不为空， 则不是完全二叉树，跳出循环退出
                else if (current.leftChild == null && current.rightChild != null)
                {
                    result = false;
                    break;
                }
                // 左右孩子都为空，说明后续节点都不能有孩子。
                else
                    hasNoChild = true;
            }
        }
        return result;
    }

    public TreeNode getRoot()
    {
        return root;
    }

    public static void main(String[] args)
    {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createBinaryTree();
        // System.out.println("height = " + binaryTree.getHeight());
        // System.out.println("size = " + binaryTree.getSize());
        // binaryTree.beforeOrder(binaryTree.getRoot());
        // binaryTree.midOrder(binaryTree.getRoot());
        // binaryTree.afterOrder(binaryTree.getRoot());
        //System.out.println(binaryTree.getMinDepth(binaryTree.getRoot()));
        System.out.println(binaryTree.numsOfkLevelTreeNode(binaryTree.getRoot(), 2));
        binaryTree.afterOrder();
        boolean bool = binaryTree.isAVL(binaryTree.getRoot());
        System.out.println(bool);
        boolean bool_comp = binaryTree.isCompleteTreeNode(binaryTree.getRoot());
        System.out.println(bool_comp);
    }
}
