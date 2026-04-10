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
<jsp:include page="/views/common/header.jsp"/>

<main class="container detail-page">
    <c:if test="${not empty error}">
        <p class="form-msg error">${error}</p>
    </c:if>

    <c:if test="${not empty job}">
        <section class="detail-hero">
            <h1>${job.title}</h1>
            <p class="company">${job.compName}</p>
            <div class="detail-meta">
                <span>${job.catName}</span>
                <span>${job.levelName}</span>
                <span>${job.workLoc}</span>
                <span>${job.workMode}</span>
            </div>
            <p class="salary">Mức lương: ${job.minSalary} - ${job.maxSalary} VND</p>
            <a class="apply-btn" href="${pageContext.request.contextPath}/apply-job?jobPostId=${job.jobPostId}">Ứng tuyển công việc này</a>
        </section>

        <section class="detail-body">
            <h2>Mô tả công việc</h2>
            <p>${job.desc}</p>

            <h3>Kỹ năng yêu cầu</h3>
            <div class="skill-list">
                <c:forEach items="${job.skills}" var="skill">
                    <span>${skill}</span>
                </c:forEach>
            </div>

            <h3>Thông tin công ty</h3>
            <p>Tên công ty: ${job.compName}</p>
            <p>Website: ${job.webUrl}</p>
        </section>
    </c:if>
</main>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>