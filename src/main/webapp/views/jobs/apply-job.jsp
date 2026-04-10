<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MiCareer - Ứng tuyển</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/apply-job.css">
</head>
<body>
<jsp:include page="/views/common/header.jsp"/>

<main class="container apply-page">
    <section class="apply-info">
        <h1>Chi tiết ứng tuyển</h1>
        <c:if test="${not empty job}">
            <div class="info-card job-card">
                <h2>Thông tin công việc</h2>
                <p class="job-title">${job.title}</p>
                <div class="detail-meta">
                    <span class="category">${job.catName}</span>
                    <span class="level">${job.levelName}</span>
                    <span class="work-loc">${job.workLoc}</span>
                    <span class="work-mode">${job.workMode}</span>
                </div>
                <p class="salary">Mức lương: ${job.salaryRangeText}</p>

                <h3>Mô tả công việc</h3>
                <p class="apply-description">${job.desc}</p>

                <h3>Kỹ năng yêu cầu</h3>
                <div class="skill-list">
                    <c:forEach items="${job.skills}" var="skill">
                        <span>${skill}</span>
                    </c:forEach>
                </div>
            </div>

            <div class="info-card company-card">
                <h2>Thông tin công ty</h2>
                <p class="company">${job.compName}</p>
                <div class="company-info">
                    <p><strong>Tên công ty:</strong> ${job.compName}</p>
                    <p><strong>Website:</strong> ${job.webUrl}</p>
                </div>
            </div>
        </c:if>
        <c:if test="${empty job}">
            <p class="apply-description">Không tìm thấy thông tin jobPosting.</p>
        </c:if>
    </section>

    <section class="apply-form-wrap">
        <h2>Form ứng tuyển</h2>
        <c:if test="${not empty error}">
            <p class="form-msg error">${error}</p>
        </c:if>
        <c:if test="${not empty success}">
            <p class="form-msg success">${success}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/apply-job" method="post" enctype="multipart/form-data" class="apply-form">
            <input type="hidden" name="jobPostId" value="${param.jobPostId != null ? param.jobPostId : job.jobPostId}">
            <input type="hidden" name="candidateId" value="${candidateId}">

            <p class="form-msg">Ứng tuyển bằng tài khoản candidate #${candidateId}</p>

            <label for="coverLetter">Cover Letter</label>
            <textarea id="coverLetter" name="coverLetter" rows="8" placeholder="Giới thiệu ngắn gọn về bản thân và lý do ứng tuyển"></textarea>

            <label for="cvFile">Upload CV (PDF/DOC/DOCX)</label>
            <input id="cvFile" name="cvFile" type="file" accept=".pdf,.doc,.docx,image/*" required>

            <button type="submit">Gửi hồ sơ</button>
        </form>
    </section>
</main>

<jsp:include page="/views/common/footer.jsp"/>
</body>
</html>