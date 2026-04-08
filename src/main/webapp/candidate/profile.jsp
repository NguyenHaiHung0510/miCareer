<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="vn.com.micareer.model.Candidate" %>

<%
    Candidate user = (Candidate) session.getAttribute("user");

    if (user == null || !"candidate".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Candidate Profile</title>
</head>
<body>

<h2>Hồ sơ cá nhân 👤</h2>

<hr>

<h3>Thông tin cơ bản</h3>

<p><b>Họ tên:</b> <%= user.getfName() + " " + user.getlName() %></p>
<p><b>Username:</b> <%= user.getUserName() %></p>
<p><b>Email:</b> <%= user.getEmail() %></p>
<p><b>Phone:</b> <%= user.getPhone() %></p>
<p><b>Địa chỉ:</b> <%= user.getStreet() %>, <%= user.getWard() %>, <%= user.getProvId() %></p>

<hr>

<h3>Thông tin nghề nghiệp</h3>

<p><b>Bio:</b> <%= user.getBio() != null ? user.getBio() : "Chưa có" %></p>

<p><b>CV:</b> 
    <% if (user.getCvUrl() != null && !user.getCvUrl().isEmpty()) { %>
        <a href="<%= user.getCvUrl() %>" target="_blank">Xem CV</a>
    <% } else { %>
        Chưa có
    <% } %>
</p>

<p><b>Ngày sinh:</b> 
    <%= user.getDob() != null ? user.getDob() : "Chưa có" %>
</p>

<p><b>Kinh nghiệm:</b> 
    <%= user.getExpYears() != null ? user.getExpYears() + " năm" : "Chưa có" %>
</p>

<hr>

<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>
<!-- Nút hành động -->
<ul>
    <li><a href="home.jsp">⬅ Quay lại Home</a></li>
    <li><a href="edit.jsp"> Chính sửa hồ sơ</a></li>
</ul>


<br><br>

<form action="../logout" method="post">
    <button type="submit">Logout</button>
</form>

</body>
</html>