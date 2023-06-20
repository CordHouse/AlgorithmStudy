import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static Node node = new Node('A', null, null);
    private static int n;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            addNode(node, line[0], line[2], line[4]);
        }
        // 전위
        preorder(node);
        sb.append("\n");
        // 중위
        inorder(node);
        sb.append("\n");
        // 후위
        postorder(node);
        System.out.println(sb.toString());
    }

    // 전위
    public static void preorder(Node node) {
        if(node == null) return;
        sb.append(node.root);
        preorder(node.left);
        preorder(node.right);
    }

    // 중위
    public static void inorder(Node node) {
        if(node == null) return;
        inorder(node.left);
        sb.append(node.root);
        inorder(node.right);
    }

    // 후위
    public static void postorder(Node node) {
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.root);
    }

    public static void addNode(Node node, char root, char left, char right) {
        if(node.root == root) {
            node.left = left != '.' ? new Node(left, null, null) : null;
            node.right = right != '.' ? new Node(right, null, null) : null;
        } else {
            if(node.left != null) {
                addNode(node.left, root, left, right);
            }
            if(node.right != null) {
                addNode(node.right, root, left, right);
            }
        }
    }

    static class Node{
        char root;
        Node left;
        Node right;
        public Node(char root, Node left, Node right) {
            this.root = root;
            this.left = left;
            this.right = right;
        }
    }
}