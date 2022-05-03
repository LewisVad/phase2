<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>University</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<style>
  fieldset {
    background-color: #eeeeee;
    width: 60%;
    margin-bottom: auto;
    margin-left: auto;
    margin-right: auto;
  }

  legend {
    background-color: gray;
    color: white;
    padding: 5px 10px;
  }

  input {
    margin: 5px;
  }

  label {
    display: inline-block;
    width: 150px;
  }

  column {
    float: left;
    width: 50%;
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

<div class="Absolute-Center">
  <%@ include file="/WEB-INF/jsp/search-form.jsp" %>
<%--  <jsp:include page="/WEB-INF/jsp/search-form.jsp" />--%>
</div>

<h3 class="text-center">List of Students</h3>
<br>
<h1 class="text-center">
  <button class="btn btn-primary" onclick='mypopup("/new");'>Add Student</button>
</h1>
<br>

<form action="/deleteStudent" method="POST" onsubmit="return removeStudent(this);">
  <div class="container">
    <div class="row">
      <table style="width:100%" border="1">
        <tr>
          <th><input type="checkbox" id="select_all" name="chckHead"/></th>
          <th>ID</th>
          <th>Picture</th>
          <th>Name</th>
          <th>Gender</th>
          <th>Email</th>
          <th>Date Of Birth</th>
          <th>Group</th>
          <th>Address</th>
          <th>Phone(s)</th>
          <th>Library Membership</th>
          <th>Action</th>
        </tr>
        <c:forEach var="student" items="${students}">
          <tr>
            <td><input type="checkbox" name="ids" class="checkbox" value="${student.id}"/></td>
            <td>${student.id}</td>
            <td id="picture">
              <img src="data:image/png;base64,${student.picture}" width="100" height="100">
                <%--              <img th:src="|data:image/png;base64, ${student.picture}" width="100" height="100"/>--%>
            </td>
            <td id="name">${student.firstName} ${student.lastName}</td>
            <td id="gender">${student.gender}</td>
            <td id="email">${student.email}</td>
            <td id="dateOfBirth">${student.dateOfBirth}</td>
            <td id="group">${student.group.name}</td>
            <td id="address">${student.address.country != null ? student.address.country : ''}<br>
                ${student.address.city != null ? student.address.city : ''}<br>
                ${student.address.street != null ? student.address.street : ''}<br></td>
            <td id="phones">
              <c:forEach items="${student.phones}" var="phone">
                ${phone.phoneType.name} : ${phone.value}<br>
              </c:forEach></td>
            <td id="membership">
              <c:if test="${student.membership != null}">
                <c:if test="${student.membership.status == 'Active'}">
                  Status : ${student.membership.status}<br>
                  Start date : ${student.membership.startDate}<br>
                  End date : ${student.membership.endDate}<br>
                </c:if>
                <c:if test="${student.membership.status != 'Active'}">
                  Status : Suspended<br>
                  Start date : <br>
                  End date : <br>
                </c:if>
              </c:if>
              <c:if test="${student.membership == null}">
                  Status : NONE<br>
                  Start date : <br>
                  End date : <br>
              </c:if>
            </td>
            <td><a href="#" class="btn btn-primary">
              <button type="button" class="btn btn-primary" onclick='mypopup("/editStudent/${student.id}");'>Edit</button>
            </a>
          </tr>
        </c:forEach>
      </table>
      <h3 class="text-center" style="display:flex; justify-content:flex-end; width:100%; padding:0;">
        <button type="submit" class="btn btn-primary">Delete Student(s)</button>
      </h3>
    </div>
  </div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
  function removeStudent() {
    return confirm('Do you want to remove selected item(s) ?');
  }

  $(document).ready(function() {
    var checkboxes = $('#ids :checkbox');
    $('#select_all').on('click', function() {
      if(this.checked){
        $('.checkbox').each(function() {
          this.checked = true;
        });
      } else {
        $('.checkbox').each(function() {
          this.checked = false;
        });
      }
    });

    $('.checkbox').on('click', function() {
      if($('.checkbox:checked').length == $('.checkbox').length){
        $('#select_all').prop('checked', true);
      } else {
        $('#select_all').prop('checked', false);
      }
    });
  });

  (function() {
    $('input[name="chckHead"]').change(function() {
      var checkboxes = $('input[name="ids"]');
      if($(this).prop('checked')){
        checkboxes.prop('checked', true);
      } else {
        checkboxes.prop('checked', false);
      }
    });
  })();

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
</body>
</html>