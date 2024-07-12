<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>テスト登録</title>
    <style>
        /* スタイルの定義 */
    </style>
</head>
<body>
<div class="container">
    <h1>テスト登録</h1>
    <form action="test_regist" method="post">
        <!-- 入学年度 -->
        <label for="entYear">入学年度</label>
        <select id="entYear" name="entYear">
            <c:forEach var="student" items="${studentList}">
                <option value="${student.entYear}" ${student.entYear == param.entYear ? 'selected' : ''}>${student.entYear}</option>
            </c:forEach>
        </select>

        <!-- クラス -->
        <label for="classNum">クラス</label>
        <select id="classNum" name="classNum">
            <c:forEach var="student" items="${studentList}">
                <option value="${student.classNum}" ${student.classNum == param.classNum ? 'selected' : ''}>${student.classNum}</option>
            </c:forEach>
        </select>

        <!-- 科目 -->
        <label for="subjectCd">科目</label>
        <select id="subjectCd" name="subjectCd">
            <c:forEach var="subject" items="${subjectList}">
                <option value="${subject.cd}" ${subject.cd == param.subjectCd ? 'selected' : ''}>${subject.name}</option>
            </c:forEach>
        </select>

        <button type="submit">検索</button>
    </form>

    <!-- 検索結果の表示 -->
    <h2>検索結果</h2>
    <c:if test="${not empty testList}">
        <table>
            <thead>
                <tr>
                    <th>学生番号</th>
                    <th>クラス</th>
                    <th>氏名</th>
                    <th>点数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="test" items="${testList}">
                    <tr>
                        <td>${test.student_NO}</td>
                        <td>${test.class_Num}</td>
                        <td>${test.name}</td>
                        <td>${test.point}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty testList}">
        <p>該当するデータが見つかりませんでした。</p>
    </c:if>
</div>
</body>
</html>
