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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/profile.css">
</head>
<body>

    
<jsp:include page="/common/header.jsp"/>
<div class="profile-container">

    <h2>Hồ sơ cá nhân 👤</h2>

    <div class="profile-card">
        <h3>Thông tin cơ bản</h3>

        <p><b>Họ tên:</b> <%= user.getfName() + " " + user.getlName() %></p>
        <p><b>Username:</b> <%= user.getUserName() %></p>
        <p><b>Email:</b> <%= user.getEmail() %></p>
        <p><b>Phone:</b> <%= user.getPhone() %></p>
        <p><b>Địa chỉ:</b> <%= user.getStreet() %>, <%= user.getWard() %>, <%= user.getProvId() %></p>
    </div>

    <div class="profile-card">
        <h3>Thông tin nghề nghiệp</h3>

        <p><b>Bio:</b> <%= user.getBio() != null ? user.getBio() : "Chưa có" %></p>

        <p><b>CV:</b> 
            <% if (user.getCvUrl() != null && !user.getCvUrl().isEmpty()) { %>
                <a href="<%= user.getCvUrl() %>" target="_blank">Xem CV</a>
            <% } else { %>
                Chưa có
            <% } %>
        </p>

        <p><b>Ngày sinh:</b> <%= user.getDob() != null ? user.getDob() : "Chưa có" %></p>

        <p><b>Kinh nghiệm:</b> 
            <%= user.getExpYears() != null ? user.getExpYears() + " năm" : "Chưa có" %>
        </p>
    </div>

    <!-- Message -->
    <c:if test="${not empty message}">
        <p class="msg-success">${message}</p>
    </c:if>

    <c:if test="${not empty error}">
        <p class="msg-error">${error}</p>
    </c:if>

    <!-- Actions -->
    <div class="profile-actions">
        <a href="${pageContext.request.contextPath}/home" class="btn btn-back">⬅ Quay lại</a>
        <a href="./editProfile" class="btn btn-edit">Chỉnh sửa</a>
    </div>

    <br>

    <form action="../logout" method="post">
        <button class="btn btn-logout">Đăng xuất</button>
    </form>

</div>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>