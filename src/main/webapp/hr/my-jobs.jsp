<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý công việc (HR) - MiCareer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/job-list.css">
    
    <style>
        /* Ghi đè grid 2 cột vì trang HR này không cần thanh filter bên trái */
        .hr-list-page {
            display: block;
            padding: 24px 0;
        }
    </style>
</head>
<body>

<jsp:include page="/common/header.jsp" />

<main class="container">
    <div class="hr-list-page">
        <div class="result-panel">
            <h2>Danh sách Job Posting đang phụ trách 📋</h2>

            <c:if test="${not empty error}">
                <div class="form-msg error">${error}</div>
            </c:if>

            <c:choose>
                <c:when test="${empty jobs}">
                    <p class="empty-state">Hiện tại bạn chưa phụ trách vị trí tuyển dụng nào.</p>
                </c:when>
                <c:otherwise>
                    <div class="job-list-grid">
                        <c:forEach var="job" items="${jobs}">
                            <div class="job-item">
                                <h3>
                                    <a href="${pageContext.request.contextPath}/hr/job-detail?jobPostId=${job.jobPostId}">
                                        ${job.title}
                                    </a>
                                </h3>
                                <div class="company">${job.compName}</div>

                                <div class="chips">
                                    <span class="category">${job.categoryName != null ? job.categoryName : 'Chưa phân loại'}</span>
                                    <span class="level">${job.levelName != null ? job.levelName : 'Tất cả cấp bậc'}</span>
                                    <span class="work-loc">${job.workLoc}</span>
                                    <span class="work-mode">${job.workMode}</span>
                                </div>

                                <div class="salary">
                                    Lương:
                                    <c:choose>
                                        <c:when test="${job.minSalary != null and job.maxSalary != null}">
                                            ${job.minSalary} - ${job.maxSalary} Triệu
                                        </c:when>
                                        <c:otherwise>Thỏa thuận</c:otherwise>
                                    </c:choose>
                                </div>

                                <div class="job-actions">
                                    <a href="${pageContext.request.contextPath}/hr/applications?jobPostId=${job.jobPostId}" class="primary">Xem ứng viên</a>
                                    
                                    <a href="${pageContext.request.contextPath}/hr/job-detail?jobPostId=${job.jobPostId}">Chi tiết Job</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</main>

<jsp:include page="/common/footer.jsp" />

</body>
</html>