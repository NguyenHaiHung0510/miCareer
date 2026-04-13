<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header class="header">
    <div class="container nav">
        <a class="card-link" href = "${pageContext.request.contextPath}/home"><div class="logo">Mi<span>Career</span></div></a>

        <nav class="menu">
            <a href="${pageContext.request.contextPath}/home">Trang chủ</a>
            <a href="${pageContext.request.contextPath}/job-list">Việc làm</a>
            <a href="#">Tạo CV</a>
            <a href="#">Công ty</a>
            <a href="#">Giới thiệu</a>
            <a href="#">Liên hệ</a>
        </nav>

        <div class="header-right">
            <div class="auth">
                <c:choose>
                    <c:when test="${empty sessionScope.user}">
                        <a class="login-link" href="${pageContext.request.contextPath}/login.jsp">Đăng nhập</a>
                        <a class="btn-primary register-link" href="${pageContext.request.contextPath}/register.jsp">Đăng ký</a>
                    </c:when>
                    <c:otherwise>
                        <span class="user-chip">Xin chào, ${sessionScope.user.fName}</span>
                        <form action="${pageContext.request.contextPath}/logout" method="post">
                            <button type="submit" class="logout-btn">Đăng xuất</button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="recruiter-box">
                <span>Bạn là nhà tuyển dụng?</span>
                <a href="#">Đăng tuyển ngay »</a>
            </div>
        </div>
    </div>
</header>