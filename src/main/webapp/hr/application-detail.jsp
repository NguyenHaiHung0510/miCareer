<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Chi tiết hồ sơ ứng viên</title>
</head>
<body>
    <button onclick="history.back()">⬅ Quay lại danh sách</button>

    <h2>Chi tiết đơn ứng tuyển #${jobAppId}</h2>

    <c:if test="${not empty success}">
        <p style="color: green;"><b>${success}</b></p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;"><b>${error}</b></p>
    </c:if>

    <fieldset>
        <legend>Thông tin ứng viên</legend>
        <c:choose>
            <c:when test="${not empty appDetail}">
                <p><b>Vị trí ứng tuyển:</b> ${appDetail.jobTitle}</p>
                <p><b>Họ và tên:</b> ${appDetail.candidateName}</p>
                <p><b>Email:</b> ${appDetail.email}</p>
                <p><b>Số điện thoại:</b> ${appDetail.phone != null ? appDetail.phone : 'Chưa cập nhật'}</p>
                <p><b>Ngày nộp:</b> ${appDetail.appliedAt}</p>
                
                <p><b>Cover Letter:</b></p>
                <div style="border: 1px solid #ccc; padding: 10px; background-color: #f9f9f9;">
                    ${appDetail.coverLetter != null && !appDetail.coverLetter.trim().isEmpty() ? appDetail.coverLetter : '<i>(Không có Cover Letter)</i>'}
                </div>
                
                <p><b>CV đính kèm:</b> 
                    <c:choose>
                        <c:when test="${not empty appDetail.cvSnapUrl}">
                            <a href="${pageContext.request.contextPath}/${appDetail.cvSnapUrl}" target="_blank">Xem / Tải xuống CV</a>
                        </c:when>
                        <c:otherwise>
                            <i>(Không có file CV)</i>
                        </c:otherwise>
                    </c:choose>
                </p>
            </c:when>
            <c:otherwise>
                <p style="color: red;">Không tìm thấy dữ liệu ứng viên hoặc có lỗi xảy ra!</p>
            </c:otherwise>
        </c:choose>
    </fieldset>

    <br>

    <c:if test="${not empty appDetail}">
        <fieldset>
            <legend>Cập nhật trạng thái</legend>
            <form action="${pageContext.request.contextPath}/hr/application-detail" method="POST">
                <input type="hidden" name="jobAppId" value="${jobAppId}">
                <input type="hidden" name="oldStatus" value="${appDetail.stat}">

                <label for="newStatus">Trạng thái hiện tại: <b>${appDetail.stat}</b> -> Chuyển thành:</label>
                <select name="newStatus" id="newStatus">
                    <option value="PENDING" ${appDetail.stat == 'PENDING' ? 'selected' : ''}>PENDING (Đang chờ)</option>
                    <option value="REVIEWING" ${appDetail.stat == 'REVIEWING' ? 'selected' : ''}>REVIEWING (Đang xem xét)</option>
                    <option value="INTERVIEWING" ${appDetail.stat == 'INTERVIEWING' ? 'selected' : ''}>INTERVIEWING (Đang phỏng vấn)</option>
                    <option value="ACCEPTED" ${appDetail.stat == 'ACCEPTED' ? 'selected' : ''}>ACCEPTED (Chấp nhận)</option>
                    <option value="REJECTED" ${appDetail.stat == 'REJECTED' ? 'selected' : ''}>REJECTED (Từ chối)</option>
                </select>
                
                <button type="submit">Lưu trạng thái</button>
            </form>
        </fieldset>
    </c:if>

</body>
</html>
