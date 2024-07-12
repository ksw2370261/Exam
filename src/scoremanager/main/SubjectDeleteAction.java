package scoremanager.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;

@WebServlet("/SubjectDelete.action")
public class SubjectDeleteAction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストパラメータから科目コードを取得
        String subjectCode = request.getParameter("subjectCode");

        // 科目の詳細を取得（実際のデータ取得処理に置き換える）
        Subject subject = getSubjectDetails(subjectCode);

        // 科目オブジェクトをリクエスト属性に設定
        request.setAttribute("subject", subject);

        // 科目削除確認JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/subject_delete.jsp");
        dispatcher.forward(request, response);
    }

    // 実際のメソッドはDBやサービスから科目の詳細を取得するものとする
    private Subject getSubjectDetails(String subjectCode) {
        // データベースやサービスから科目の詳細を取得する処理を実装する
        // ダミー実装:
        Subject subject = new Subject();
        subject.setCd(subjectCode);
        subject.setName("ダミー科目");
        return subject;
    }
}
