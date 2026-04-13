<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Chi tiết bài đăng tuyển dụng</title>
</head>
<body>
    <h2>Chi tiết công việc: ${jobDetail.title}</h2>
    
    <a href="${pageContext.request.contextPath}/hr/my-jobs">← Quay lại danh sách Job</a>
    <br><br>

    <div style="border: 1px solid #ccc; padding: 15px; background-color: #f8f9fa;">
        <p><strong>Cấp bậc:</strong> ${jobDetail.levelName} | <strong>Danh mục:</strong> ${jobDetail.catName}</p>
        <p><strong>Mức lương:</strong> ${jobDetail.minSalary} - ${jobDetail.maxSalary} VNĐ</p>
        <p><strong>Địa điểm:</strong> ${jobDetail.workLoc}</p>
        <p><strong>Hình thức làm việc:</strong> ${jobDetail.workMode}</p>
        <hr>
        <p><strong>Mô tả chi tiết:</strong> ${jobDetail.desc}</p>
    </div>

</body>
</html>