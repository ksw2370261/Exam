package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    // 入学年度、クラス番号、科目コード、学校コードでフィルタリングするSQL
    private String baseSql = "SELECT * FROM test_list_subject WHERE entry_year = ? AND class_num = ? AND subject_cd = ?";

    // ResultSetからTestListSubjectリストを生成するメソッド
    private List<TestListSubject> postFilter(ResultSet rs) throws SQLException {
        List<TestListSubject> list = new ArrayList<>();
        while (rs.next()) {
            TestListSubject testListSubject = new TestListSubject();
            testListSubject.setEntYear(rs.getInt("entry_year"));
            testListSubject.setStudentNo(rs.getString("student_no"));
            testListSubject.setStudentName(rs.getString("student_name"));
            testListSubject.setClassNum(rs.getString("class_num"));

            // points列をMapに変換して設定する必要がある場合、適切に処理する
            // 例: testListSubject.setPoints(parsePointsFromResultSet(rs));

            list.add(testListSubject);
        }
        return list;
    }

    // 入学年度、クラス番号、科目でTestListSubjectリストをフィルタリングするメソッド
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject) throws Exception {
        List<TestListSubject> subjects = new ArrayList<>();
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = getConnection(); // Daoクラスで定義されているメソッドでコネクションを取得する
            stmt = connection.prepareStatement(baseSql);
            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subject.getCd());
            rs = stmt.executeQuery();
            subjects = postFilter(rs);
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
}
