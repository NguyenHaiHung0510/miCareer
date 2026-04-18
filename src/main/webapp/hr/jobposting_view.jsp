<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="vn.com.micareer.util.SalaryFormatUtil" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Chi tiết công việc</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jobposting-view.css">
    </head>

    <body>

        <jsp:include page="/common/header.jsp"/>

        <main class="container">

            <!-- HEADER -->
            <div class="page-header">
                <h2>Chi tiết công việc</h2>

                <a class="btn-secondary" href="jobposting?action=list">
                    ← Quay lại
                </a>
            </div>

            <!-- CARD -->
            <div class="detail-card">

                <div class="detail-title">
                    ${job.title}
                </div>

                <div class="detail-desc">
                    ${job.desc}
                </div>

                <div class="detail-grid">

                    <div class="info">

                        <p><span>💰 Mức lương:</span>
                            <%= SalaryFormatUtil.formatRange(
                                    ((vn.com.micareer.model.JobPosting) request.getAttribute("job")).getMinSalary(),
                                    ((vn.com.micareer.model.JobPosting) request.getAttribute("job")).getMaxSalary()
                            )%>
                        </p>

                        <p><span>📍 Địa điểm:</span> ${job.workLoc}</p>
                        <p><span>🧭 Hình thức:</span> ${job.workMode}</p>
                        <p><span>📌 Trạng thái:</span> ${job.stat}</p>
                        <p><span>⏰ Hạn tuyển:</span> ${job.expAt}</p>

                    </div>

                    <div class="info">

                        <p><span>🏢 Danh mục:</span> ${job.catName}</p>
                        <p><span>🎓 Cấp bậc:</span> ${job.levelName}</p>

                    </div>

                </div>

                <!-- SKILLS -->
                <div class="skill-section">
                    <h3>Kỹ năng yêu cầu</h3>

                    <div class="skills">
                        <c:forEach var="skill" items="${skills}">
                            <span class="tag">${skill}</span>
                        </c:forEach>
                    </div>
                </div>

                <!-- ACTION -->
                <div class="action-row">
                    <a href="jobposting?action=edit&id=${job.jobPostId}" class="btn-primary">
                        Chỉnh sửa
                    </a>
                </div>

            </div>

        </main>

        <jsp:include page="/common/footer.jsp"/>

    </body>
</html>