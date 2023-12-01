package bean;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.Forward;
import dao.MemberDao;

public class MemberInfo {
	HttpServletRequest request;
	HttpServletResponse response;

	public MemberInfo(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	public Forward loginFrm() {
		Forward fw = new Forward();
		
		fw.setPath("/loginfrm.jsp");
		fw.setRedirect(false);
		return fw;
	}

	public Forward login() {
		MemberDao md = new MemberDao();
		Forward fw = new Forward();
		HttpSession hs = request.getSession();

		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("id", id);
		hMap.put("pw", pw);
		
		if(md.login(hMap)) {
			hs.setAttribute("id", id);
			
			fw.setPath("/index.jsp");
			fw.setRedirect(true);
		} else {
			request.setAttribute("msg", "로그인 실패!");
			fw.setPath("/loginfrm.jsp");
			fw.setRedirect(false);
		}
		return fw;
	}

	public Forward join() {
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		
		System.out.printf("%s,%s,%s,%s\n", id, pw, name, gender);
		
		HashMap<String, String> hMap = new HashMap<>();
		hMap.put("id", id);
		hMap.put("pw", pw);
		hMap.put("name", name);
		hMap.put("gender", gender);
		
		MemberDao mDao = new MemberDao(); // db접속
		boolean result = mDao.join(hMap); // db 로직-->-->insert작업--->
		mDao.close();
		Forward fw = new Forward();
		
		if (result) {
			System.out.println("회원가입 성공");
			fw.setPath("/loginfrm.jsp");
			fw.setRedirect(true);
		} else {
			// System.out.println("회원가입 실패");
			request.setAttribute("msg", "회원가입실패");
			fw.setPath("/joinFrm.jsp");
			fw.setRedirect(false);
		}
		return fw;
	}

	public Forward joinFrm() {
		Forward fw = new Forward();
		
		fw.setPath("/joinfrm.jsp");
		fw.setRedirect(false);
		return fw;
	}
	
	public Forward logOut() {
		request.getSession().invalidate();
		Forward fw = new Forward();
		fw.setPath("/");
		fw.setRedirect(true);
		return fw;
	}
}
