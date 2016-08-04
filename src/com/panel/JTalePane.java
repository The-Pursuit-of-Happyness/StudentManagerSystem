package com.panel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sql.SQL;

public class JTalePane extends JTabbedPane implements Runnable {
	private SQL sql;
	public JTalePane(SQL sql){
		this.sql = sql;
		this.setTabPlacement(JTabbedPane.TOP);
		this.setBackground(Color.blue);	
		init();
		
		new Thread(this).start();
	}	
	public void init(){
		//向面板组中添加面板
		this.addTab(null,new ImageIcon("src\\com\\image\\addJPanel.png"),new AddJPanel(sql),"添加菜单");
		this.addTab(null,new ImageIcon("src\\com\\image\\deleteJPanel.png"),new DeleteJPanel(sql),"删除菜单");
		this.addTab(null,new ImageIcon("src\\com\\image\\searchJPanel.png"),new SearchJPanel(sql),"查询菜单");
		this.addTab(null,new ImageIcon("src\\com\\image\\ModificationJPanel.png"),new ModificationJPanel(sql),"修改菜单");
		this.addTab(null,new ImageIcon("src\\com\\image\\countJPanel.png"),new CountJPanel(sql),"统计菜单");
		this.addTab(null,new ImageIcon("src\\com\\image\\helpJPanel.png"),new HelpJPanel(),"帮助菜单");
	}
	
	//线程刷新
	public void run() {
		while(true){
			try {
				Thread.sleep(50);
				this.repaint();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
}
