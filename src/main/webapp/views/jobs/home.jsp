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
                <article class="job-item">
                    <h3><a href="${pageContext.request.contextPath}/job-detail?id=${job.jobPostId}">${job.title}</a></h3>
                    <p class="company">${job.compName}</p>
                    <div class="chips">
                        <span class="category">${job.categoryName}</span>
                        <span class="level">${job.levelName}</span>
                        <span class="work-loc">${job.workLoc}</span>
                        <span class="work-mode">${job.workMode}</span>
                    </div>
                    <p class="salary">${job.salaryRangeText}</p>
                    <div class="job-actions">
                        <a href="${pageContext.request.contextPath}/job-detail?id=${job.jobPostId}">Xem chi tiết</a>
                        <a class="primary" href="${pageContext.request.contextPath}/apply-job?jobPostId=${job.jobPostId}">Ứng tuyển</a>
                    </div>
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