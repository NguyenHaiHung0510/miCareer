<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MiCareer - Danh sách việc làm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/job-list.css">
</head>
<body>
<jsp:include page="/views/common/header.jsp"/>

<main class="container list-page">
    <section class="filter-panel">
        <h2>Bộ lọc nâng cao</h2>
        <form action="${pageContext.request.contextPath}/job-list" method="get" class="filter-form">
            <input type="text" name="keyword" placeholder="Từ khóa (tiêu đề, công ty...)" value="${criteria.keyword}">

            <select name="location">
                <option value="">Tất cả địa điểm</option>
                <c:forEach items="${locations}" var="loc">
                    <option value="${loc}" ${criteria.location == loc ? 'selected' : ''}>${loc}</option>
                </c:forEach>
            </select>

            <select name="catId">
                <option value="">Tất cả lĩnh vực</option>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.id}" ${criteria.catId == cat.id ? 'selected' : ''}>${cat.name}</option>
                </c:forEach>
            </select>

            <select name="levelId">
                <option value="">Tất cả cấp độ</option>
                <c:forEach items="${levels}" var="lv">
                    <option value="${lv.id}" ${criteria.levelId == lv.id ? 'selected' : ''}>${lv.name}</option>
                </c:forEach>
            </select>

            <select name="skillId">
                <option value="">Tất cả kỹ năng</option>
                <c:forEach items="${skills}" var="sk">
                    <option value="${sk.id}" ${criteria.skillId == sk.id ? 'selected' : ''}>${sk.name}</option>
                </c:forEach>
            </select>

            <select name="workMode">
                <option value="">Tất cả hình thức</option>
                <option value="ONSITE" ${criteria.workMode == 'ONSITE' ? 'selected' : ''}>Onsite</option>
                <option value="HYBRID" ${criteria.workMode == 'HYBRID' ? 'selected' : ''}>Hybrid</option>
                <option value="REMOTE" ${criteria.workMode == 'REMOTE' ? 'selected' : ''}>Remote</option>
            </select>

            <div class="action-row">
                <button type="submit">Lọc</button>
                <a href="${pageContext.request.contextPath}/job-list">Xóa lọc</a>
            </div>
        </form>
    </section>

    <section class="result-panel">
        <h2>Danh sách tin tuyển dụng</h2>
        <c:if test="${not empty error}">
            <p class="form-msg error">${error}</p>
        </c:if>

        <c:if test="${empty jobs}">
            <p class="empty-state">Không có tin tuyển dụng phù hợp với bộ lọc hiện tại.</p>
        </c:if>

        <div class="job-list-grid">
            <c:forEach items="${jobs}" var="job">
                <article class="job-item">
                    <h3><a href="${pageContext.request.contextPath}/job-detail?id=${job.jobPostId}">${job.title}</a></h3>
                    <p class="company">${job.compName}</p>
                    <div class="chips">
                        <span>${job.categoryName}</span>
                        <span>${job.levelName}</span>
                        <span>${job.workLoc}</span>
                        <span>${job.workMode}</span>
                    </div>
                    <p class="salary">${job.minSalary} - ${job.maxSalary} VND</p>
                    <div class="job-actions">
                        <a href="${pageContext.request.contextPath}/job-detail?id=${job.jobPostId}">Xem chi tiết</a>
                        <a class="primary" href="${pageContext.request.contextPath}/apply-job?jobPostId=${job.jobPostId}">Ứng tuyển</a>
                    </div>
                </article>
            </c:forEach>
        </div>
    </section>
</main>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>