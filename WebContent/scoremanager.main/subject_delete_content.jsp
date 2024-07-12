<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <!-- 必要なCSSとJSを含める -->
</head>

<body>
    <section>
        <h2>科目削除確認</h2>
        <div>
            <p>以下の科目を削除しますか？</p>
            <p>科目名: ${subject.name}</p>
            <p>科目コード: ${subject.code}</p>
        </div>
        <form action="SubjectDeleteExecute.action" method="post">
            <input type="hidden" name="subjectCode" value="${subject.code}">
            <button type="submit" class="btn btn-danger">削除</button>
            <br>
            <a href="SubjectList.action">戻る</a>
        </form>
    </section>
</body>
