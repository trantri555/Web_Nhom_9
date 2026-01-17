<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="images/png" href="images/logo/logo-juicy.png" sizes="32x32">
    <link rel="shortcut icon" href="images/logo/logo-juicy.png" type="image/png">
    <title>Juicy - Trái Cây Tươi Ngon &amp; Nước Ép</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<!-- HEADER -->
<header class="sticky-top shadow-sm">
    <nav class="navbar navbar-expand-lg navbar-light bg-white py-3">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand fw-bold text-success fs-3" href="index.html">
                <img src="images/logo/logo-juicy.png" alt="Juicy Logo" height="40" class="me-2">
                JUICY <span class="text-warning"></span>
            </a>

            <div class="navbar-collapse show" id="navbarNav">
                <ul class="navbar-nav ms-auto align-items-lg-center">
                    <li class="nav-item">
                        <a class="nav-link  fw-semibold" href="admin-dashboard.html">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active fw-semibold" href="admin-products.html">Sản Phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link  fw-semibold" href="admin-orders.html">Đơn hàng</a>
                    </li>
                </ul>
                <a href="login.html"
                   class="btn btn-outline-success rounded-pill fw-semibold ms-lg-3 my-2 my-lg-0 me-lg-2">
                    <i class="bi bi-person-circle me-1"></i> Đăng Xuất
                </a>
                <a href="profile.html" class="btn btn-outline-success rounded-pill fw-semibold my-2 my-lg-0 me-lg-2">
                    <i class="bi bi-person-circle me-1"></i> Thông Tin
                </a>
            </div>
        </div>
    </nav>
</header>

<div class="container my-4">
    <div class="d-flex justify-content-between">
        <h3>Danh sách sản phẩm</h3>
        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addProductModal"><i
                class="bi bi-plus"></i> Thêm sản phẩm
        </button>
    </div>

    <table class="table table-bordered table-hover mt-3 bg-white">
        <thead>
        <tr>
            <th>Ảnh</th>
            <th>Tên SP</th>
            <th>Giá</th>
            <th>Thể tích</th>
            <th>Số lượng</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${products}">
            <tr>
                <td>
                    <img src="images/product/${p.image}" width="60">
                </td>
                <td>${p.name}</td>
                <td>${p.price}₫</td>
                <td>${p.category}</td>
                <td>${p.quantity}</td>
                <td>
                    <form method="post" action="products" style="display:inline">
                        <input type="hidden" name="action" value="hidden">
                        <input type="hidden" name="id" value="${p.id}">
                        <button class="btn btn-danger btn-sm">Ẩn</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal thêm sản phẩm -->
<div class="modal fade" id="addProductModal" tabindex="-1">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">

            <!-- Header -->
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title">
                    <i class="bi bi-box-seam me-2"></i>Thêm sản phẩm mới
                </h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
            </div>

            <!-- Body -->
            <div class="modal-body">
                <form method="post" action="/add-product" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="add">
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Tên sản phẩm</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">Giá (VNĐ)</label>
                        <input type="number" name="price" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">Thể tích</label>
                        <input type="text" name="volume" class="form-control" placeholder="Thể tích">
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">Số lượng</label>
                        <input type="number" name="quantity" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Hình ảnh</label>
                        <input type="file" name="image" class="form-control" required>
                        <c:if test="${param.error == 'no_image'}">
                            <div class="alert alert-danger">
                                Vui lòng chọn hình ảnh cho sản phẩm!
                            </div>
                        </c:if>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                            Hủy
                        </button>
                        <button type="submit" class="btn btn-success">
                            Lưu sản phẩm
                        </button>
                    </div>
                </form>
            </div>
            <c:if test="${param.success == 'add'}">
                <div class="alert alert-success">
                    Thêm sản phẩm thành công!
                </div>
            </c:if>


            <!-- Footer -->
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <button class="btn btn-success">
                    <i class="bi bi-check-circle me-1"></i>Lưu sản phẩm
                </button>
            </div>

        </div>
    </div>
</div>

<!--footer-->
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
                    <a href="products.html" class="text-white text-decoration-none">Trái Cây Văn Phòng</a>
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
                <p>© 2024 Juicy. All Rights Reserved.</p>
            </div>
        </div>
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="module" src="js/init.js"></script>
</body>
</html>
