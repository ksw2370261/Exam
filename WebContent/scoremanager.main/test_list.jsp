<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <title>成績管理システム - 検索</title>
    <style>
        /* 基本スタイル */
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
        form {
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        select {
            padding: 5px;
            border-radius: 4px;
            border: 1px solid #ddd;
            width: 100%;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        #subjectErrorMessage {
            color: yellow;
            font-weight: bold;
            margin-top: 10px;
        }
        #studentErrorMessage {
            color: black;
            font-weight: bold;
            margin-top: 10px;
        }
        .info-message {
            color: #666;
            margin-top: 20px;
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
                                <c:forEach var="class" items="${classes}">
                                    <option value="${class}">${class}</option>
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
                            <button type="submit">科目情報を検索</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div id="subjectErrorMessage"></div>
        </form>
        
        <!-- 学生情報検索フォーム -->
        <form id="studentSearchForm" action="TestListStudentExecuteAction" method="post" onsubmit="return validateStudentSearch()">
            <table>
                <tbody>
                    <tr>
                        <td class="section-title">学生情報</td>
                        <td>
                            <label for="studentId">学生番号</label><br>
                            <input type="text" id="studentId" name="studentId" placeholder="学生番号を入力してください">
                        </td>
                        <td>
                            <button type="submit">学生情報を検索</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div id="studentErrorMessage"></div>
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

        function validateStudentSearch() {
            var studentId = document.getElementById('studentId').value;
            var errorMessage = document.getElementById('studentErrorMessage');

            if (studentId.trim() === '') {
                errorMessage.textContent = '学生番号を入力してください。';
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
