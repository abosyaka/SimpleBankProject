import java.util.Arrays;

/* Please, do not rename it */
class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        int[] arr = new int[args.length - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(args[i + 1]);
        }
        // write your code here
        switch (operator) {
            case "MAX":
                System.out.println(getMax(arr));
                break;
            case "MIN":
                System.out.println(getMin(arr));
                break;
            case "SUM":
                System.out.println(getSum(arr));
                break;
            default:
                System.out.println("Wrong operator or no numbers");
                break;
        }
    }

    public static int getMax(int... nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }

    public static int getMin(int... nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }

    public static int getSum(int... nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}