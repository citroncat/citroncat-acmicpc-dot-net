package com.citroncat.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DragonCurve15685 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] pattern = new int[4][1024];
        boolean[][] map = new boolean[101][101];

        pattern[0][0] = 0;
        pattern[1][0] = 1;
        pattern[2][0] = 2;
        pattern[3][0] = 3;

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 10; j++) {
                int start = (int) Math.pow(2, j - 1);
                int end = (int) Math.pow(2, j);
                for (int k = start, l = 1; k < end; k++, l += 2) {
                    pattern[i][k] = pattern[i][k - l] + 1 == 4 ? 0 : pattern[i][k - l] + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            int d = Integer.parseInt(stringTokenizer.nextToken());
            int g = Integer.parseInt(stringTokenizer.nextToken());
            int end = (int) Math.pow(2, g);

            int currentX = x;
            int currentY = y;
            map[currentY][currentX] = true;

            for (int j = 0; j < end; j++) {
                if (pattern[d][j] == 0) {
                    currentX += 1;
                } else if (pattern[d][j] == 1) {
                    currentY -= 1;
                } else if (pattern[d][j] == 2) {
                    currentX -= 1;
                } else {
                    currentY += 1;
                }
                map[currentY][currentX] = true;
            }
        }

        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
