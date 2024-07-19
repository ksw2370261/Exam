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

    private String baseSql = "SELECT * FROM tests WHERE ";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        String sql = baseSql + "student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
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
            if (test.getSchool_CD().equals(school.getCd())) {
                list.add(test);
            }
        }
        return list;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int no, School school) throws Exception {
        // 入学年度に基づいて学生情報を取得
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(String.valueOf(entYear));

        if (student == null) {
            return new ArrayList<>(); // 学生が見つからない場合は空のリストを返す
        }

        // SQLクエリの基本部分を定義
        String baseSql = "SELECT * FROM test WHERE ent_year = ? AND class_num = ? AND subject_cd = ? AND no = ?";

        // テスト回数が1から10の範囲内であることを確認
        if (no < 1 || no > 10) {
            throw new IllegalArgumentException("テスト回数は1から10の範囲で指定してください。");
        }

        // SQLクエリを実行して結果を取得
        String sql = baseSql;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, student.getEntYear());
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setInt(5, no);
            try (ResultSet resultSet = statement.executeQuery()) {
                return postFilter(resultSet, school);
            }
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
        String sql = "INSERT INTO tests (student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, test.getStudent_NO());
            statement.setString(2, test.getSubject_CD());
            statement.setString(3, test.getSchool_CD());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            statement.setString(6, test.getClass_Num());
            statement.executeUpdate();
            return true;
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
        String sql = "DELETE FROM tests WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, test.getStudent_NO());
            statement.setString(2, test.getSubject_CD());
            statement.setString(3, test.getSchool_CD());
            statement.setInt(4, test.getNo());
            return statement.executeUpdate() > 0;
        }
    }

    private Test mapTest(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        test.setStudent_NO(resultSet.getString("student_no"));
        test.setSubject_CD(resultSet.getString("subject_cd"));
        test.setSchool_CD(resultSet.getString("school_cd"));
        test.setNo(resultSet.getInt("no"));
        test.setPoint(resultSet.getInt("point"));
        test.setClass_Num(resultSet.getString("class_num"));
        return test;
    }
}
