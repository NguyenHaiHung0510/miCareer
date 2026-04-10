<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MiCareer - Trang chủ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
</head>
<body>
<jsp:include page="/views/common/header.jsp"/>

<main class="home-page container">
    <section class="hero-box">
        <h1>Cơ hội việc làm mới mỗi ngày</h1>
        <p>Tìm đúng công việc, đúng kỹ năng, đúng mục tiêu nghề nghiệp của bạn.</p>
        <a class="hero-cta" href="${pageContext.request.contextPath}/job-list">Khám phá việc làm</a>
    </section>

    <section class="job-section">
        <div class="section-title-row">
            <h2>Tin tuyển dụng nổi bật</h2>
            <a href="${pageContext.request.contextPath}/job-list">Xem tất cả</a>
        </div>

        <c:if test="${not empty error}">
            <p class="form-msg error">${error}</p>
        </c:if>

        <div class="job-grid">
            <c:forEach items="${jobs}" var="job">
                <article class="job-card">
                    <h3>
                        <a href="${pageContext.request.contextPath}/job-detail?id=${job.jobPostId}">${job.title}</a>
                    </h3>
                    <p class="company">${job.compName}</p>
                    <div class="meta-row">
                        <span>${job.categoryName}</span>
                        <span>${job.levelName}</span>
                    </div>
                    <div class="meta-row">
                        <span>${job.workLoc}</span>
                        <span>${job.workMode}</span>
                    </div>
                    <p class="salary">${job.minSalary} - ${job.maxSalary} VND</p>
                    <a class="apply-btn" href="${pageContext.request.contextPath}/apply-job?jobPostId=${job.jobPostId}">Ứng tuyển ngay</a>
                </article>
            </c:forEach>
        </div>

        <c:if test="${empty jobs}">
            <p class="empty-state">Chưa có tin tuyển dụng phù hợp.</p>
        </c:if>
    </section>
</main>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>