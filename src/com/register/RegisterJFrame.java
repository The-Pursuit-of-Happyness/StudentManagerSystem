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
		
		//��ȡ��Ļ��С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//��ȡ��Ļ�ĸ߶ȺͿ��
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();
		
		//��ʼ�����
		imageJLabel = new JLabel(imagefile.registerbackground);
		accountJLabel = new JLabel("�˺�:");
		passwordJLabel = new JLabel("����:");
		accountJTextField = new JTextField("2013083222");
		passwordJPasswordField = new JPasswordField("2013083222");
		okJButton = new JButton("ȷ��");
		cancleJButton = new JButton("ȡ��");
		
		//���������С��λ��
		accountJLabel.setBounds(screenWidth/18,screenHeight/26,screenWidth/15,screenHeight/45);
		accountJTextField.setBounds(screenWidth/11,screenHeight/30,screenWidth/9,screenHeight/30);		
		passwordJLabel.setBounds(screenWidth/18,screenHeight/11,screenWidth/15,screenHeight/45);
		passwordJPasswordField.setBounds(screenWidth/11,screenHeight/12,screenWidth/9,screenHeight/30);
		okJButton.setBounds(screenWidth/10,screenHeight/7,screenWidth/16,screenHeight/25);
		cancleJButton.setBounds(screenWidth/6,screenHeight/7,screenWidth/16,screenHeight/25);
		imageJLabel.setBounds(0,0,screenWidth/4,screenHeight/4);
		
		//������
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
		this.setTitle("��½����");
		this.setSize(this.screenWidth/4,this.screenHeight/4);
		this.setLocationRelativeTo(null);
		this.setResizable(false);//������ı䴰���С
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
