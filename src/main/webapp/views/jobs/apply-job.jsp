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
        <h1>Form ứng tuyển</h1>
        <c:if test="${not empty job}">
            <p class="job-title">${job.title}</p>
            <p>${job.compName} • ${job.workLoc} • ${job.workMode}</p>
        </c:if>
    </section>

    <section class="apply-form-wrap">
        <c:if test="${not empty error}">
            <p class="form-msg error">${error}</p>
        </c:if>
        <c:if test="${not empty success}">
            <p class="form-msg success">${success}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/apply-job" method="post" enctype="multipart/form-data" class="apply-form">
            <input type="hidden" name="jobPostId" value="${param.jobPostId != null ? param.jobPostId : job.jobPostId}">

            <label for="candidateId">Candidate ID</label>
            <input id="candidateId" name="candidateId" type="number" min="1" required placeholder="Nhập candidateId của bạn">

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