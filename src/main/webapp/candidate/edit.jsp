<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="vn.com.micareer.model.Candidate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Candidate user = (Candidate) session.getAttribute("user");
%>

<html>
<head>
    <title>Edit Profile</title>
</head>
<body>

<h2>✏️ Cập nhật hồ sơ</h2>

<form action="../editProfile" method="post" enctype="multipart/form-data">

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

<c:choose>

    <c:when test="${not empty user.cvUrl}">
        <p>
            CV hiện tại: 
            <a href="${user.cvUrl}" target="_blank">Xem CV</a>
        </p>

        <label>Thay CV mới:</label>
        <input type="file" name="file"><br><br>
    </c:when>

    <c:otherwise>
        <label>Tải lên CV:</label>
        <input type="file" name="file" required><br><br>
    </c:otherwise>

</c:choose>
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