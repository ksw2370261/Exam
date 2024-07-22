package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;
import bean.TestListSubject;

public class TestListStudentDao extends Dao {

    private String baseSql = "SELECT * FROM test_list_student WHERE student_no = ?";

    // ResultSetからTestListStudentリストを生成するメソッド
    private List<TestListStudent> postFilter(ResultSet rs) throws SQLException {
        List<TestListStudent> list = new ArrayList<>();
        while (rs.next()) {
            TestListStudent student = new TestListStudent();
            student.setSubjectName(rs.getString("subject_name"));
            student.setSubjectCd(rs.getString("subject_cd"));
            student.setNum(rs.getInt("num"));
            student.setPoint(rs.getInt("point"));
            list.add(student);
        }
        return list;
    }

    // TestListSubjectオブジェクトに基づいてTestListStudentリストをフィルタリングするメソッド
    public List<TestListStudent> filter(TestListSubject testListSubject) throws Exception {
        List<TestListStudent> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection(); // Daoクラスで定義されているメソッドでコネクションを取得する
            stmt = connection.prepareStatement(baseSql);
            stmt.setString(1, testListSubject.getStudentNo()); // TestListSubjectから学生番号を取得してフィルタリング
            rs = stmt.executeQuery();
            students = postFilter(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("データベースエラーが発生しました。");
        } finally {
            // リソースのクローズ
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return students;
    }
}
