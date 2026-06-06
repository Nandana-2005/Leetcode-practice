import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

public class Main {

    static Node buildTree(int[] arr, int i) {
        if (i >= arr.length)
            return null;

        Node root = new Node(arr[i]);

        root.left = buildTree(arr, 2 * i + 1);
        root.right = buildTree(arr, 2 * i + 2);

        return root;
    }

    static void inorder(Node root, List<Integer> list) {
        if (root == null)
            return;

        inorder(root.left, list);
        list.add(root.data);
        inorder(root.right, list);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        Node root = buildTree(arr, 0);

        List<Integer> inorderList = new ArrayList<>();
        inorder(root, inorderList);

        Map<Integer, Integer> pos = new HashMap<>();

        for (int i = 0; i < inorderList.size(); i++) {
            pos.put(inorderList.get(i), i);
        }

        for (int x : arr) {

            int idx = pos.get(x);

            int pred = (idx == 0) ? 0 : inorderList.get(idx - 1);
            int succ = (idx == inorderList.size() - 1)
                    ? 0
                    : inorderList.get(idx + 1);

            System.out.println(x + " : " + pred + "," + succ);
        }

        sc.close();
    }
}
