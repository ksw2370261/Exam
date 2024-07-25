package bean;

import java.util.Map;

public class TestListSubject {
    private int entYear;
    private String studentNo;
    private String studentName;
    private String classNum;
    private Map<Integer, Integer> points;

    // 新しいフィールドの追加
    private String subjectCd;
    private String subjectName;
    private String no;
    private int point;

    public TestListSubject() {
    }

    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Map<Integer, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, Integer> points) {
        this.points = points;
    }

    public String getPoint(int key) {
        return points.getOrDefault(key, 0).toString();
    }

    public void putPoint(int key, int value) {
        points.put(key, value);
    }

    // 新しいフィールドの getter と setter メソッド
    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
