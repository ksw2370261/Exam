<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>科目情報</title>
</head>
<body>
    <div class="container">
        <h2>科目情報</h2>

        <table class="table">
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>クラス</th>
                    <th>学生番号</th>
                    <th>氏名</th>
                    <th>1回目の点数</th>
                    <th>2回目の点数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${year}</td>
                        <td>${classNum}</td>
                        <td>${student.studentId}</td>
                        <td>${student.name}</td>
                        <td>${student.score1}</td>
                        <td>${student.score2}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</body>
</html>
