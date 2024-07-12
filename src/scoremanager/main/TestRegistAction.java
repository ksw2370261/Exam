package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
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

            // リクエストパラメータから検索条件を取得
            String entYearStr = request.getParameter("entYear");
            String classNum = request.getParameter("classNum");
            String subjectCd = request.getParameter("subjectCd");
            String noStr = request.getParameter("no");

            // 検索条件をパース
            int entYear = (entYearStr != null && !entYearStr.isEmpty()) ? Integer.parseInt(entYearStr) : 0;
            int no = (noStr != null && !noStr.isEmpty()) ? Integer.parseInt(noStr) : 0;
            Subject subject = subjectList.stream().filter(s -> s.getCd().equals(subjectCd)).findFirst().orElse(null);
            School school = new School();
            school.setCd(teacher.getSchool_cd());

            // デバッグ用ログ出力
            System.out.println("entYear: " + entYear);
            System.out.println("classNum: " + classNum);
            System.out.println("subjectCd: " + subjectCd);
            System.out.println("no: " + no);
            if (subject != null) {
                System.out.println("subject.getCd(): " + subject.getCd());
            } else {
                System.out.println("subject is null");
            }

            // 検索結果を取得
            TestDao testDao = new TestDao();
            List<Test> testList = testDao.filter(entYear, classNum, subject, no, school);

            // リクエストにデータを格納
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("studentList", studentList);
            request.setAttribute("testList", testList);

            // フォワードする先のJSPを指定します（例: test_regist.jsp）
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        } else {
            // ユーザーがセッションに存在しない場合、エラーページにリダイレクトします
            response.sendRedirect("error.jsp");
        }
    }
}
