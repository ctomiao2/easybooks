package com.EasyBook.Client;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginListener implements ActionListener {

	public JTextField tf_username;
	public JTextField tf_password;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand ();
		String username = tf_username.getText ();
		String password = tf_password.getText ();
		System.out.println ("username: " + username + " password: " + password);
		if(username.equals ("") || password.equals ("")){
			JOptionPane.showMessageDialog (null, "user name and password can not be empty");
			return;
		}
		if(ac.equals ("login")){
			System.out.println ("click login");
			Global.g_user.login(username, password);
		} else if(ac.equals ("sign up")){
			System.out.println ("click signup");
			Global.g_user.sign_up(username, password);
		}
	}
}

public class LoginPanel extends PanelBase {
	JPanel panel_username;
	JPanel panel_password;
	JPanel panel_btn;

	public LoginPanel() {
		JFrame main_frame = Global.g_frame;
		main_frame.setLayout (new FlowLayout ());
		panel_username = new JPanel(new FlowLayout ());
		JLabel label_username = new JLabel ("user name: ");
		JTextField tf_username = new JTextField (30);// 30个char length
		panel_username.add(label_username);
		panel_username.add(tf_username);
		panel_password = new JPanel(new FlowLayout ());
		panel_username.setSize(1080, 50);
		JLabel label_password = new JLabel ("password: ");
		JPasswordField tf_password = new JPasswordField (30);// 30 char length
		panel_password.add(label_password);
		panel_password.add(tf_password);
		panel_password.setSize(1080, 50);
		panel_btn = new JPanel(new FlowLayout ());
		JButton btn_login = new JButton ("login");
		JButton btn_reg = new JButton ("sign up");
		panel_btn.add(btn_login);
		panel_btn.add(btn_reg);
		// 创建监听器对象 并添加给登录注册按钮
		LoginListener loginListener = new LoginListener ();
		btn_login.addActionListener(loginListener);
		btn_reg.addActionListener (loginListener);
		loginListener.tf_username = tf_username;
		loginListener.tf_password = tf_password;

		main_frame.add (panel_username);
		main_frame.add (panel_password);
		main_frame.add (panel_btn);
	}

	@Override
	public void destroy() {
		JFrame main_frame = Global.g_frame;
		main_frame.remove(this.panel_username);
		main_frame.remove(this.panel_password);
		main_frame.remove(this.panel_btn);
	}
}
