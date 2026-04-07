<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>View Job</title>
        <style>
            .skills {
                display: flex;
                flex-wrap: wrap;
                gap: 8px; /* khoảng cách giữa các tag */
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

        <h2>Job Details</h2>

        <p><b>Title:</b> ${job.title}</p>
        <p><b>Description:</b> ${job.desc}</p>
        <p><b>Salary:</b> ${job.minSalary} - ${job.maxSalary}</p>
        <p><b>Work Location:</b> ${job.workLoc}</p>
        <p><b>Work Mode:</b> ${job.workMode}</p>
        <p><b>Status:</b> ${job.stat}</p>
        <p><b>Exp At:</b> ${job.expAt}</p>
        <p><b>Category:</b> ${job.catName}</p>
        <p><b>Level:</b> ${job.levelName}</p>

        <h3>Skills:</h3>
        <div class="skills">
            <c:forEach var="skill" items="${skills}">
                <span class="tag">${skill}</span>
            </c:forEach>
        </div>

        <br/>
        <a href="jobposting?action=list">Back to list</a>

    </body>
</html>