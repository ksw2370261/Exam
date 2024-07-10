<%-- login-in_content.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="LoginExecute.action" method="post">
    <h2>ログイン</h2> <!-- No.1 画面タイトル -->

    <!-- エラーメッセージをタイトルの下に表示 -->
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <p>ID<input type="text" name="id" size="20" placeholder="id" maxlength="20" pattern="[\x21-\x7E]*" title="半角でご入力ください" required></p> <!-- No.2 ログインID -->
    <p>パスワード<input type="password" name="password" size="20" placeholder="password" maxlength="20" pattern="[\x21-\x7E]*" title="20文字以内の半角英数字でご入力ください" required></p> <!-- No.3 パスワード -->
    <p><input type="checkbox" id="chk_d_ps" onclick="togglePasswordVisibility()"> パスワードを表示</p> <!-- No.4, No.5 パスワード表示/非表示 -->
    <p><input type="submit" value="ログイン" name="login"></p> <!-- No.6 ログインボタン -->
</form>

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
