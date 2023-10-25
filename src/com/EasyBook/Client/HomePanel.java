package com.EasyBook.Client;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends PanelBase {

	JPanel panel_topbar;
	
	public HomePanel() {
		JFrame main_frame = Global.g_frame;
		main_frame.setLayout (new FlowLayout ());
		panel_topbar = new JPanel(new FlowLayout ());
		JLabel label_username = new JLabel();
		label_username.setText ("welcome: " + Global.g_user.getUserName());
		panel_topbar.add(label_username);
		main_frame.add(panel_topbar);
	}

	@Override
	public void destroy() {
		JFrame main_frame = Global.g_frame;
		main_frame.remove(panel_topbar);
	}
}
