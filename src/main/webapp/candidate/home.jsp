<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="vn.com.micareer.model.User" %>

<%
    User user = (User) session.getAttribute("user");
%>

<html>
<head>
    <title>Candidate Home</title>
</head>
<body>

<h2>Welcome Candidate 👋</h2>

<p>
    Xin chào: <b><%= user.getfName() + " " + user.getlName() %></b>
</p>



<hr>

<h3>Candidate Features</h3>

<ul>
    <li><a href="profile.jsp">Xem hồ sơ cá nhân</a></li>
    <li><a href="createCV.jsp">Tạo CV</a></li>
    <li><a href="#">Tìm việc</a></li>
    <li><a href="#">Ứng tuyển</a></li>
</ul>

<hr>

<!-- Logout -->
<form action="../logout" method="post">
    <button type="submit">Logout</button>
</form>

</body>
</html>
