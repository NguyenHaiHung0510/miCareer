<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="vn.com.micareer.model.Candidate" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
    Candidate user = (Candidate) session.getAttribute("user");
%>

<html>
<head>
    <title>Edit Profile</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/edit-profile.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>
<body>

<jsp:include page="/common/header.jsp"/>

<div class="edit-container">
    <div class="edit-card">

        <h2>✏️ Cập nhật hồ sơ</h2>

        <form action="${pageContext.request.contextPath}/candidate/editProfile" method="post" enctype="multipart/form-data">

            <!-- BASIC -->
            <div class="edit-section">
                <h3>Thông tin cơ bản</h3>

                <div class="form-group">
                    <label>First Name</label>
                    <input type="text" name="fName" value="<%= user.getfName() %>" required>
                </div>

                <div class="form-group">
                    <label>Last Name</label>
                    <input type="text" name="lName" value="<%= user.getlName() %>" required>
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" value="<%= user.getEmail() %>" required>
                </div>

                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone" value="<%= user.getPhone() %>">
                </div>
            </div>

            <!-- JOB -->
            <div class="edit-section">
                <h3>Thông tin nghề nghiệp</h3>

                <div class="form-group">
                    <label>Bio</label>
                    <textarea name="bio"><%= user.getBio() != null ? user.getBio() : "" %></textarea>
                </div>

                <c:choose>
                    <c:when test="${not empty user.cvUrl}">
                        <p>CV hiện tại: <a href="${user.cvUrl}" target="_blank">Xem CV</a></p>

                        <div class="form-group">
                            <label>Thay CV mới</label>
                            <input type="file" name="file">
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="form-group">
                            <label>Tải lên CV</label>
                            <input type="file" name="file">
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="form-group">
                    <label>Date of Birth</label>
                    <input type="date" name="dob"
                    value="<%= user.getDob() != null ? user.getDob() : "" %>"><br><br>
                </div>

                <div class="form-group">
                    <label>Experience Years</label>
                    <input type="number" step="0.1" name="expYears"
                        value="<%= user.getExpYears() != null ? user.getExpYears() : "" %>">
                </div>
            </div>

            <!-- ACTION -->
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Update</button>
                <a href="./profile" class="btn btn-secondary">⬅ Quay lại</a>
            </div>

        </form>

        <p class="msg-error">${error}</p>
        <p class="msg-success">${message}</p>

    </div>
</div>

<jsp:include page="/common/footer.jsp"/>

</body>
</html>