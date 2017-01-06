package com.paletter.client.hotdeploy.window;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;


public class ShowWindow {
	
	public static void main(String[] args) {
		
		try {
			
			HotDeployClientWindow window = new HotDeployClientWindow();
			
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
