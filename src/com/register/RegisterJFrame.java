package com.register;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.image.ImageFile;
import com.mouse.MouseAction;
import com.sql.SQL;

public class RegisterJFrame extends JFrame {
	private ImageFile imagefile;
	private int screenHeight;
	private int screenWidth;
	private JLabel imageJLabel;
	private JLabel accountJLabel;
	private JLabel passwordJLabel;
	private JTextField accountJTextField;
	private JPasswordField passwordJPasswordField;
	private JButton okJButton;
	private JButton cancleJButton;
	private SQL sql;
	
	public RegisterJFrame(SQL sql){
		this.sql =sql;
		imagefile = new ImageFile();
		
		//获取屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//获取屏幕的高度和宽度
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();
		
		//初始化组件
		imageJLabel = new JLabel(imagefile.registerbackground);
		accountJLabel = new JLabel("账号:");
		passwordJLabel = new JLabel("密码:");
		accountJTextField = new JTextField("2013083222");
		passwordJPasswordField = new JPasswordField("2013083222");
		okJButton = new JButton("确定");
		cancleJButton = new JButton("取消");
		
		//设置组件大小和位置
		accountJLabel.setBounds(screenWidth/18,screenHeight/26,screenWidth/15,screenHeight/45);
		accountJTextField.setBounds(screenWidth/11,screenHeight/30,screenWidth/9,screenHeight/30);		
		passwordJLabel.setBounds(screenWidth/18,screenHeight/11,screenWidth/15,screenHeight/45);
		passwordJPasswordField.setBounds(screenWidth/11,screenHeight/12,screenWidth/9,screenHeight/30);
		okJButton.setBounds(screenWidth/10,screenHeight/7,screenWidth/16,screenHeight/25);
		cancleJButton.setBounds(screenWidth/6,screenHeight/7,screenWidth/16,screenHeight/25);
		imageJLabel.setBounds(0,0,screenWidth/4,screenHeight/4);
		
		//添加组件
		this.add(accountJLabel);
		this.add(accountJTextField);
		this.add(passwordJLabel);
		this.add(passwordJPasswordField);
		okJButton.addMouseListener(new MouseAction(this,sql));
		this.add(okJButton);
		cancleJButton.addMouseListener(new MouseAction(this,sql));
		this.add(cancleJButton);
		this.add(imageJLabel);
		
		this.setLayout(null);
		this.setIconImage(imagefile.icon);
		this.setTitle("登陆窗口");
		this.setSize(this.screenWidth/4,this.screenHeight/4);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//不允许改变窗体大小
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTextField getAccountJTextField() {
		return accountJTextField;
	}

	public void setAccountJTextField(JTextField accountJTextField) {
		this.accountJTextField = accountJTextField;
	}

	public JPasswordField getPasswordJPasswordField() {
		return passwordJPasswordField;
	}

	public void setPasswordJPasswordField(JPasswordField passwordJPasswordField) {
		this.passwordJPasswordField = passwordJPasswordField;
	}

	public JButton getOkJButton() {
		return okJButton;
	}

	public JButton getCancleJButton() {
		return cancleJButton;
	}
}
