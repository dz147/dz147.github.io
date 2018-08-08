package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
public class DBUtil {
	//���Ӷ���
	//Statement �������
	//������
	//�ر�����
	//�õ�һ�����Ӷ���
	//��ѯ���вΣ��޲Σ�
	//�޸ģ��вΣ��޲Σ�
	
	static Connection conn = null;
	static Statement stmt = null;
	//��������������ַ����¼�û���������	
	static String DBDRIVER;
	static String DBURL;
	static String DBUID;
	static String DBPWD;
	
	static {
		DBDRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		DBURL = "jdbc:sqlserver://localhost:1433;databasename=bookDB";
		DBUID = "sa";
		//DBPWD = "@zdm168168...";
		DBPWD = "123456";
		/*
		//�ȴ�����Դ�ļ�����չ��Ϊ.properties
		//�������ԣ�dbuser=sa  ��ʽ
		
		Properties prop = new Properties();//�Ȼ�ȡ��Դ����
		//��������������ȡ��Դ�ļ�
		InputStream in =Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("jdbc.properties");
		try {
			prop.load(in);//����
			
			
			System.out.println(DBDRIVER);
		} catch (IOException e) {
			System.out.println("��Դ�ļ���ȡ����");
		} 
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	//������
	public static void open() {
		//��������
		try {
			Class.forName(DBDRIVER);
			conn=DriverManager.getConnection(DBURL,DBUID,DBPWD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}
	//�ر�����
	public static void close() {
		try {
			if(stmt!=null)
					stmt.close();
			if(conn!=null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�õ�һ�����Ӷ��󣬵��û�ʹ��DBUtil�޷������������ʱ
	//����ͨ��������������Ӷ���
	public static Connection getConnection() {
		try {
			if(conn==null ||conn.isClosed())
				open();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//executeQuery
	//executeUpdate
	//execute
	//��ò�ѯ�����ݼ�
	//select * from student where name='' and sex=''
	public static ResultSet executeQuery(String sql) {
		try {
			open();//��֤�����ǳɹ���
			stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//�޸ı������
	public static int executeUpdate(String sql) {
		int result = 0;
		try {
			open();//��֤�����ǳɹ���
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	//���ִ�еĲ�ѯ��洢���̣��᷵�ض�����ݼ�������ִ�гɹ���¼��
	//���Ե��ñ����������صĽ����
	//��һ��List<ResultSet>��List<Integer>����
	public static Object execute(String sql) {
		boolean b=false;
		try {
			open();//��֤�����ǳɹ���
			stmt = conn.createStatement();
			b = stmt.execute(sql);			
			//true,ִ�е���һ����ѯ��䣬���ǿ��Եõ�һ�����ݼ�
			//false,ִ�е���һ���޸���䣬���ǿ��Եõ�һ��ִ�гɹ��ļ�¼��
			if(b){
				return stmt.getResultSet();
			}
			else {
				return stmt.getUpdateCount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(!b) {
				close();
			}
		}
		return null;
	}
	
	//
	//select * from student where name=? and sex=?
	public static ResultSet executeQuery(String sql,Object[] in) {
		try {
			open();//��֤�����ǳɹ���
			PreparedStatement pst = conn.prepareStatement(sql);
			for(int i=0;i<in.length;i++)
				pst.setObject(i+1, in[i]);
			stmt = pst;//ֻ��Ϊ�˹ر��������pst
			return pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int executeUpdate(String sql,Object[] in) {
		try {
			open();//��֤�����ǳɹ���
			PreparedStatement pst = conn.prepareStatement(sql);
			for(int i=0;i<in.length;i++)
				pst.setObject(i+1, in[i]);
			stmt = pst;//ֻ��Ϊ�˹ر��������pst
			return pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return 0;
	}
	public static Object execute(String sql,Object[] in) {
		boolean b=false;
		try {
			open();//��֤�����ǳɹ���
			PreparedStatement pst = conn.prepareStatement(sql);
			for(int i=0;i<in.length;i++)
				pst.setObject(i+1, in[i]);
			b = pst.execute();
			//true,ִ�е���һ����ѯ��䣬���ǿ��Եõ�һ�����ݼ�
			//false,ִ�е���һ���޸���䣬���ǿ��Եõ�һ��ִ�гɹ��ļ�¼��
			if(b){
				System.out.println("----");
				/*List<ResultSet> list = new ArrayList<ResultSet>();
				list.add(pst.getResultSet());
				while(pst.getMoreResults()) {
					list.add(pst.getResultSet());
				}*/
				return pst.getResultSet();
			}
			else {
				System.out.println("****");
				List<Integer> list = new ArrayList<Integer>();
				list.add(pst.getUpdateCount());
				while(pst.getMoreResults()) {
					list.add(pst.getUpdateCount());
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(!b) {
				System.out.println("====");
				close();
			}
		}
		return null;
	}
	//���ô洢����  proc_Insert(?,?,?)
	public static Object executeProcedure(String procName,Object[] in) {
		open();
		try {
			procName = "{call "+procName+"(";
			String link="";
			for(int i=0;i<in.length;i++) {
				procName+=link+"?";
				link=",";
			}
			procName+=")}";
			CallableStatement cstmt = conn.prepareCall(procName);
			for(int i=0;i<in.length;i++) {
				cstmt.setObject(i+1, in[i]);
			}
			if(cstmt.execute())
			{
				return cstmt.getResultSet();
			}
			else {
				return cstmt.getUpdateCount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

	/*
	 * ���ô洢���̣������������
	 * @procName ���洢�������ƣ�proc_Insert(?,?)
	 * @in ,�����������
	 * @output,�����������
	 * @type,����������ͼ���
	 * */
	public static Object executeOutputProcedure(String procName,
			Object[] in,Object[] output,int[] type){
		Object result = null;
		try {
			CallableStatement cstmt = conn.prepareCall("{call "+procName+"}");
			//���ô洢���̵Ĳ���ֵ
			int i=0;
			for(;i<in.length;i++){//�����������
				cstmt.setObject(i+1, in[i]);
				//print(i+1);
			}
			int len = output.length+i;
			for(;i<len;i++){//�����������
				cstmt.registerOutParameter(i+1,type[i-in.length]);
				//print(i+1);
			}
			boolean b = cstmt.execute();
			//��ȡ���������ֵ
			for(i=in.length;i<output.length+in.length;i++)
				output[i-in.length] = cstmt.getObject(i+1);
			if(b) {
				result = cstmt.getResultSet();
			}
			else {
				result = cstmt.getUpdateCount();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//ʱ��ת��
	public static Date date(String date_str) {
        try {
            Calendar cal = Calendar.getInstance();//������
            Timestamp timestampnow = new Timestamp(cal.getTimeInMillis());//ת�������������ڸ�ʽ
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//��Ϊ��Ҫ�Ķ���
            ParsePosition pos = new ParsePosition(0);
            Date current = formatter.parse(date_str, pos);
            timestampnow = new Timestamp(current.getTime());
            return timestampnow;
        }
        catch (NullPointerException e) {
            return null;
        }
    };

}


