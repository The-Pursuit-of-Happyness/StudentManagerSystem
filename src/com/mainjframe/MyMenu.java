package com.mainjframe;

import java.awt.Component;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.panel.JTalePane;

public class MyMenu extends JMenuBar {
	
	private JMenu help;
	private JMenuItem exit;
	private JMenuItem information;
	
	private JMenu manage;
	private JMenu menuMenu;
	private JMenuItem addMenu;
	private JMenuItem deleteMenu;
	private JMenuItem searchMenu;
	private JTalePane jtablepane;
	
	public MyMenu(final JTalePane jtablepane){
		this.jtablepane  = jtablepane;		
		help = new JMenu("����");
		information = new JMenuItem("��������Ϣ");
		information.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				if(a.getSource() == help){
					jtablepane.setSelectedIndex(3);
				}
			}			
		});
		exit = new JMenuItem("�˳�");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				if(a.getSource() == exit){
					System.exit(0);
				}
			}			
		});
		
		menuMenu= new JMenu("�˵�");
		addMenu = new JMenuItem("���");		
		deleteMenu = new JMenuItem("ɾ��");
		searchMenu = new JMenuItem("����");
		
		help.add(information);
		help.insertSeparator(1);//��ӷָ���
		help.add(exit);
		
		menuMenu.add(addMenu);
		menuMenu.insertSeparator(2);//��ӷָ���
		menuMenu.add(deleteMenu);
		menuMenu.insertSeparator(3);//��ӷָ���
		menuMenu.add(searchMenu);
		
		this.add(menuMenu);
		this.add(help);
	}
}
