<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:if test="${empty sessionScope.user || sessionScope.user.role != 'ADMIN'}">
    <c:redirect url="/login"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
</head>
<body>

<!-- HEADER -->
<jsp:include page="/views/common/header.jsp"/>

<div class="admin-container">

    <h2>Admin Dashboard 🛠️</h2>

    <div class="admin-info">
        <p>Xin chào Admin: 
            <b>${sessionScope.user.fName} ${sessionScope.user.lName}</b>
        </p>
        <p>Username: ${sessionScope.user.userName}</p>
        <p>Email: ${sessionScope.user.email}</p>
    </div>

    <!-- QUẢN LÝ -->
    <div class="admin-section">
        <h3>Quản lý hệ thống</h3>
        <div class="card-grid">
            <a href="#" class="card">Users</a>
            <a href="#" class="card">Candidates</a>
            <a href="#" class="card">HR</a>
            <a href="#" class="card">Jobs</a>
            <a href="#" class="card">Thống kê</a>
        </div>
    </div>

    <!-- ACTION -->
    <div class="admin-section">
        <h3>Hành động nhanh</h3>
        <div class="card-grid">
            <a href="#" class="card">Tạo Admin</a>
            <a href="#" class="card">Khóa tài khoản</a>
            <a href="#" class="card">Logs</a>
        </div>
    </div>

</div>

<!-- FOOTER -->
<jsp:include page="/views/common/footer.jsp"/>

</body>
</html>