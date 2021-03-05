package com.citroncat.problems;

import java.io.*;
import java.util.*;

public class Cheating1014 {
    static int[][] dp;
    static boolean[][] obstacles;
    static int horizontal, vertical;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer;

        int total = Integer.parseInt(bufferedReader.readLine());
        for (int t = 0; t < total; ++t) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            horizontal = Integer.parseInt(stringTokenizer.nextToken());
            vertical = Integer.parseInt(stringTokenizer.nextToken());
            dp = new int[1 << horizontal][vertical];
            for(int i = 0 ; i < (1 << horizontal) ; ++i){
                Arrays.fill(dp[i], -1);
            }
            obstacles = new boolean[horizontal][vertical];
            for (int h = 0; h < horizontal; ++h) {
                String input = bufferedReader.readLine();
                for (int v = 0; v < vertical; ++v) {
                    if (input.charAt(v) == 'x') {
                        obstacles[h][v] = true;
                    }
                }
            }
            bufferedWriter.write(getDP(0, 0) + "\n");
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    static int getDP(int h, int v) {
        if (v == vertical) {
            return 0;
        }
        if (dp[h][v] != -1) {
            return dp[h][v];
        }
        int lastHorizontal = h;
        for (int i = 0; i < horizontal; ++i) {
            if ((h & (1 << i)) > 0) {
                lastHorizontal |= (1 << (i + 1));
                lastHorizontal |= (1 << (i - 1));
            }
        }
        int result = getDP(0, v + 1);
        for (int i = 1; i < (1 << horizontal); ++i) {
            if ((i & lastHorizontal) > 0) {
                continue;
            }
            int count = 0;
            boolean isAvailable = true;
            for (int j = 0; j < horizontal && isAvailable; ++j) {
                if ((i & (1 << j)) > 0) {
                    count++;
                    if (obstacles[j][v]) {
                        isAvailable = false;
                    }
                }
            }
            if(!isAvailable) {
                continue;
            }
            result = Math.max(result, getDP(i, v + 1) + count);
        }
        return dp[h][v] = result;
    }
}