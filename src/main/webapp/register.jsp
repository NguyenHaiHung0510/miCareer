<%@ page contentType="text/html;charset=UTF-8" %>
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

    Province:
    <select name="provId" required>
        <option value="">-- Chọn tỉnh/thành --</option>

        <option value="HN">Thành phố Hà Nội</option>
        <option value="HU">Thành phố Huế</option>
        <option value="LC">Tỉnh Lai Châu</option>
        <option value="DB">Tỉnh Điện Biên</option>
        <option value="SL">Tỉnh Sơn La</option>
        <option value="LS">Tỉnh Lạng Sơn</option>
        <option value="QN">Tỉnh Quảng Ninh</option>
        <option value="TH">Tỉnh Thanh Hóa</option>
        <option value="NA">Tỉnh Nghệ An</option>
        <option value="HT">Tỉnh Hà Tĩnh</option>
        <option value="CB">Tỉnh Cao Bằng</option>

        <option value="TQ">Tỉnh Tuyên Quang</option>
        <option value="LCA">Tỉnh Lào Cai</option>
        <option value="TN">Tỉnh Thái Nguyên</option>
        <option value="PT">Tỉnh Phú Thọ</option>
        <option value="BN">Tỉnh Bắc Ninh</option>
        <option value="HY">Tỉnh Hưng Yên</option>
        <option value="HP">Thành phố Hải Phòng</option>
        <option value="NB">Tỉnh Ninh Bình</option>
        <option value="QT">Tỉnh Quảng Trị</option>
        <option value="DN">Thành phố Đà Nẵng</option>
        <option value="QNG">Tỉnh Quảng Ngãi</option>
        <option value="GL">Tỉnh Gia Lai</option>
        <option value="KH">Tỉnh Khánh Hòa</option>
        <option value="LD">Tỉnh Lâm Đồng</option>
        <option value="DL">Tỉnh Đắk Lắk</option>
        <option value="HCM">TP. Hồ Chí Minh</option>
        <option value="DNI">Tỉnh Đồng Nai</option>
        <option value="TNIN">Tỉnh Tây Ninh</option>
        <option value="CT">Thành phố Cần Thơ</option>
        <option value="VL">Tỉnh Vĩnh Long</option>
        <option value="DT">Tỉnh Đồng Tháp</option>
        <option value="CM">Tỉnh Cà Mau</option>
        <option value="AG">Tỉnh An Giang</option>

    </select>
    <br><br>

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
            <option value="manager">Quản lí HR</option>
            <option value="staff">Nhân viên HR</option>
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