<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="icon" type="images/png" href="images/logo/logo-juicy.png" sizes="32x32">
  <title>${product.name} - Juicy</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="css/product.css">
</head>
<body>

<header class="sticky-top shadow-sm">
  <nav class="navbar navbar-expand-lg navbar-light bg-white py-3">
    <div class="container">
      <a class="navbar-brand fw-bold text-success fs-3" href="${pageContext.request.contextPath}/home">
        <img src="images/logo/logo-juicy.png" alt="Juicy Logo" height="40" class="me-2">
        JUICY
      </a>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <a class="nav-link fw-semibold" href="${pageContext.request.contextPath}/home">Trang Chủ</a>
          </li>
          <li class="nav-item">
            <a class="nav-link active fw-semibold" href="${pageContext.request.contextPath}/products">Sản phẩm</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</header>

<main class="container my-5">
  <nav aria-label="breadcrumb" class="mb-4">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="home" class="text-success text-decoration-none">Trang chủ</a></li>
      <li class="breadcrumb-item"><a href="products" class="text-success text-decoration-none">Sản phẩm</a></li>
      <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
    </ol>
  </nav>

  <div class="row g-5">
    <div class="col-md-6 animate__animated animate__fadeInLeft">
      <div class="card border-0 shadow-sm overflow-hidden">
        <img src="${product.img}" class="img-fluid rounded" alt="${product.name}"
             style="width: 100%; height: 500px; object-fit: cover;">
      </div>
    </div>

    <div class="col-md-6 animate__animated animate__fadeInRight">
      <div class="product-info-detail">
        <span class="badge bg-success mb-2">Nước ép tươi mỗi ngày</span>
        <h1 class="display-5 fw-bold text-dark mb-3">${product.name}</h1>

        <h2 class="text-danger fw-bold mb-4">
          <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="đ" maxFractionDigits="0"/>
        </h2>

        <div class="mb-4">
          <p class="mb-1 text-muted">Dung tích: <span class="text-dark fw-bold">${product.volume} ml</span></p>
          <p class="mb-1 text-muted">Nhà cung cấp: <span class="text-dark fw-bold">${product.supplier_name}</span></p>
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
            ${product.description != null ? product.description : "Chưa có mô tả cho sản phẩm này. Nước ép Juicy cam kết 100% nguyên chất, không đường hóa học."}
          </p>
        </div>

        <form action="add-to-cart" method="POST">
          <input type="hidden" name="productId" value="${product.id}">
          <div class="d-flex align-items-center gap-3 mb-4">
            <div class="input-group" style="width: 130px;">
              <button class="btn btn-outline-secondary" type="button" onclick="this.parentNode.querySelector('input').stepDown()">-</button>
              <input type="number" name="quantity" class="form-control text-center" value="1" min="1">
              <button class="btn btn-outline-secondary" type="button" onclick="this.parentNode.querySelector('input').stepUp()">+</button>
            </div>
            <button type="submit" class="btn btn-success btn-lg px-4 flex-grow-1 fw-bold rounded-pill">
              <i class="bi bi-cart-plus me-2"></i> THÊM VÀO GIỎ HÀNG
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</main>

<footer class="bg-dark text-white pt-5 pb-4">
  <div class="container text-center text-md-start">
    <div class="row text-center text-md-start">
      <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
        <h5 class="text-uppercase mb-4 fw-bold text-success">JUICY</h5>
        <p>Mang đến nguồn dinh dưỡng từ thiên nhiên, tốt cho sức khỏe.</p>
      </div>

      <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
        <h5 class="text-uppercase mb-4 fw-bold text-success">Danh Mục</h5>
        <p>
          <a href="products.html" class="text-white text-decoration-none">Nước Ép</a>
        </p>
        <p>
          <a href="products.html" class="text-white text-decoration-none"
          >Trái Cây Văn Phòng</a
          >
        </p>
        <p>
          <a href="promotions.html" class="text-white text-decoration-none">Khuyến Mãi</a>
        </p>
      </div>

      <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
        <h5 class="text-uppercase mb-4 fw-bold text-success">Liên Hệ</h5>
        <p>
          <i class="bi bi-geo-alt-fill me-2"></i> Đường số 7, Đông Hoà, Thủ
          Đức, Thành phố Hồ Chí Minh, Việt Nam
        </p>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>