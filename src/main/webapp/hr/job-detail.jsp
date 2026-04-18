<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết Công việc (HR) - MiCareer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/job-detail.css">
    
    <style>
        .hr-back-btn {
            display: inline-block;
            margin-bottom: 16px;
            padding: 8px 16px;
            background: #f1f5f9;
            color: #475569;
            text-decoration: none;
            border-radius: 8px;
            font-weight: 600;
            transition: all 0.2s;
        }
        .hr-back-btn:hover {
            background: #e2e8f0;
            color: #0f172a;
        }
        .hr-action-btn {
            background: #1d4ed8 !important; 
        }
        .hr-action-btn:hover { background: #1e40af !important; }
        .job-content-text {
            white-space: pre-wrap; 
            line-height: 1.6;
            color: #334155;
            font-size: 15px;
        }
    </style>
</head>
<body>

<jsp:include page="../common/header.jsp" />

<main class="container detail-page">
    
    <a href="${pageContext.request.contextPath}/hr/my-jobs" class="hr-back-btn">⬅ Quay lại danh sách Job</a>

    <c:if test="${not empty error}">
        <div class="form-msg error">${error}</div>
    </c:if>

    <c:if test="${not empty jobDetail}">
        <div class="detail-hero">
            <h1>${jobDetail.title}</h1>
            <div class="company">${jobDetail.compName}</div>
            
            <div class="detail-meta">
                <span class="category">${jobDetail.catName != null ? jobDetail.catName : 'Chưa phân loại'}</span>
                <span class="level">${jobDetail.levelName != null ? jobDetail.levelName : 'Tất cả cấp bậc'}</span>
                <span class="work-loc">${jobDetail.workLoc}</span>
                <span class="work-mode">${jobDetail.workMode}</span>
            </div>
            
            <div class="salary">
                Lương: 
                <c:choose>
                    <c:when test="${jobDetail.minSalary != null and jobDetail.maxSalary != null}">
                        ${jobDetail.minSalary} - ${jobDetail.maxSalary} Triệu
                    </c:when>
                    <c:otherwise>Thỏa thuận</c:otherwise>
                </c:choose>
            </div>
            
            <a href="${pageContext.request.contextPath}/hr/applications?jobPostId=${jobDetail.jobPostId}" class="apply-btn hr-action-btn">
                👥 Xem danh sách ứng viên
            </a>
        </div>

        <div class="detail-body">
            <div class="detail-grid">
                
                <div class="detail-job-info detail-card">
                    <h2>Mô tả công việc</h2>
                    <div class="job-content-text">${jobDetail.desc}</div>
                </div>
                
                <div class="detail-company-info detail-card">
                    <h2>Trạng thái Bài đăng</h2>
                    
                    <p><strong>Ngày đăng:</strong> ${jobDetail.createdAtText}</p>
                    <p><strong>Hạn chót:</strong> ${jobDetail.expAtText}</p>
                    
                    <hr style="margin: 15px 0; border: 0; border-top: 1px solid #e2e8f0;">
                    
                    <p><strong>Trạng thái:</strong> 
                        <c:choose>
                            <c:when test="${jobDetail.stat == 'ACTIVE'}">
                                <span style="color: #15803d; font-weight: 700; background: #dcfce7; padding: 4px 8px; border-radius: 4px;">Đang tuyển (ACTIVE)</span>
                            </c:when>
                            <c:when test="${jobDetail.stat == 'INACTIVE'}">
                                <span style="color: #b91c1c; font-weight: 700; background: #fee2e2; padding: 4px 8px; border-radius: 4px;">Tạm ngưng (INACTIVE)</span>
                            </c:when>
                            <c:when test="${jobDetail.stat == 'EXPIRED'}">
                                <span style="color: #64748b; font-weight: 700; background: #f1f5f9; padding: 4px 8px; border-radius: 4px;">Hết hạn (EXPIRED)</span>
                            </c:when>
                            <c:otherwise>
                                <span style="font-weight: 700;">${jobDetail.stat}</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                
            </div>
        </div>
    </c:if>
</main>

<jsp:include page="../common/footer.jsp" />

</body>
</html>