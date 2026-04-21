<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${not empty company ? company.compName : 'Chi tiết công ty'} - MiCareer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <style>
        .page-wrap { max-width: 860px; margin: 36px auto; padding: 0 16px; }
        .back-btn {
            display: inline-block; margin-bottom: 20px; color: #475569;
            text-decoration: none; font-size: 14px; font-weight: 600;
        }
        .back-btn:hover { color: #1d4ed8; }

        .comp-header {
            display: flex; align-items: center; gap: 20px; margin-bottom: 28px;
        }
        .comp-logo-lg {
            width: 80px; height: 80px; border-radius: 14px;
            background: #f1f5f9; display: flex; align-items: center;
            justify-content: center; font-size: 36px; flex-shrink: 0;
            border: 1px solid #e5e7eb; overflow: hidden;
        }
        .comp-logo-lg img { width: 100%; height: 100%; object-fit: contain; }
        .comp-title { font-size: 26px; font-weight: 800; color: #0f172a; margin-bottom: 4px; }
        .comp-sub   { font-size: 14px; color: #64748b; }

        .card {
            background: #fff; border: 1px solid #e5e7eb; border-radius: 14px;
            padding: 24px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); margin-bottom: 20px;
        }
        .card h3 {
            margin: 0 0 16px; font-size: 15px; font-weight: 700; color: #1e293b;
            border-bottom: 1px solid #f1f5f9; padding-bottom: 10px;
        }

        .info-row { display: flex; gap: 12px; margin-bottom: 12px; font-size: 14px; }
        .info-row .label { min-width: 130px; color: #64748b; font-weight: 600; flex-shrink: 0; }
        .info-row .value { color: #1e293b; }
        .info-row a { color: #1d4ed8; text-decoration: none; }
        .info-row a:hover { text-decoration: underline; }

        .form-msg.error { background:#fee2e2; color:#b91c1c; padding:12px 16px; border-radius:8px; margin-bottom:16px; }
    </style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>

<main class="page-wrap">
    <a href="${pageContext.request.contextPath}/companies" class="back-btn">⬅ Về danh sách công ty</a>

    <c:if test="${not empty error}">
        <div class="form-msg error">${error}</div>
    </c:if>

    <c:if test="${not empty company}">
        <%-- Header công ty --%>
        <div class="comp-header">
            <div class="comp-logo-lg">
                <c:choose>
                    <c:when test="${not empty company.logoUrl}">
                        <img src="${company.logoUrl}" alt="${company.compName}">
                    </c:when>
                    <c:otherwise>🏢</c:otherwise>
                </c:choose>
            </div>
            <div>
                <div class="comp-title">${company.compName}</div>
                <c:if test="${not empty province}">
                    <div class="comp-sub">📍 ${province.provName}</div>
                </c:if>
            </div>
        </div>

        <%-- Thông tin liên hệ --%>
        <div class="card">
            <h3>Thông tin công ty</h3>

            <div class="info-row">
                <span class="label">Tên công ty</span>
                <span class="value">${company.compName}</span>
            </div>

            <c:if test="${not empty company.taxCode}">
                <div class="info-row">
                    <span class="label">Mã số thuế</span>
                    <span class="value">${company.taxCode}</span>
                </div>
            </c:if>

            <c:if test="${not empty province}">
                <div class="info-row">
                    <span class="label">Tỉnh / Thành phố</span>
                    <span class="value">${province.provName}</span>
                </div>
            </c:if>

            <c:if test="${not empty company.ward}">
                <div class="info-row">
                    <span class="label">Phường / Xã</span>
                    <span class="value">${company.ward}</span>
                </div>
            </c:if>

            <c:if test="${not empty company.street}">
                <div class="info-row">
                    <span class="label">Địa chỉ</span>
                    <span class="value">${company.street}</span>
                </div>
            </c:if>

            <c:if test="${not empty company.contactEmail}">
                <div class="info-row">
                    <span class="label">Email liên hệ</span>
                    <span class="value">
                        <a href="mailto:${company.contactEmail}">${company.contactEmail}</a>
                    </span>
                </div>
            </c:if>

            <c:if test="${not empty company.webUrl}">
                <div class="info-row">
                    <span class="label">Website</span>
                    <span class="value">
                        <a href="${company.webUrl}" target="_blank">${company.webUrl}</a>
                    </span>
                </div>
            </c:if>
        </div>
    </c:if>
</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
