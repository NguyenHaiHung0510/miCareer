<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Danh sách ứng viên</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/hr/my-jobs">⬅ Quay lại danh sách Job</a>
    
    <h2>Danh sách ứng viên cho vị trí: ${jobDetail != null ? jobDetail.title : 'N/A'}</h2>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>Mã Hồ Sơ</th>
                <th>Mã Ứng Viên</th>
                <th>Trạng Thái</th>
                <th>Ngày Nộp</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty applications}">
                    <tr>
                        <td colspan="5">Chưa có ứng viên nào nộp hồ sơ vào vị trí này.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="app" items="${applications}">
                        <tr>
                            <td>${app.jobAppId}</td>
                            <td>${app.candidateId}</td>
                            <td>
                                <strong>${app.stat}</strong>
                            </td>
                            <td>${app.appliedAt}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/hr/application-detail?jobAppId=${app.jobAppId}">
                                    Chi tiết & Cập nhật
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
