package com.app;

import com.register.RegisterJFrame;
import com.sql.SQL;

public class App {

	public static void main(String[] args) {
		SQL sql = new SQL();
		new RegisterJFrame(sql);		
		//sql.judge();
	}
}
