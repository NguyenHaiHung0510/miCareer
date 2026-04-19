<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Feedback phỏng vấn - MiCareer HR</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <style>
        .page-wrap { max-width: 860px; margin: 36px auto; padding: 0 16px; }
        .page-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 24px; }
        .back-btn { padding: 8px 16px; background: #f1f5f9; color: #475569;
                    text-decoration: none; border-radius: 8px; font-weight: 600; font-size: 14px; }
        .back-btn:hover { background: #e2e8f0; }

        .card { background: #fff; border: 1px solid #e5e7eb; border-radius: 14px;
                padding: 24px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); margin-bottom: 20px; }
        .card h3 { margin: 0 0 18px; font-size: 15px; font-weight: 700; color: #1e293b;
                   border-bottom: 1px solid #f1f5f9; padding-bottom: 10px; }

        .iv-info { background: #f8fafc; border-radius: 10px; padding: 14px 16px;
                   margin-bottom: 20px; font-size: 14px; color: #334155; }
        .iv-info strong { color: #1e293b; }

        /* Form */
        .form-group { margin-bottom: 14px; }
        .form-group label { display: block; font-size: 13px; font-weight: 600;
                            color: #374151; margin-bottom: 5px; }
        .form-group input, .form-group textarea {
            width: 100%; padding: 9px 12px; border: 1px solid #d1d5db;
            border-radius: 8px; font-size: 14px; box-sizing: border-box;
        }
        .form-group input:focus, .form-group textarea:focus {
            border-color: #6366f1; outline: none;
        }
        .score-hint { font-size: 12px; color: #94a3b8; margin-top: 4px; }
        .btn-submit { background: #6366f1; color: #fff; border: none; padding: 10px 24px;
                      border-radius: 8px; font-size: 14px; font-weight: 700; cursor: pointer; }
        .btn-submit:hover { background: #4f46e5; }

        /* Feedback cards */
        .fb-list { display: flex; flex-direction: column; gap: 14px; }
        .fb-card { background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 10px; padding: 16px; }
        .fb-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px; }
        .fb-score { font-size: 22px; font-weight: 800; color: #6366f1; }
        .fb-score span { font-size: 13px; font-weight: 400; color: #94a3b8; }
        .fb-time { font-size: 12px; color: #94a3b8; }
        .fb-cmt { font-size: 14px; color: #334155; line-height: 1.6; white-space: pre-wrap; }

        .form-msg { padding: 11px 14px; border-radius: 8px; margin-bottom: 16px; font-size: 14px; }
        .form-msg.success { background: #dcfce7; color: #15803d; }
        .form-msg.error   { background: #fee2e2; color: #b91c1c; }
        .empty-text { color: #94a3b8; font-style: italic; font-size: 14px; }
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp"/>

<main class="page-wrap">
    <div class="page-header">
        <h2 style="margin:0; font-size: 20px;">📝 Feedback phỏng vấn</h2>
        <c:if test="${not empty interview}">
            <a href="${pageContext.request.contextPath}/hr/interview?jobAppId=${interview.jobAppId}" class="back-btn">
                ⬅ Về phỏng vấn
            </a>
        </c:if>
    </div>

    <c:if test="${not empty param.success}">
        <div class="form-msg success">✅ Đã lưu feedback thành công!</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="form-msg error">❌ ${error}</div>
    </c:if>

    <c:if test="${not empty interview}">
        <div class="iv-info">
            <strong>Vòng phỏng vấn:</strong> ${interview.title}
            &nbsp;|&nbsp;
            🕐
            <fmt:parseDate value="${interview.startAt}" pattern="yyyy-MM-dd'T'HH:mm" var="st" type="both"/>
            <fmt:formatDate value="${st}" pattern="dd/MM/yyyy HH:mm"/>
            &nbsp;|&nbsp; ${interview.mode}
        </div>
    </c:if>

    <%-- Form nhập feedback --%>
    <div class="card">
        <h3>Nhập đánh giá</h3>
        <form action="${pageContext.request.contextPath}/hr/interview-feedback" method="post">
            <input type="hidden" name="intervId" value="${intervId}">

            <div class="form-group">
                <label for="score">Điểm đánh giá (0 – 10)</label>
                <input type="number" id="score" name="score" min="0" max="10" step="0.1"
                       placeholder="VD: 8.5">
                <div class="score-hint">Để trống nếu không muốn cho điểm.</div>
            </div>
            <div class="form-group">
                <label for="cmt">Nhận xét chi tiết *</label>
                <textarea id="cmt" name="cmt" rows="6"
                          placeholder="Mô tả điểm mạnh, điểm yếu, kết luận..." required></textarea>
            </div>
            <button type="submit" class="btn-submit">Lưu feedback</button>
        </form>
    </div>

    <%-- Danh sách feedback đã có --%>
    <div class="card">
        <h3>Các đánh giá đã ghi nhận (${feedbacks.size()} đánh giá)</h3>
        <c:choose>
            <c:when test="${empty feedbacks}">
                <p class="empty-text">Chưa có đánh giá nào cho vòng phỏng vấn này.</p>
            </c:when>
            <c:otherwise>
                <div class="fb-list">
                    <c:forEach items="${feedbacks}" var="fb">
                        <div class="fb-card">
                            <div class="fb-header">
                                <div class="fb-score">
                                    <c:choose>
                                        <c:when test="${not empty fb.score}">${fb.score}<span>/10</span></c:when>
                                        <c:otherwise><span style="font-size:14px">Không cho điểm</span></c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="fb-time">
                                    <fmt:parseDate value="${fb.subAt}" pattern="yyyy-MM-dd'T'HH:mm" var="sat" type="both"/>
                                    <fmt:formatDate value="${sat}" pattern="dd/MM/yyyy HH:mm"/>
                                </div>
                            </div>
                            <div class="fb-cmt">${fb.cmt}</div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
