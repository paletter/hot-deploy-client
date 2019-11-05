package test;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

public class ClipboardTest {
	
	public static void main(String[] args) {
		System.out.println(ClipboardTest.getClipboardText());
	}
	
	public static String getClipboardText() {
		String text = "";  
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();  
        
        Transferable clipTf = sysClip.getContents(null);  
        if (clipTf != null) {  
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {  
                try {  
                    text = (String)clipTf.getTransferData(DataFlavor.stringFlavor);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }
        
        return text;
	}
}
