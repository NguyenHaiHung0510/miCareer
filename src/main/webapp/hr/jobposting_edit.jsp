<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>${job != null ? 'Edit Job' : 'Create Job'}</title>
    </head>
    <body>

        <h2>${job != null ? 'Edit Job' : 'Create Job'}</h2>

        <form action="jobposting" method="post">

            <input type="hidden" name="action" value="${job != null ? 'update' : 'create'}">

            <c:if test="${job != null}">
                <input type="hidden" name="jobPostId" value="${job.jobPostId}">
            </c:if>

            <p>
                Comp ID:
                <input type="text" name="compId" value="${job != null ? job.compId : ''}" required>
            </p>

            <!-- HR ID đã bỏ, servlet sẽ lấy từ session -->

            <p>
                Title:
                <input type="text" name="title" value="${job != null ? job.title : ''}" required>
            </p>

            <p>
                Description:
                <textarea name="desc" required>${job != null ? job.desc : ''}</textarea>
            </p>

            <p>
                Min Salary:
                <input type="text" name="minSalary" value="${job != null ? job.minSalary : ''}" required>
            </p>

            <p>
                Max Salary:
                <input type="text" name="maxSalary" value="${job != null ? job.maxSalary : ''}" required>
            </p>

            <p>
                Work Location:
                <input type="text" name="workLoc" value="${job != null ? job.workLoc : ''}">
            </p>

            <p>
                Work Mode:
                <input type="text" name="workMode" value="${job != null ? job.workMode : ''}">
            </p>

            <p>
                Exp At (yyyy-MM-dd HH:mm:ss):
                <input type="text" name="expAt" value="${job != null ? job.expAt : ''}" required>
            </p>

            <p>
                Category:
                <select name="catId">
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.catId}"
                                ${job != null && job.catId == c.catId ? 'selected' : ''}>
                            ${c.catName}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <p>
                Level:
                <select name="levelId">
                    <c:forEach var="l" items="${levels}">
                        <option value="${l.levelId}"
                                ${job != null && job.levelId == l.levelId ? 'selected' : ''}>
                            ${l.levelName}
                        </option>
                    </c:forEach>
                </select>
            </p>

            <h3>Skills</h3>

            <c:forEach var="skill" items="${allSkills}">
                <input type="checkbox"
                       name="skills"
                       value="${skill.skillId}"
                       <c:if test="${skills != null && skills.contains(skill.skillId)}">checked</c:if>>
                ${skill.skillName}
                <br/>
            </c:forEach>

            <button type="submit">
                ${job != null ? 'Update Job' : 'Create Job'}
            </button>

        </form>

        <a href="jobposting?action=list">Back to list</a>

    </body>
</html>