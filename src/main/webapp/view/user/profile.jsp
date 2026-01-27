<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="images/png" href="images/logo/logo-juicy.png" sizes="32x32">
    <link rel="shortcut icon" href="images/logo/logo-juicy.png" type="image/png">
    <title>Juicy - Nước Ép Tươi Ngon &amp; Healthy</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/product.css">
</head>
<body>
<!-- HEADER -->
<header class="sticky-top shadow-sm">
    <nav class="navbar navbar-expand-lg navbar-light bg-white py-3">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand fw-bold text-success fs-3" href="/">
                <img src="images/logo/logo-juicy.png" alt="Juicy Logo" height="40" class="me-2">
                JUICY <span class="text-warning"></span>
            </a>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto align-items-lg-center">
                    <li class="nav-item">
                        <a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/home">Trang Chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/products">Sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/about">Giới thiệu</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/contact">Liên hệ</a>
                    </li>
                </ul>
                <c:choose>
                    <c:when test="${not empty sessionScope.auth}">
                        <div id="userInfoContainer">
                            <a href="${pageContext.request.contextPath}/profile"
                               class="btn btn-warning rounded-pill ms-lg-3 my-2 my-lg-0 fw-semibold shadow-sm">
                                Thông Tin</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div id="loginButtonContainer">
                            <a href="${pageContext.request.contextPath}/login"
                               class="btn btn-warning rounded-pill ms-lg-3 my-2 my-lg-0 fw-semibold shadow-sm">
                                Đăng Nhập</a>
                        </div>

                    </c:otherwise>
                </c:choose>

                <a href="${pageContext.request.contextPath}/order"
                   class="btn btn-warning rounded-pill ms-lg-3 my-2 my-lg-0 fw-semibold shadow-sm">
                    <i class="bi bi-cart me-1"></i> Giỏ Hàng
                </a>
            </div>
        </div>
    </nav>
</header>
<!-- PROFILE FORM -->
<section class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-7 col-md-8">
            <div class="auth-container shadow-sm p-4">
                <form>
                    <div class="mb-3">
                        <label for="fullname" class="form-label fw-semibold">Họ và tên</label>
                        <input type="text" class="form-control" id="fullname" placeholder="Nguyễn Văn A">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label fw-semibold">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="example@mail.com">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label fw-semibold">Số điện thoại</label>
                        <input type="text" class="form-control" id="phone" placeholder="0347xxxxxx">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label fw-semibold">Địa chỉ</label>
                        <input type="text" class="form-control" id="address" placeholder="Địa chỉ của bạn">
                    </div>
                    <div class="mb-3">
                        <label for="birthday" class="form-label fw-semibold">Ngày sinh</label>
                        <input type="date" class="form-control" id="birthday">
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Giới tính</label>
                        <select class="form-select">
                            <option selected>Chọn giới tính</option>
                            <option value="male">Nam</option>
                            <option value="female">Nữ</option>
                            <option value="other">Khác</option>
                        </select>
                    </div>
                    <div class="mb-4">
                        <label for="avatar" class="form-label fw-semibold">Ảnh đại diện (tùy chọn)</label>
                        <input class="form-control" type="file" id="avatar">
                    </div>
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary-custom fw-bold">Cập nhật thông tin</button>
                        <a href="${pageContext.request.contextPath}/logout" id="logoutButton" class="btn btn-outline-danger fw-bold">Đăng xuất</a></div>
                </form>
            </div>
        </div>
    </div>
</section>

<!-- FOOTER -->
<footer class="bg-dark text-white pt-5 pb-4">
    <div class="container text-center text-md-start">
        <div class="row text-center text-md-start">
            <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                <h5 class="text-uppercase mb-4 fw-bold text-success">JUICY</h5>
                <p>Mang đến nguồn dinh dưỡng từ thiên nhiên, tốt cho sức khỏe.</p>
            </div>
            <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
                <h5 class="text-uppercase mb-4 fw-bold text-success">Danh Mục</h5>
                <p><a href="products.html" class="text-white text-decoration-none">Nước Ép</a></p>
                <p><a href="products.html" class="text-white text-decoration-none">Trái Cây Văn Phòng</a></p>
                <p><a href="promotions.html" class="text-white text-decoration-none">Khuyến Mãi</a></p>
            </div>
            <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                <h5 class="text-uppercase mb-4 fw-bold text-success">Liên Hệ</h5>
                <p><i class="bi bi-geo-alt-fill me-2"></i> Đường số 7, Đông Hoà, Thủ Đức, TP.HCM, Việt Nam</p>
                <p><i class="bi bi-envelope-fill me-2"></i> order@juicy.vn</p>
                <p><i class="bi bi-telephone-fill me-2"></i> 0347 270 120</p>
            </div>
            <div class="col-md-3 mb-4">
                <h5 class="text-uppercase fw-bold text-success">Theo Dõi Chúng Tôi</h5>
                <a href="#" class="text-white me-3"><i class="bi bi-facebook"></i></a>
                <a href="#" class="text-white me-3"><i class="bi bi-instagram"></i></a>
                <a href="#" class="text-white me-3"><i class="bi bi-tiktok"></i></a>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-12 text-center pt-3 border-top border-secondary">
                <p>&copy; 2024 Juicy. All Rights Reserved.</p>
            </div>
        </div>
    </div>
</footer>
<script type="module" src="${pageContext.request.contextPath}/js/init.js"></script>
</body>
</html>
