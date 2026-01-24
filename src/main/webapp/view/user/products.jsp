<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/logo/logo-juicy.png" sizes="32x32">
    <title>Juicy - Danh sách sản phẩm</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">

    <style>
        .product-card { transition: transform 0.3s; border: none; box-shadow: 0 4px 6px rgba(0,0,0,0.1); border-radius: 15px; overflow: hidden; }
        .product-card:hover { transform: translateY(-5px); }
        .btn-primary-custom { background-color: #28a745; color: white; border: none; transition: 0.3s; }
        .btn-primary-custom:hover { background-color: #218838; color: white; }
        .card-img-top { height: 220px; object-fit: cover; background-color: #f8f9fa; }
    </style>
</head>
<body>

<header class="sticky-top shadow-sm">
    <nav class="navbar navbar-expand-lg navbar-light bg-white py-3">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand fw-bold text-success fs-3" href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/images/logo/logo-juicy.png" alt="Juicy Logo" height="40" class="me-2">
                JUICY
            </a>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto align-items-lg-center">
                    <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/">Trang Chủ</a></li>
                    <li class="nav-item"><a class="nav-link active fw-semibold" href="${pageContext.request.contextPath}/product">Sản Phẩm</a></li>
                    <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/about">Giới thiệu</a></li>
                    <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/contact">Liên hệ</a></li>
                </ul>
                <div id="loginButtonContainer">
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-warning rounded-pill ms-lg-3 my-2 my-lg-0 fw-semibold shadow-sm">Đăng Nhập</a>
                </div>
                <a href="${pageContext.request.contextPath}/order" class="btn btn-warning rounded-pill ms-lg-3 my-2 my-lg-0 fw-semibold shadow-sm">
                    <i class="bi bi-cart me-1"></i> Giỏ Hàng
                </a>
            </div>
        </div>
    </nav>
</header>

<section class="bg-light py-4 border-bottom">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <form class="d-flex" action="${pageContext.request.contextPath}/search" method="get">
                    <input class="form-control form-control-lg me-2 border-success" type="search" name="query"
                           placeholder="Tìm kiếm tên sản phẩm, loại trái cây..." aria-label="Search">
                    <button class="btn btn-primary-custom btn-lg fw-bold" type="submit">
                        <i class="bi bi-search"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>
</section>

<div class="container my-5">
    <h2 class="text-center mb-5 fw-bold text-dark">Sản phẩm của chúng tôi</h2>

    <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
        <c:choose>
            <c:when test="${not empty productList}">
                <c:forEach items="${productList}" var="p">
                    <div class="col animate__animated animate__fadeInUp">
                        <div class="card product-card h-100 text-center">
                            <img src="${pageContext.request.contextPath}/images/products/${p.img}.png"
                                 class="card-img-top" alt="${p.name}"
                                 onerror="this.src='${pageContext.request.contextPath}/images/default-product.png'"/>

                            <div class="card-body d-flex flex-column">
                                <h6 class="text-muted small">${p.volume}ml</h6>
                                <h5 class="card-title fw-bold fs-6">${p.name}</h5>
                                <p class="card-text text-danger fw-bold fs-5 my-2">
                                    <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
                                </p>
                                <div class="mt-auto pt-3">
                                    <a href="product-detail?id=${p.id}" class="btn btn-sm btn-outline-success rounded-pill px-3">Chi tiết</a>
                                    <button class="btn btn-sm btn-primary-custom rounded-pill px-3">Thêm vào giỏ</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="col-12 text-center py-5">
                    <p class="text-muted">Không tìm thấy sản phẩm nào!</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<footer class="bg-dark text-white pt-5 pb-4">
    <div class="container text-center text-md-start">
        <div class="row">
            <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                <h5 class="text-uppercase mb-4 fw-bold text-success">JUICY</h5>
                <p>Mang đến nguồn dinh dưỡng từ thiên nhiên, tốt cho sức khỏe.</p>
            </div>
            <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
                <h5 class="text-uppercase mb-4 fw-bold text-success">Danh Mục</h5>
                <p><a href="#" class="text-white text-decoration-none">Nước Ép</a></p>
                <p><a href="#" class="text-white text-decoration-none">Trái Cây</a></p>
            </div>
            <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                <h5 class="text-uppercase mb-4 fw-bold text-success">Liên Hệ</h5>
                <p><i class="bi bi-geo-alt-fill me-2"></i> Thủ Đức, TP. Hồ Chí Minh</p>
                <p><i class="bi bi-telephone-fill me-2"></i> 0347 270 120</p>
            </div>
            <div class="col-md-3 mb-4 mt-3">
                <h5 class="text-uppercase fw-bold text-success">Theo Dõi</h5>
                <a href="#" class="text-white me-3 fs-4"><i class="bi bi-facebook"></i></a>
                <a href="#" class="text-white me-3 fs-4"><i class="bi bi-instagram"></i></a>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-12 text-center pt-3 border-top border-secondary">
                <p>© 2026 Juicy. All Rights Reserved.</p>
            </div>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>