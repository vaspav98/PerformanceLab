package org.example;

public class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        StringBuilder sb = new StringBuilder();
        int[] interval = new int[m];
        int j = 1;
        while (interval[m - 1] != 1) {
            for (int i = 0; i < m; i++) {
                interval[i] = j++;
                if (j > n) {
                    j = 1;
                }
            }
            j = j > 1 ? j - 1 : n;
            sb.append(interval[0]);
        }

        System.out.println(sb);
    }

}
