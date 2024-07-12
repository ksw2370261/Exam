package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("login");

        if (teacher != null) {
            // 科目データを取得
        	SubjectDao subjectDao = new SubjectDao();
        	List<Subject> subjectList = subjectDao.filter(teacher.getSchool_cd());

            // クラスデータを取得
        	StudentDao studentDao = new StudentDao();
        	List<Student> studentList = studentDao.filter(teacher.getSchool_cd(), false);

            // リクエストにデータを格納
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("classNumList", studentList);

            // フォワードする先のJSPを指定します（例: test_regist.jsp）
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        } else {
            // ユーザーがセッションに存在しない場合、エラーページにリダイレクトします
            response.sendRedirect("error.jsp");
        }
    }
}
