<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<br>
<div align="center">
  <h2>
    ${teacher.id != 0 ? 'Edit teacher' : 'Add new teacher'}
  </h2>
  <br>

  <form:form action="/saveTeacher" method="POST" modelAttribute="teacher">
    <table border="1px solid black" cellpadding="5">
      <form:hidden path="id"/>
      <tr>
        <th><form:label path="firstName">First Name</form:label></th>
        <td><form:input type="text" path="firstName" size="45" value="${teacher.firstName}" required="required"/>
          <form:errors path="firstName" cssClass="text-warning"/></td>
      </tr>
      <tr>
        <th><form:label path="lastName">Last Name</form:label></th>
        <td><form:input type="text" path="lastName" size="45" value="${teacher.lastName}" required="required"/>
          <form:errors path="lastName" cssClass="text-warning"/></td>
      </tr>
      <tr>
        <th><form:label path="gender">Gender</form:label></th>
        <td><input type="radio" id="man" name="gender" value="M" ${teacher.gender == "M".charAt(0) ? 'checked="checked"' : ''}>Male
          <input type="radio" id="woman" name="gender" value="F" ${teacher.gender == "F".charAt(0) ? 'checked="checked"' : ''}>Female
        </td>
      </tr>
      <tr>
        <th><form:label path="email">Email</form:label></th>
        <td><form:input type="text" path="email" size="45" value="${teacher.email}" required="required"/>
          <form:errors path="email" cssClass="text-warning"/></td>
      </tr>
      <tr>
        <th><form:label path="dateOfBirth">Date of birth</form:label></th>
        <td><form:input type="date" path="dateOfBirth" size="45" value="${teacher.dateOfBirth}" required="required"/>
          <form:errors path="dateOfBirth" cssClass="text-warning"/></td>
      </tr>
