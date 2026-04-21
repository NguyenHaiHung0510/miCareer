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
    <title>Chi tiết đơn ứng tuyển - MiCareer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <style>
        .page-wrap { max-width: 900px; margin: 36px auto; padding: 0 16px; }
        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px; }
        .back-btn { padding: 8px 16px; background: #f1f5f9; color: #475569; text-decoration: none;
                    border-radius: 8px; font-weight: 600; font-size: 14px; }
        .back-btn:hover { background: #e2e8f0; }

        .card { background: #fff; border: 1px solid #e5e7eb; border-radius: 14px; padding: 24px;
                box-shadow: 0 2px 8px rgba(0,0,0,0.05); margin-bottom: 20px; }
        .card h3 { margin: 0 0 16px; font-size: 16px; font-weight: 700; color: #1e293b;
                   border-bottom: 1px solid #f1f5f9; padding-bottom: 10px; }
        .info-row { margin-bottom: 10px; font-size: 14px; color: #334155; }
        .info-row strong { display: inline-block; min-width: 140px; color: #0f172a; }

        .badge { display: inline-block; padding: 5px 14px; border-radius: 999px; font-weight: 700; font-size: 13px; }
        .st-PENDING    { color: #b45309; background: #fef3c7; }
        .st-REVIEWING  { color: #1d4ed8; background: #dbeafe; }
        .st-INTERVIEWING { color: #6d28d9; background: #ede9fe; }
        .st-ACCEPTED   { color: #15803d; background: #dcfce7; }
        .st-REJECTED   { color: #b91c1c; background: #fee2e2; }

        .cover-letter-box { background: #f8fafc; padding: 14px; border-radius: 8px;
                            border: 1px dashed #cbd5e1; white-space: pre-wrap; line-height: 1.6;
                            font-size: 14px; color: #334155; }
        .cv-link { display: inline-block; margin-top: 8px; padding: 8px 16px; background: #10b981;
                   color: #fff; border-radius: 8px; text-decoration: none; font-weight: 600; font-size: 14px; }
        .cv-link:hover { background: #059669; }

        /* Offer */
        .offer-list { display: flex; flex-direction: column; gap: 12px; }
        .offer-card { border: 1px solid #e5e7eb; border-radius: 10px; padding: 16px; background: #fffbeb; }
        .offer-card.latest { border-color: #f59e0b; box-shadow: 0 0 0 2px rgba(245,158,11,0.2); }
        .offer-ver { font-size: 11px; font-weight: 700; color: #92400e;
                     text-transform: uppercase; letter-spacing: 0.05em; margin-bottom: 6px; }
        .offer-salary { font-size: 20px; font-weight: 800; color: #1e293b; margin-bottom: 8px; }
        .offer-desc-text { font-size: 14px; color: #475569; white-space: pre-wrap;
                           line-height: 1.5; margin-bottom: 10px; }
        .offer-stat-badge { display: inline-block; padding: 3px 10px; border-radius: 999px;
                            font-size: 12px; font-weight: 700; }
        .stat-PENDING  { background: #fef3c7; color: #92400e; }
        .stat-ACCEPTED { background: #dcfce7; color: #15803d; }
        .stat-REJECTED { background: #fee2e2; color: #b91c1c; }
        .latest-tag { font-size: 11px; background: #f59e0b; color: #fff;
                      padding: 2px 7px; border-radius: 4px; margin-left: 6px; font-weight: 700; }

        /* Interview timeline */
        .interview-list { list-style: none; padding: 0; margin: 0; }
        .interview-item { display: flex; gap: 16px; align-items: flex-start;
                          border-left: 3px solid #e2e8f0; padding: 0 0 20px 16px; position: relative; }
        .interview-item:last-child { border-left-color: transparent; }
        .interview-dot { position: absolute; left: -8px; top: 4px; width: 13px; height: 13px;
                         border-radius: 50%; background: #6366f1; border: 2px solid #fff;
                         box-shadow: 0 0 0 2px #6366f1; }
        .interview-dot.COMPLETED { background: #10b981; box-shadow: 0 0 0 2px #10b981; }
        .interview-dot.CANCELED  { background: #ef4444; box-shadow: 0 0 0 2px #ef4444; }
        .interview-content { flex: 1; }
        .interview-title { font-weight: 700; font-size: 14px; color: #1e293b; }
        .interview-meta { font-size: 13px; color: #64748b; margin-top: 4px; }
        .interview-stat-badge { display: inline-block; padding: 2px 8px; border-radius: 4px; font-size: 12px; font-weight: 600; }
        .SCHEDULED { background: #ede9fe; color: #6d28d9; }
        .COMPLETED { background: #dcfce7; color: #15803d; }
        .CANCELED  { background: #fee2e2; color: #b91c1c; }

        .form-msg.error { background: #fee2e2; color: #b91c1c; padding: 12px; border-radius: 8px; margin-bottom: 16px; }
        .empty-text { color: #94a3b8; font-style: italic; font-size: 14px; }
    </style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>

<main class="page-wrap">
    <div class="page-header">
        <h2 style="margin:0; font-size: 20px;">Chi tiết đơn ứng tuyển</h2>
        <a href="${pageContext.request.contextPath}/candidate/my-applications" class="back-btn">⬅ Quay lại</a>
    </div>

    <c:if test="${not empty error}">
        <div class="form-msg error">${error}</div>
    </c:if>
    <c:if test="${not empty param.offerUpdated}">
        <div class="form-msg success">✅ Đã cập nhật phản hồi Offer thành công!</div>
    </c:if>

    <c:if test="${not empty appDetail}">
        <%-- Thông tin chung --%>
        <div class="card">
            <h3>Thông tin đơn ứng tuyển</h3>
            <div class="info-row"><strong>Vị trí:</strong> ${appDetail.jobTitle}</div>
            <div class="info-row">
                <strong>Ngày nộp:</strong>
                <fmt:parseDate value="${appDetail.appliedAt}" pattern="yyyy-MM-dd'T'HH:mm" var="pd" type="both"/>
                <fmt:formatDate value="${pd}" pattern="dd/MM/yyyy HH:mm"/>
            </div>
            <div class="info-row">
                <strong>Trạng thái:</strong>
                <span class="badge st-${appDetail.stat}">
                    <c:choose>
                        <c:when test="${appDetail.stat == 'PENDING'}">Đang chờ</c:when>
                        <c:when test="${appDetail.stat == 'REVIEWING'}">Đang xem xét</c:when>
                        <c:when test="${appDetail.stat == 'INTERVIEWING'}">Đang phỏng vấn</c:when>
                        <c:when test="${appDetail.stat == 'ACCEPTED'}">Trúng tuyển 🎉</c:when>
                        <c:when test="${appDetail.stat == 'REJECTED'}">Không phù hợp</c:when>
                        <c:otherwise>${appDetail.stat}</c:otherwise>
                    </c:choose>
                </span>
            </div>
        </div>

        <%-- CV đính kèm --%>
        <div class="card">
            <h3>CV đã nộp</h3>
            <c:choose>
                <c:when test="${not empty appDetail.cvSnapUrl}">
                    <p style="font-size:14px; color:#475569;">CV của bạn đã được lưu lại tại thời điểm ứng tuyển.</p>
                    <a href="${appDetail.cvSnapUrl}" target="_blank" class="cv-link">📄 Xem CV</a>
                </c:when>
                <c:otherwise>
                    <p class="empty-text">Không có CV đính kèm.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <%-- Cover letter --%>
        <div class="card">
            <h3>Thư giới thiệu (Cover Letter)</h3>
            <c:choose>
                <c:when test="${not empty appDetail.coverLetter}">
                    <div class="cover-letter-box">${appDetail.coverLetter}</div>
                </c:when>
                <c:otherwise>
                    <p class="empty-text">Bạn không viết thư giới thiệu.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <%-- Lịch phỏng vấn --%>
        <div class="card">
            <h3>📅 Lịch phỏng vấn</h3>
            <c:choose>
                <c:when test="${empty interviews}">
                    <p class="empty-text">Chưa có lịch phỏng vấn nào được sắp xếp.</p>
                </c:when>
                <c:otherwise>
                    <ul class="interview-list">
                        <c:forEach items="${interviews}" var="iv">
                            <li class="interview-item">
                                <div class="interview-dot ${iv.stat}"></div>
                                <div class="interview-content">
                                    <div class="interview-title">
                                        ${iv.title}
                                        <span class="interview-stat-badge ${iv.stat}">
                                            <c:choose>
                                                <c:when test="${iv.stat == 'SCHEDULED'}">Đã lên lịch</c:when>
                                                <c:when test="${iv.stat == 'COMPLETED'}">Đã hoàn thành</c:when>
                                                <c:when test="${iv.stat == 'CANCELED'}">Đã huỷ</c:when>
                                                <c:otherwise>${iv.stat}</c:otherwise>
                                            </c:choose>
                                        </span>
                                    </div>
                                    <div class="interview-meta">
                                        🕐
                                        <fmt:parseDate value="${iv.startAt}" pattern="yyyy-MM-dd'T'HH:mm" var="st" type="both"/>
                                        <fmt:formatDate value="${st}" pattern="dd/MM/yyyy HH:mm"/>
                                        <c:if test="${not empty iv.endAt}">
                                            &nbsp;–&nbsp;
                                            <fmt:parseDate value="${iv.endAt}" pattern="yyyy-MM-dd'T'HH:mm" var="et" type="both"/>
                                            <fmt:formatDate value="${et}" pattern="HH:mm"/>
                                        </c:if>
                                        &nbsp;|&nbsp; ${iv.mode}
                                    </div>
                                    <c:if test="${not empty iv.linkMeet}">
                                        <div class="interview-meta">
                                            🔗 <a href="${iv.linkMeet}" target="_blank">${iv.linkMeet}</a>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty iv.loc}">
                                        <div class="interview-meta">📍 ${iv.loc}</div>
                                    </c:if>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>

        <%-- Offer từ nhà tuyển dụng --%>
        <div class="card">
            <h3>📨 Offer từ nhà tuyển dụng</h3>
            <c:choose>
                <c:when test="${empty offers}">
                    <p class="empty-text">Chưa có offer nào được gửi đến bạn.</p>
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
                                            <fmt:formatNumber value="${off.salary}" type="number" groupingUsed="true"/> VNĐ / tháng
                                        </c:when>
                                        <c:otherwise>Mức lương: Thỏa thuận</c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="offer-desc-text">${off.desc}</div>
                                <span class="offer-stat-badge stat-${off.stat}">
                                    <c:choose>
                                        <c:when test="${off.stat == 'PENDING'}">⏳ Đang chờ phản hồi của bạn</c:when>
                                        <c:when test="${off.stat == 'ACCEPTED'}">✅ Bạn đã chấp nhận</c:when>
                                        <c:when test="${off.stat == 'REJECTED'}">❌ Bạn đã từ chối</c:when>
                                        <c:otherwise>${off.stat}</c:otherwise>
                                    </c:choose>
                                </span>
                                <%-- Nút phản hồi: chỉ hiện khi offer đang PENDING --%>
                                <c:if test="${off.stat == 'PENDING'}">
                                    <div style="margin-top:12px; display:flex; gap:10px; flex-wrap:wrap;">
                                        <form action="${pageContext.request.contextPath}/candidate/application-detail" method="post">
                                            <input type="hidden" name="action" value="respondOffer">
                                            <input type="hidden" name="offerId" value="${off.offerId}">
                                            <input type="hidden" name="newStat" value="ACCEPTED">
                                            <input type="hidden" name="jobAppId" value="${jobAppId}">
                                            <button type="submit"
                                                style="background:#10b981;color:#fff;border:none;padding:9px 18px;
                                                       border-radius:8px;font-weight:700;cursor:pointer;font-size:14px;">
                                                ✅ Chấp nhận Offer
                                            </button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/candidate/application-detail" method="post">
                                            <input type="hidden" name="action" value="respondOffer">
                                            <input type="hidden" name="offerId" value="${off.offerId}">
                                            <input type="hidden" name="newStat" value="REJECTED">
                                            <input type="hidden" name="jobAppId" value="${jobAppId}">
                                            <button type="submit"
                                                onclick="return confirm('Bạn chắc chắn muốn từ chối offer này?')"
                                                style="background:#ef4444;color:#fff;border:none;padding:9px 18px;
                                                       border-radius:8px;font-weight:700;cursor:pointer;font-size:14px;">
                                                ❌ Từ chối
                                            </button>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
