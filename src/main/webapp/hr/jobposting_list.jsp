<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="vn.com.micareer.util.SalaryFormatUtil" %>

<html>
    <head>
        <title>Danh sách Job</title>

        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                padding: 8px 12px;
                border: 1px solid #ccc;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            a {
                text-decoration: none;
                color: blue;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>

        <h2>Danh sách công việc</h2>

        <a href="jobposting?action=edit">+ Tạo công việc mới</a>
        <br/><br/>

        <table>
            <tr>
                <th>Tiêu đề</th>
                <th>Trạng thái</th>
                <th>Mức lương</th>
                <th>Địa điểm làm việc</th>
                <th>Hình thức làm việc</th>
                <th>Hạn tuyển</th>
                <th>Thao tác</th>
            </tr>

            <c:forEach var="job" items="${jobs}">
                <tr>

                    <td>${job.title}</td>

                    <td>
                        <form action="jobposting" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="updateStatus">
                            <input type="hidden" name="jobPostId" value="${job.jobPostId}">

                            <select name="stat" onchange="this.form.submit()">
                                <option value="DRAFT" ${job.stat == 'DRAFT' ? 'selected' : ''}>Nháp</option>
                                <option value="PUBLISHED" ${job.stat == 'PUBLISHED' ? 'selected' : ''}>Đang tuyển</option>
                                <option value="CLOSED" ${job.stat == 'CLOSED' ? 'selected' : ''}>Đã đóng</option>
                                <option value="EXPIRED" ${job.stat == 'EXPIRED' ? 'selected' : ''}>Hết hạn</option>
                            </select>
                        </form>
                    </td>

                    <!-- ✅ FORMAT LƯƠNG -->
                        <td><%= SalaryFormatUtil.formatRange(((vn.com.micareer.model.JobPosting) pageContext.getAttribute("job")).getMinSalary(),
                    ((vn.com.micareer.model.JobPosting) pageContext.getAttribute("job")).getMaxSalary())%></td>

                    <td>${job.workLoc}</td>
                    <td>${job.workMode}</td>
                    <td>${job.expAt}</td>

                    <td>
                        <a href="jobposting?action=view&id=${job.jobPostId}">Xem</a> |
                        <a href="jobposting?action=edit&id=${job.jobPostId}">Sửa</a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty jobs}">
                <tr>
                    <td colspan="7" style="text-align:center;">
                        Chưa có công việc nào
                    </td>
                </tr>
            </c:if>
        </table>

    </body>
</html>