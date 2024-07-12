package scoremanager.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubjectDeleteExecute.action")
public class SubjectDeleteExecuteAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームパラメータから科目コードを取得
        String subjectCode = request.getParameter("subjectCode");

        // 科目削除を実行（実際の削除ロジックに置き換える）
        boolean deleteSuccessful = performSubjectDeletion(subjectCode);

        if (deleteSuccessful) {
            // 科目削除完了ページにフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/subject_delete_done.jsp");
            dispatcher.forward(request, response);
        } else {
            // 削除失敗時の処理（オプション）
            request.setAttribute("error", "科目の削除に失敗しました");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/subject_delete.jsp");
            dispatcher.forward(request, response);
        }
    }

    // 実際のメソッドはDBやサービスから科目を削除するものとする
    private boolean performSubjectDeletion(String subjectCode) {
        // データベースやサービスから科目を削除する処理を実装する
        // ダミー実装:
        // 例: SubjectDao.delete(subjectCode);
        return true; // ダミー成功を返す
    }
}