<%--      <tr>--%>
<%--        <th><form:label path="courses">Courses</form:label></th>--%>
<%--        <td>--%>
<%--          <form:select path="course.id" items="${courseItems}"/>--%>
<%--          <form:errors path="course" cssClass="error"/>--%>
<%--        </td>--%>
<%--      </tr>--%>
      <tr>
        <th><form:label path="address">Address</form:label></th>
        <td>
          <form:input type="hidden" path="address.id" items="${teacher.address.id}" required="required"/>
          <form:input type="text" path="address.country" size="3" items="${teacher.address.country}" required="required"/>
          <form:input type="text" path="address.city" size="7" items="${teacher.address.city}" required="required"/>
          <form:input type="text" path="address.street" size="25" items="${teacher.address.street}" required="required"/>
        </td>
      </tr>
      <tr>
        <th><form:label path="salary">Salary</form:label></th>
        <td><form:input type="number" path="salary" size="45" value="${teacher.salary}" required="required"/>
          <form:errors path="salary" cssClass="text-warning"/></td>
      </tr>
      <c:if test="${teacher.id != 0 }">
        <tr>
          <th><form:label path="phones">Phone(s)</form:label></th>

          <td>
            <table align='center' cellspacing=2 cellpadding=5 id="data_table" border=1>
              <thead>
              <tr>
                <th>ID</th>
                <th>Phone Type</th>
                <th>Phone</th>
                <th>Action</th>
              </tr>
              </thead>
              <tbody id="tbl">
              <c:if test="${teacher.phones.size() > 0}">
                <c:forEach items="${teacher.phones}" var="phone" varStatus="loop">
                  <tr>
                    <td class='sno'>${loop.index+1}</td>
                    <td>
                      <select path="phones" name="phones[${loop.index}].phoneType.id">
                        <c:forEach items="${phoneTypes}" var="phoneType">
                          <option value="${phoneType.id}" ${phoneType.id == phone.phoneType.id ? 'selected' : ''}>${phoneType.name}</option>
                        </c:forEach>
                      </select></td>
                    <td><input type="hidden" name="phones[${loop.index}].id" value="${phone.id}"/>
                      <input type="text" name="phones[${loop.index}].value" value="${phone.value}"/>
                        <%--                      <form:input type="text" path="phones" name="phones[${loop.index}].value" value="${phone.value}"/>--%>
                    </td>
                    <td>
                      <input type="button" class='btn btn-success btn-xs' onclick="add_row();" value="Add">
                      <input type="button" class='btn btn-danger btn-xs' onclick="remove_row(this);" value="Del">
                    </td>
                  </tr>
                </c:forEach>
              </c:if>
              <c:if test="${teacher.phones.size() < 1}">
              <tr>
                <td class='sno'>1</td>
                <td>
                  <select path="phones" name="phones[0].phoneType.id">
                    <c:forEach items="${phoneTypes}" var="phoneType">
                      <option value="${phoneType.id}" ${phoneType.id == phone.phoneType.id ? 'selected' : ''}>${phoneType.name}</option>
                    </c:forEach>
                  </select>
                </td>
                <td><input type="hidden" name="phones[0].id" value=""/>
                  <input type="text" name="phones[0].value" value=""/></td>
                <td>
                  <input type="button" class='btn btn-success btn-xs' onclick="add_row();" value="Add">
                  <input type="button" class='btn btn-danger btn-xs' onclick="remove_row(this);" value="Del">
                </td>
              </tr>
              </tbody>
              </c:if>
            </table>
          </td>
        </tr>
      </c:if>
      <tr>
        <td colspan="2" align="center">
            <%--          <a href="/save"> <button class="btn btn-primary">Save</button></a>--%>
          <a href="/saveTeacher"><input type="submit" class="btn btn-primary" value="Submit"/></a>
          <input type="button" class="btn btn-primary" onclick="window.close()" value="Cancel"/>
        </td>
      </tr>
    </table>
  </form:form>
  <c:if test="${teacher.id != 0 }">
    <form id="form_image" enctype="multipart/form-data" action="/save/teacherImage/${teacher.id}" method="post">
      <label>Picture</label>
      <img id="picture" src="data:image/png;base64,${teacher.picture}" width="100" height="100"/>
      <input type="file" name="picture" onchange="document.getElementById('picture').src = window.URL.createObjectURL(this.files[0])">
      <br>
      <a><input type="submit" value="Save Image"></a>
    </form>

    <script>
      window.globalCounter = 3;

      function add_row() {
        var sno = document.querySelectorAll(".sno").length;
        sno++;
        if(sno < 4){
          var tr = document.createElement("tr");
          tr.innerHTML =
              "<td class='sno'>" + sno + "</td><td><select path='phones' name='phones[" + window.globalCounter + "].phoneType.id'>" +
              "<option value='2' selected>Home</option><option value='1'>Mobile</option></select></td>" +
              "<td><input type='hidden' name='phones[" + window.globalCounter + "].id' value=''/>" +
              "<input type='text' name='phones[" + window.globalCounter + "].value' value=''/></td>" +
              "<td><input type='button' value='ADD' onclick='add_row()' class='btn btn-success btn-xs'><input type='button' value='DEL' onclick='remove_row(this)' " +
              "class='btn btn-danger btn-xs'></td>";
          document.getElementById("tbl").appendChild(tr);
          window.globalCounter++;
        } else {
          return alert("You have reached the maximum phone!");
        }
      }

      function remove_row(e) {
        var n = document.querySelector("#tbl").querySelectorAll("tr").length;
        if(n > 1 && confirm("Are You Sure") == true){
          var ele = e.parentNode.parentNode;
          ele.remove();
          serial_no();
        }
      }

      function serial_no() {
        var cls = document.querySelectorAll(".sno");
        for(var i = 0; i < cls.length; i++){
          cls[i].innerHTML = i + 1;
        }
      }
    </script>
  </c:if>
</div>
<script>
  function encodeImageFileAsURL(picture) {
    var file = picture.files[0];
    var reader = new FileReader();
    reader.onloadend = function() {
      console.log('RESULT', reader.result)
    }
    reader.readAsDataURL(file);
  }

  var loadFile = function(event) {
    var picture = document.getElementById('picture');
    picture.src = URL.createObjectURL(event.target.files[0]);
    picture.onload = function() {
      URL.revokeObjectURL(picture.src) // free memory
    }
  };

  function redirect() {
    var url = "/teachers";
    window.location(url);
  }

  window.onunload = refreshParent;

  function refreshParent() {
    window.opener.location.reload();
  }

  function close_window() {
    self.close();
  }
</script>
</body>
</html>
