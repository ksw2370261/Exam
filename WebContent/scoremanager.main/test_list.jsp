<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <title>成績管理システム</title>
    
    <script>
        function validateSubjectSearch() {
            var year = document.getElementById("year").value;
            var classNum = document.getElementById("class").value;
            var subject = document.getElementById("subject").value;
            
            if (year === "--------" || classNum === "--------" || subject === "--------") {
                document.getElementById("subjectErrorMessage").innerText = "入学年度、クラス、科目を選択してください";
                return false;
            }
            return true;
        }
        
        function clearSubjectError() {
            document.getElementById("subjectErrorMessage").innerText = "";
        }
        
        function validateStudentSearch() {
            var studentId = document.getElementById("studentId").value;
            
            if (studentId.trim() === "") {
                document.getElementById("studentErrorMessage").innerText = "学生番号を入力してください";
                return false;
            }
            return true;
        }
        
        function clearStudentError() {
            document.getElementById("studentErrorMessage").innerText = "";
        }
    </script>
</head>
<body>
    <div class="container">
        <h2>成績参照</h2>
        
        <!-- 科目情報検索フォーム -->
        <form id="subjectSearchForm" action="SearchSubjectServlet" method="post" onsubmit="return validateSubjectSearch()">
            <table>
                <tbody>
                    <tr>
                        <td class="section-title">科目情報</td>
                        <td>
                            <label for="year">入学年度</label>
                            <select id="year" name="f1" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
                                <jsp:useBean id="years" scope="request" class="java.util.ArrayList"/>
                                <c:forEach var="year" items="${years}">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="class">クラス</label>
                            <select id="class" name="f2" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
                                <jsp:useBean id="classes" scope="request" class="java.util.ArrayList"/>
                                <c:forEach var="classNum" items="${classes}">
                                    <option value="${classNum}">クラス ${classNum}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="subject">科目</label>
                            <select id="subject" name="f3" onchange="clearSubjectError()">
                                <option value="--------">--------</option>
                                <jsp:useBean id="subjects" scope="request" class="java.util.ArrayList"/>
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
            <div id="subjectErrorMessage" style="color: yellow;"></div>
        </form>
        
        <!-- 学生情報検索フォーム -->
        <form id="studentSearchForm" action="SearchStudentServlet" method="post" onsubmit="return validateStudentSearch()">
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
            <div id="studentErrorMessage" style="color: black;"></div>
        </form>
        
        <!-- 検索結果表示エリア -->
        <div id="searchResults">
            <!-- 検索結果は各サーブレットで取得して表示する -->
        </div>
        
        <p class="info-message">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
    </div>
</body>
</html>
