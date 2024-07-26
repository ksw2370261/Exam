package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School; // Schoolクラスのインポート
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("login");

        if (teacher != null) {
            try {
                // リクエストからテストデータを取得
                String[] studentNos = request.getParameterValues("studentNo");
                String[] subjectCds = request.getParameterValues("subjectCd");
                String[] schoolCds = request.getParameterValues("schoolCd");
                String[] scores = request.getParameterValues("score");
                String entYear = request.getParameter("entYear");
                String classNum = request.getParameter("classNum");
                String no = request.getParameter("no");

                // パラメータのチェック
                if (entYear == null || classNum == null || no == null) {
                    request.setAttribute("errorMessage", "入学年度とクラスと科目と回数を入力してください。");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }

                // パラメータのチェック
                if (studentNos == null || subjectCds == null || scores == null || schoolCds == null) {
                    StringBuilder missingParams = new StringBuilder("不足しているパラメータ: ");
                    if (studentNos == null) missingParams.append("studentNos ");
                    if (subjectCds == null) missingParams.append("subjectCds ");
                    if (schoolCds == null) missingParams.append("schoolCds ");
                    if (scores == null) missingParams.append("scores ");
                    throw new Exception(missingParams.toString().trim());
                }

                // パラメータの数が一致しているかチェック
                if (studentNos.length != subjectCds.length || subjectCds.length != schoolCds.length || schoolCds.length != scores.length) {
                    throw new Exception("パラメータの数が一致しません。");
                }

                TestDao testDao = new TestDao();
                List<Test> testList = new ArrayList<>();

                for (int i = 0; i < studentNos.length; i++) {
                    Test test = new Test();

                    // Studentオブジェクトを作成し、テストに設定
                    Student student = new Student();
                    student.setNo(studentNos[i]);
                    test.setStudent(student);

                    // Subjectオブジェクトを作成し、テストに設定
                    Subject subject = new Subject();
                    subject.setCd(subjectCds[i]);
                    test.setSubject(subject);

                    // Schoolオブジェクトを作成し、テストに設定
                    School school = new School();
                    school.setCd(schoolCds[i]);
                    test.setSchool(school);

                    // 点数を設定
                    try {
                        test.setPoint(Integer.parseInt(scores[i]));
                    } catch (NumberFormatException e) {
                        throw new Exception("点数の形式が正しくありません: " + scores[i]);
                    }

                    testList.add(test);
                }

                boolean success = testDao.save(testList);

                if (success) {
                    request.setAttribute("message", "成績が正常に保存されました。");
                    request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "成績の保存に失敗しました。");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "エラーが発生しました。再度お試しください。" + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            // ユーザーがセッションに存在しない場合、エラーページにリダイレクト
            response.sendRedirect("error.jsp");
        }
    }
}
