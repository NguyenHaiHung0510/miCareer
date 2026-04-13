<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MiCareer - Chi tiết việc làm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/job-detail.css">
</head>
<body>
<jsp:include page="/common/header.jsp"/>

<main class="container detail-page">
    <c:if test="${not empty error}">
        <p class="form-msg error">${error}</p>
    </c:if>

    <c:if test="${not empty job}">
        <section class="detail-hero">
            <h1>${job.title}</h1>
            <p class="company">${job.compName}</p>
            <div class="detail-meta">
                <span class="category">${job.catName}</span>
                <span class="level">${job.levelName}</span>
                <span class="work-loc">${job.workLoc}</span>
                <span class="work-mode">${job.workMode}</span>
                <span class="posted-at">
                    Bắt đầu:
                    <c:choose>
                        <c:when test="${not empty job.createdAtText}">${job.createdAtText}</c:when>
                        <c:otherwise>N/A</c:otherwise>
                    </c:choose>
                </span>
                <span class="closed-at">
                    Kết thúc:
                    <c:choose>
                        <c:when test="${not empty job.expAtText}">${job.expAtText}</c:when>
                        <c:otherwise>N/A</c:otherwise>
                    </c:choose>
                </span>
                <span class="application-count">Đã nộp CV: ${applicationCount} người</span>
            </div>
            <p class="salary">Mức lương: ${job.salaryRangeText}</p>
            <a class="apply-btn" href="${pageContext.request.contextPath}/apply-job?jobPostId=${job.jobPostId}">Ứng tuyển công việc này</a>
        </section>

        <section class="detail-body">
            <div class="detail-grid">
                <article class="detail-card detail-job-info">
                    <h2>Thông tin công việc</h2>
                    <h3>Mô tả công việc</h3>
                    <p>${job.desc}</p>

                    <h3>Kỹ năng yêu cầu</h3>
                    <div class="skill-list">
                        <c:forEach items="${job.skills}" var="skill">
                            <span>${skill}</span>
                        </c:forEach>
                    </div>
                </article>

                <aside class="detail-card detail-company-info">
                    <h2>Thông tin công ty</h2>
                    <p><strong>Tên công ty:</strong> ${job.compName}</p>
                    <p><strong>Website:</strong> ${job.webUrl}</p>
                </aside>
            </div>
        </section>
    </c:if>
</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>