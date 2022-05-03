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
      <th>Status</th>
      <td>
        <select name="status">
          <option value="" ${membership.status==null ? ' selected' : ''}>NONE</option>
          <option value="active" ${membership.status=="active" ? ' selected' : ''}>Active</option>
          <option value="suspended" ${membership.status=="suspended" ? ' selected' : ''}>Suspended</option>
        </select>
      </td>
    </tr>
    <tr>
      <th>Start date</th>
      <td><input type="date" name="startDate" size="35" value="${membership.startDate}"/></td>
    </tr>
    <tr>
      <th>End date</th>
      <td><input type="date" name="endDate" size="35" value="${membership.endDate}"/></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <a href="/saveMembership"><input type="submit" name="libUpdate" value="Submit" /></a>
        <input type="submit" name="btn-cancel" value="Cancel" onclick="refreshParent();window.close()"/></td>
    </tr>
  </table>
</form>
</body>
</html>
