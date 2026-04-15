<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">
</head>

<body>

<!-- HEADER -->
<jsp:include page="/common/header.jsp"/>

<main class="container" style="display:flex; justify-content:center; padding:40px 0;">

    <div class="form-container" style="max-width:600px;">
        <h2>Register Account</h2>

        <form action="register" method="post">

            <!-- Username -->
            <div class="form-group">
                <label>Username</label>
                <input type="text" name="userName" required>
            </div>

            <!-- Password -->
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="pwd" required>
            </div>

            <!-- Confirm -->
            <div class="form-group">
                <label>Confirm Password</label>
                <input type="password" name="confirmPwd" required>
            </div>

            <!-- Name -->
            <div class="form-group">
                <label>First Name</label>
                <input type="text" name="fName" required>
            </div>

            <div class="form-group">
                <label>Last Name</label>
                <input type="text" name="lName" required>
            </div>

            <!-- Email -->
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" required>
            </div>

            <!-- Phone -->
            <div class="form-group">
                <label>Phone</label>
                <input type="text" name="phone">
            </div>

            <!-- Province -->
            <div class="form-group">
                <label>Tỉnh / Thành</label>
                <select name="provId" required>
                    <option value="">-- Chọn tỉnh/thành --</option>

                    <optgroup label="Miền Bắc">
                        <c:forEach var="p" items="${north}">
                            <option value="${p.provId}">${p.provName}</option>
                        </c:forEach>
                    </optgroup>

                    <optgroup label="Miền Trung">
                        <c:forEach var="p" items="${central}">
                            <option value="${p.provId}">${p.provName}</option>
                        </c:forEach>
                    </optgroup>

                    <optgroup label="Miền Nam">
                        <c:forEach var="p" items="${south}">
                            <option value="${p.provId}">${p.provName}</option>
                        </c:forEach>
                    </optgroup>
                </select>
            </div>

            <!-- Address -->
            <div class="form-group">
                <label>Ward</label>
                <input type="text" name="ward">
            </div>

            <div class="form-group">
                <label>Street</label>
                <input type="text" name="street">
            </div>

            <!-- Role -->
            <div class="form-group">
                <label>Role</label>
                <select name="role" id="role" onchange="toggleFields()" required>
                    <option value="candidate">Candidate</option>
                    <option value="hr">HR</option>
                </select>
            </div>

            <!-- HR Fields -->
            <div id="hrFields" style="display:none;">
                <div class="form-group">
                    <label>Company</label>
                    <input type="text" name="company">
                </div>

                <div class="form-group">
                    <label>HR Position</label>
                    <select name="hrPosition">
                        <option value="HR_MANAGER">Quản lí HR</option>
                        <option value="HR_STAFF">Nhân viên HR</option>
                    </select>
                </div>
            </div>

            <!-- Candidate Fields -->
            <div id="candidateFields" style="display:none;">
                <div class="form-group">
                    <label>Date of Birth</label>
                    <input type="date" name="dob">
                </div>
            </div>

            <button class="btn" type="submit">Register</button>
        </form>

        <p class="msg-error">${error}</p>
        <p class="msg-success">${message}</p>

        <a class="form-link" href="login">Back to Login</a>
    </div>

</main>

<!-- FOOTER -->
<jsp:include page="/common/footer.jsp"/>

<script>
function toggleFields() {
    var role = document.getElementById("role").value;

    document.getElementById("hrFields").style.display = (role === "hr") ? "block" : "none";
    document.getElementById("candidateFields").style.display = (role === "candidate") ? "block" : "none";
}

window.onload = toggleFields;
</script>

</body>
</html>