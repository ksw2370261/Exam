<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <title>成績管理システム - 検索</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            font-size: 12px; /* 全体のフォントサイズを小さく */
        }
        .container {
            padding: 10px; /* 余白を減らす */
        }
        h2 {
            margin-bottom: 10px; /* 下の余白を減らす */
            font-size: 16px; /* 見出しのフォントサイズを小さく */
            background-color: #a9a9a9;
            padding: 5px; /* パディングを減らす */
            border-radius: 3px; /* 角丸を小さく */
        }
        form {
            margin-top: 10px; /* 上の余白を減らす */
            padding: 10px; /* パディングを減らす */
            border-radius: 3px; /* 角丸を小さく */
            border: 1px solid #ccc;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px; /* パディングを減らす */
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            border: 1px solid #ccc;
        }
        button {
            padding: 5px 10px; /* パディングを減らす */
            background-color: #d3d3d3;
            color: white;
            border: none;
            border-radius: 3px; /* 角丸を小さく */
            cursor: pointer;
        }
        button:hover {
            background-color: #bbb;
        }
        .separator {
            border: 1px solid #ccc;
            margin: 10px 0; /* 余白を減らす */
        }
        .section-title {
            font-weight: bold;
            padding-right: 20px; /* 余白を減らす */
            vertical-align: middle; /* 中央揃え */
            text-align: left; /* 左揃え */
        }
        .grade-management td, .grade-registration td {
            vertical-align: middle; 
        }
        select {
            width: 100%;
            padding: 4px; /* パディングを減らす */
            border-radius: 3px; /* 角丸を小さく */
            border: 1px solid #ccc;
            box-sizing: border-box;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-image: url('data:image/svg+xml;utf8,<svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/></svg>');
            background-repeat: no-repeat;
            background-position: right 4px top 50%;
            background-size: 8px;
        }
        input[type="text"] {
            width: 100%;
            max-width: 150px; /* 最大幅を設定 */
            border-radius: 3px; /* 角丸を小さく */
            padding: 4px; /* パディングを減らす */
            border: 1px solid #ccc;
        }
        .info-message {
            color: #0000FF;
            margin-top: 5px; /* 上の余白を減らす */
            font-size: 12px; /* フォントサイズを小さく */
        }
        .error-message {
            color: #FF0000;
            margin-top: 5px; /* 上の余白を減らす */
            font-size: 12px; /* フォントサイズを小さく */
        }
        .form-group {
            display: flex;
            flex-direction: column; /* 縦に並べる */
        }
        .form-group label {
            margin-bottom: 5px; /* ラベルと入力フォームの間のマージン */
            text-align: left; /* 左揃え */
        }
        .form-group select,
        .form-group input {
            width: 100%;
            max-width: 150px; /* 最大幅を設定 */
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
                            <label for="f1">入学年度</label>
                            <select id="f1" name="f1" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
                                <!-- JSP スクリプトレットで年のリストを表示する -->
                                <c:forEach var="year" items="${years}">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="f2">クラス</label>
                            <select id="f2" name="f2" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
                                <!-- JSP スクリプトレットでクラスのリストを表示する -->
                                <c:forEach var="className" items="${classes}">
                                    <option value="${className}">${className}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="f3">科目</label>
                            <select id="f3" name="f3" onchange="clearSubjectError()">
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
                            <div class="form-group">
                                <label for="f4">学生番号</label>
                                <input type="text" id="f4" name="f4" value="${f4}" placeholder="学生番号を入力してください" required>
                            </div>
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
        var year = document.getElementById('f1').value;
        var classValue = document.getElementById('f2').value;
        var subject = document.getElementById('f3').value;
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