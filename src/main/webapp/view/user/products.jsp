<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="images/png" href="${pageContext.request.contextPath}/images/logo/logo-juicy.png" sizes="32x32">
    <title>Juicy - Sản phẩm & Healthy</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
</head>
<body>

<header class="sticky-top shadow-sm bg-white">
    <nav class="navbar navbar-expand-lg navbar-light container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/images/logo/logo-juicy.png" alt="Logo" width="50">
        </a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Trang Chủ</a></li>
                <li class="nav-item"><a class="nav-link active fw-bold" href="${pageContext.request.contextPath}/products">Sản phẩm</a></li>
            </ul>
        </div>
    </nav>
</header>

<main class="container mt-5">
    <h2 class="text-center mb-4 text-success">DANH SÁCH SẢN PHẨM</h2>

    <div class="row mb-4">
        <div class="col-md-6 offset-md-3">
            <form action="search" method="get" class="d-flex">
                <input class="form-control me-2" type="search" name="query" placeholder="Tìm sản phẩm..." value="${lastSearch}">
                <button class="btn btn-success" type="submit">Tìm kiếm</button>
            </form>
        </div>
    </div>

    <div class="table-responsive">
        <table class="table table-hover table-bordered">
            <thead class="table-success">
            <tr>
                <th>ID</th>
                <th>Hình ảnh</th>
                <th>Tên sản phẩm</th>
                <th>Giá bán</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${productList}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td><img src="${pageContext.request.contextPath}/images/products/${p.image}" width="50" alt="${p.name}"></td>
                    <td class="fw-bold">${p.name}</td>
                    <td class="text-danger">
                        <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="đ"/>
                    </td>
                    <td>
                        <a href="#" class="btn btn-sm btn-outline-primary">Xem chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty productList}">
                <tr>
                    <td colspan="5" class="text-center">Không tìm thấy sản phẩm nào.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
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
<script type="module" src="js/init.js"></script>
<%--</body>--%>
<%--</html>--%>
<%--<html>--%>
<%--<head><title>Danh sách sản phẩm</title></head>--%>
<%--<body>--%>
<%--    <h2>Danh sách sản phẩm từ Database</h2>--%>
<%--    <table border="1">--%>
<%--        <tr>--%>
<%--            <th>ID</th>--%>
<%--            <th>Tên</th>--%>
<%--            <th>Giá</th>--%>
<%--        </tr>--%>
<%--        <c:forEach items="${productList}" var="p">--%>
<%--            <tr>--%>
<%--                <td>${p.id}</td>--%>
<%--                <td>${p.name}</td>--%>
<%--                <td>${p.price}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</body>--%>
<%--<form action="search" method="get" class="d-flex">--%>
<%--    <input class="form-control me-2" type="search" name="query"--%>
<%--           placeholder="Tìm sản phẩm..." value="${lastSearch}">--%>
<%--    <button class="btn btn-outline-success" type="submit">Tìm</button>--%>
<%--</form>--%>

<%--<div class="mt-3">--%>
<%--    <c:if test="${not empty lastSearch}">--%>
<%--        <p>Kết quả tìm kiếm cho: <strong>${lastSearch}</strong></p>--%>
<%--    </c:if>--%>
<%--    <c:if test="${empty productList}">--%>
<%--        <p class="alert alert-warning">Không tìm thấy sản phẩm nào phù hợp!</p>--%>
<%--    </c:if>--%>
<%--</div>--%>
<%--</html>--%>