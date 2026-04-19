<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Offer - MiCareer HR</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <style>
        .page-wrap { max-width: 900px; margin: 36px auto; padding: 0 16px; }
        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px; }
        .back-btn { padding: 8px 16px; background: #f1f5f9; color: #475569;
                    text-decoration: none; border-radius: 8px; font-weight: 600; font-size: 14px; }
        .back-btn:hover { background: #e2e8f0; }

        .layout { display: grid; grid-template-columns: 1fr 1.3fr; gap: 24px; }
        @media (max-width: 768px) { .layout { grid-template-columns: 1fr; } }

        .card { background: #fff; border: 1px solid #e5e7eb; border-radius: 14px;
                padding: 24px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); margin-bottom: 20px; }
        .card h3 { margin: 0 0 18px; font-size: 15px; font-weight: 700; color: #1e293b;
                   border-bottom: 1px solid #f1f5f9; padding-bottom: 10px; }

        .candidate-info { background: #f8fafc; border-radius: 10px; padding: 12px 16px;
                          margin-bottom: 20px; font-size: 14px; color: #334155; }
        .candidate-info strong { color: #1e293b; }

        /* Form */
        .form-group { margin-bottom: 14px; }
        .form-group label { display: block; font-size: 13px; font-weight: 600;
                            color: #374151; margin-bottom: 5px; }
        .form-group input, .form-group textarea {
            width: 100%; padding: 9px 12px; border: 1px solid #d1d5db;
            border-radius: 8px; font-size: 14px; box-sizing: border-box;
        }
        .form-group input:focus, .form-group textarea:focus {
            border-color: #f59e0b; outline: none;
        }
        .btn-submit { width: 100%; background: #f59e0b; color: #fff; border: none;
                      padding: 11px; border-radius: 8px; font-size: 14px; font-weight: 700;
                      cursor: pointer; transition: background 0.2s; }
        .btn-submit:hover { background: #d97706; }

        /* Offer list */
        .offer-list { display: flex; flex-direction: column; gap: 14px; }
        .offer-card { border: 1px solid #e5e7eb; border-radius: 10px; padding: 16px;
                      background: #fffbeb; }
        .offer-card.latest { border-color: #f59e0b; background: #fffbeb;
                             box-shadow: 0 0 0 2px rgba(245,158,11,0.2); }
        .offer-ver { font-size: 11px; font-weight: 700; color: #92400e;
                     text-transform: uppercase; letter-spacing: 0.05em; margin-bottom: 6px; }
        .offer-salary { font-size: 20px; font-weight: 800; color: #1e293b; margin-bottom: 8px; }
        .offer-desc { font-size: 14px; color: #475569; white-space: pre-wrap; line-height: 1.5; margin-bottom: 10px; }
        .offer-stat { display: inline-block; padding: 3px 10px; border-radius: 999px;
                      font-size: 12px; font-weight: 700; }
        .stat-PENDING  { background: #fef3c7; color: #92400e; }
        .stat-ACCEPTED { background: #dcfce7; color: #15803d; }
        .stat-REJECTED { background: #fee2e2; color: #b91c1c; }

        .form-msg { padding: 11px 14px; border-radius: 8px; margin-bottom: 16px; font-size: 14px; }
        .form-msg.success { background: #dcfce7; color: #15803d; }
        .form-msg.error   { background: #fee2e2; color: #b91c1c; }
        .empty-text { color: #94a3b8; font-style: italic; font-size: 14px; }
        .latest-tag { font-size: 11px; background: #f59e0b; color: #fff;
                      padding: 2px 7px; border-radius: 4px; margin-left: 6px; font-weight: 700; }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<main class="page-wrap">
    <div class="page-header">
        <h2 style="margin:0; font-size: 20px;">📨 Quản lý Offer</h2>
        <a href="${pageContext.request.contextPath}/hr/application-detail?jobAppId=${jobAppId}" class="back-btn">
            ⬅ Về hồ sơ
        </a>
    </div>

    <c:if test="${not empty appDetail}">
        <div class="candidate-info">
            Ứng viên: <strong>${appDetail.candidateName}</strong>
            &nbsp;|&nbsp; Vị trí: <strong>${appDetail.jobTitle}</strong>
        </div>
    </c:if>

    <c:if test="${not empty param.success}">
        <div class="form-msg success">✅ Đã gửi offer thành công!</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="form-msg error">❌ ${error}</div>
    </c:if>

    <div class="layout">
        <%-- Form tạo offer mới --%>
        <div>
            <div class="card">
                <h3>➕ Tạo offer mới</h3>
                <form action="${pageContext.request.contextPath}/hr/offer" method="post">
                    <input type="hidden" name="jobAppId" value="${jobAppId}">

                    <div class="form-group">
                        <label for="salary">Mức lương đề nghị (VNĐ)</label>
                        <input type="number" id="salary" name="salary" min="0"
                               placeholder="VD: 25000000">
                    </div>
                    <div class="form-group">
                        <label for="desc">Nội dung & Đãi ngộ *</label>
                        <textarea id="desc" name="desc" rows="8"
                                  placeholder="Mô tả chi tiết gói offer: lương, thưởng, ngày onboard, phúc lợi..."
                                  required></textarea>
                    </div>
                    <button type="submit" class="btn-submit">📨 Gửi Offer</button>
                </form>
            </div>
        </div>

        <%-- Lịch sử offer --%>
        <div>
            <div class="card">
                <h3>Lịch sử Offer</h3>
                <c:choose>
                    <c:when test="${empty offers}">
                        <p class="empty-text">Chưa có offer nào được gửi.</p>
                    </c:when>
                    <c:otherwise>
                        <div class="offer-list">
                            <c:forEach items="${offers}" var="off" varStatus="st">
                                <div class="offer-card ${st.first ? 'latest' : ''}">
                                    <div class="offer-ver">
                                        Phiên bản ${off.ver}
                                        <c:if test="${st.first}">
                                            <span class="latest-tag">MỚI NHẤT</span>
                                        </c:if>
                                    </div>
                                    <div class="offer-salary">
                                        <c:choose>
                                            <c:when test="${not empty off.salary}">
                                                <fmt:formatNumber value="${off.salary}" type="number" groupingUsed="true"/> VNĐ
                                            </c:when>
                                            <c:otherwise>Thỏa thuận</c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="offer-desc">${off.desc}</div>
                                    <span class="offer-stat stat-${off.stat}">
                                        <c:choose>
                                            <c:when test="${off.stat == 'PENDING'}">Đang chờ phản hồi</c:when>
                                            <c:when test="${off.stat == 'ACCEPTED'}">Ứng viên chấp nhận</c:when>
                                            <c:when test="${off.stat == 'REJECTED'}">Ứng viên từ chối</c:when>
                                            <c:otherwise>${off.stat}</c:otherwise>
                                        </c:choose>
                                    </span>
                                </div>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
