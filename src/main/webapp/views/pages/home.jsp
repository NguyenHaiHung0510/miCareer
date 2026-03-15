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
                        <select class="search-box__input search-box__select" name="location">
                            <option value="">Chọn tỉnh/thành phố</option><option value="Tuyên Quang">Tuyên Quang</option>
                            <option value="Lào Cai">Lào Cai</option><option value="Thái Nguyên">Thái Nguyên</option>
                            <option value="Phú Thọ">Phú Thọ</option><option value="Bắc Ninh">Bắc Ninh</option>
                            <option value="Hưng Yên">Hưng Yên</option><option value="Hải Phòng">Hải Phòng</option>
                            <option value="Ninh Bình">Ninh Bình</option><option value="Quảng Trị">Quảng Trị</option>
                            <option value="Đà Nẵng">Đà Nẵng</option><option value="Quảng Ngãi">Quảng Ngãi</option>
                            <option value="Gia Lai">Gia Lai</option><option value="Khánh Hòa">Khánh Hòa</option>
                            <option value="Lâm Đồng">Lâm Đồng</option><option value="Đắk Lắk">Đắk Lắk</option>
                            <option value="Thành phố Hồ Chí Minh">Thành phố Hồ Chí Minh</option><option value="Đồng Nai">Đồng Nai</option>
                            <option value="Tây Ninh">Tây Ninh</option><option value="Cần Thơ">Cần Thơ</option>
                            <option value="Vĩnh Long">Vĩnh Long</option><option value="Đồng Tháp">Đồng Tháp</option>
                            <option value="Cà Mau">Cà Mau</option><option value="An Giang">An Giang</option>
                        </select>
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
                    <h2 class="section-title">Việc làm nổi bật</h2>

                    <a class="section-link" href="${pageContext.request.contextPath}/job-list">
                        Xem tất cả
                    </a>
                </div>

                <div class="card-grid">
                    <article class="card card--job">
                        <div class="card__body">
                            <h3 class="card__title card__title--lg">Frontend Developer</h3>
                            <p class="card__meta card__meta--highlight">VNG</p>

                            <div class="card__details">
                                <p><strong>Địa điểm:</strong> TP. Hồ Chí Minh</p>
                                <p><strong>Mức lương:</strong> 12 - 20 triệu</p>
                                <p><strong>Hạn nộp:</strong> 05/05/2026</p>
                            </div>
                        </div>

                        <div class="card__footer">
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
                    <h2 class="section-title">Các công ty IT</h2>
                </div>

                <div class="card-grid">
                    <article class="card">
                        <div class="card__head">
                            <div class="card__thumb">
                                <img
                                    src="https://via.placeholder.com/80x80"
                                    alt="FPT Software"
                                    class="card__img"
                                >
                            </div>

                            <div class="card__body">
                                <h3 class="card__title">FPT SOFTWARE</h3>
                                <p class="card__meta">IT - Phần mềm</p>
                            </div>
                        </div>

                        <div class="card__footer">
                            <span class="badge">
                                <span>💼</span>
                                <span>39 việc làm</span>
                            </span>
                            <a href="#" class="button button--primary">+ Theo dõi</a>
                        </div>
                    </article>

                    <article class="card">
                        <div class="card__head">
                            <div class="card__thumb">
                                <img
                                    src="https://via.placeholder.com/80x80"
                                    alt="HTI"
                                    class="card__img"
                                >
                            </div>

                            <div class="card__body">
                                <h3 class="card__title">CÔNG TY CỔ PHẦN ĐẦU TƯ VÀ CÔNG NGHỆ HTI</h3>
                                <p class="card__meta">IT - Phần mềm</p>
                            </div>
                        </div>

                        <div class="card__footer">
                            <span class="badge">
                                <span>💼</span>
                                <span>8 việc làm</span>
                            </span>
                            <a href="#" class="button button--primary">+ Theo dõi</a>
                        </div>
                    </article>

                    <article class="card">
                        <div class="card__head">
                            <div class="card__thumb">
                                <img
                                    src="https://via.placeholder.com/80x80"
                                    alt="Synnex FPT"
                                    class="card__img"
                                >
                            </div>

                            <div class="card__body">
                                <h3 class="card__title">CÔNG TY TNHH PHÂN PHỐI SYNNEX FPT</h3>
                                <p class="card__meta">IT - Phần mềm</p>
                            </div>
                        </div>

                        <div class="card__footer">
                            <span class="badge">
                                <span>💼</span>
                                <span>3 việc làm</span>
                            </span>
                            <a href="#" class="button button--primary">+ Theo dõi</a>
                        </div>
                    </article>
                </div>
            </div>
        </section>

    </main>

    <jsp:include page="/views/common/footer.jsp" />

</body>
</html>