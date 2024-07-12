package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubjectUpdateExecute.action")
public class SubjectUpdateExecuteAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectCode = request.getParameter("subjectCode");
        String newSubjectName = request.getParameter("subjectName");

        // バリデーションの例
        if (newSubjectName == null || newSubjectName.isEmpty()) {
            request.setAttribute("errors", "科目名を入力してください");
            request.getRequestDispatcher("/subject_update.jsp").forward(request, response);
            return;
        }

        // 科目の更新処理（ここではダミーとして記述）
        boolean updatedSuccessfully = updateSubject(subjectCode, newSubjectName);

        if (updatedSuccessfully) {
            // 変更が成功した場合は完了画面にリダイレクト
            response.sendRedirect(request.getContextPath() + "/subject_update_done.jsp");
        } else {
            // 変更が失敗した場合はエラーメッセージをセットしてフォームに戻る
            request.setAttribute("error", "変更に失敗しました");
            request.getRequestDispatcher("/subject_update.jsp").forward(request, response);
        }
    }

    private boolean updateSubject(String subjectCode, String newSubjectName) {
        // ここに実際のデータベース更新処理を記述する（例としてダミーメソッド）
        // この例では成功として常にtrueを返す
        return true;
    }
}
