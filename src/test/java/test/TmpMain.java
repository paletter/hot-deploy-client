package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TmpMain {

	public static void main(String[] args) {

		List<String> l = new ArrayList<String>();
		
		Collections.sort(l);
		
		for(String s : l) {
			System.out.println(s);
		}
	}
}
