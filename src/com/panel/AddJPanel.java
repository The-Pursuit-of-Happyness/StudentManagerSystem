package com.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.image.ImageFile;
import com.sql.SQL;

public class AddJPanel extends JPanel {
	private JLabel label;
	private ImageFile imagefile;
	private int screenHeight;
	private int screenWidth;
	private JButton okButton;
	private JButton cancleButton;
	
	private String name[]={"学生学号:","学生姓名:","学生性别:","学生年龄:","英语成绩:","数学成绩:","语文成绩:"};	
	private JLabel namesJLabel[] = new JLabel[10];
	private JTextField textsfield[] = new JTextField[10];
	private ButtonGroup sexButton;
	private JRadioButton manButton;
	private JRadioButton famanButton;
	private SQL sql;
	private int isCreate = -1; //用于判定數據庫是否創建成功
	
	public AddJPanel(final SQL sql){
		this.sql = sql;
		okButton = new JButton("确定");
		cancleButton = new JButton("取消");
		okButton.setBounds(380,350,120,40);
		cancleButton.setBounds(520,350,120,40);
		
		//确定添加信息
		okButton.addMouseListener(new MouseAdapter(){
			String s = null;
			public void mouseClicked(MouseEvent e){
				if(e.getSource()==okButton){
					if(manButton.isSelected()){
						s ="男";
					}else if(famanButton.isSelected()){
						s = "女";
					}
					else s=null;
					//判断输入不能为空
					boolean isempty = true;
					for(int j =0;j<name.length;j++){
						
						if(j!=2){
							if(textsfield[j].getText().length()==0){
								isempty = false;
							}
						}
						else if(j==2){
							if(s == null){
								isempty = false;
							}
						}
					}
					if(isempty){
						
					sql.addStudent(textsfield, s);
					}else{
						JOptionPane.showMessageDialog(null, "数据不能为空，请检查是否有未填项!", "添加失败", JOptionPane.ERROR_MESSAGE);
					}
					//清除掉框内的文字，为下次添加做准备
					for(int j=0;j<name.length;j++){
						if(j!=2){
							textsfield[j].setText("");
						}
						else if(j==2){
							sexButton.clearSelection();
						}
					}			
				}
			}
		});		
		
		//取消添加信息
		cancleButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource()== cancleButton){
					for(int j=0;j<name.length;j++){
						if(j!=2){
							textsfield[j].setText("");
						}
						else if(j==2){
							sexButton.clearSelection();
						}
					}
				}
			}
		});
		this.add(okButton);
		this.add(cancleButton);
		
		for(int i=0;i<name.length;i++){
			namesJLabel[i] = new JLabel(name[i]);
			namesJLabel[i].setBounds(230,20+40*i,120,35);
			namesJLabel[i].setFont(new Font("华文楷体",0,25));
			if(i==2){
				sexButton = new ButtonGroup();
				manButton = new JRadioButton("男",false);
				manButton.setBounds(350,100,80,35);
				manButton.setFont(new Font("华文楷体",0,25));
				//设置背景为透明的
				manButton.setOpaque(false);
				famanButton = new JRadioButton("女",false);
				famanButton.setBounds(450,100,80,35);
				famanButton.setFont(new Font("华文楷体",0,25));
				famanButton.setOpaque(false);
				sexButton.add(manButton);
				sexButton.add(famanButton);			
				this.add(manButton);
				this.add(famanButton);
			}else{
				textsfield[i] = new JTextField();
				textsfield[i].setBounds(350,20+40*i,180,35);
				this.add(textsfield[i]);
			}
			this.add(namesJLabel[i]);			
		}
		
		//获取屏幕大小
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//获取屏幕的高度和宽度
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();	
		imagefile = new ImageFile();
		this.setLayout(null);
	}
	//绘制背景图片
		protected void paintComponent( Graphics g ) {
			  setOpaque(true);
			  super.paintComponent(g);
			    g.drawImage(imagefile.addbackground,0,0,(int)(screenWidth/1.8),(int)(screenHeight/1.8),null);
			 }
}
