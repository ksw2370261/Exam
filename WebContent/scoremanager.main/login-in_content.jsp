<!-- login_in_content.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>ログインページ</title>
    <link rel="stylesheet" href="style.css">
    <style>
        .login-box {
            max-width: 500px;
            margin: 50px auto;
            padding: 20px;
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

        .form-group {
            margin-bottom: 10px;
            position: relative;
        }

        .form-group label {
            position: absolute;
            top: -10px;
            left: 10px;
            background-color: #f9f9f9;
            padding: 0 5px;
            font-size: 12px;
            z-index: 1;
        }

        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            position: relative;
            z-index: 0;
        }

        .form-actions {
            margin-top: 10px;
            text-align: center;
        }
    </style>
</head>
<div class="login-box">
    <h2>ログイン</h2>

    <c:if test="${not empty error}">
        <p style="color:black;">${error}</p>
    </c:if>

    <form action="LoginExecute.action" method="post">
        <div class="form-group">
            <label for="id">ID</label>
            <input type="text" id="id" name="id" size="20" placeholder="半角でご入力ください" maxlength="20" pattern="[\x21-\x7E]*" title="半角でご入力ください" required>
        </div>
        <div class="form-group">
            <label for="password">パスワード</label>
            <input type="password" id="password" name="password" size="20" placeholder="20文字以内の半角英数字でご入力ください" maxlength="20" pattern="[\x21-\x7E]*" title="20文字以内の半角英数字でご入力ください" required>
        </div>
        <p><input type="checkbox" id="chk_d_ps" onclick="togglePasswordVisibility()"> パスワードを表示</p>
        <div class="form-actions">
            <input type="submit" value="ログイン" name="login">
        </div>
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
