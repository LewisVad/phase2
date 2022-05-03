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

<h1 class="text-center">Teacher</h1><br>
<h3 class="text-center">
  <a href="#" onclick='mypopup("/newTeacher"); return false;'>
    <button class="btn btn-primary">Add Teacher</button>
  </a>
</h3>
<br>

<div class="container">
  <div class="row">
    <table style="width:100%" border="1">
      <tr>
        <th>ID</th>
        <th>Picture</th>
        <th>Name</th>
        <th>Gender</th>
        <th>Email</th>
        <th>Date Of Birth</th>
        <th>Address</th>
        <th>Phone(s)</th>
        <th>Salary</th>
<%--        <th>Courses</th>--%>
        <th>Action</th>
      </tr>
      <c:forEach var="teacher" items="${teachers}">
        <tr>
          <td>${teacher.id}</td>
          <td id="picture"><img src="data:image/png;base64,${teacher.picture}" width="100" height="100"></td>
          <td id="name">${teacher.firstName} ${teacher.lastName}</td>
          <td id="gender">${teacher.gender}</td>
          <td id="email">${teacher.email}</td>
          <td id="dateOfBirth">${teacher.dateOfBirth}</td>
          <td id="address">${teacher.address.country != null ? teacher.address.country : ''}<br>
              ${teacher.address.city != null ? teacher.address.city : ''}<br>
              ${teacher.address.street != null ? teacher.address.street : ''}<br></td>
          <td id="phones">
            <c:forEach items="${teacher.phones}" var="phone">
              ${phone.phoneType.name} : ${phone.value}<br>
            </c:forEach></td>
          <td id="salary">${teacher.salary}</td>
<%--          <td id="course">--%>
<%--              ${teacher.course.title}--%>
<%--&lt;%&ndash;            <c:forEach items="${teacher.courses}" var="course">${course.title}<br></c:forEach>&ndash;%&gt;--%>
<%--          </td>--%>
          <td>
            <a href="#" class="btn btn-primary"><button type="button" class="btn btn-primary" onclick='mypopup("/editTeacher/${teacher.id}");'>Edit</button></a>
            <form action="/deleteTeacher/${teacher.id}" method="post"><a href="/deleteTeacher/${teacher.id}">
                <button type="submit" class="btn btn-primary" onclick="return confirm('Are you sure you want to delete this item?');">DELETE</button>
              </a></form></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
  function removeStudent() {
    return confirm('Do you want to remove selected item(s) ?');
  }

  function mypopup(url) {
    width = window.screen.width / 2;
    height = window.screen.height;
    mywindow = window.open(url, "Title", "location=0,status=1,scrollbars=1,resizable=1,menubar=0,toolbar=no,width=" + width + ",height=" + height);
    mywindow.moveTo(0, 0);
    mywindow.focus();
  }

  function load() {
    console.log("Page load finished");
  }
</script>
</html>
