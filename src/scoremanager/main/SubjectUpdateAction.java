package scoremanager.main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 科目コードを取得
        String subjectCd = request.getParameter("cd");
        if (subjectCd == null || subjectCd.isEmpty()) {
            throw new ServletException("科目コードが指定されていません。");
        }

        // 科目情報を取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.find(subjectCd);
        if (subject == null) {
            throw new ServletException("指定された科目が見つかりません。");
        }

        // 科目情報をリクエストに設定
        request.setAttribute("subject", subject);

        // subject_update.jsp にフォワード
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
