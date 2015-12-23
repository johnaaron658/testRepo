package idk;

import java.util.Scanner;

public class idk {
	public static void main(String args[]){
		int[][] matrix1 = {{3,1},{2,4}};
		int[][] matrix2 = {{1,-1},{-1,1}};
		//int[][] matrix3 = {};
		
		Matrix A = new Matrix(matrix1);
		Matrix B = new Matrix(matrix2);
		
		System.out.println(A.toString()+"\n");
		System.out.println(A.transpose().toString());

		
	}
}
