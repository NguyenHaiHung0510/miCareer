<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="vn.com.micareer.util.SalaryFormatUtil" %>

<html>
    <head>
        <title>Chi tiết công việc</title>
        <style>
            .skills {
                display: flex;
                flex-wrap: wrap;
                gap: 8px;
                margin-top: 8px;
            }

            .tag {
                background-color: #e0e0e0;
                padding: 4px 10px;
                border-radius: 12px;
                font-size: 0.9em;
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

        <h2>Chi tiết công việc</h2>

        <p><b>Tiêu đề:</b> ${job.title}</p>
        <p><b>Mô tả:</b> ${job.desc}</p>

        <!-- ✅ FORMAT LƯƠNG -->
        <p><b>Mức lương:</b>
            <%= SalaryFormatUtil.formatRange(
                    ((vn.com.micareer.model.JobPosting) request.getAttribute("job")).getMinSalary(),
                    ((vn.com.micareer.model.JobPosting) request.getAttribute("job")).getMaxSalary()
            )%>
        </p>

        <p><b>Địa điểm làm việc:</b> ${job.workLoc}</p>
        <p><b>Hình thức làm việc:</b> ${job.workMode}</p>
        <p><b>Trạng thái:</b> ${job.stat}</p>
        <p><b>Hạn tuyển:</b> ${job.expAt}</p>
        <p><b>Danh mục:</b> ${job.catName}</p>
        <p><b>Cấp bậc:</b> ${job.levelName}</p>

        <h3>Kỹ năng yêu cầu:</h3>
        <div class="skills">
            <c:forEach var="skill" items="${skills}">
                <span class="tag">${skill}</span>
            </c:forEach>
        </div>

        <br/>
        <a href="jobposting?action=list">Quay lại danh sách</a>

    </body>
</html>