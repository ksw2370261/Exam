<!-- login_in_content.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>ログインページ</title>
    <link rel="stylesheet" href="style.css"> <!-- スタイルシートへのリンク -->
    <style>
        .login-box {
            max-width: 500px;
            margin: 50px auto;
            padding: 0;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
            position: relative;
        }

        .login-box h2 {
            text-align: center;
            margin: 0;
            padding: 10px;
            background-color: #efefef;
            border-bottom: 1px solid #ccc;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            width: 100%;
            box-sizing: border-box;
        }
    </style>
</head>
<div class="login-box">
    <h2>ログイン</h2> <!-- No.1 画面タイトル -->

    <!-- エラーメッセージをタイトルの下に表示 -->
    <c:if test="${not empty error}">
        <p style="color:black;">${error}</p>
    </c:if>

    <form action="LoginExecute.action" method="post">
        <input type="text" name="id" size="20" placeholder="ID" maxlength="20" pattern="[\x21-\x7E]*" title="半角でご入力ください" required>
        <input type="password" name="password" size="20" placeholder="パスワード" maxlength="20" pattern="[\x21-\x7E]*" title="20文字以内の半角英数字でご入力ください" required>
        <p><input type="checkbox" id="chk_d_ps" onclick="togglePasswordVisibility()"> パスワードを表示</p> <!-- No.4, No.5 パスワード表示/非表示 -->
        <p><input type="submit" value="ログイン" name="login"></p> <!-- No.6 ログインボタン -->
    </form>
</div>

<script>
function togglePasswordVisibility() {
    var passwordField = document.querySelector('input[name="password"]');
    var checkbox = document.getElementById('chk_d_ps');
    if (checkbox.checked) {
        passwordField.type = "text";
    } else {
        passwordField.type = "password";
    }
}
</script>