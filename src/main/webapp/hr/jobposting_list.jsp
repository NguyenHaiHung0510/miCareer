<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="vn.com.micareer.util.SalaryFormatUtil" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách Job</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jobposting_list.css">
    </head>

    <body>

        <jsp:include page="/common/header.jsp"/>

        <main class="container">

            <div class="page-header">
                <h2>Danh sách công việc</h2>

                <a class="btn-primary" href="jobposting?action=edit">
                    + Tạo công việc mới
                </a>
            </div>

            <div class="table-wrapper">

                <table class="table">

                    <thead>
                        <tr>
                            <th>Tiêu đề</th>
                            <th>Trạng thái</th>
                            <th>Mức lương</th>
                            <th>Địa điểm</th>
                            <th>Hình thức</th>
                            <th>Hạn tuyển</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>

                    <tbody>

                        <c:forEach var="job" items="${jobs}">
                            <tr>

                                <td class="title">
                                    ${job.title}
                                </td>

                                <td>
                                    <form action="jobposting" method="post" class="inline-form">
                                        <input type="hidden" name="action" value="updateStatus">
                                        <input type="hidden" name="jobPostId" value="${job.jobPostId}">

                                        <select name="stat" class="select" onchange="this.form.submit()">
                                            <option value="DRAFT" ${job.stat == 'DRAFT' ? 'selected' : ''}>Nháp</option>
                                            <option value="PUBLISHED" ${job.stat == 'PUBLISHED' ? 'selected' : ''}>Đang tuyển</option>
                                            <option value="CLOSED" ${job.stat == 'CLOSED' ? 'selected' : ''}>Đã đóng</option>
                                            <option value="EXPIRED" ${job.stat == 'EXPIRED' ? 'selected' : ''}>Hết hạn</option>
                                        </select>
                                    </form>
                                </td>

                                <td class="salary">
                                    <%= SalaryFormatUtil.formatRange(
                                            ((vn.com.micareer.model.JobPosting) pageContext.getAttribute("job")).getMinSalary(),
                                            ((vn.com.micareer.model.JobPosting) pageContext.getAttribute("job")).getMaxSalary()
                                    )%>
                                </td>

                                <td>${job.workLoc}</td>
                                <td>${job.workMode}</td>
                                <td>${job.expAt}</td>

                                <td class="action">
                                    <a href="jobposting?action=view&id=${job.jobPostId}">Xem</a>
                                    <a href="jobposting?action=edit&id=${job.jobPostId}">Sửa</a>
                                </td>

                            </tr>
                        </c:forEach>

                        <c:if test="${empty jobs}">
                            <tr>
                                <td colspan="7" class="empty">
                                    Chưa có công việc nào
                                </td>
                            </tr>
                        </c:if>

                    </tbody>
                </table>
            </div>

        </main>

        <jsp:include page="/common/footer.jsp"/>

    </body>
</html>