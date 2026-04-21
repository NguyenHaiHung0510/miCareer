<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách công ty - MiCareer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <style>
        .page-wrap { max-width: 1100px; margin: 36px auto; padding: 0 16px; }
        .page-title { font-size: 24px; font-weight: 800; color: #0f172a; margin-bottom: 8px; }
        .page-sub   { font-size: 15px; color: #64748b; margin-bottom: 28px; }

        .company-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
            gap: 20px;
        }

        .company-card {
            background: #fff;
            border: 1px solid #e5e7eb;
            border-radius: 14px;
            padding: 24px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            display: flex;
            flex-direction: column;
            transition: box-shadow 0.2s, transform 0.2s;
        }
        .company-card:hover {
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
            transform: translateY(-2px);
        }

        .comp-logo {
            width: 56px; height: 56px; border-radius: 10px;
            background: #f1f5f9; display: flex; align-items: center;
            justify-content: center; font-size: 24px; margin-bottom: 14px;
            overflow: hidden;
        }
        .comp-logo img { width: 100%; height: 100%; object-fit: contain; }

        .comp-name { font-size: 16px; font-weight: 700; color: #1e293b; margin-bottom: 6px; }
        .comp-info { font-size: 13px; color: #64748b; margin-bottom: 4px; }
        .comp-info span { font-weight: 600; color: #374151; }

        .btn-detail {
            display: inline-block; margin-top: auto; padding-top: 14px;
            color: #1d4ed8; font-size: 14px; font-weight: 600;
            text-decoration: none;
        }
        .btn-detail:hover { text-decoration: underline; }

        .empty-state { text-align: center; color: #94a3b8; padding: 60px 0; font-size: 16px; }
        .form-msg.error { background:#fee2e2; color:#b91c1c; padding:12px 16px; border-radius:8px; margin-bottom:16px; }
    </style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>

<main class="page-wrap">
    <h1 class="page-title">🏢 Danh sách công ty</h1>
    <p class="page-sub">Khám phá các doanh nghiệp đang tuyển dụng trên MiCareer</p>

    <c:if test="${not empty error}">
        <div class="form-msg error">${error}</div>
    </c:if>

    <c:choose>
        <c:when test="${empty companies}">
            <div class="empty-state">Chưa có công ty nào trong hệ thống.</div>
        </c:when>
        <c:otherwise>
            <div class="company-grid">
                <c:forEach items="${companies}" var="comp">
                    <div class="company-card">
                        <div class="comp-logo">
                            <c:choose>
                                <c:when test="${not empty comp.logoUrl}">
                                    <img src="${comp.logoUrl}" alt="${comp.compName}">
                                </c:when>
                                <c:otherwise>🏢</c:otherwise>
                            </c:choose>
                        </div>
                        <div class="comp-name">${comp.compName}</div>
                        <c:if test="${not empty comp.contactEmail}">
                            <div class="comp-info">📧 <span>${comp.contactEmail}</span></div>
                        </c:if>
                        <c:if test="${not empty comp.provId}">
                            <div class="comp-info">📍 <span>${comp.provId}</span></div>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/company-detail?id=${comp.compId}"
                           class="btn-detail">Xem chi tiết →</a>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
