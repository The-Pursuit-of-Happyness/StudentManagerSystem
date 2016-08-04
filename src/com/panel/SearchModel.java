package com.panel;
import javax.swing.table.AbstractTableModel;

import com.date.Student;
import com.sql.SQL;

public class SearchModel extends AbstractTableModel {		
		private SQL sql;
		
		public SearchModel(SQL sql) {
			this.sql = sql;
		}

		public void showSearchStudent() {
			String opSql = "";
			//显示学生信息
			sql.showsearch(opSql);
			//数据改变后自己监听
			this.fireTableStructureChanged();
		}
		
		public int getRowCount() {
			return sql.getStudent1().size();
		}
		
		public String getColumnName(int column) {
			return sql.getName()[column];
		}

		public int getColumnCount() {
			return sql.getName().length;
		}
		public Object getValueAt(int rowIndex,int columnIndex) {
			Student stu = sql.getStudent1().get(rowIndex);
			switch (columnIndex) {
			case 0:
				return stu.getNum();
			case 1:
				return stu.getName();
			case 2:
				return stu.getSex();
			case 3:
				return stu.getAge();
			case 4:
				return stu.getEnglish();
			case 5:
				return stu.getMath();
			case 6:
				return stu.getChina();
			}
			return null;
		}

	}

