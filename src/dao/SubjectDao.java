package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject; // Subjectクラスのパッケージに合わせてインポートを変更

public class SubjectDao extends Dao {

    private String baseSql = "select * from subject where cd=? and school_cd=? and name=?";

    public Subject get(String cd, String school) throws Exception {
        Subject subject = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection(); // Daoクラスで定義されているメソッドでコネクションを取得する
            stmt = connection.prepareStatement(baseSql);
            stmt.setString(1, cd);
            stmt.setString(2, school);
            rs = stmt.executeQuery();

            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setSchool_CD(rs.getString("school_cd"));
                subject.setName(rs.getString("name"));
                // 他のフィールドも必要に応じて設定
            }
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

        return subject;
    }

    public List<Subject> filter(String school) throws Exception {
        List<Subject> subjects = new ArrayList<>();
        String sql = "select * from subject where school_cd=?";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection(); // Daoクラスで定義されているメソッドでコネクションを取得する
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, school);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("cd"));
                subject.setSchool_CD(rs.getString("school_cd"));
                subject.setName(rs.getString("name"));
                // 他のフィールドも必要に応じて設定
                subjects.add(subject);
            }
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

        return subjects;
    }

    public boolean save(Subject subject) throws Exception {
        String sql = "insert into subject (cd, school_cd, name) values (?, ?, ?)";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection(); // Daoクラスで定義されているメソッドでコネクションを取得する
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, subject.getCd());
            stmt.setString(2, subject.getSchool_CD());
            stmt.setString(3, subject.getName());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("データベースエラーが発生しました。");
        } finally {
            // リソースのクローズ
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
    }

    public boolean delete(Subject subject) throws Exception {
        String sql = "delete from subject where cd=? and school_cd=?";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = getConnection(); // Daoクラスで定義されているメソッドでコネクションを取得する
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, subject.getCd());
            stmt.setString(2, subject.getSchool_CD());

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("データベースエラーが発生しました。");
        } finally {
            // リソースのクローズ
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
    }
}
