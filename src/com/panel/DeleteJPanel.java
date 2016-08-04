package com.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.image.ImageFile;
import com.sql.SQL;

public class DeleteJPanel extends JPanel {
	private ImageFile imagefile;
	private int screenHeight;
	private int screenWidth;
	private JButton okButton;
	private JButton cancleButton;
	
	private String name[]={"ѧ��ѧ��:","ѧ������:","ѧ���Ա�:","ѧ������:","Ӣ��ɼ�:","��ѧ�ɼ�:","���ĳɼ�:"};	
	private JLabel namesJLabel[] = new JLabel[10];
	private JTextField textsfield[] = new JTextField[10];
	private JButton modification[] = new JButton[10];
	private ButtonGroup sexButton;
	private JRadioButton manButton;
	private JRadioButton famanButton;
	private JButton searchButton;
	private SQL sql;
	
	public DeleteJPanel(final SQL sql){
		this.sql = sql;		
		okButton = new JButton("ȷ��");
		cancleButton = new JButton("ȡ��");
		okButton.setBounds(380,350,120,40);
		cancleButton.setBounds(520,350,120,40);
		this.add(okButton);
		this.add(cancleButton);
		
		namesJLabel[0] = new JLabel(name[0]);
		namesJLabel[0].setBounds(200,20,120,35);
		namesJLabel[0].setFont(new Font("���Ŀ���",0,25));
		textsfield[0] = new JTextField();
		textsfield[0].setBounds(320,20,180,35);
		this.add(textsfield[0]);
		this.add(namesJLabel[0]);
		searchButton = new JButton("����");
		searchButton.setBounds(520,20,60,30);
		this.add(searchButton);
		searchButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				//��ѧ�Ų���Ҫ�޸ĵ���Ϣ������ʾ����
				if(e.getSource() == searchButton){
					try {
						sql.setResult(sql.getStat().executeQuery("select * from studen1 where num ='"+textsfield[0].getText()+"';"));
						while (sql.getResult().next()){
						for(int i=1;i<name.length;i++){
							if(i==2){								
								if(sql.getResult().getString(i+1).equals("��")){
									manButton.setSelected(true);
									famanButton.setSelected(false);
								}else if(sql.getResult().getString(i+1).equals("Ů")){
									manButton.setSelected(false);
									famanButton.setSelected(true);
								}										
							}else{
								textsfield[i].setText(sql.getResult().getString(i+1));
							}								
						}
						}
						} catch (SQLException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				}
			}
		});
		
		okButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getSource() == okButton&&textsfield[0].getText()!=null){
					sql.deleteMeassage(textsfield[0].getText());
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
		//ȡ��ɾ����Ϣ
		cancleButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
			if(e.getSource()== cancleButton){
				for(int j=0;j<name.length;j++){
					if(j!=2){
						     textsfield[j].setText("");
						}else if(j==2){
								sexButton.clearSelection();
							}
						}
					}
				}
			});
		
		//��ʾ���ҵ���Ϣ
		for(int i=1;i<name.length;i++){
			namesJLabel[i] = new JLabel(name[i]);
			namesJLabel[i].setBounds(200,60+40*i,120,35);
			namesJLabel[i].setFont(new Font("���Ŀ���",0,25));
			if(i==2){
				sexButton = new ButtonGroup();
				manButton = new JRadioButton("��",false);
				manButton.setBounds(320,140,80,35);
				manButton.setFont(new Font("���Ŀ���",0,25));
				//���ñ���Ϊ͸����
				manButton.setOpaque(false);
				famanButton = new JRadioButton("Ů",false);
				famanButton.setBounds(420,140,80,35);
				famanButton.setFont(new Font("���Ŀ���",0,25));
				famanButton.setOpaque(false);
				sexButton.add(manButton);
				sexButton.add(famanButton);			
				this.add(manButton);
				this.add(famanButton);
			}else{
				textsfield[i] = new JTextField();
				textsfield[i].setBounds(320,60+40*i,180,35);
				this.add(textsfield[i]);
			}
			this.add(namesJLabel[i]);			
		}
		
		//��ȡ��Ļ��С
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		//��ȡ��Ļ�ĸ߶ȺͿ��
		this.screenHeight = (int) screenSize.getHeight();
		this.screenWidth = (int) screenSize.getWidth();	
		imagefile = new ImageFile();
		this.setLayout(null);
	}
	
	//���Ʊ���ͼƬ
		protected void paintComponent( Graphics g ) {
			  setOpaque(true);
			  super.paintComponent(g);
			    g.drawImage(imagefile.addbackground,0,0,(int)(screenWidth/1.8),(int)(screenHeight/1.8),null);
			 }
}

