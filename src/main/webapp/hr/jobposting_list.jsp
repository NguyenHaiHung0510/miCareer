<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

        <h2>Danh sách Job</h2>

        <a href="jobposting?action=edit">+ Tạo Job mới</a>
        <br/><br/>

        <table>
            <tr>
                <th>Job ID</th>
                <th>Title</th>
                <th>Status</th>
                <th>Salary</th>
                <th>Work Location</th>
                <th>Work Mode</th>
                <th>Exp At</th>
                <th>Actions</th>
            </tr>

            <c:forEach var="job" items="${jobs}">
                <tr>
                    <td>${job.jobPostId}</td>
                    <td>${job.title}</td>

                    <td>
                        <form action="jobposting" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="updateStatus">
                            <input type="hidden" name="jobPostId" value="${job.jobPostId}">

                            <select name="stat" onchange="this.form.submit()">
                                <option value="OPEN" ${job.stat=='OPEN' ? 'selected' : ''}>OPEN</option>
                                <option value="CLOSED" ${job.stat=='Closed' ? 'selected' : ''}>CLOSED</option>
                                <option value="DRAFT" ${job.stat=='Draft' ? 'selected' : ''}>DRAFT</option>
                            </select>
                        </form>
                    </td>

                    <td>${job.minSalary} - ${job.maxSalary}</td>
                    <td>${job.workLoc}</td>
                    <td>${job.workMode}</td>
                    <td>${job.expAt}</td>

                    <td>
                        <a href="jobposting?action=view&id=${job.jobPostId}">View</a> |
                        <a href="jobposting?action=edit&id=${job.jobPostId}">Edit</a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty jobs}">
                <tr>
                    <td colspan="8" style="text-align:center;">
                        Chưa có job nào
                    </td>
                </tr>
            </c:if>
        </table>

    </body>
</html>