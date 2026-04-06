<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>View Job</title>
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

        <ul>
            <c:forEach var="skill" items="${skills}">
                <li>${skill}</li>
                </c:forEach>
        </ul>

        <a href="jobposting?action=list">Back to list</a>

    </body>
</html>