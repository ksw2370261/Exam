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
    <jsp:include page="base.jsp">
        <jsp:param name="showSidebar" value="true" />
        <jsp:param name="contentPage" value="test_list_content.jsp" />
    </jsp:include>
</body>
</html>
