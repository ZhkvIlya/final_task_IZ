<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>

<body>

<center>

<h2>Users</h2>

<c:forEach items = "$(userList)" var = "user">
	${user.user_id} ${user.firstname} ${user.lastname} ${user.age}

<form:form method="post">

</form:select>
<br />

</c:forEach>
</body>

</html>