<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>テスト登録</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        h1 {
            text-align: center;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-top: 10px;
        }
        select, input[type="text"] {
            margin-top: 5px;
            padding: 8px;
            font-size: 16px;
        }
        button {
            margin-top: 20px;
            padding: 10px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>テスト登録</h1>
    <form action="search_students.jsp" method="post">
        <!-- 入学年度 -->
        <label for="entYear">入学年度</label>
        <select id="entYear" name="entYear">
            <c:forEach var="student" items="${classNumList}">
                <option value="${student.entYear}">${student.entYear}</option>
            </c:forEach>
        </select>

        <!-- クラス -->
        <label for="classNum">クラス</label>
        <select id="classNum" name="classNum">
            <c:forEach var="student" items="${classNumList}">
                <option value="${student.classNum}">${student.classNum}</option>
            </c:forEach>
        </select>

        <!-- 科目 -->
        <label for="subjectCd">科目</label>
        <select id="subjectCd" name="subjectCd">
            <c:forEach var="subject" items="${subjectList}">
                <option value="${subject.cd}">${subject.name}</option>
            </c:forEach>
        </select>

        <button type="submit">検索</button>
    </form>
</div>
</body>
</html>
