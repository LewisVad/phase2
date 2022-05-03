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

<div align="center">
  <h2>
    ${group.id != 0 ? 'Edit Group' : 'Add new group'}
  </h2>
  <form action="/saveGroup" method="POST" name="saveGroup" >
    <table border="1" cellpadding="5">
      <c:if test="${group != null}"><input type="hidden" name="id" value="${group.id}"/></c:if>
      <tr>
        <th>Name:</th>
        <td><input type="text" name="name" size="45" value="${group.name}" required/></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="Save" onclick="myFunction();" onsubmit="CloseWindow()"></button></a>
<%--          <a href="/groups"><button onclick="return confirm('Are you sure you want to cancel?');">Cancel</button></a>--%>
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
