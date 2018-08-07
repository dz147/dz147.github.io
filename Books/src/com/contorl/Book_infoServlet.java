package com.contorl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.Book_infoDAO;
import com.dao.JsonUtil;

@WebServlet("/ListAllBookServlet")
public class Book_infoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Book_infoServlet() {
        super();
    }
    PrintWriter out;
    Book_infoDAO dao=new Book_infoDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out=response.getWriter();
		String action=request.getParameter("action");
		 if(action.equals("list")){
			 list(request,response);
	     }else if(action.equals("delt")){
	    	 dateBook(request,response);
	     }
		
	}


	private void dateBook(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("id"));//ɾ������
	   	 if(id>0){
	   		 int x=dao.getDelete(id);
	   		 if(x>0){
	   			 out.print("{\"msg\":\"ɾ���ɹ�\"}");
	   		 }else {
	   			 out.print("{\"msg\":\"ʧ�ܳɹ�\"}");
	   			 }
	   	 }else{
	   		 out.print("{\"msg\":\"�����Ч\"}");
		   	 }
	}


	private void list(HttpServletRequest request, HttpServletResponse response) {
		String str=JsonUtil.toJson(dao.getBooks());//��Ⱦ����
        out.println(str);//�������
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
