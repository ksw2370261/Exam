<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <title>成績管理システム - 検索結果</title>
    <%@include file="test_list_content.jsp" %>
</head>
<body>
    <div class="container">
        <div class="main-content">

            <!-- 科目情報検索結果表示 -->
            <c:if test="${not empty testListSubjects}">
                <table class="table">
                    <thead>
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>学生名</th>
                            <th>第1テスト点数</th>
                            <th>第2テスト点数</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="result" items="${testListSubjects}">
                            <tr>
                                <td>${result.entYear}</td>
                                <td>${result.classNum}</td>
                                <td>${result.studentNo}</td>
                                <td>${result.studentName}</td>
                                <td>${result.getPoint(1)}</td>
                                <td>${result.getPoint(2)}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</body>
</html>
