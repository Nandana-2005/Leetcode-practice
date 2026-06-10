import java.util.*;

public class Main {

    static List<List<Integer>> ans = new ArrayList<>();

    static void backtrack(int[] nums, List<Integer> curr, boolean[] used) {

        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        //used array keeps track of elements added to print
        for (int i = 0; i < nums.length; i++) {

            if (used[i]) {
                continue;
            }

            used[i] = true;
            curr.add(nums[i]);

            backtrack(nums, curr, used);

            curr.remove(curr.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();

        while (sc.hasNextInt()) {
            list.add(sc.nextInt());
        }

        int[] nums = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        boolean[] used = new boolean[nums.length];

        backtrack(nums, new ArrayList<>(), used);

        for (List<Integer> perm : ans) {
            System.out.println(perm);
        }
    }
}
