<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>${job != null ? 'Chỉnh sửa công việc' : 'Tạo công việc'}</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jobposting-edit.css">
    </head>

    <body>

        <jsp:include page="/common/header.jsp"/>

        <main class="container">

            <div class="page-header">
                <h2>${job != null ? 'Chỉnh sửa công việc' : 'Tạo công việc'}</h2>
                <a class="btn-secondary" href="jobposting?action=list">← Quay lại</a>
            </div>

            <form action="jobposting" method="post" class="form-card">

                <input type="hidden" name="action" value="${job != null ? 'update' : 'create'}">

                <c:if test="${job != null}">
                    <input type="hidden" name="jobPostId" value="${job.jobPostId}">
                </c:if>

                <!-- Company -->
                <div class="form-group">
                    <label>Công ty</label>
                    <input type="text" value="${job.compName}" disabled>
                </div>

                <!-- Title -->
                <div class="form-group">
                    <label>Tiêu đề</label>
                    <input type="text" name="title"
                           class="${fieldErrors.title != null ? 'error' : ''}"
                           value="${param.title != null ? param.title : (job != null ? job.title : '')}">
                    <c:if test="${fieldErrors.title != null}">
                        <div class="error-text">${fieldErrors.title}</div>
                    </c:if>
                </div>

                <!-- Desc -->
                <div class="form-group">
                    <label>Mô tả</label>
                    <textarea name="desc"
                              class="${fieldErrors.desc != null ? 'error' : ''}">${param.desc != null ? param.desc : (job != null ? job.desc : '')}</textarea>
                    <c:if test="${fieldErrors.desc != null}">
                        <div class="error-text">${fieldErrors.desc}</div>
                    </c:if>
                </div>

                <!-- Salary -->
                <div class="grid-2">
                    <div class="form-group">
                        <label>Lương tối thiểu</label>
                        <input type="number" step="0.01" name="minSalary"
                               class="${fieldErrors.minSalary != null ? 'error' : ''}"
                               value="${param.minSalary != null ? param.minSalary : (job != null ? job.minSalary : '')}">
                    </div>

                    <div class="form-group">
                        <label>Lương tối đa</label>
                        <input type="number" step="0.01" name="maxSalary"
                               class="${fieldErrors.maxSalary != null ? 'error' : ''}"
                               value="${param.maxSalary != null ? param.maxSalary : (job != null ? job.maxSalary : '')}">
                    </div>
                </div>

                <!-- Work info -->
                <div class="grid-2">

                    <div class="form-group">
                        <label>Địa điểm</label>
                        <input type="text" name="workLoc"
                               class="${fieldErrors.workLoc != null ? 'error' : ''}"
                               value="${param.workLoc != null ? param.workLoc : (job != null ? job.workLoc : '')}">
                    </div>

                    <div class="form-group">
                        <label>Hình thức</label>
                        <select name="workMode">
                            <option value="ONSITE" ${(param.workMode != null ? param.workMode : job.workMode) == 'ONSITE' ? 'selected' : ''}>ONSITE</option>
                            <option value="REMOTE" ${(param.workMode != null ? param.workMode : job.workMode) == 'REMOTE' ? 'selected' : ''}>REMOTE</option>
                            <option value="HYBRID" ${(param.workMode != null ? param.workMode : job.workMode) == 'HYBRID' ? 'selected' : ''}>HYBRID</option>
                        </select>
                    </div>

                </div>

                <!-- Exp -->
                <div class="form-group">
                    <label>Hạn tuyển</label>
                    <input type="datetime-local" name="expAt"
                           class="${fieldErrors.expAt != null ? 'error' : ''}"
                           value="${param.expAt != null ? param.expAt :
                                    (job != null && job.expAt != null ?
                                    job.expAt.toString().substring(0,16).replace(' ', 'T') : '')}">
                </div>

                <!-- Category / Level -->
                <div class="grid-2">

                    <div class="form-group">
                        <label>Danh mục</label>
                        <select name="catId">
                            <c:forEach var="c" items="${categories}">
                                <option value="${c.catId}"
                                        ${(param.catId != null ? param.catId : job.catId) == c.catId ? 'selected' : ''}>
                                    ${c.catName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Cấp bậc</label>
                        <select name="levelId">
                            <c:forEach var="l" items="${levels}">
                                <option value="${l.levelId}"
                                        ${(param.levelId != null ? param.levelId : job.levelId) == l.levelId ? 'selected' : ''}>
                                    ${l.levelName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                </div>

                <!-- Skills -->
                <div class="form-group">
                    <label>Kỹ năng</label>
                    <select name="skills" multiple size="8">
                        <c:forEach var="skill" items="${allSkills}">
                            <option value="${skill.skillId}"
                                    <c:if test="${skills != null && skills.contains(skill.skillId.toString())}">
                                        selected
                                    </c:if>>
                                ${skill.skillName}
                            </option>
                        </c:forEach>
                    </select>
                    <small>Giữ Ctrl để chọn nhiều</small>
                </div>

                <!-- Submit -->
                <div class="form-actions">
                    <button type="submit">
                        ${job != null ? 'Cập nhật' : 'Tạo mới'}
                    </button>
                </div>

            </form>

        </main>

        <jsp:include page="/common/footer.jsp"/>

    </body>
</html>