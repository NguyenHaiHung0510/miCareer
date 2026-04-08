<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="vn.com.micareer.model.Admin" %>

<%
    Admin user = (Admin) session.getAttribute("user");

    if (user == null || !"ADMIN".equals(user.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>

<h2>Admin Dashboard 🛠️</h2>

<p>
    Xin chào Admin: 
    <b><%= user.getfName() + " " + user.getlName() %></b>
</p>

<p>Username: <%= user.getUserName() %></p>
<p>Email: <%= user.getEmail() %></p>

<hr>

<h3>Quản lý hệ thống</h3>

<ul>
    <li><a href="#">Quản lý Users</a></li>
    <li><a href="#">Quản lý Candidates</a></li>
    <li><a href="#">Quản lý HR</a></li>
    <li><a href="#">Quản lý Job</a></li>
    <li><a href="#">Thống kê</a></li>
</ul>

<hr>

<h3>Hành động nhanh</h3>

<ul>
    <li><a href="#">Tạo Admin mới</a></li>
    <li><a href="#">Khóa tài khoản</a></li>
    <li><a href="#">Xem log hệ thống</a></li>
</ul>

<hr>

<!-- Logout -->
<form action="../logout" method="post">
    <button type="submit">Logout</button>
</form>

</body>
</html>