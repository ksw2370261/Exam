package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student_reate")
public class StudentCreateAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 学生情報の入力フォームを表示するJSPへフォワードする
        request.getRequestDispatcher("/WEB-INF/views/studentCreate.jsp").forward(request, response);
    }
}
