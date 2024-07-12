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
            throw new Exception("Error while fetching test", e);
        }
    }


        public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
            // 入学年度を取得するためにStudentDaoを利用します
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.get(String.valueOf(entYear)); // 入学年度を文字列に変換して学生を取得します

            if (student == null) {
                // 学生が見つからない場合は、空のリストを返すなどの適切な処理を行います
                return new ArrayList<>();
            }

            // 学生の入学年度を取得します
            int studentEntYear = student.getEntYear();

            // SQL文の組み立て
            String sql = baseSql + "ent_year = ? AND class_num = ? AND subject_cd = ? AND no = ? AND school_cd = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, studentEntYear);  // 学生の入学年度を設定します
                statement.setString(2, classNum);
                statement.setString(3, subject.getCd());
                statement.setInt(4, num);
                statement.setString(5, school.getCd());
                try (ResultSet resultSet = statement.executeQuery()) {
                    return postFilter(resultSet, school);  // 結果セットを後処理してリストを返します
                }
            }
        }

        private List<Test> postFilter(ResultSet resultSet, School school) throws SQLException {
            List<Test> list = new ArrayList<>();
            while (resultSet.next()) {
                Test test = mapTest(resultSet);
                if (test.getSchool_CD().equals(school.getCd())) {
                    list.add(test);
                }
            }
            return list;
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
                    throw new Exception("Error while saving tests", e);
                }
            } catch (SQLException e) {
                throw new Exception("Database connection error", e);
            }
        }

        private boolean save(Test test, Connection connection) throws SQLException {
            String sql = "INSERT INTO tests (student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, test.getStudnet_NO());
                statement.setString(2, test.getSubject_CD());
                statement.setString(3, test.getSchool_CD());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClass_Num());
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
                    throw new Exception("Error while deleting tests", e);
                }
            } catch (SQLException e) {
                throw new Exception("Database connection error", e);
            }
        }

        private boolean delete(Test test, Connection connection) throws SQLException {
            String sql = "DELETE FROM tests WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, test.getStudnet_NO());
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
