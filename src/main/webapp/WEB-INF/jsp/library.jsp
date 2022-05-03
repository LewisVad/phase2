<%--
  Created by IntelliJ IDEA.
  User: vgheorghita
  Date: 12/3/2021
  Time: 9:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">
  function alertName(){
    alert("Form has been submitted");
  }
  window.onunload = refreshParent;
  function refreshParent() {
    window.opener.location.reload();
  }
  function close_window(){
    self.close();
  }
</script>
<head>
  <link href="../resources/css/style1.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="library" action="students" method="post">
  <table border="1px solid black" cellpadding="5">
    <tr>
      <td colspan="2" align="center">
        <input type="hidden" name="personId" value="${student.id}"/>
        <input type="hidden" name="membershipId" value="${membership.id}"/>
        <label>
          ${student.firstName} ${student.lastName} - library abonament
        </label>
    </tr>
    <tr>
      <h1 class="text-center">Membership is updated!</h1><br>

    </tr>
  </table>
</form>
</body>
</html>
