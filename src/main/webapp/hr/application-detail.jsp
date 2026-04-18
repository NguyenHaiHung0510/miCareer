<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết hồ sơ - MiCareer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    
    <style>
        .detail-wrapper {
            padding: 30px 0;
            max-width: 1000px;
            margin: auto;
        }

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 24px;
        }

        .back-btn {
            padding: 8px 16px;
            background: #f1f5f9;
            color: #475569;
            text-decoration: none;
            border-radius: 8px;
            font-weight: 600;
        }

        .back-btn:hover { background: #e2e8f0; color: #0f172a; }

        .layout-grid {
            display: grid;
            grid-template-columns: 2fr 1fr;
            gap: 24px;
        }

        .card {
            background: #fff;
            border: 1px solid #e5e7eb;
            border-radius: 16px;
            padding: 24px;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
            margin-bottom: 24px;
        }

        .card h3 {
            margin-top: 0;
            margin-bottom: 16px;
            color: #0f172a;
            border-bottom: 1px solid #f1f5f9;
            padding-bottom: 10px;
        }

        .info-row {
            margin-bottom: 12px;
            font-size: 15px;
            color: #334155;
        }

        .info-row strong {
            display: inline-block;
            width: 120px;
            color: #0f172a;
        }

        .cover-letter-box {
            background: #f8fafc;
            padding: 16px;
            border-radius: 8px;
            border: 1px dashed #cbd5e1;
            white-space: pre-wrap; /* Giữ nguyên xuống dòng của thư */
            line-height: 1.6;
            color: #334155;
            font-size: 15px;
        }

        .cv-button {
            display: inline-block;
            background: #10b981;
            color: #fff;
            padding: 10px 20px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 600;
            margin-top: 10px;
        }
        .cv-button:hover { background: #059669; }

        /* Form Cập nhật trạng thái */
        .status-form label {
            display: block;
            font-weight: 600;
            margin-bottom: 8px;
            color: #1e293b;
        }

        .status-select {
            width: 100%;
            padding: 10px;
            border: 1px solid #cbd5e1;
            border-radius: 8px;
            font-size: 15px;
            margin-bottom: 16px;
        }

        .submit-btn {
            width: 100%;
            background: #1d4ed8;
            color: #fff;
            border: none;
            padding: 12px;
            border-radius: 8px;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s;
        }
        .submit-btn:hover { background: #1e40af; }

        /* Current status display */
        .current-status {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 999px;
            font-size: 14px;
            font-weight: 600;
            margin-bottom: 20px;
            background: #f1f5f9;
        }
        .st-PENDING { color: #b45309; background: #fef3c7; }
        .st-REVIEWING { color: #1d4ed8; background: #dbeafe; }
        .st-ACCEPTED { color: #15803d; background: #dcfce7; }
        .st-REJECTED { color: #b91c1c; background: #fee2e2; }

        @media (max-width: 768px) {
            .layout-grid { grid-template-columns: 1fr; }
        }
    </style>
</head>
<body>

<jsp:include page="../common/header.jsp" />

<main class="container detail-wrapper">
    <div class="page-header">
        <h2>Chi tiết hồ sơ ứng tuyển</h2>
        <a href="javascript:history.back()" class="back-btn">⬅ Quay lại</a>
    </div>

    <c:if test="${not empty success}">
        <div class="form-msg success">✅ ${success}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="form-msg error">❌ ${error}</div>
    </c:if>

    <div class="layout-grid">
        <div class="left-col">
            <div class="card">
                <h3>Thông tin chung</h3>
                <div class="info-row"><strong>Ứng viên:</strong> ${appDetail.candidateName}</div>
                <div class="info-row"><strong>Email:</strong> ${appDetail.email}</div>
                <div class="info-row"><strong>SĐT:</strong> ${appDetail.phone != null ? appDetail.phone : 'Không có'}</div>
                <div class="info-row"><strong>Vị trí ứng tuyển:</strong> <span style="color: #1d4ed8; font-weight: 600;">${appDetail.jobTitle}</span></div>
                <div class="info-row">
                    <strong>Ngày nộp:</strong> 
                    <fmt:parseDate value="${appDetail.appliedAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                    <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy HH:mm" />
                </div>
            </div>

            <div class="card">
                <h3>Thư giới thiệu (Cover Letter)</h3>
                <c:choose>
                    <c:when test="${not empty appDetail.coverLetter}">
                        <div class="cover-letter-box">${appDetail.coverLetter}</div>
                    </c:when>
                    <c:otherwise>
                        <p style="color: #64748b; font-style: italic;">Ứng viên không viết thư giới thiệu.</p>
                    </c:otherwise>
                </c:choose>
            </div>
            
            <div class="card">
                <h3>Hồ sơ đính kèm (CV)</h3>
                <c:choose>
                    <c:when test="${not empty appDetail.cvSnapUrl}">
                        <p>Ứng viên đã đính kèm một bản CV. Bấm vào nút bên dưới để xem/tải về.</p>
                        <a href="${appDetail.cvSnapUrl}" target="_blank" class="cv-button">📄 Mở xem CV</a>
                    </c:when>
                    <c:otherwise>
                        <p style="color: #94a3b8;">Không có CV đính kèm.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="right-col">
            <div class="card">
                <h3>Quản lý trạng thái</h3>
                
                <p style="margin-bottom: 8px; color: #475569;">Trạng thái hiện tại:</p>
                <div class="current-status st-${appDetail.stat}">
                    <c:choose>
                        <c:when test="${appDetail.stat == 'PENDING'}">Đang chờ</c:when>
                        <c:when test="${appDetail.stat == 'REVIEWING'}">Đang xem xét</c:when>
                        <c:when test="${appDetail.stat == 'ACCEPTED'}">Trúng tuyển</c:when>
                        <c:when test="${appDetail.stat == 'REJECTED'}">Từ chối</c:when>
                        <c:otherwise>${appDetail.stat}</c:otherwise>
                    </c:choose>
                </div>

                <form action="${pageContext.request.contextPath}/hr/application-detail" method="post" class="status-form">
                    <input type="hidden" name="jobAppId" value="${appDetail.jobAppId}">
                    <input type="hidden" name="oldStatus" value="${appDetail.stat}">

                    <label for="newStatus">Đổi trạng thái mới:</label>
                    <select name="newStatus" id="newStatus" class="status-select">
                        <option value="PENDING" ${appDetail.stat == 'PENDING' ? 'selected' : ''}>Đang chờ (Pending)</option>
                        <option value="REVIEWING" ${appDetail.stat == 'REVIEWING' ? 'selected' : ''}>Đang xem xét (Reviewing)</option>
                        <option value="ACCEPTED" ${appDetail.stat == 'ACCEPTED' ? 'selected' : ''}>Trúng tuyển (Accepted)</option>
                        <option value="REJECTED" ${appDetail.stat == 'REJECTED' ? 'selected' : ''}>Từ chối (Rejected)</option>
                    </select>

                    <button type="submit" class="submit-btn">Cập nhật trạng thái</button>
                </form>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../common/footer.jsp" />

</body>
</html>