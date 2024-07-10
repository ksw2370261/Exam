package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.LoginDAO2;
import tool.Action;

public class LoginExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // 入力チェック
        if (id == null || password == null || id.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "IDまたはパスワードが入力されていません。");
            request.getRequestDispatcher("login-in.jsp").forward(request, response);
            return;
        }

        // DAOのインスタンス生成
        LoginDAO2 dao = new LoginDAO2();
        Teacher login = dao.search(id, password);

        if (login != null) {
            // ログイン成功時の処理
            session.setAttribute("login", login);
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        } else {
            // ログイン失敗時の処理
            request.setAttribute("error", "IDまたはパスワードが間違っています。");
            request.getRequestDispatcher("login-in.jsp").forward(request, response);
        }
    }
}
