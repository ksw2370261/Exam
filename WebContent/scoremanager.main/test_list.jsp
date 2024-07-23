<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <title>成績管理システム - 検索</title>
    <style>
        /* style4.css */

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
        h2 {
            margin-bottom: 20px;
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

        /* Table styles */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            border: 1px solid #ccc;
        }

        /* Button styles */
        button {
            padding: 10px 20px;
            background-color: #d3d3d3;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #bbb;
        }

        /* Separator styles */
        .separator {
            border: 1px solid #ccc;
            margin: 20px 0;
        }

        /* Section title styles */
        .section-title {
            font-weight: bold;
            padding-right: 10px;
            vertical-align: top; /* 垂直方向に揃える */
        }

        /* Grade management and registration styles */
        .grade-management td, .grade-registration td {
            vertical-align: middle; /* 垂直方向に中央揃え */
        }

        /* Select box styles */
        select {
            width: 100%;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            -webkit-appearance: none; /* Remove default arrow for Chrome/Safari/Edge */
            -moz-appearance: none; /* Remove default arrow for Firefox */
            appearance: none; /* Remove default arrow */
            background-image: url('data:image/svg+xml;utf8,<svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/></svg>'); /* Add custom arrow icon */
            background-repeat: no-repeat;
            background-position: right 8px top 50%;
            background-size: 12px;
            background-color: white;
        }

        /* Input box styles */
        input[type="text"] {
            width: 150%;
            max-width: 200px;
            border-radius: 5px;
            padding: 8px; /* Match the padding of select boxes */
            border: 1px solid #ccc;
        }

        /* Placeholder text styles */
        input::placeholder {
            color: #aaa;
        }

        /* Info message styles */
        .info-message {
            color: #0000FF;
            margin-top: 10px;
            font-size: 14px;
        }

        /* Error message styles */
        .error-message {
            color: #FF0000;
            margin-top: 10px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>成績参照</h2>
        
        <!-- 科目情報検索フォーム -->
        <form id="subjectSearchForm" action="TestListSubjectExecuteAction" method="post" onsubmit="return validateSubjectSearch()">
            <table>
                <tbody>
                    <tr>
                        <td class="section-title">科目情報</td>
                        <td>
                            <label for="year">入学年度</label>
                            <select id="year" name="year" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
                                <!-- JSP スクリプトレットで年のリストを表示する -->
                                <c:forEach var="year" items="${years}">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="class">クラス</label>
                            <select id="class" name="class" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
                                <!-- JSP スクリプトレットでクラスのリストを表示する -->
                                <c:forEach var="className" items="${classes}">
                                    <option value="${className}">${className}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="subject">科目</label>
                            <select id="subject" name="subject" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.cd}">${subject.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <button type="submit">検索</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div id="subjectErrorMessage" class="error-message"></div>
        </form>
        
        <!-- 学生情報検索フォーム -->
        <form id="studentSearchForm" action="TestListStudentExecuteAction" method="post">
            <table>
                <tbody>
                    <tr>
                        <td class="section-title">学生情報</td>
                        <td>
                            <label for="studentId">学生番号</label><br>
                            <input type="text" id="studentId" name="studentId" placeholder="学生番号を入力してください" required>
                        </td>
                        <td>
                            <button type="submit">検索</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        
        <p class="info-message">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
    </div>
    <script>
    function validateSubjectSearch() {
        var year = document.getElementById('year').value;
        var classValue = document.getElementById('class').value;
        var subject = document.getElementById('subject').value;
        var errorMessage = document.getElementById('subjectErrorMessage');

        if (year === '--------' || classValue === '--------' || subject === '--------') {
            errorMessage.textContent = '入学年度、クラス、科目のいずれかを選択してください。';
            return false;
        } else {
            errorMessage.textContent = '';
            return true;
        }
    }

        function clearSubjectError() {
            document.getElementById('subjectErrorMessage').textContent = '';
        }
    </script>
</body>
</html>
