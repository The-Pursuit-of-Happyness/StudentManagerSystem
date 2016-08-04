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
		help = new JMenu("帮助");
		information = new JMenuItem("开发者信息");
		information.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				if(a.getSource() == help){
					jtablepane.setSelectedIndex(3);
				}
			}			
		});
		exit = new JMenuItem("退出");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				if(a.getSource() == exit){
					System.exit(0);
				}
			}			
		});
		
		menuMenu= new JMenu("菜单");
		addMenu = new JMenuItem("添加");		
		deleteMenu = new JMenuItem("删除");
		searchMenu = new JMenuItem("查找");
		
		help.add(information);
		help.insertSeparator(1);//添加分割线
		help.add(exit);
		
		menuMenu.add(addMenu);
		menuMenu.insertSeparator(2);//添加分割线
		menuMenu.add(deleteMenu);
		menuMenu.insertSeparator(3);//添加分割线
		menuMenu.add(searchMenu);
		
		this.add(menuMenu);
		this.add(help);
	}
}
