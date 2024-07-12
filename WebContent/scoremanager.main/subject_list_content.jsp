<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
</head>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>

<section class="me-4">
    <h2 class="h3 mb-3 fw-normal bg-light bg-opacity-10 py-2 px-4">科目管理</h2>
    <div class="my-2 text-end px-4">
        <!-- 新規登録ボタンなどのコンポーネント -->
        <a href="addSubject.action">新規登録</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <!-- エラーメッセージの表示 -->
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <!-- フィルターフォーム -->
    <form method="get">
        <!-- フィルターの内容 -->
        <!-- 例: 入学年度、クラスの選択肢、在学中チェックボックス -->
    </form>

    <!-- 科目リストの表示 -->
    <c:choose>
    <c:when test="${not empty subjects}">
        <div>検索結果: ${subjects.size()}件</div>
        <table class="table table-hover">
            <tr>
                <th>科目コード</th>
                <th>科目名</th>
                <th>操作</th>
            </tr>
            <c:forEach var="subject" items="${subjects}">
                <tr>
                    <td>${subject.cd}</td>
                    <td>${subject.name}</td>
                    <td>
                        <a href="editSubject.action?cd=${subject.cd}&school_cd=${subject.school_CD}">変更</a>
                        <a href="deleteSubject.action?cd=${subject.cd}&school_cd=${subject.school_CD}">削除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <div>科目情報が存在しませんでした</div>
    </c:otherwise>
</c:choose>


</section>
