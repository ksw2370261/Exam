<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>成績管理</title>
    <style>
        /* Reset */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        /* Container styles */
        .container {
            padding: 20px;
        }

        /* Heading styles */
        h1 {
            background-color: #a9a9a9;
            padding: 10px;
            border-radius: 5px;
        }

        h2 {
            margin-top: 20px;
            background-color: #a9a9a9;
            padding: 10px;
            border-radius: 5px;
        }

        /* Form styles */
        form {
            margin-top: 20px;
            padding: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        /* Form-group styles */
        .form-group {
            display: inline-block;
            margin-right: 10px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        /* Select box styles */
        select {
            width: 150px;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-image: url('data:image/svg+xml;utf8,<svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/></svg>');
            background-repeat: no-repeat;
            background-position: right 8px top 50%;
            background-size: 12px;
        }

        /* Button styles */
        .form-group button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #d3d3d3;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 10px;
        }

        .form-group button:hover {
            background-color: #bbb;
        }

        /* Table styles */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Search result styles */
        .search-results {
            color: black;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>成績管理</h1>

    <!-- 検索フォーム -->
    <form action="TestRegist.action" method="post">
        <div class="form-group">
            <!-- 入学年度 -->
            <label for="entYear">入学年度</label>
            <select id="entYear" name="entYear">
                <option value="">---------</option>
                <c:forEach var="entYear" items="${entYearList}">
                    <option value="${entYear}" ${entYear == param.entYear ? 'selected' : ''}>${entYear}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <!-- クラス -->
            <label for="classNum">クラス</label>
            <select id="classNum" name="classNum">
                <option value="">---------</option>
                <c:forEach var="classNum" items="${classNumList}">
                    <option value="${classNum}" ${classNum == param.classNum ? 'selected' : ''}>${classNum}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <!-- 科目 -->
            <label for="subjectCd">科目</label>
            <select id="subjectCd" name="subjectCd">
                <option value="">---------</option>
                <c:forEach var="subject" items="${subjectList}">
                    <option value="${subject.cd}" ${subject.cd == param.subjectCd ? 'selected' : ''}>${subject.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <!-- 回数 -->
            <label for="no">回数</label>
            <select id="no" name="no">
                <option value="">---------</option>
                <c:forEach begin="1" end="10" var="no">
                    <option value="${no}" ${no == param.no ? 'selected' : ''}>${no}</option>
                </c:forEach>
            </select>
        </div>

        <!-- 検索ボタン -->
        <div class="form-group">
            <button type="submit">検索</button>
        </div>
    </form>

    <!-- 検索結果の表示 -->
    <h2>検索結果</h2>
    <div class="search-results">
        <c:if test="${not empty testList}">
            <table>
                <thead>
                    <tr>
                        <th>入学年度</th>
                        <th>学生番号</th>
                        <th>クラス</th>
                        <th>氏名</th>
                        <th>点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="test" items="${testList}">
                        <tr>
                            <td>${test.ent_Year}</td>
                            <td>${test.student_NO}</td>
                            <td>${test.class_Num}</td>
                            <td>${test.name}</td>
                            <td>${test.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
