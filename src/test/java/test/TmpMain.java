package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TmpMain {

	public static void main(String[] args) {

		int[] a = {9, 8, 2, 5, 1, 11, 3};
		
		int min = 0;
		
		for(int j = 0; j < a.length; j ++) {
			if(a[min] > a[j]) min = j;
		}
		
		System.out.println(a[min]);
		
//		for(int i = 0; i < a.length; i ++) {
//			min = i;
//			for(int j = i; j < a.length; j ++) {
//				if(a[min] > a[j]) min = j;
//			}
//		}
	}
}
