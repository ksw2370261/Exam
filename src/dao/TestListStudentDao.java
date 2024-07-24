package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    private String baseSql = "SELECT * FROM test_list_student WHERE student_no = ?";

    // ResultSetからTestListStudentリストを生成するメソッド
    private List<TestListStudent> postFilter(ResultSet rs) throws SQLException {
        List<TestListStudent> list = new ArrayList<>();
        while (rs.next()) {
            TestListStudent testListStudent = new TestListStudent();
            testListStudent.setSubjectName(rs.getString("subject_name"));
            testListStudent.setSubjectCd(rs.getString("subject_cd"));
            testListStudent.setNum(rs.getInt("num"));
            testListStudent.setPoint(rs.getInt("point"));
            list.add(testListStudent);
        }
        return list;
    }

    // 学生番号でTestListStudentリストをフィルタリングするメソッド
    public List<TestListStudent> filter(String studentNo) throws Exception {
        List<TestListStudent> testListStudents = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            stmt = connection.prepareStatement(baseSql);
            stmt.setString(1, studentNo);
            rs = stmt.executeQuery();
            testListStudents = postFilter(rs);
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

        return testListStudents;
    }
}
