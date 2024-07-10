package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.LoginDAO2;
import tool.Action;

public class LoginExecuteAction extends Action{
	public void execute(
	HttpServletRequest request, HttpServletResponse response
	)throws Exception{
		HttpSession session=request.getSession();

		String id=request.getParameter("id");
		String password=request.getParameter("password");
		LoginDAO2 dao=new LoginDAO2();
		Teacher Login=dao.search(id, password);

		if(Login!=null){
			session.setAttribute("login", Login);
			request.getRequestDispatcher("menu.jsp").forward(request, response);
		}
     }
}