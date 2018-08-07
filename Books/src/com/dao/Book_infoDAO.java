package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.vo.Book_info;

public class Book_infoDAO {
public  List<Book_info> getBooks(){
        
		String sql="select book_id,book_name,book_author,book_price,book_company,publish_dare from book_info ORDER BY book_id desc";
		ResultSet rs=DBUtil.executeQuery(sql);
		List<Book_info> list=new ArrayList<Book_info>();
		Book_info pro=null;
		try {
			while(rs.next()){
				pro=new Book_info(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getBigDecimal(4),
						rs.getString(5),
						rs.getDate(6));
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
		//ɾ������
		public int getDelete(int id){
			String sql="delete from book_info where  book_id=?";
			int x=DBUtil.executeUpdate(sql, new Object[]{id});
			return x;
		}
}
