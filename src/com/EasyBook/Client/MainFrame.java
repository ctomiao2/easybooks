package com.EasyBook.Client;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// UI related
public class MainFrame extends JFrame  {
	
	private com.EasyBook.Client.PanelBase content_panel;

	public MainFrame() {
		Global.g_frame = this;
		setTitle("EasyBook");
		setSize(1280, 720);
		setResizable(true);
	    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo (null);// 居中
		this.content_panel = new LoginPanel();
		setVisible (true);
	}

	// login success/fail
	public void on_login(boolean succ) {
		if (!succ) {
			JOptionPane.showMessageDialog (null, "user name or password not correct!");
		} else {
			this.content_panel.destroy();
			this.update(getGraphics());
			this.content_panel = new HomePanel();
			setVisible(true);
		}
	}

	public void on_register_fail() {
		JOptionPane.showMessageDialog (null, "user has been registered!");
	}

}
