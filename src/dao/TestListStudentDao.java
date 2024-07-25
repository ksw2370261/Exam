package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    public List<TestListStudent> filter(String subjectCd) throws Exception {
        List<TestListStudent> testListStudents = new ArrayList<>();
        String sql = "SELECT st.no, st.name, t.point " +
                     "FROM student st " +
                     "JOIN test t ON st.no = t.student_no " +
                     "WHERE t.subject_cd = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, subjectCd);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    TestListStudent testListStudent = new TestListStudent();
                    testListStudent.setNo(rs.getString("no"));
                    testListStudent.setName(rs.getString("name"));
                    testListStudent.setPoint(rs.getInt("point"));
                    testListStudents.add(testListStudent);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("データベースエラーが発生しました。");
        }

        return testListStudents;
    }
}
