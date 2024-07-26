package scoremanager.main;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import bean.TestListSubject;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String schoolCd = ((Teacher) req.getSession().getAttribute("login")).getSchool_cd();

        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty() && !entYearStr.equals("--------")) {
            entYear = Integer.parseInt(entYearStr);
        }

        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> testListSubjects = dao.filter(entYear, classNum, subjectCd, schoolCd);

        req.setAttribute("testListSubjects", testListSubjects);

        RequestDispatcher dispatcher = req.getRequestDispatcher("test_list_student.jsp");
        dispatcher.forward(req, res);
    }
}
