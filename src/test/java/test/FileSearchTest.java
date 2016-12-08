package test;

import java.io.File;
import java.util.Date;

public class FileSearchTest {

	public static void main(String[] args) {
		
		String dir = "D:/02.ProjectPath/rls/.metadata/.plugins/org.eclipse.wst.server.core/tmp3/wtpwebapps/shop_spd_ticket-shop/WEB-INF/classes/com/ebuy/shop/web/logic";
//		outFileName(dir);
		outNewestFileName(dir);
	}
	
	public static void outFileName(String dir) {
		File[] files = new File(dir).listFiles();
		for(File file : files) {
			if(file.isFile()) {
				System.out.println(file.getName());
			}
			if(file.isDirectory()) {
				System.out.println(file.getPath());
				outFileName(file.getPath());
			}
		}
	}
	
	public static void outNewestFileName(String dir) {
		File[] files = new File(dir).listFiles();
		for(File file : files) {
			if(file.isFile()) {
				if(file.getName().indexOf(".class") > 0) {
					System.out.println(file.getName() + "," + System.currentTimeMillis() + "," + file.lastModified());
					System.out.println((System.currentTimeMillis() - file.lastModified())/1000);
					if(System.currentTimeMillis() - file.lastModified() <= 30) {
						System.out.println(file.getName() + "===================================================");
					}
				}
			}
			if(file.isDirectory()) {
				outNewestFileName(file.getPath());
			}
		}
	}
}
