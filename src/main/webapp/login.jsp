<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    
    <!-- CSS riêng cho form (nếu có) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
    <!-- CSS chung -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>

<body>

<!-- HEADER -->
<jsp:include page="/common/header.jsp"/>

<main class="container" style="display:flex; justify-content:center; align-items:center; min-height:70vh;">

    <div class="form-container">
        <h2>Login</h2>

        <form action="login" method="post">
            <input type="hidden" name="redirect" value="${param.redirect}">

            <div class="form-group">
                <label>Username</label>
                <input type="text" name="username" required>
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" required>
            </div>

            <button class="btn" type="submit">Login</button>
        </form>

        <p class="msg-error">${error}</p>
        <c:if test="${param.success == '1'}">
            <p class="msg-success">Đăng kí thành công</p>
        </c:if>
        <a class="form-link" href="register">Register</a>
    </div>

</main>

<!-- FOOTER -->
<jsp:include page="/common/footer.jsp"/>

</body>
</html>