<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý phỏng vấn - MiCareer HR</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <style>
        .page-wrap { max-width: 960px; margin: 36px auto; padding: 0 16px; }
        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px; }
        .back-btn { padding: 8px 16px; background: #f1f5f9; color: #475569;
                    text-decoration: none; border-radius: 8px; font-weight: 600; font-size: 14px; }
        .back-btn:hover { background: #e2e8f0; }

        .layout { display: grid; grid-template-columns: 1fr 1.2fr; gap: 24px; }
        @media (max-width: 768px) { .layout { grid-template-columns: 1fr; } }

        .card { background: #fff; border: 1px solid #e5e7eb; border-radius: 14px;
                padding: 24px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); margin-bottom: 20px; }
        .card h3 { margin: 0 0 18px; font-size: 15px; font-weight: 700; color: #1e293b;
                   border-bottom: 1px solid #f1f5f9; padding-bottom: 10px; }

        /* Form */
        .form-group { margin-bottom: 14px; }
        .form-group label { display: block; font-size: 13px; font-weight: 600;
                            color: #374151; margin-bottom: 5px; }
        .form-group input, .form-group select, .form-group textarea {
            width: 100%; padding: 9px 12px; border: 1px solid #d1d5db;
            border-radius: 8px; font-size: 14px; box-sizing: border-box;
            transition: border-color 0.2s;
        }
        .form-group input:focus, .form-group select:focus, .form-group textarea:focus {
            border-color: #6366f1; outline: none;
        }
        .btn-submit { width: 100%; background: #6366f1; color: #fff; border: none;
                      padding: 11px; border-radius: 8px; font-size: 14px; font-weight: 700;
                      cursor: pointer; transition: background 0.2s; }
        .btn-submit:hover { background: #4f46e5; }

        /* Interview list */
        .iv-list { list-style: none; padding: 0; margin: 0; }
        .iv-item { padding: 14px 0; border-bottom: 1px solid #f1f5f9; }
        .iv-item:last-child { border-bottom: none; }
        .iv-title { font-weight: 700; font-size: 14px; color: #1e293b; margin-bottom: 4px; }
        .iv-meta { font-size: 13px; color: #64748b; margin-bottom: 6px; }

        .stat-badge { display: inline-block; padding: 2px 9px; border-radius: 4px;
                      font-size: 12px; font-weight: 600; }
        .SCHEDULED { background: #ede9fe; color: #6d28d9; }
        .COMPLETED { background: #dcfce7; color: #15803d; }
        .CANCELED  { background: #fee2e2; color: #b91c1c; }

        .iv-actions { display: flex; gap: 8px; flex-wrap: wrap; margin-top: 8px; }
        .btn-sm { padding: 5px 10px; border: none; border-radius: 6px; font-size: 12px;
                  font-weight: 600; cursor: pointer; text-decoration: none; display: inline-block; }
        .btn-complete { background: #10b981; color: #fff; }
        .btn-complete:hover { background: #059669; }
        .btn-cancel   { background: #ef4444; color: #fff; }
        .btn-cancel:hover { background: #dc2626; }
        .btn-feedback { background: #6366f1; color: #fff; }
        .btn-feedback:hover { background: #4f46e5; }

        .form-msg { padding: 11px 14px; border-radius: 8px; margin-bottom: 16px; font-size: 14px; }
        .form-msg.success { background: #dcfce7; color: #15803d; }
        .form-msg.error   { background: #fee2e2; color: #b91c1c; }
        .empty-text { color: #94a3b8; font-style: italic; font-size: 14px; }

        .candidate-info { font-size: 14px; color: #475569; margin-bottom: 16px; }
        .candidate-info strong { color: #0f172a; }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<main class="page-wrap">
    <div class="page-header">
        <h2 style="margin:0; font-size: 20px;">📅 Quản lý phỏng vấn</h2>
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
        <div class="form-msg success">✅ Thao tác thành công!</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="form-msg error">❌ ${error}</div>
    </c:if>

    <div class="layout">
        <%-- Form tạo lịch phỏng vấn mới --%>
        <div>
            <div class="card">
                <h3>➕ Tạo lịch phỏng vấn mới</h3>
                <form action="${pageContext.request.contextPath}/hr/interview" method="post">
                    <input type="hidden" name="jobAppId" value="${jobAppId}">
                    <input type="hidden" name="action" value="create">

                    <div class="form-group">
                        <label for="title">Tên vòng phỏng vấn *</label>
                        <input type="text" id="title" name="title" placeholder="VD: Vòng 1 - Kỹ thuật" required>
                    </div>
                    <div class="form-group">
                        <label for="startAt">Thời gian bắt đầu *</label>
                        <input type="datetime-local" id="startAt" name="startAt" required>
                    </div>
                    <div class="form-group">
                        <label for="endAt">Thời gian kết thúc</label>
                        <input type="datetime-local" id="endAt" name="endAt">
                    </div>
                    <div class="form-group">
                        <label for="mode">Hình thức</label>
                        <select id="mode" name="mode">
                            <option value="Online">Online</option>
                            <option value="Offline">Offline (Tại văn phòng)</option>
                            <option value="Hybrid">Hybrid</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="linkMeet">Link họp (nếu Online)</label>
                        <input type="text" id="linkMeet" name="linkMeet" placeholder="https://meet.google.com/...">
                    </div>
                    <div class="form-group">
                        <label for="loc">Địa điểm (nếu Offline)</label>
                        <input type="text" id="loc" name="loc" placeholder="VD: Tầng 5, 123 Nguyễn Huệ, Q.1">
                    </div>
                    <button type="submit" class="btn-submit">Tạo lịch phỏng vấn</button>
                </form>
            </div>
        </div>

        <%-- Danh sách vòng phỏng vấn --%>
        <div>
            <div class="card">
                <h3>Danh sách vòng phỏng vấn</h3>
                <c:choose>
                    <c:when test="${empty interviews}">
                        <p class="empty-text">Chưa có vòng phỏng vấn nào.</p>
                    </c:when>
                    <c:otherwise>
                        <ul class="iv-list">
                            <c:forEach items="${interviews}" var="iv">
                                <li class="iv-item">
                                    <div class="iv-title">
                                        ${iv.title}
                                        <span class="stat-badge ${iv.stat}">
                                            <c:choose>
                                                <c:when test="${iv.stat == 'SCHEDULED'}">Đã lên lịch</c:when>
                                                <c:when test="${iv.stat == 'COMPLETED'}">Hoàn thành</c:when>
                                                <c:when test="${iv.stat == 'CANCELED'}">Đã huỷ</c:when>
                                                <c:otherwise>${iv.stat}</c:otherwise>
                                            </c:choose>
                                        </span>
                                    </div>
                                    <div class="iv-meta">
                                        🕐
                                        <fmt:parseDate value="${iv.startAt}" pattern="yyyy-MM-dd'T'HH:mm" var="st" type="both"/>
                                        <fmt:formatDate value="${st}" pattern="dd/MM/yyyy HH:mm"/>
                                        <c:if test="${not empty iv.endAt}">
                                            &nbsp;–&nbsp;
                                            <fmt:parseDate value="${iv.endAt}" pattern="yyyy-MM-dd'T'HH:mm" var="et" type="both"/>
                                            <fmt:formatDate value="${et}" pattern="HH:mm"/>
                                        </c:if>
                                    </div>
                                    <div class="iv-meta">
                                        📌 ${iv.mode}
                                        <c:if test="${not empty iv.linkMeet}">
                                            &nbsp;|&nbsp; <a href="${iv.linkMeet}" target="_blank">Link họp</a>
                                        </c:if>
                                        <c:if test="${not empty iv.loc}">
                                            &nbsp;|&nbsp; 📍 ${iv.loc}
                                        </c:if>
                                    </div>
                                    <div class="iv-actions">
                                        <a href="${pageContext.request.contextPath}/hr/interview-feedback?intervId=${iv.intervId}"
                                           class="btn-sm btn-feedback">📝 Feedback</a>
                                        <c:if test="${iv.stat == 'SCHEDULED'}">
                                            <form action="${pageContext.request.contextPath}/hr/interview" method="post" style="display:inline">
                                                <input type="hidden" name="jobAppId" value="${jobAppId}">
                                                <input type="hidden" name="action" value="updateStat">
                                                <input type="hidden" name="intervId" value="${iv.intervId}">
                                                <input type="hidden" name="stat" value="COMPLETED">
                                                <button type="submit" class="btn-sm btn-complete">✔ Hoàn thành</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/hr/interview" method="post" style="display:inline">
                                                <input type="hidden" name="jobAppId" value="${jobAppId}">
                                                <input type="hidden" name="action" value="updateStat">
                                                <input type="hidden" name="intervId" value="${iv.intervId}">
                                                <input type="hidden" name="stat" value="CANCELED">
                                                <button type="submit" class="btn-sm btn-cancel"
                                                        onclick="return confirm('Xác nhận huỷ lịch này?')">✖ Huỷ</button>
                                            </form>
                                        </c:if>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
