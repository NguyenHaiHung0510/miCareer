<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%
    Object user = session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đơn ứng tuyển của tôi - MiCareer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <style>
        .page-wrap {
            max-width: 960px;
            margin: 40px auto;
            padding: 0 16px;
        }
        .page-title {
            font-size: 22px;
            font-weight: 700;
            color: #0f172a;
            margin-bottom: 24px;
        }
        .app-table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0,0,0,0.07);
        }
        .app-table thead {
            background: #f1f5f9;
        }
        .app-table th, .app-table td {
            padding: 13px 16px;
            text-align: left;
            font-size: 14px;
            border-bottom: 1px solid #e5e7eb;
        }
        .app-table th {
            font-weight: 700;
            color: #475569;
            text-transform: uppercase;
            font-size: 12px;
            letter-spacing: 0.04em;
        }
        .app-table tr:last-child td { border-bottom: none; }
        .app-table tr:hover td { background: #f8fafc; }

        .badge {
            display: inline-block;
            padding: 3px 10px;
            border-radius: 999px;
            font-size: 12px;
            font-weight: 600;
        }
        .st-PENDING    { color: #b45309; background: #fef3c7; }
        .st-REVIEWING  { color: #1d4ed8; background: #dbeafe; }
        .st-INTERVIEWING { color: #6d28d9; background: #ede9fe; }
        .st-ACCEPTED   { color: #15803d; background: #dcfce7; }
        .st-REJECTED   { color: #b91c1c; background: #fee2e2; }

        .btn-detail {
            display: inline-block;
            padding: 5px 12px;
            background: #1d4ed8;
            color: #fff;
            border-radius: 6px;
            font-size: 13px;
            font-weight: 600;
            text-decoration: none;
        }
        .btn-detail:hover { background: #1e40af; }

        .empty-msg {
            text-align: center;
            padding: 60px 0;
            color: #94a3b8;
            font-size: 15px;
        }
        .form-msg.error {
            background: #fee2e2;
            color: #b91c1c;
            padding: 12px 16px;
            border-radius: 8px;
            margin-bottom: 16px;
        }
        .back-link {
            display: inline-block;
            margin-bottom: 16px;
            color: #475569;
            text-decoration: none;
            font-size: 14px;
        }
        .back-link:hover { color: #1d4ed8; }
    </style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>

<main class="page-wrap">
    <a href="${pageContext.request.contextPath}/candidate/profile" class="back-link">⬅ Về hồ sơ</a>
    <h1 class="page-title">📋 Đơn ứng tuyển của tôi</h1>

    <c:if test="${not empty error}">
        <div class="form-msg error">${error}</div>
    </c:if>

    <c:choose>
        <c:when test="${empty applications}">
            <div class="empty-msg">Bạn chưa có đơn ứng tuyển nào. <a href="${pageContext.request.contextPath}/job-list">Tìm việc ngay →</a></div>
        </c:when>
        <c:otherwise>
            <table class="app-table">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Vị trí ứng tuyển</th>
                        <th>Công ty</th>
                        <th>Ngày nộp</th>
                        <th>Trạng thái</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${applications}" var="app" varStatus="st">
                        <tr>
                            <td>${st.count}</td>
                            <td><strong>${app.jobTitle}</strong></td>
                            <td>${app.compName}</td>
                            <td>
                                <fmt:parseDate value="${app.appliedAt}" pattern="yyyy-MM-dd'T'HH:mm" var="pd" type="both"/>
                                <fmt:formatDate value="${pd}" pattern="dd/MM/yyyy HH:mm"/>
                            </td>
                            <td>
                                <span class="badge st-${app.stat}">
                                    <c:choose>
                                        <c:when test="${app.stat == 'PENDING'}">Đang chờ</c:when>
                                        <c:when test="${app.stat == 'REVIEWING'}">Đang xem xét</c:when>
                                        <c:when test="${app.stat == 'INTERVIEWING'}">Đang phỏng vấn</c:when>
                                        <c:when test="${app.stat == 'ACCEPTED'}">Trúng tuyển</c:when>
                                        <c:when test="${app.stat == 'REJECTED'}">Từ chối</c:when>
                                        <c:otherwise>${app.stat}</c:otherwise>
                                    </c:choose>
                                </span>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/candidate/application-detail?jobAppId=${app.jobAppId}"
                                   class="btn-detail">Xem chi tiết</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
