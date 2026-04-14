<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>${job != null ? 'Chỉnh sửa công việc' : 'Tạo công việc'}</title>
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
            button {
                padding: 8px 16px;
            }
            .error-text {
                color: red;
                font-size: 13px;
            }
            .error-input {
                border: 1px solid red;
            }
        </style>
    </head>

    <body>

        <h2>${job != null ? 'Chỉnh sửa công việc' : 'Tạo công việc'}</h2>

        <form action="jobposting" method="post">

            <input type="hidden" name="action" value="${job != null ? 'update' : 'create'}">

            <c:if test="${job != null}">
                <input type="hidden" name="jobPostId" value="${job.jobPostId}">
            </c:if>

            <!-- Company -->
            <div class="box">
                Công ty:
                <input type="text" value="${job.compName}" disabled>
            </div>

            <!-- Title -->
            <div class="box">
                Tiêu đề:
                <input type="text" name="title"
                       class="${fieldErrors.title != null ? 'error-input' : ''}"
                       value="${param.title != null ? param.title : (job != null ? job.title : '')}">
                <c:if test="${fieldErrors.title != null}">
                    <div class="error-text">${fieldErrors.title}</div>
                </c:if>
            </div>

            <!-- Desc -->
            <div class="box">
                Mô tả:
                <textarea name="desc"
                          class="${fieldErrors.desc != null ? 'error-input' : ''}">${param.desc != null ? param.desc : (job != null ? job.desc : '')}</textarea>
                <c:if test="${fieldErrors.desc != null}">
                    <div class="error-text">${fieldErrors.desc}</div>
                </c:if>
            </div>

            <!-- Min Salary -->
            <div class="box">
                Lương tối thiểu:
                <input type="number" step="0.01" name="minSalary"
                       class="${fieldErrors.minSalary != null ? 'error-input' : ''}"
                       value="${param.minSalary != null ? param.minSalary : (job != null ? job.minSalary : '')}">
                <c:if test="${fieldErrors.minSalary != null}">
                    <div class="error-text">${fieldErrors.minSalary}</div>
                </c:if>
            </div>

            <!-- Max Salary -->
            <div class="box">
                Lương tối đa:
                <input type="number" step="0.01" name="maxSalary"
                       class="${fieldErrors.maxSalary != null ? 'error-input' : ''}"
                       value="${param.maxSalary != null ? param.maxSalary : (job != null ? job.maxSalary : '')}">
                <c:if test="${fieldErrors.maxSalary != null}">
                    <div class="error-text">${fieldErrors.maxSalary}</div>
                </c:if>
            </div>

            <!-- Work Location -->
            <div class="box">
                Địa điểm làm việc:
                <input type="text" name="workLoc"
                       class="${fieldErrors.workLoc != null ? 'error-input' : ''}"
                       value="${param.workLoc != null ? param.workLoc : (job != null ? job.workLoc : '')}">

                <c:if test="${fieldErrors.workLoc != null}">
                    <div class="error-text">${fieldErrors.workLoc}</div>
                </c:if>
            </div>

            <!-- Work Mode -->
            <div class="box">
                Hình thức làm việc:
                <select name="workMode">
                    <option value="ONSITE"
                            ${(param.workMode != null ? param.workMode : job.workMode) == 'ONSITE' ? 'selected' : ''}>
                        ONSITE
                    </option>
                    <option value="REMOTE"
                            ${(param.workMode != null ? param.workMode : job.workMode) == 'REMOTE' ? 'selected' : ''}>
                        REMOTE
                    </option>
                    <option value="HYBRID"
                            ${(param.workMode != null ? param.workMode : job.workMode) == 'HYBRID' ? 'selected' : ''}>
                        HYBRID
                    </option>
                </select>
            </div>

            <!-- Exp Date -->
            <div class="box">
                Hạn ứng tuyển:
                <input type="datetime-local" name="expAt"
                       class="${fieldErrors.expAt != null ? 'error-input' : ''}"
                       value="${param.expAt != null ? param.expAt :
                                (job != null && job.expAt != null ?
                                job.expAt.toString().substring(0,16).replace(' ', 'T') : '')}">
                <c:if test="${fieldErrors.expAt != null}">
                    <div class="error-text">${fieldErrors.expAt}</div>
                </c:if>
            </div>

            <!-- Category -->
            <div class="box">
                Danh mục:
                <select name="catId">
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.catId}"
                                ${(param.catId != null ? param.catId : job.catId) == c.catId ? 'selected' : ''}>
                            ${c.catName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Level -->
            <div class="box">
                Cấp bậc:
                <select name="levelId">
                    <c:forEach var="l" items="${levels}">
                        <option value="${l.levelId}"
                                ${(param.levelId != null ? param.levelId : job.levelId) == l.levelId ? 'selected' : ''}>
                            ${l.levelName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- Skills -->
            <div class="box">
                <h3>Kỹ năng yêu cầu</h3>
                <select name="skills" multiple size="10" style="width:100%;">
                    <c:forEach var="skill" items="${allSkills}">
                        <option value="${skill.skillId}"
                                <c:if test="${skills != null && skills.contains(skill.skillId.toString())}">
                                    selected
                                </c:if>>
                            ${skill.skillName}
                        </option>
                    </c:forEach>
                </select>
                <small>Giữ Ctrl để chọn nhiều kỹ năng</small>
            </div>

            <button type="submit">
                ${job != null ? 'Cập nhật công việc' : 'Tạo công việc'}
            </button>

        </form>

        <br/>
        <a href="jobposting?action=list">Quay lại danh sách</a>

    </body>
</html>