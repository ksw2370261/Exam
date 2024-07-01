package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectListAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}
}