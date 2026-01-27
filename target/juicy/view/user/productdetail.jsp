<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/logo/logo-juicy.png"
          sizes="32x32">
    <title>${product.name} - Juicy</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
    <style>
        .product-card {
            transition: transform 0.3s;
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 15px;
            overflow: hidden;
        }

        .product-card:hover {
            transform: translateY(-5px);
        }

        .card-img-top {
            height: 220px;
            object-fit: contain;
            background-color: #fff;
            padding: 10px;
        }

        .btn-primary-custom {
            background-color: #28a745;
            color: white;
            border: none;
        }
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
                <img src="${pageContext.request.contextPath}/images/logo/logo-juicy.png" alt="Juicy Logo" height="40"
                     class="me-2">
                JUICY
            </a>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto align-items-lg-center">
                    <li class="nav-item"><a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/">Trang
                        Chủ</a></li>
                    <li class="nav-item"><a class="nav-link active fw-semibold"
                                            href="${pageContext.request.contextPath}/product">Sản Phẩm</a></li>
                    <li class="nav-item"><a class="nav-link fw-semibold"
                                            href="${pageContext.request.contextPath}/about">Giới thiệu</a></li>
                    <li class="nav-item"><a class="nav-link fw-semibold"
                                            href="${pageContext.request.contextPath}/contact">Liên hệ</a></li>
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

<%-- SECTION TÌM KIẾM --%>
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

<main class="container my-5">
    <nav aria-label="breadcrumb" class="mb-4">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/"
                                           class="text-success text-decoration-none">Trang chủ</a></li>
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/product"
                                           class="text-success text-decoration-none">Sản phẩm</a></li>
            <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
        </ol>
    </nav>

    <div class="row g-5">
        <div class="col-md-6 animate__animated animate__fadeInLeft">
            <div class="card border-0 shadow-sm overflow-hidden text-center p-3 bg-white">
                <img src="${product.img}" class="img-fluid rounded" alt="${product.name}"
                     style="width: 100%; height: 500px; object-fit: contain;">
            </div>
        </div>

        <div class="col-md-6 animate__animated animate__fadeInRight">
            <div class="product-info-detail">
                <span class="badge bg-success mb-2">Nước ép tươi mỗi ngày</span>
                <h1 class="display-5 fw-bold text-dark mb-3">${product.name}</h1>

                <h2 class="text-danger fw-bold mb-4">
                    <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ"
                                      maxFractionDigits="0"/>
                </h2>

                <div class="mb-4">
                    <p class="mb-1 text-muted">Dung tích: <span class="text-dark fw-bold">${product.volume} ml</span>
                    </p>
                    <p class="mb-1 text-muted">Nhà cung cấp: <span
                            class="text-dark fw-bold">${product.supplier_name}</span></p>
                    <p class="mb-1 text-muted">Tình trạng:
                        <c:choose>
                            <c:when test="${product.quantity > 0}">
                                <span class="text-success fw-bold">Còn hàng (${product.quantity})</span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-danger fw-bold">Hết hàng</span>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>

                <div class="mb-4 pb-4 border-bottom">
                    <h5 class="fw-bold">Mô tả sản phẩm:</h5>
                    <p class="text-muted leading-relaxed">
                        ${product.description != null ? product.description : "Chưa có mô tả cho sản phẩm này. Nước ép
                                Juicy
                                cam kết 100% nguyên chất, không đường hóa học."}
                    </p>
                </div>

                <form action="add-to-cart" method="POST">
                    <input type="hidden" name="productId" value="${product.id}">
                    <div class="d-flex align-items-center gap-3 mb-4">
                        <div class="input-group" style="width: 130px;">
                            <button class="btn btn-outline-secondary" type="button"
                                    onclick="this.parentNode.querySelector('input').stepDown()">-
                            </button>
                            <input type="number" name="quantity" class="form-control text-center" value="1" min="1">
                            <button class="btn btn-outline-secondary" type="button"
                                    onclick="this.parentNode.querySelector('input').stepUp()">+
                            </button>
                        </div>
                        <button type="submit" class="btn btn-success btn-lg px-4 flex-grow-1 fw-bold rounded-pill">
                            <i class="bi bi-cart-plus me-2"></i> THÊM VÀO GIỎ HÀNG
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%-- SECTION SẢN PHẨM LIÊN QUAN --%>
    <div class="mt-5 pt-5 border-top">
        <h3 class="fw-bold text-success mb-4 text-center text-uppercase">Có thể bạn sẽ thích</h3>
        <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
            <c:forEach items="${relatedProducts}" var="rp">
                <div class="col">
                    <div class="card product-card h-100 text-center">
                        <img src="${rp.img}" class="card-img-top" alt="${rp.name}"
                             onerror="this.src='${pageContext.request.contextPath}/images/logo/logo-juicy.png'"/>

                        <div class="card-body d-flex flex-column">
                            <h6 class="text-muted small">${rp.volume}ml</h6>
                            <h5 class="card-title fw-bold fs-6">${rp.name}</h5>
                            <p class="card-text text-danger fw-bold fs-5 my-2">
                                <fmt:formatNumber value="${rp.price}" type="currency" currencySymbol="đ"
                                                  maxFractionDigits="0"/>
                            </p>
                            <div class="mt-auto pt-3">
                                <a href="${pageContext.request.contextPath}/product-detail?id=${rp.id}"
                                   class="btn btn-sm btn-outline-success rounded-pill px-3">Chi tiết</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

</main>

<footer class="bg-dark text-white pt-5 pb-4">
    <div class="container text-center text-md-start">
        <div class="row">
            <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                <h5 class="text-uppercase mb-4 fw-bold text-success">JUICY</h5>
                <p>Mang đến nguồn dinh dưỡng từ thiên nhiên, tốt cho sức khỏe.</p>
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
<script type="module" src="${pageContext.request.contextPath}/js/init.js"></script>
</body>
</html>