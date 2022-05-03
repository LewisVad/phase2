<%--
  Created by IntelliJ IDEA.
  User: vgheorghita
  Date: 12/2/2021
  Time: 12:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Edit Group</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div align="center">
  <h2>
    ${course.id != 0 ? 'Edit course' : 'Add new course'}
  </h2>
  <form action="/saveCourse" method="POST" name="saveCourse" >
    <table border="1" cellpadding="5">
      <c:if test="${course != null}"><input type="hidden" name="id" value="${course.id}"/></c:if>
      <tr>
        <th>Title :</th>
        <td><input type="text" name="title" size="45" value="${course.title}" required/></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <a href="/saveCourse"><input type="submit" value="Save" onclick="myFunction();" onsubmit="CloseWindow()"></a>
          <input type="button" onClick="cancelFunction()" value="Close" />
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
<SCRIPT LANGUAGE="JavaScript">
  function myFunction()
  {
    let update = confirm("Save updates?");
    alert (update ? "Updates saved!" : "");
    window.opener.location.reload();
  }

  function cancelFunction()
  {
    let cancel = confirm("Are you sure?");
    alert (cancel ? "Cancel updates!" : "");
    window.opener.location.reload();
    window.close();
  }

  window.onunload = refreshParent;
  function refreshParent() {
    window.opener.location.reload();
  }

  function CloseAndRefresh(f)
  {
    f.submit();
    self.close();
  }
  function CloseWindow()
  {
    window.top.opener = null;
    window.close();
  }
</SCRIPT>
</html>
