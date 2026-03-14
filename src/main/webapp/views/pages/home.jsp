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
        <section class="hero-section">
            <div class="hero-section__overlay"></div>

            <div class="container hero-section__content">
                <span class="hero-section__badge">Nền tảng tuyển dụng hàng đầu</span>

                <h1 class="hero-section__title">Tìm việc làm mơ ước của bạn</h1>
                <p class="hero-section__description">
                    MiCareer kết nối bạn với hàng ngàn cơ hội việc làm từ các công ty hàng đầu.
                    Tìm kiếm, ứng tuyển và phát triển sự nghiệp của bạn ngay hôm nay!
                </p>

                <form action="${pageContext.request.contextPath}/job-list" method="get" class="search-box">
                    <div class="search-box__field">
                        <span class="search-box__icon">💼</span>
                        <input
                            class="search-box__input"
                            type="text"
                            name="keyword"
                            placeholder="Nhập vị trí công việc..."
                        >
                    </div>

                    <div class="search-box__field">
                        <span class="search-box__icon">📍</span>
                        <input
                            class="search-box__input"
                            type="text"
                            name="location"
                            placeholder="Nhập địa điểm..."
                        >
                    </div>

                    <button type="submit" class="button button--primary search-box__button">
                        Tìm kiếm
                    </button>
                </form>
            </div>
        </section>

        <!-- Việc làm nổi bật -->
        <section class="content-section">
            <div class="container">
                <div class="section-header">
                    <div class="section-header__left">
                        <h2 class="section-title">Việc làm nổi bật</h2>
                    </div>

                    <a class="section-link" href="${pageContext.request.contextPath}/job-list">
                        Xem tất cả
                    </a>
                </div>

                <div class="card-grid card-grid--3">
                    <article class="info-card info-card--job">
                        <div class="info-card__content">
                            <h3 class="info-card__title">Frontend Developer</h3>
                            <p class="info-card__subtitle">VNG</p>

                            <div class="info-card__details">
                                <p class="info-card__detail">
                                    <strong>Địa điểm:</strong> TP. Hồ Chí Minh
                                </p>
                                <p class="info-card__detail">
                                    <strong>Mức lương:</strong> 12 - 20 triệu
                                </p>
                                <p class="info-card__detail">
                                    <strong>Hạn nộp:</strong> 05/05/2026
                                </p>
                            </div>
                        </div>

                        <div class="info-card__footer">
                            <a
                                class="button button--soft"
                                href="${pageContext.request.contextPath}/job-detail?id=${job.id}"
                            >
                                Xem chi tiết
                            </a>
                        </div>
                    </article>
                </div>
            </div>
        </section>

        <!-- Các công ty -->
        <section class="content-section content-section--light">
            <div class="container">
                <div class="section-header">
                    <div class="section-header__left">
                        <h2 class="section-title">Các công ty IT</h2>
                    </div>
                </div>

                <div class="card-grid card-grid--3">
                    <article class="info-card info-card--company">
                        <div class="info-card__top">
                            <div class="info-card__image-wrap">
                                <img
                                    src="https://via.placeholder.com/80x80"
                                    alt="FPT Software"
                                    class="info-card__image"
                                >
                            </div>

                            <div class="info-card__content">
                                <h3 class="info-card__title">FPT SOFTWARE</h3>
                                <p class="info-card__subtitle">IT - Phần mềm</p>
                            </div>
                        </div>

                        <div class="info-card__footer">
                            <span class="info-badge">
                                <span>💼</span>
                                <span>39 việc làm</span>
                            </span>
                            <a href="#" class="button button--primary button--follow">+ Theo dõi</a>
                        </div>
                    </article>

                    <article class="info-card info-card--company">
                        <div class="info-card__top">
                            <div class="info-card__image-wrap">
                                <img
                                    src="https://via.placeholder.com/80x80"
                                    alt="HTI"
                                    class="info-card__image"
                                >
                            </div>

                            <div class="info-card__content">
                                <h3 class="info-card__title">CÔNG TY CỔ PHẦN ĐẦU TƯ VÀ CÔNG NGHỆ HTI</h3>
                                <p class="info-card__subtitle">IT - Phần mềm</p>
                            </div>
                        </div>

                        <div class="info-card__footer">
                            <span class="info-badge">
                                <span>💼</span>
                                <span>8 việc làm</span>
                            </span>
                            <a href="#" class="button button--primary button--follow">+ Theo dõi</a>
                        </div>
                    </article>

                    <article class="info-card info-card--company">
                        <div class="info-card__top">
                            <div class="info-card__image-wrap">
                                <img
                                    src="https://via.placeholder.com/80x80"
                                    alt="Synnex FPT"
                                    class="info-card__image"
                                >
                            </div>

                            <div class="info-card__content">
                                <h3 class="info-card__title">CÔNG TY TNHH PHÂN PHỐI SYNNEX FPT</h3>
                                <p class="info-card__subtitle">IT - Phần mềm</p>
                            </div>
                        </div>

                        <div class="info-card__footer">
                            <span class="info-badge">
                                <span>💼</span>
                                <span>3 việc làm</span>
                            </span>
                            <a href="#" class="button button--primary button--follow">+ Theo dõi</a>
                        </div>
                    </article>
                </div>
            </div>
        </section>

    </main>

    <jsp:include page="/views/common/footer.jsp" />

</body>
</html>