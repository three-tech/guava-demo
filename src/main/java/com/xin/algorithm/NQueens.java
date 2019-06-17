package com.xin.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author three
 * @since 2019/6/13 16:29
 * <p>
 *
 * </p>
 */
public class NQueens {
	// n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
	// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
	// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

	//	### 问题分析
//
// 约束条件为每个棋子所在的行、列、对角线都不能有另一个棋子。
//
// 使用一维数组表示一种解法，下标（index）表示行，值（value）表示该行的Q（皇后）在哪一列。
// 每行只存储一个元素，然后递归到下一行，这样就不用判断行了，只需要判断列和对角线。
//
// ###

	public static void main(String[] args) {
		solveNQueens(8);
	}

	static List<List<String>> solveNQueens(int n) {
		// 下标代表行，值代表列。如result[0] = 3 表示第1行的Q在第3列
		int[]              result     = new int[n];
		List<List<String>> resultList = new LinkedList<>();
		dfs(resultList, result, 0, n);
		return resultList;
	}

	static void dfs(List<List<String>> resultList, int[] result, int row, int n) {
		// 递归终止条件
		if (row == n) {
			List<String> list = new LinkedList<>();
			for (int x = 0; x < n; ++x) {
				StringBuilder sb = new StringBuilder();
				for (int y = 0; y < n; ++y)
					sb.append(result[x] == y ? "Q" : ".");
				list.add(sb.toString());
			}
			resultList.add(list);
			printResult(list);
			return;
		}
		for (int column = 0; column < n; ++column) {
			boolean isValid = true;
			result[row] = column;
			/*
			 * 逐行往下考察每一行。同列，result[i] == column
			 * 同对角线，row - i == Math.abs(result[i] - column)
			 */
			for (int i = row - 1; i >= 0; --i) {
				if (result[i] == column || row - i == Math.abs(result[i] - column)) {
					isValid = false;
					break;
				}
			}
			if (isValid) dfs(resultList, result, row + 1, n);
		}
	}

	private static void printResult(List<String> result) {
		result.forEach(System.out::println);
		System.out.println("========================");
	}
}
