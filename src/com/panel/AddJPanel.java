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
	
	private String name[]={"ѧ��ѧ��:","ѧ������:","ѧ���Ա�:","ѧ������:","Ӣ��ɼ�:","��ѧ�ɼ�:","���ĳɼ�:"};	
	private JLabel namesJLabel[] = new JLabel[10];
	private JTextField textsfield[] = new JTextField[10];
	private ButtonGroup sexButton;
	private JRadioButton manButton;
	private JRadioButton famanButton;
	private SQL sql;
	private int isCreate = -1; //�����ж��������Ƿ񄓽��ɹ�
	
	public AddJPanel(final SQL sql){
		this.sql = sql;
		okButton = new JButton("ȷ��");
		cancleButton = new JButton("ȡ��");
		okButton.setBounds(380,350,120,40);
		cancleButton.setBounds(520,350,120,40);
		
		//ȷ�������Ϣ
		okButton.addMouseListener(new MouseAdapter(){
			String s = null;
			public void mouseClicked(MouseEvent e){
				if(e.getSource()==okButton){
					if(manButton.isSelected()){
						s ="��";
					}else if(famanButton.isSelected()){
						s = "Ů";
					}
					else s=null;
					//�ж����벻��Ϊ��
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
						JOptionPane.showMessageDialog(null, "���ݲ���Ϊ�գ������Ƿ���δ����!", "���ʧ��", JOptionPane.ERROR_MESSAGE);
					}
					//��������ڵ����֣�Ϊ�´������׼��
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
		
		//ȡ�������Ϣ
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
			namesJLabel[i].setFont(new Font("���Ŀ���",0,25));
			if(i==2){
				sexButton = new ButtonGroup();
				manButton = new JRadioButton("��",false);
				manButton.setBounds(350,100,80,35);
				manButton.setFont(new Font("���Ŀ���",0,25));
				//���ñ���Ϊ͸����
				manButton.setOpaque(false);
				famanButton = new JRadioButton("Ů",false);
				famanButton.setBounds(450,100,80,35);
				famanButton.setFont(new Font("���Ŀ���",0,25));
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
