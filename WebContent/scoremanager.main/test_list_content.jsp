<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>成績管理システム - 検索</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="testlist.css"> <!-- testlist.css を追加 -->
</head>
<body>
    <div class="main-content">
        <%-- タイトルをリクエストから取得 --%>
        <h2><c:out value="${title}" default="成績参照" /></h2>

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
                                <c:forEach var="year" items="${years}">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="f2">クラス</label>
                            <select id="f2" name="f2" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
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