<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Quản lý tin tuyển dụng</title>
</head>
<body>
    <h2>Danh sách tin tuyển dụng tôi phụ trách</h2>
    
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>Mã Job</th>
                <th>Tiêu đề</th>
                <th>Cấp bậc</th>
                <th>Nơi làm việc</th>
                <th>Ngày tạo</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty jobs}">
                    <tr>
                        <td colspan="6">Hiện chưa có bài đăng nào.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="job" items="${jobs}">
                        <tr>
                            <td>${job.jobPostId}</td>
                            <td>${job.title}</td>
                            <td>${job.levelName}</td>
                            <td>${job.workLoc}</td>
                            <td>${job.createdAt}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/hr/applications?jobPostId=${job.jobPostId}">
                                    Xem ứng viên
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</body>
</html>
