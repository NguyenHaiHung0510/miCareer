<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>${job != null ? 'Edit Job' : 'Create Job'}</title>
        <style>
            body {
                font-family: Arial;
                max-width: 700px;
                margin: auto;
            }
            input, textarea, select {
                width: 100%;
                padding: 6px;
                margin-top: 5px;
            }
            textarea {
                height: 100px;
            }
            .box {
                margin-bottom: 15px;
            }
            .skills-select {
                width: 100%;
                height: 150px;
            }
            button {
                padding: 8px 16px;
            }
        </style>
    </head>
    <body>
        <h2>${job != null ? 'Edit Job' : 'Create Job'}</h2>

        <form action="jobposting" method="post">

            <input type="hidden" name="action" value="${job != null ? 'update' : 'create'}">

            <c:if test="${job != null}">
                <input type="hidden" name="jobPostId" value="${job.jobPostId}">
            </c:if>

            <div class="box">
                Comp ID:
                <input type="number" name="compId" value="${job != null ? job.compId : ''}" required>
            </div>

            <div class="box">
                Title:
                <input type="text" name="title" value="${job != null ? job.title : ''}" required>
            </div>

            <div class="box">
                Description:
                <textarea name="desc" required>${job != null ? job.desc : ''}</textarea>
            </div>

            <div class="box">
                Min Salary:
                <input type="number" step="0.01" name="minSalary" value="${job != null ? job.minSalary : ''}" required>
            </div>

            <div class="box">
                Max Salary:
                <input type="number" step="0.01" name="maxSalary" value="${job != null ? job.maxSalary : ''}" required>
            </div>

            <div class="box">
                Work Location:
                <input type="text" name="workLoc" value="${job != null ? job.workLoc : ''}">
            </div>

            <div class="box">
                Work Mode:
                <select name="workMode">
                    <option value="ONSITE" ${job.workMode == 'ONSITE' ? 'selected' : ''}>ONSITE</option>
                    <option value="REMOTE" ${job.workMode == 'REMOTE' ? 'selected' : ''}>REMOTE</option>
                    <option value="HYBRID" ${job.workMode == 'HYBRID' ? 'selected' : ''}>HYBRID</option>
                </select>
            </div>

            <div class="box">
                Expire At:
                <input type="datetime-local" name="expAt"
                       value="${job != null && job.expAt != null ? job.expAt.toString().substring(0,16).replace(' ', 'T') : ''}" required>
            </div>

            <div class="box">
                Category:
                <select name="catId">
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.catId}" ${job != null && job.catId == c.catId ? 'selected' : ''}>${c.catName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="box">
                Level:
                <select name="levelId">
                    <c:forEach var="l" items="${levels}">
                        <option value="${l.levelId}" ${job != null && job.levelId == l.levelId ? 'selected' : ''}>${l.levelName}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Multi-select skills -->
            <div class="box">
                <h3>Skills</h3>
                <select name="skills" multiple size="10" style="width:100%;">
                    <c:forEach var="skill" items="${allSkills}">
                        <option value="${skill.skillId}"
                                <c:if test="${skills != null && skills.contains(skill.skillId.toString())}">selected</c:if>>
                            ${skill.skillName}
                        </option>
                    </c:forEach>
                </select>
                <small>Ctrl+click để chọn nhiều skills</small>
            </div>      

            <button type="submit">${job != null ? 'Update Job' : 'Create Job'}</button>
        </form>

        <br/>
        <a href="jobposting?action=list">Back to list</a>

    </body>
</html>