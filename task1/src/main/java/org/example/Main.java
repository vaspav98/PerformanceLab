package org.example;

public class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] circularArr = new int[n];
        for (int i = 0; i < n; i++) {
            circularArr[i] = 1 + i;
        }

        StringBuilder sb = new StringBuilder();
        int[] interval = new int[m];
        int j = 0;
        while (interval[m - 1] != circularArr[0]) {
            for (int i = 0; i < m; i++) {
                interval[i] = circularArr[j++];
                if (j == n) {
                    j = 0;
                }
            }
            j = j > 0 ? j - 1 : n - 1;
            sb.append(interval[0]);
        }

        System.out.println(sb);
    }

}
