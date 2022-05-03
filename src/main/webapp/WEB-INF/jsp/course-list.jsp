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
<style>
  body {
    padding: 24px;
    font-family: 'Source Sans Pro', sans-serif;
    margin: 0;
  }

  h1, h2, h3, h4, h5, h6 {
    margin: 0;
  }

  .container {
    max-width: 1000px;
    margin-right: auto;
    margin-left: auto;
    display: flex;
    justify-content: center;
    align-items: center;
    /*min-height: 100vh;*/
  }

  .entities {
    display: inline-block;
  }
</style>

<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: gray">
    <div>
      <a href="" class="navbar-brand" style="font-size: 200%;"> University </a>
    </div>
    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/students" class="nav-link" style="font-size: 150%;">Students</a></li>
      <li><a href="<%=request.getContextPath()%>/groups" class="nav-link" style="font-size: 150%;">Groups</a></li>
      <li><a href="<%=request.getContextPath()%>/teachers" class="nav-link" style="font-size: 150%;">Teachers</a></li>
      <li><a href="<%=request.getContextPath()%>/courses" class="nav-link" style="font-size: 150%;">Courses</a></li>
    </ul>
  </nav>
</header>

<h1 class="text-center">Courses</h1><br>
<h3 class="text-center">
  <a href="#" onclick='mypopup("/newCourse"); return false;'>
    <button class="btn btn-primary">Add course</button>
  </a>
</h3>
<br>
<div class="container">
  <table style="width:100%" border="1">
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Action</th>
    </tr>
    <c:forEach var="course" items="${courses}">
    <tr>
      <td id="id">${course.id}</td>
      <td id="title">${course.title}</td>
      <td id="action">
          <a href="#" onclick="mypopup('/course/${course.id}'); return false;">
            <button class="btn btn-primary">EDIT</button>
          </a>
            <form action="/deleteCourse/${course.id}" method="post">
            <a href="/deleteCourse/${course.id}">
              <button type="submit" class="btn btn-primary" onclick="return confirm('Are you sure you want to delete this item?');">DELETE</button>
            </a>
          </form>
      </td>
    </tr>
    </c:forEach>
</div>
</body>
<script type="text/javascript">
  function mypopup(url) {
    width = window.screen.width / 2;
    height = window.screen.height / 2;
    mywindow = window.open(url, "Title","location=0,status=1,scrollbars=1,resizable=1,menubar=0,toolbar=no,width="+ width + ",height=" + height);
    mywindow.moveTo(0, 0);
    mywindow.focus();
  }
  function load() {console.log("Page load finished");}
</script>
</html>
