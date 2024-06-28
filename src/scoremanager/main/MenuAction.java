package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class MenuAction extends Action {

	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.getRequestDispatcher("menu.jsp").forward(req, res);
		return null;
	}
}