<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="vn.com.micareer.model.Candidate" %>

<%
    Candidate user = (Candidate) session.getAttribute("user");
%>

<html>
<head>
    <title>Edit Profile</title>
</head>
<body>

<h2>✏️ Cập nhật hồ sơ</h2>

<form action="../editProfile" method="post">

    <h3>Thông tin cơ bản</h3>

    First Name:
    <input type="text" name="fName" value="<%= user.getfName() %>" required><br><br>

    Last Name:
    <input type="text" name="lName" value="<%= user.getlName() %>" required><br><br>

    Email:
    <input type="email" name="email" value="<%= user.getEmail() %>" required><br><br>

    Phone:
    <input type="text" name="phone" value="<%= user.getPhone() %>"><br><br>

    <hr>

    <h3>Thông tin nghề nghiệp</h3>

    Bio:
    <textarea name="bio"><%= user.getBio() != null ? user.getBio() : "" %></textarea><br><br>

    CV URL:
    <input type="text" name="cvUrl" value="<%= user.getCvUrl() != null ? user.getCvUrl() : "" %>"><br><br>

    Date of Birth:
    <input type="date" name="dob"
           value="<%= user.getDob() != null ? user.getDob() : "" %>"><br><br>

    Experience Years:
    <input type="number" step="0.1" name="expYears"
           value="<%= user.getExpYears() != null ? user.getExpYears() : "" %>"><br><br>

    <hr>

    <button type="submit">Update</button>
</form>

<br>

<a href="profile.jsp">⬅ Quay lại Profile</a>

<p style="color:red">${error}</p>
<p style="color:green">${message}</p>

</body>
</html>