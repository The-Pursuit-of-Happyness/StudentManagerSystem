package com.mouse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import com.mainjframe.MainJFrame;
import com.register.RegisterJFrame;
import com.sql.SQL;

public class MouseAction extends MouseAdapter {
	private RegisterJFrame register;
	private SQL sql;
	public MouseAction(RegisterJFrame register,SQL sql){
		this.sql =sql;
		this.register = register;		
	}
	
	public void mouseClicked(MouseEvent m){
		if(m.getSource() == register.getOkJButton()){
			if(register.getAccountJTextField().getText().equals("2013083222")&&register.getPasswordJPasswordField().getText().equals("2013083222")){
				new MainJFrame(sql);
				register.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "��¼ʧ��!", "��¼ʧ��......", JOptionPane.ERROR_MESSAGE);
			}
		}
		//ȡ���������Ϣ
		else if(m.getSource() == register.getCancleJButton()){
			register.getAccountJTextField().setText("");
			register.getPasswordJPasswordField().setText("");
		}
	}
}
