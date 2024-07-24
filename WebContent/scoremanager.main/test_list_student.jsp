<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <title>成績管理システム - 結果</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin: 20px auto;
            max-width: 800px;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
       
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="test_list.jsp" />
        <h2>検索結果</h2>
        
        <!-- 科目情報検索結果表示 -->
        <c:if test="${not empty subjectResults}">
            <h3>科目情報検索結果</h3>
            <table>
                <thead>
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>第1テスト点数</th>
                        <th>第2テスト点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="result" items="${subjectResults}">
                        <tr>
                            <td>${result.entYear}</td>
                            <td>${result.classNum}</td>
                            <td>${result.studentNo}</td>
                            <td>${result.name}</td>
                            <td>${result.test1Score}</td>
                            <td>${result.test2Score}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <!-- 学生情報検索結果表示 -->
        <c:if test="${not empty studentResults}">
            <h3>学生情報検索結果</h3>
            <table>
                <thead>
                    <tr>
                        <th>科目名</th>
                        <th>科目コード</th>
                        <th>テスト回数</th>
                        <th>テスト点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="result" items="${studentResults}">
                        <tr>
                            <td>${result.subjectName}</td>
                            <td>${result.subjectCode}</td>
                            <td>${result.testCount}</td>
                            <td>${result.testScores}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
       
    </div>
</body>
</html>
