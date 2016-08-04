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
		//���������������
		this.addTab(null,new ImageIcon("src\\com\\image\\addJPanel.png"),new AddJPanel(sql),"��Ӳ˵�");
		this.addTab(null,new ImageIcon("src\\com\\image\\deleteJPanel.png"),new DeleteJPanel(sql),"ɾ���˵�");
		this.addTab(null,new ImageIcon("src\\com\\image\\searchJPanel.png"),new SearchJPanel(sql),"��ѯ�˵�");
		this.addTab(null,new ImageIcon("src\\com\\image\\ModificationJPanel.png"),new ModificationJPanel(sql),"�޸Ĳ˵�");
		this.addTab(null,new ImageIcon("src\\com\\image\\countJPanel.png"),new CountJPanel(sql),"ͳ�Ʋ˵�");
		this.addTab(null,new ImageIcon("src\\com\\image\\helpJPanel.png"),new HelpJPanel(),"�����˵�");
	}
	
	//�߳�ˢ��
	public void run() {
		while(true){
			try {
				Thread.sleep(50);
				this.repaint();
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		
	}
}
