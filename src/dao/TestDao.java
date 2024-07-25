package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    private String baseSql = "SELECT Test.*, Student.*, Subject.*, School.* FROM test Test "
                             + "JOIN student Student ON Test.student_no = Student.no "
                             + "JOIN subject Subject ON Test.subject_cd = Subject.cd "
                             + "JOIN school School ON Test.school_cd = School.cd "
                             + "WHERE Test.school_cd = ?";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        String sql = baseSql + " AND Test.student_no = ? AND Test.subject_cd = ? AND Test.no = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, school.getCd());
            statement.setString(2, student.getNo());
            statement.setString(3, subject.getCd());
            statement.setInt(4, no);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapTest(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new Exception("テストの取得中にエラーが発生しました", e);
        }
    }

    public List<Test> postFilter(ResultSet resultSet, School school) throws SQLException {
        List<Test> list = new ArrayList<>();
        while (resultSet.next()) {
            Test test = mapTest(resultSet);
            if (test.getSchool().getCd().equals(school.getCd())) {
                list.add(test);
            }
        }
        return list;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int no, School school) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder(baseSql);
        List<Object> params = new ArrayList<>();
        params.add(school.getCd());

        if (entYear > 0) {
            sqlBuilder.append(" AND Student.ent_year = ?");
            params.add(entYear);
        }
        if (classNum != null && !classNum.isEmpty()) {
            sqlBuilder.append(" AND Test.class_num = ?");
            params.add(classNum);
        }
        if (subject != null && subject.getCd() != null && !subject.getCd().isEmpty()) {
            sqlBuilder.append(" AND Test.subject_cd = ?");
            params.add(subject.getCd());
        }
        if (no > 0) {
            sqlBuilder.append(" AND Test.no = ?");
            params.add(no);
        }

        String sql = sqlBuilder.toString();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            System.out.println("Executing SQL: " + sql);
            System.out.println("Parameters: " + params);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Test> testList = postFilter(resultSet, school);
                System.out.println("Test List Size: " + testList.size());
                return testList;
            }
        } catch (SQLException e) {
            throw new Exception("テストの検索中にエラーが発生しました", e);
        }
    }

    public boolean save(List<Test> list) throws Exception {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try {
                for (Test test : list) {
                    save(test, connection);
                }
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                throw new Exception("テストの保存中にエラーが発生しました", e);
            }
        }
    }

    public boolean save(Test test, Connection connection) throws SQLException {
        String sql = "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) "
                   + "VALUES (?, ?, ?, ?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE point = VALUES(point), class_num = VALUES(class_num)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            statement.setString(6, test.getClassNum());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean delete(List<Test> list) throws Exception {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try {
                for (Test test : list) {
                    delete(test, connection);
                }
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                throw new Exception("テストの削除中にエラーが発生しました", e);
            }
        }
    }

    public boolean delete(Test test, Connection connection) throws SQLException {
        String sql = "DELETE FROM test WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            return statement.executeUpdate() > 0;
        }
    }

    private Test mapTest(ResultSet resultSet) throws SQLException {
        Test test = new Test();

        // Create and set Student object
        Student student = new Student();
        student.setNo(resultSet.getString("student_no"));
        student.setName(resultSet.getString("name")); // 修正: "name" から "student_name" へ変更
        student.setEntYear(resultSet.getInt("ent_year"));
        student.setClassNum(resultSet.getString("class_num"));
        student.setAttend(resultSet.getBoolean("is_attend"));
        student.setSchool(resultSet.getString("school_cd"));
        test.setStudent(student);

        // Create and set Subject object
        Subject subject = new Subject();
        subject.setCd(resultSet.getString("subject_cd"));
        subject.setName(resultSet.getString("name")); // 修正: "name" から "subject_name" へ変更
        test.setSubject(subject);

        // Create and set School object
        School school = new School();
        school.setCd(resultSet.getString("school_cd"));
        school.setName(resultSet.getString("name"));
        test.setSchool(school);

        // Set other Test properties
        test.setNo(resultSet.getInt("no"));
        test.setPoint(resultSet.getInt("point"));
        test.setClassNum(resultSet.getString("class_num"));

        return test;
    }
}
