<%@ page contentType="text/html; charset=UTF-8" %>

 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>得点管理システム</title>
<style>

    </style>
</head>
<body>


<div class="content">
<div class="h2">
<h2>成績管理</h2>
</div>

    <div class="message">
<%
            String message = (String) request.getAttribute("message");
            if (message == null) {
                message = "登録が完了しました";
            }
            out.print(message);
        %>
</div>

    <div class="links">
<a href="<%= request.getContextPath() %>/back">戻る</a>
<a href="<%= request.getContextPath() %>/reference">成績参照</a>
</div>
</div>

</body>
</html>

