<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <a class="login-link" href="#">Đăng nhập</a>
                <a class="btn-primary register-link" href="#">Đăng ký</a>
            </div>

            <div class="recruiter-box">
                <span>Bạn là nhà tuyển dụng?</span>
                <a href="#">Đăng tuyển ngay »</a>
            </div>
        </div>
    </div>
</header>