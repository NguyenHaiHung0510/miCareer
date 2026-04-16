<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>HR Dashboard</title>

        <!-- CSS dùng chung -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/hr_dashboard.css">

        <style>
            .dashboard {
                padding: 30px 0;
            }

            .dashboard h2 {
                margin-bottom: 20px;
                font-size: 24px;
                font-weight: 700;
            }

            /* FILTER */
            .filter-box {
                background: #fff;
                padding: 16px;
                border-radius: 12px;
                margin-bottom: 20px;
                display: flex;
                gap: 12px;
                flex-wrap: wrap;
                box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            }

            .filter-box input,
            .filter-box select {
                padding: 10px;
                border-radius: 8px;
                border: 1px solid #ddd;
            }

            .filter-box button {
                padding: 10px 16px;
                border-radius: 8px;
                border: none;
                background: #1d4ed8;
                color: white;
                font-weight: 600;
                cursor: pointer;
            }

            /* CARD */
            .card-grid {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
                gap: 20px;
                margin-bottom: 30px;
            }

            .card {
                background: #fff;
                padding: 20px;
                border-radius: 14px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.06);
            }

            .card h3 {
                font-size: 14px;
                color: #6b7280;
                margin-bottom: 8px;
            }

            .card .value {
                font-size: 26px;
                font-weight: 700;
                color: #111827;
            }

            /* TABLE */
            .table-box {
                background: #fff;
                padding: 20px;
                border-radius: 14px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.06);
                margin-bottom: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            table th, table td {
                padding: 12px;
                border-bottom: 1px solid #eee;
                text-align: left;
            }

            table th {
                background: #f9fafb;
                font-weight: 600;
            }

            .status-chip {
                padding: 6px 10px;
                border-radius: 8px;
                font-size: 12px;
                font-weight: 600;
            }

            .status-PENDING {
                background: #fef3c7;
                color: #92400e;
            }
            .status-ACCEPTED {
                background: #dcfce7;
                color: #166534;
            }
            .status-REJECTED {
                background: #fee2e2;
                color: #991b1b;
            }
        </style>
    </head>

    <body>

        <!-- HEADER -->
        <jsp:include page="/common/header.jsp"/>

        <main class="container dashboard">

            <h2>📊 HR Dashboard</h2>

            <!-- FILTER -->
            <form class="filter-box" method="get">
                <input type="date" name="fromDate" value="${fromDate}">
                <input type="date" name="toDate" value="${toDate}">

                <select name="status">
                    <option value="">-- Trạng thái job --</option>
                    <option value="PUBLISHED" ${status == 'PUBLISHED' ? 'selected' : ''}>Published</option>
                    <option value="DRAFT" ${status == 'DRAFT' ? 'selected' : ''}>Draft</option>
                    <option value="CLOSED" ${status == 'CLOSED' ? 'selected' : ''}>Closed</option>
                </select>

                <button type="submit">Lọc</button>
            </form>

            <!-- CARDS -->
            <div class="card-grid">
                <div class="card">
                    <h3>Tổng số Job</h3>
                    <div class="value">${totalJobs}</div>
                </div>

                <div class="card">
                    <h3>Tổng Applications</h3>
                    <div class="value">${totalApplications}</div>
                </div>

                <div class="card">
                    <h3>Applications hôm nay</h3>
                    <div class="value">${applicationsToday}</div>
                </div>
            </div>

            <!-- TOP JOBS -->
            <div class="table-box">
                <h3>🔥 Top Jobs nhiều ứng tuyển</h3>
                <table>
                    <tr>
                        <th>Job Title</th>
                        <th>Số ứng tuyển</th>
                    </tr>

                    <c:forEach var="job" items="${topJobs}">
                        <tr>
                            <td>${job[0]}</td>
                            <td>${job[1]}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <!-- STATS -->
            <div class="table-box">
                <h3>📈 Thống kê theo trạng thái</h3>
                <table>
                    <tr>
                        <th>Status</th>
                        <th>Số lượng</th>
                    </tr>

                    <c:forEach var="s" items="${stats}">
                        <tr>
                            <td>
                                <span class="status-chip status-${s[0]}">
                                    ${s[0]}
                                </span>
                            </td>
                            <td>${s[1]}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </main>

        <!-- FOOTER -->
        <jsp:include page="/common/footer.jsp"/>

    </body>
</html>