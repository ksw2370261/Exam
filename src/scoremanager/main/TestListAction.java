package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.SchoolDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // パラメータ取得
        String studentNo = req.getParameter("student_no");
        String entryYearStr = req.getParameter("entry_year");
        String classNum = req.getParameter("class_num");
        String subjectCd = req.getParameter("subject_cd");
        String schoolCd = req.getParameter("school_cd");

        // パラメータチェック
        int entYear = 0;
        if (entryYearStr != null && !entryYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entryYearStr);
            } catch (NumberFormatException e) {
                // エラー処理: 無効な入学年度の場合、エラーを設定するなど
                req.setAttribute("error", "Invalid entry year.");
                req.getRequestDispatcher("/error.jsp").forward(req, res);
                return;
            }
        }

        // 学校と科目情報の取得
        SubjectDao subjectDao = new SubjectDao();
        SchoolDao schoolDao = new SchoolDao();
        Subject subject = subjectDao.get(subjectCd, schoolCd);
        School school = schoolDao.get(schoolCd);

        // TestListStudentとTestListSubjectの設定
        if ((studentNo != null && !studentNo.isEmpty()) || (entYear > 0 && classNum != null && subject != null && school != null)) {
            setTestList(req, studentNo, entYear, classNum, subject, school);
        }

        // 検索ボックスの初期値を設定
        req.setAttribute("entryYear", entryYearStr);
        req.setAttribute("classNum", classNum);
        req.setAttribute("subjectCd", subjectCd);
        req.setAttribute("schoolCd", schoolCd);

        // JSPへフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }

    private void setTestList(HttpServletRequest req, String studentNo, int entYear, String classNum, Subject subject, School school) throws Exception {
        // TestListStudentの設定
        if (studentNo != null && !studentNo.isEmpty()) {
            TestListStudentDao testListStudentDao = new TestListStudentDao();
            List<TestListStudent> testListStudents = testListStudentDao.filter(studentNo);
            req.setAttribute("testListStudents", testListStudents);
        }

        // TestListSubjectの設定
        if (entYear > 0 && classNum != null && subject != null && school != null) {
            TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
            List<TestListSubject> testListSubjects = testListSubjectDao.filter(entYear, classNum, subject, school);
            req.setAttribute("testListSubjects", testListSubjects);
        }
    }
}
