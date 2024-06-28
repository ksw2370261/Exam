<%-- base.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
 <head>
  <meta charset="UTF-8">
  <title><c:out value="得点管理システム" /></title>
  <link rel="stylesheet" href="style.css">
  <c:if test="${not empty param.scripts}">
      <c:out value="${param.scripts}" escapeXml="false"/>
  </c:if>
 </head>
 <header>
  <div class="header-container"><h1 class="title">得点管理システム</h1></div>
 </header>
<body>
    <div class="container">
        <div class="sidebar">
            <nav>
                <ul>
                <br>
                    <li><a href="Menu.action">メニュー</a></li>
                    <li><a href="StudentList.action">学生管理</a></li>
                    <li>成績管理
                        <ul>
                            <li><a href="">成績登録</a></li>
                            <li><a href="">成績参照</a></li>
                        </ul>
                    </li>
                    <li><a href="">科目管理</a></li>
                </ul>
            </nav>
        </div>
        <div class="main-content">
            <jsp:include page="${param.contentPage}" />
        </div>
    </div>
</body>
 <footer>
     <p>&copy; 2023 TIC
     <br>
     大原学園</p>
 </footer>
</html>
