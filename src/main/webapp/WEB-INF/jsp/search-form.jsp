<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<style>
  fieldset {
    background-color: #eeeeee;
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
    width: 100px;
  }

  .column {
    float: left;
    width: 50%;
  }
</style>
<body>
<form:form action="/students/search" method="POST" modelAttribute="studentSearchForm">
  <fieldset>
    <legend>Search form</legend>
    <div class="column">
      <form:label path="fullName">Name : </form:label>
      <form:input path="fullName" value="${studentSearchForm.fullName}"/><br>
      <form:label path="fullAddress">Address : </form:label>
      <form:input path="fullAddress" value="${studentSearchForm.fullAddress}"/><br>
      <form:label path="gender">Gender : </form:label>
      <form:radiobutton path="gender" value="M" checked="${studentSearchForm.gender == 'M'.charAt(0) ? 'checked' : ''}"/>
      <form:label path="gender" for="male" >Male</form:label>
      <form:radiobutton path="gender" value="F" checked="${studentSearchForm.gender == 'F'.charAt(0) ? 'checked' : ''}"/>
      <form:label path="gender" for="female" >Female</form:label>
      <form:radiobutton path="gender" value="A" checked="${studentSearchForm.gender == 'A'.charAt(0) ? 'checked' : ''}" size="15"/>
      <form:label path="gender" for="A" style="">All</form:label>
    </div>
    <div class="column">
      <label>Date of birth : </label>
      <form:input path="startDate" type="date" value="${studentSearchForm.startDate}"/>
      <form:input path="endDate" type="date" value="${studentSearchForm.endDate}"/><br>
      <form:label path="group">Group : </form:label>
      <form:select path="group.id">
      <form:option value="0">NONE</form:option>
        <form:options items="${groups}"/>
      </form:select><br>
      <a href="/students"><input type="reset" name="restore" value="Reset" onclick="window.location.href='/students'" style="float: right;"/></a>
      <a><input type="submit" name="search" value="Search" style="float: right;"/></a>
    </div>
  </fieldset>
</form:form>
</body>
</html>
