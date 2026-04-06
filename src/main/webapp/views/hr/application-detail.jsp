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
        <legend>Thông tin ứng viên (Đang chờ Data)</legend>
        <p><i>Chỗ này bước sau chúng ta bổ sung hàm DAO sẽ load tên tuổi, link CV, Cover Letter của ứng viên ra đây...</i></p>
    </fieldset>

    <br>

    <fieldset>
        <legend>Cập nhật trạng thái</legend>
        <form action="${pageContext.request.contextPath}/hr/application-detail" method="POST">
            <input type="hidden" name="jobAppId" value="${jobAppId}">
            <input type="hidden" name="oldStatus" value="${appDetail.stat != null ? appDetail.stat : 'PENDING'}">

            <label for="newStatus">Trạng thái mới:</label>
            <select name="newStatus" id="newStatus">
                <option value="PENDING">PENDING (Đang chờ)</option>
                <option value="REVIEWING">REVIEWING (Đang xem xét)</option>
                <option value="INTERVIEWING">INTERVIEWING (Đang phỏng vấn)</option>
                <option value="ACCEPTED">ACCEPTED (Chấp nhận)</option>
                <option value="REJECTED">REJECTED (Từ chối)</option>
            </select>
            
            <button type="submit">Lưu trạng thái</button>
        </form>
    </fieldset>

</body>
</html>
