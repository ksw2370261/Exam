package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // フォームから科目情報を取得
        String subjectCd = request.getParameter("cd");
        String subjectName = request.getParameter("subjectName");

        // バリデーション
        Map<String, String> errors = new HashMap<>();
        if (subjectName == null || subjectName.isEmpty()) {
            errors.put("subjectName", "科目名を入力してください");
        }

        if (!errors.isEmpty()) {
            // エラーがある場合、元のフォームに戻る
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        // 科目情報を更新
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();
        subject.setCd(subjectCd);
        subject.setName(subjectName);
        subjectDao.update(subject);

        // 成功メッセージを設定してリダイレクト
        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
    }
}
