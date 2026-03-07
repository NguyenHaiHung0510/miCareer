<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ tuyển dụng</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css">
</head>
<body>

    <jsp:include page="/views/common/header.jsp" />

    <main class="home-page">

        <!-- Hero -->
        <section class="home-hero">
            <div class="home-hero__overlay"></div>

            <div class="container home-hero__content">
                <span class="home-hero__badge">Nền tảng tuyển dụng hàng đầu</span>

                <h1 class="home-hero__title">Tìm việc làm mơ ước của bạn</h1>
                <p class="home-hero__description">
                    MiCareer kết nối bạn với hàng ngàn cơ hội việc làm từ các công ty hàng đầu.
                    Tìm kiếm, ứng tuyển và phát triển sự nghiệp của bạn ngay hôm nay!
                </p>

                <form action="${pageContext.request.contextPath}/job-list" method="get" class="hero-search">
                    <div class="hero-search__field">
                        <span class="hero-search__icon">💼</span>
                        <input class="hero-search__input" type="text" name="keyword" placeholder="Nhập vị trí công việc...">
                    </div>

                    <div class="hero-search__field">
                        <span class="hero-search__icon">📍</span>
                        <input class="hero-search__input" type="text" name="location" placeholder="Nhập địa điểm...">
                    </div>

                    <button type="submit" class="hero-search__button">Tìm kiếm</button>
                </form>
            </div>
        </section>

        <!-- Việc làm nổi bật -->
        <section class="home-section ">
            <div class="container">
                <div class="home-section__header">
                    <h2 class="home-section__title">Việc làm nổi bật</h2>
                    <a class="home-section__link" href="${pageContext.request.contextPath}/job-list">Xem tất cả</a>
                </div>

                <div class="job-grid">
                    <c:choose>
                        <c:when test="${not empty featuredJobs}">
                            <c:forEach var="job" items="${featuredJobs}">
                                <article class="job-card">
                                    <h3 class="job-card__title">${job.title}</h3>
                                    <p class="job-card__company">${job.companyName}</p>
                                    <p class="job-card__meta"><strong>Địa điểm:</strong> ${job.location}</p>
                                    <p class="job-card__meta"><strong>Mức lương:</strong> ${job.salary}</p>
                                    <p class="job-card__meta"><strong>Hạn nộp:</strong> ${job.deadline}</p>
                                    <a class="job-card__button"
                                       href="${pageContext.request.contextPath}/job-detail?id=${job.id}">
                                        Xem chi tiết
                                    </a>
                                </article>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p class="job-grid__empty">Hiện chưa có việc làm nổi bật.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </section>

        <!-- Công ty nổi bật -->
        <section class="company-section">
            <div class="company-section__header">
                <h2 class="home-section__title">Các công ty</h2>
                <div class="company-section__tabs">
                <button class="company-section__tab">Tất cả</button>
                <button class="company-section__tab">Ngân hàng</button>
                <button class="company-section__tab">Bất động sản</button>
                <button class="company-section__tab">Xây dựng</button>
                <button class="company-section__tab is-active">IT - Phần mềm</button>
                <button class="company-section__tab">Tài chính</button>
                <button class="company-section__tab">Bán lẻ - Hàng tiêu dùng - FMCG</button>
                <button class="company-section__tab">Sản xuất</button>
                </div>

                <div class="company-section__controls">
                <button class="company-section__control" aria-label="Previous">
                    &#8249;
                </button>
                <button class="company-section__control" aria-label="Next">
                    &#8250;
                </button>
                </div>
            </div>

            <div class="company-section__body">
                <button class="company-section__side company-section__side--left" aria-label="Previous">
                &#8249;
                </button>

                <div class="company-section__grid">
                <article class="company-card">
                    <div class="company-card__top">
                    <div class="company-card__logo-box">
                        <img src="https://via.placeholder.com/80x80" alt="FPT Software" class="company-card__logo">
                    </div>

                    <div class="company-card__info">
                        <h3 class="company-card__name">FPT SOFTWARE</h3>
                        <p class="company-card__industry">IT - Phần mềm</p>
                    </div>
                    </div>

                    <div class="company-card__bottom">
                    <div class="company-card__jobs">
                        <span class="company-card__jobs-icon">💼</span>
                        <span>39 việc làm</span>
                    </div>
                    <a href="#" class="company-card__follow">+ Theo dõi</a>
                    </div>
                </article>

                <article class="company-card">
                    <div class="company-card__top">
                    <div class="company-card__logo-box">
                        <img src="https://via.placeholder.com/80x80" alt="HTI" class="company-card__logo">
                    </div>

                    <div class="company-card__info">
                        <h3 class="company-card__name">CÔNG TY CỔ PHẦN ĐẦU TƯ VÀ CÔNG NGHỆ HTI</h3>
                        <p class="company-card__industry">IT - Phần mềm</p>
                    </div>
                    </div>

                    <div class="company-card__bottom">
                    <div class="company-card__jobs">
                        <span class="company-card__jobs-icon">💼</span>
                        <span>8 việc làm</span>
                    </div>

                    <a href="#" class="company-card__follow">+ Theo dõi</a>
                    </div>
                </article>

                <article class="company-card">
                    <div class="company-card__top">
                    <div class="company-card__logo-box">
                        <img src="https://via.placeholder.com/80x80" alt="Synnex FPT" class="company-card__logo">
                    </div>

                    <div class="company-card__info">
                        <h3 class="company-card__name">CÔNG TY TNHH PHÂN PHỐI SYNNEX FPT</h3>
                        <p class="company-card__industry">IT - Phần mềm</p>
                    </div>
                    </div>

                    <div class="company-card__bottom">
                    <div class="company-card__jobs">
                        <span class="company-card__jobs-icon">💼</span>
                        <span>3 việc làm</span>
                    </div>
                    <a href="#" class="company-card__follow">+ Theo dõi</a>
                    </div>
                </article>
                </div>

                <button class="company-section__side company-section__side--right" aria-label="Next">
                &#8250;
                </button>
            </div>
        </section>

        <!-- Top ngành nghề nổi bật -->
        <section class="home-section">
            <div class="container">
                <div class="category-showcase__header">
                    <div class="category-showcase__intro">
                        <h2 class="home-section__title">Top ngành nghề nổi bật</h2>
                        <p class="home-section__description">
                            Bạn muốn tìm việc mới? Xem danh sách việc làm
                            <a class="home-section__inline-link" href="${pageContext.request.contextPath}/job-list">tại đây</a>
                        </p>
                    </div>

                    <div class="category-showcase__nav">
                        <button type="button" class="category-showcase__nav-button" aria-label="Previous">&#10094;</button>
                        <button type="button" class="category-showcase__nav-button category-showcase__nav-button--active" aria-label="Next">&#10095;</button>
                    </div>
                </div>

                <div class="category-grid">
                    <a href="${pageContext.request.contextPath}/job-list?category=sales" class="category-card">
                        <div class="category-card__icon"></div>
                        <h3 class="category-card__title">Kinh doanh - Bán hàng</h3>
                        <span class="category-card__count">11.077 việc làm</span>
                    </a>

                    <a href="${pageContext.request.contextPath}/job-list?category=marketing" class="category-card">
                        <div class="category-card__icon"></div>
                        <h3 class="category-card__title">Marketing - PR - Quảng cáo</h3>
                        <span class="category-card__count">7.529 việc làm</span>
                    </a>

                    <a href="${pageContext.request.contextPath}/job-list?category=customer-service" class="category-card">
                        <div class="category-card__icon"></div>
                        <h3 class="category-card__title">Chăm sóc khách hàng</h3>
                        <span class="category-card__count">2.826 việc làm</span>
                    </a>

                </div>
            </div>
        </section>

    </main>

    <jsp:include page="/views/common/footer.jsp" />

</body>
</html>