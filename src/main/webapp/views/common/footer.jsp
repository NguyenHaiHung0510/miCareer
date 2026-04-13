
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<footer class="footer">
    <div class="container">
        <p>Contact: 0969696969</p>
        <p>© 2026 MiCareer. All rights reserved.</p>
        <div class="footer-auth">
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/login.jsp">Đăng nhập</a>
                    <a href="${pageContext.request.contextPath}/register.jsp">Đăng ký</a>
                </c:when>
                <c:otherwise>
                    <span>Đang đăng nhập: ${sessionScope.user.userName}</span>
                    <form action="${pageContext.request.contextPath}/logout" method="post">
                        <button type="submit" class="logout-btn">Đăng xuất</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</footer>