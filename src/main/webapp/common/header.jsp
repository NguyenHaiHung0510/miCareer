<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header class="header">
    <div class="container nav">

        <!-- LOGO -->
        <a class="card-link" href="${pageContext.request.contextPath}/home">
            <div class="logo">Mi<span>Career</span></div>
        </a>

        <!-- MENU -->
        <nav class="menu">

            <!-- ❌ Chưa login -->
            <c:if test="${empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
                <a href="${pageContext.request.contextPath}/job-list">Việc làm</a>
                <a href="#">Công ty</a>
            </c:if>

            <!-- 👤 Candidate -->
            <c:if test="${sessionScope.user != null && sessionScope.user.role == 'CANDIDATE'}">
                <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
                <a href="${pageContext.request.contextPath}/job-list">Việc làm</a>
                <a href="#">Tạo CV</a>
                <a href="#">Công ty</a>
            </c:if>

            <!-- 🧑‍💼 HR -->
            <c:if test="${sessionScope.user != null && sessionScope.user.role == 'HR'}">
                <a href="${pageContext.request.contextPath}/hr/dashboard">Dashboard</a>
                <a href="${pageContext.request.contextPath}/hr/jobs">Tin tuyển dụng</a>
                <a href="${pageContext.request.contextPath}/hr/applications">Ứng viên</a>
            </c:if>

            <!-- 👑 Admin -->
            <c:if test="${sessionScope.user != null && sessionScope.user.role == 'ADMIN'}">
                <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                <a href="${pageContext.request.contextPath}/admin/users">Users</a>
                <a href="${pageContext.request.contextPath}/admin/jobs">Jobs</a>
                <a href="${pageContext.request.contextPath}/admin/companies">Companies</a>
            </c:if>

        </nav>

        <!-- RIGHT -->
        <div class="header-right">

            <!-- AUTH -->
            <div class="auth">
                <c:choose>

                    <c:when test="${empty sessionScope.user}">
                        <a class="login-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                        <a class="btn-primary register-link" href="${pageContext.request.contextPath}/register">Đăng ký</a>
                    </c:when>

                    <c:otherwise>
                        <span class="user-chip">
                            Xin chào, ${sessionScope.user.fName}
                        </span>
                        <c:if test="${sessionScope.user.role == 'CANDIDATE'}">
                            <a class = "profile-link" href="${pageContext.request.contextPath}/candidate/profile">
                                Hồ sơ
                            </a>
                        </c:if>
                        
                        <c:if test="${sessionScope.user.role == 'HR'}">
                                <!-- FOR HR -->

                        </c:if>
                        <form action="${pageContext.request.contextPath}/logout" method="post">
                            <button type="submit" class="logout-btn">Đăng xuất</button>
                        </form>
                    </c:otherwise>

                </c:choose>
            </div>

            <!-- RECRUITER chỉ cho Candidate -->
            <c:if test="${empty sessionScope.user || sessionScope.user.role == 'CANDIDATE'}">
                <div class="recruiter-box">
                    <span>Bạn là nhà tuyển dụng?</span>
                    <a href="#">Đăng tuyển ngay »</a>
                </div>
            </c:if>

        </div>
    </div>
</header>