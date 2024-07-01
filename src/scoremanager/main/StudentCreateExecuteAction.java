package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

	@WebServlet("/studentCreateExecute")
	public class StudentCreateExecuteAction extends HttpServlet {
	    @Override
	    public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        // フォームから送信されたデータを取得
	        String studentNo = req.getParameter("no");
	        String name = req.getParameter("name");
	        int entYear = Integer.parseInt(req.getParameter("ent_year"));
	        String classNum = req.getParameter("class_num");
	        String schoolCd = req.getParameter("school_cd");

	        Student student = new Student();
	        student.setNo(studentNo);
	        student.setName(name);
	        student.setEntYear(entYear);
	        student.setClassNum(classNum);
	        student.setSchool(schoolCd);

	        StudentDao studentDao = new StudentDao();

	        try {
	            boolean result = studentDao.save(student);

	            if (result) {
	                // 成功した場合の処理
	                return "student_create_done.jsp";
	            } else {
	                // 失敗した場合の処理
	                return "error.jsp";
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            // エラーページへのリダイレクトなど
	            return "error.jsp";
	        }
	    }
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}
