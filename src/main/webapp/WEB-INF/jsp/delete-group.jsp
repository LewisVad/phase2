<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Groups</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: gray">
    <div>
      <a href="" class="navbar-brand" style="font-size: 200%;"> University </a>
    </div>
    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/students" class="nav-link" style="font-size: 150%;">Students</a></li>
      <li><a href="<%=request.getContextPath()%>/groups" class="nav-link" style="font-size: 150%;">Groups</a></li>
    </ul>
  </nav>
</header>

<h1 class="text-center">Group cannot be deleted.</h1><br>
<h1 class="text-center">It contains students</h1><br>
</body>
</html>
