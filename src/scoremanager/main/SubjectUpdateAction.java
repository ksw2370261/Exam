package scoremanager.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubjectUpdate.action")
public class SubjectUpdateAction extends tool.Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 科目変更の処理

        // 科目コードを取得
        String subjectCode = request.getParameter("subjectCode");

        // 科目データを取得し、リクエスト属性に設定
        request.setAttribute("subjectCode", subjectCode); // 実際の科目データオブジェクトで置き換える

        // JSPにフォワードして科目変更フォームを表示
        RequestDispatcher dispatcher = request.getRequestDispatcher("/subject_update.jsp");
        dispatcher.forward(request, response);
    }
}
