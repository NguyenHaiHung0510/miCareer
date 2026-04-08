<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register Account</h2>

<form action="register" method="post">

    Username: 
    <input type="text" name="userName" required><br><br>

    Password: 
    <input type="password" name="pwd" required><br><br>

    Confirm Password: 
    <input type="password" name="confirmPwd" required><br><br>

    First Name: 
    <input type="text" name="fName" required><br><br>

    Last Name: 
    <input type="text" name="lName" required><br><br>

    Email: 
    <input type="email" name="email" required><br><br>

    Phone: 
    <input type="text" name="phone"><br><br>


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

    </select><br><br> 
    Ward: 
    <input type="text" name="ward"><br><br>

    Street: 
    <input type="text" name="street"><br><br>

    Role:
    <select name="role" id="role" onchange="toggleFields()" required>
        <option value="candidate">Candidate</option>
        <option value="hr">HR</option>
    </select>
    <br><br>


    <div id="hrFields" style="display:none;">

        Company:
        <input type="text" name="company"><br><br>

        HR Position:
        <select name="hrPosition">
            <option value="HR_MANAGER">Quản lí HR</option>
            <option value="HR_STAFF">Nhân viên HR</option>
        </select>
        <br><br>

    </div>
    
    <div id="candidateFields" style="display:none;">

        Bio:
        <textarea name="bio"></textarea><br><br>

        CV URL:
        <input type="text" name="cvUrl"><br><br>

        Date of Birth:
        <input type="date" name="dob"><br><br>

        Experience Years:
        <input type="number" step="0.1" name="expYears"><br><br>

    </div>
    <button type="submit">Register</button>
</form>

<p style="color:red">${error}</p>
<p style="color:green">${message}</p>

<a href="login.jsp">Back to Login</a>

<script>
function toggleFields() {
    var role = document.getElementById("role").value;

    var hrFields = document.getElementById("hrFields");
    var candidateFields = document.getElementById("candidateFields");

    if (role === "hr") {
        hrFields.style.display = "block";
        candidateFields.style.display = "none";
    } else if (role === "candidate") {
        hrFields.style.display = "none";
        candidateFields.style.display = "block";
    } else {
        hrFields.style.display = "none";
        candidateFields.style.display = "none";
    }
}

window.onload = toggleFields;
</script>
</body>
</html>