<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách ứng viên - MiCareer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    
    <style>
        .app-list-container {
            background: #fff;
            border: 1px solid #e5e7eb;
            border-radius: 16px;
            padding: 24px;
            margin: 30px auto;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
        }

        .header-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            padding-bottom: 15px;
            border-bottom: 2px solid #f1f5f9;
        }

        .header-section h2 {
            color: #0f172a;
            font-size: 22px;
            margin: 0;
        }

        .header-section .job-title-badge {
            color: #1d4ed8;
            font-weight: 600;
        }

        .back-btn {
            padding: 8px 16px;
            background: #f1f5f9;
            color: #475569;
            text-decoration: none;
            border-radius: 8px;
            font-weight: 600;
            transition: all 0.2s;
        }

        .back-btn:hover {
            background: #e2e8f0;
            color: #0f172a;
        }

        /* Table Styling */
        .app-table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
        }

        .app-table th, .app-table td {
            padding: 14px 16px;
            border-bottom: 1px solid #e2e8f0;
        }

        .app-table th {
            background-color: #f8fafc;
            color: #475569;
            font-weight: 600;
            font-size: 14px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .app-table tr:hover {
            background-color: #f8fbff;
        }

        .candidate-name {
            font-weight: 700;
            color: #1e293b;
        }

        .candidate-email {
            font-size: 13px;
            color: #64748b;
        }

        /* Status Badges */
        .status-badge {
            padding: 6px 12px;
            border-radius: 999px;
            font-size: 12px;
            font-weight: 600;
            display: inline-block;
        }
        .status-pending { background: #fef3c7; color: #b45309; }
        .status-reviewing { background: #dbeafe; color: #1d4ed8; }
        .status-interviewing { background: #ede9fe; color: #6d28d9; }
        .status-accepted { background: #dcfce7; color: #15803d; }
        .status-rejected { background: #fee2e2; color: #b91c1c; }

        .action-link {
            color: #0ea5e9;
            text-decoration: none;
            font-weight: 600;
            font-size: 14px;
        }
        
        .action-link:hover {
            text-decoration: underline;
        }

        .cv-link {
            color: #10b981;
            text-decoration: none;
            font-weight: 500;
        }
        .cv-link:hover { text-decoration: underline; }
    </style>
</head>
<body>

<jsp:include page="../common/header.jsp" />

<main class="container">
    <div class="app-list-container">
        
        <div class="header-section">
            <h2>Hồ sơ ứng tuyển: <span class="job-title-badge">${jobDetail.title}</span></h2>
            <a href="${pageContext.request.contextPath}/hr/my-jobs" class="back-btn">⬅ Quay lại danh sách Job</a>
        </div>

        <c:if test="${not empty error}">
            <div class="form-msg error">${error}</div>
        </c:if>

        <c:choose>
            <c:when test="${empty applications}">
                <p class="empty-state" style="text-align: center; padding: 40px; color: #64748b;">
                    Chưa có ứng viên nào nộp hồ sơ cho vị trí này.
                </p>
            </c:when>
            <c:otherwise>
                <div style="overflow-x: auto;">
                    <table class="app-table">
                        <thead>
                            <tr>
                                <th>Ứng viên</th>
                                <th>Liên hệ</th>
                                <th>Ngày nộp</th>
                                <th>CV</th>
                                <th>Trạng thái</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="app" items="${applications}">
                                <tr>
                                    <td>
                                        <div class="candidate-name">${app.candidateName}</div>
                                    </td>
                                    <td>
                                        <div class="candidate-email">📞 ${app.phone != null ? app.phone : 'Chưa cập nhật'}</div>
                                        <div class="candidate-email">✉️ ${app.email != null ? app.email : 'Chưa cập nhật'}</div>
                                    </td>
                                    <td>
                                        <fmt:parseDate value="${app.appliedAt}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
                                        <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy HH:mm" />
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty app.cvSnapUrl}">
                                                <a href="${app.cvSnapUrl}" target="_blank" class="cv-link">📥 Xem CV</a>
                                            </c:when>
                                            <c:otherwise>
                                                <span style="color: #94a3b8; font-size: 13px;">Không có CV</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${app.stat == 'PENDING'}">
                                                <span class="status-badge status-pending">Đang chờ</span>
                                            </c:when>
                                            <c:when test="${app.stat == 'REVIEWING'}">
                                                <span class="status-badge status-reviewing">Đang xem xét</span>
                                            </c:when>
                                            <c:when test="${app.stat == 'INTERVIEWING'}">
                                                <span class="status-badge status-interviewing">Đang phỏng vấn</span>
                                            </c:when>                                                
                                            <c:when test="${app.stat == 'ACCEPTED'}">
                                                <span class="status-badge status-accepted">Đã trúng tuyển</span>
                                            </c:when>
                                            <c:when test="${app.stat == 'REJECTED'}">
                                                <span class="status-badge status-rejected">Đã từ chối</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="status-badge" style="background:#e2e8f0;">${app.stat}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/hr/application-detail?jobAppId=${app.jobAppId}" class="action-link">Chi tiết »</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</main>

<jsp:include page="../common/footer.jsp" />

</body>
</html>