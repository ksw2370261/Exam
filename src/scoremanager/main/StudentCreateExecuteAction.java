package scoremanager.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/studentCreateExecuteAction")
public class StudentCreateExecuteAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String studentNo = request.getParameter("no");
        String name = request.getParameter("name");
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        String classNum = request.getParameter("class_num");

        // データベースへの接続設定
        String jdbcUrl = "jdbc:h2:~/school";
        String username = "sa"; // データベースのユーザー名
        String password = ""; // データベースのパスワード

        try {
            // JDBCドライバのロードとデータベースへの接続
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, username, password);

            // SQLクエリの準備と実行
            String sql = "INSERT INTO STUDENTS (STUDENT_NO, NAME, ENT_YEAR, CLASS_NUM) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentNo);
            stmt.setString(2, name);
            stmt.setInt(3, entYear);
            stmt.setString(4, classNum);

            // クエリの実行
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("学生情報がデータベースに挿入されました。");
                // 成功した場合の処理
                response.sendRedirect("student_create_done.jsp");
            } else {
                // 失敗した場合の処理
                response.sendRedirect("error.jsp");
            }

            // リソースの解放
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // エラーページへのリダイレクトなど
            response.sendRedirect("error.jsp");
        }
    }
}
