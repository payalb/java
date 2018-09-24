package com.java.demo.concurrentModification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*Prevent concurrent Modification exception*/
public class ConcurrentModificationDemo4 {

	public static void main(String[] args) {
		List<String> list= new ArrayList<>(Arrays.asList("abc","def","ghi", "khg"));
		//Convert list to array and then iterate over array
		
		/*Convert list to string array. By default converts it to Object array. 
		 * Could be a performance hit if size is too long.*/
		String[] arr= list.toArray(new String[list.size()]);
		for(String temp: arr) {
			if(temp.contains("a")) {
				list.remove(temp);
				list.add(0, "new"+ temp);
			}
		}
		System.out.println(list);
	}

}
