package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Forward;

@WebServlet({"/login","/loginfrm","/joinfrm","/join","/logout","/main", "/memberList", "/memberInfo", "/memberDelete"})
public class HomeContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getServletPath();
		System.out.println("cmd="+cmd);
		
		
		Forward fw = null;
		
//		if(cmd.equals("/loginfrm")) {
//			fw = mp.showLoginFrm(cmd);			
//		} else if(cmd.equals("/login")) {
//			fw = mm.login();
//		} else if(cmd.equals("/joinfrm")) {
//			fw = mp.showJoinFrm();
//		} else if(cmd.equals("/join")) {
//			fw = mm.join();
//		} else if(cmd.equals("/logout")) {
//			fw = mm.logOut();
//		} else if(cmd.equals("/memberList")) {
//			fw = mm.getMemberList();
//		} else if(cmd.equals("/memberInfo")) {
//			fw = mm.getMemberInfo();
//		} else if(cmd.equals("/memberDelete")) {
//			String id = request.getParameter("id");
//			fw = mm.memberDelete(id);
//		}
		
		if(fw != null) {
			if(fw.isRedirect()) { //true: redirect
				response.sendRedirect(fw.getPath()); //새로운 req, res객체,주소창 새url
			}else {
				request.getRequestDispatcher(fw.getPath()).forward(request, response); //기존 req, res객체사용,주소창 갱신안됨
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
