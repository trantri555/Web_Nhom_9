<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="icon" type="images/png" href="images/logo/logo-juicy.png" sizes="32x32">
                <link rel="shortcut icon" href="images/logo/logo-juicy.png" type="image/png">
                <title>Juicy - Nước Ép Tươi Ngon &amp; Healthy</title>
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
                <link rel="stylesheet" href="css/style.css">
                <link rel="stylesheet" href="css/product.css">
            </head>

            <body>
                <!-- HEADER -->
                <header class="sticky-top shadow-sm">
                    <nav class="navbar navbar-expand-lg navbar-light bg-white py-3">
                        <div class="container">
                            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNav">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <a class="navbar-brand fw-bold text-success fs-3" href="/">
                                <img src="images/logo/logo-juicy.png" alt="Juicy Logo" height="40" class="me-2">
                                JUICY <span class="text-warning"></span>
                            </a>

                            <div class="collapse navbar-collapse" id="navbarNav">
                                <ul class="navbar-nav ms-auto align-items-lg-center">
                                    <li class="nav-item">
                                        <a class="nav-link  fw-semibold" href="/">Trang Chủ</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link fw-semibold"
                                            href="${pageContext.request.contextPath}/products">Sản phẩm</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link fw-semibold"
                                            href="${pageContext.request.contextPath}/about">Giới thiệu</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link fw-semibold"
                                            href="${pageContext.request.contextPath}/contact">Liên hệ</a>
                                    </li>
                                </ul>
                                <div id="loginButtonContainer">
                                    <a href="login"
                                        class="btn btn-warning rounded-pill ms-lg-3 my-2 my-lg-0 fw-semibold shadow-sm">
                                        Đăng Nhập</a>
                                </div>

                                <div id="userInfoContainer" class="d-none">
                                    <a href="profile"
                                        class="btn btn-warning rounded-pill ms-lg-3 my-2 my-lg-0 fw-semibold shadow-sm">
                                        Thông Tin</a>
                                </div>

                                <a href="order.html"
                                    class="btn btn-warning rounded-pill ms-lg-3 my-2 my-lg-0 fw-semibold shadow-sm">
                                    <i class="bi bi-cart me-1"></i> Giỏ Hàng
                                </a>
                            </div>
                        </div>
                    </nav>
                </header>

                <!-- GIỎ HÀNG -->
                <section class="container my-5">
                    <h2 class="text-center text-success fw-bold mb-4">Giỏ Hàng Của Bạn</h2>

                    <div class="row g-4">
                        <!-- Danh sách sản phẩm -->
                        <div class="col-lg-8">
                            <div class="table-responsive shadow rounded bg-white p-3">
                                <table class="table align-middle">
                                    <thead class="table-light">
                                        <tr>
                                            <th>Sản Phẩm</th>
                                            <th class="text-center">Số Lượng</th>
                                            <th class="text-end">Đơn Giá</th>
                                            <th class="text-end">Thành Tiền</th>
                                            <th></th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:choose>

                                            <%-- Đưa comment vào trong hoặc xóa đi --%>
                                                <c:when
                                                    test="${empty sessionScope.cart || empty sessionScope.cart.allItems}">
                                                    <tr>
                                                        <td colspan="5" class="text-center text-muted py-4">
                                                            🛒 Giỏ hàng đang trống
                                                        </td>
                                                    </tr>
                                                </c:when>

                                                <%-- Có sản phẩm --%>
                                                    <c:otherwise>
                                                        <c:forEach items="${sessionScope.cart.allItems}" var="item">
                                                            <tr>
                                                                <%-- Sản phẩm --%>
                                                                    <td>
                                                                        <div class="d-flex align-items-center">
                                                                            <c:choose>
                                                                                <c:when
                                                                                    test="${item.product.img != null && item.product.img.contains('http')}">
                                                                                    <img src="${item.product.img}"
                                                                                        width="60" class="rounded me-3"
                                                                                        alt="${item.product.name}"
                                                                                        onerror="this.src='${pageContext.request.contextPath}/images/logo/logo-juicy.png'">
                                                                                </c:when>
                                                                                <c:when
                                                                                    test="${item.product.img != null && (item.product.img.contains('/') || item.product.img.contains('\\\\'))}">
                                                                                    <img src="${pageContext.request.contextPath}/${item.product.img}"
                                                                                        width="60" class="rounded me-3"
                                                                                        alt="${item.product.name}"
                                                                                        onerror="this.src='${pageContext.request.contextPath}/images/logo/logo-juicy.png'">
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <img src="${pageContext.request.contextPath}/images/product/${item.product.img}"
                                                                                        width="60" class="rounded me-3"
                                                                                        alt="${item.product.name}"
                                                                                        onerror="this.src='${pageContext.request.contextPath}/images/logo/logo-juicy.png'">
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                            <div>
                                                                                <h6 class="fw-semibold mb-0">
                                                                                    ${item.product.name}
                                                                                </h6>
                                                                                <small class="text-muted">
                                                                                    ${item.product.volume} ml
                                                                                </small>
                                                                            </div>
                                                                        </div>
                                                                    </td>

                                                                    <%-- Số lượng --%>
                                                                        <td class="text-center">
                                                                            <form
                                                                                action="${pageContext.request.contextPath}/cart"
                                                                                method="post" class="d-inline">
                                                                                <input type="hidden" name="action"
                                                                                    value="update">
                                                                                <input type="hidden" name="productId"
                                                                                    value="${item.product.id}">
                                                                                <input type="number" name="quantity"
                                                                                    value="${item.quantity}" min="1"
                                                                                    class="form-control text-center"
                                                                                    style="width:70px"
                                                                                    onchange="this.form.submit()">
                                                                            </form>
                                                                        </td>

                                                                        <%-- Đơn giá --%>
                                                                            <td class="text-end">
                                                                                <fmt:formatNumber value="${item.price}"
                                                                                    type="currency" currencySymbol="đ"
                                                                                    maxFractionDigits="0" />
                                                                            </td>

                                                                            <%-- Thành tiền --%>
                                                                                <td
                                                                                    class="text-end fw-bold text-success">
                                                                                    <fmt:formatNumber
                                                                                        value="${item.totalPrice}"
                                                                                        type="currency"
                                                                                        currencySymbol="đ"
                                                                                        maxFractionDigits="0" />
                                                                                </td>

                                                                                <%-- Xóa --%>
                                                                                    <td class="text-end">
                                                                                        <form
                                                                                            action="${pageContext.request.contextPath}/cart"
                                                                                            method="post">
                                                                                            <input type="hidden"
                                                                                                name="action"
                                                                                                value="remove">
                                                                                            <input type="hidden"
                                                                                                name="productId"
                                                                                                value="${item.product.id}">
                                                                                            <button
                                                                                                class="btn btn-sm btn-outline-danger">
                                                                                                <i
                                                                                                    class="bi bi-trash"></i>
                                                                                            </button>
                                                                                        </form>
                                                                                    </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:otherwise>

                                        </c:choose>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <c:set var="shippingFee" value="15000" />

                        <div class="col-lg-4">
                            <div class="shadow rounded bg-white p-4">
                                <h5 class="fw-bold mb-3 text-success">Tổng Đơn Hàng</h5>

                                <!-- Tạm tính -->
                                <div class="d-flex justify-content-between mb-2">
                                    <span>Tạm tính:</span>
                                    <span>
                                        <fmt:formatNumber value="${sessionScope.cart.totalPrice}" type="currency"
                                            currencySymbol="đ" maxFractionDigits="0" />
                                    </span>
                                </div>

                                <!-- Phí ship -->
                                <div class="d-flex justify-content-between mb-2">
                                    <span>Phí giao hàng:</span>
                                    <span>
                                        <fmt:formatNumber value="${shippingFee}" type="currency" currencySymbol="đ"
                                            maxFractionDigits="0" />
                                    </span>
                                </div>

                                <!-- Tổng cộng -->
                                <div class="d-flex justify-content-between fw-bold border-top pt-2">
                                    <span>Tổng cộng:</span>
                                    <span class="text-success">
                                        <fmt:formatNumber value="${sessionScope.cart.totalPrice + shippingFee}"
                                            type="currency" currencySymbol="đ" maxFractionDigits="0" />
                                    </span>
                                </div>

                                <!-- Mã giảm giá (chưa xử lý logic) -->
                                <div class="mt-3">
                                    <label class="form-label fw-semibold">Mã giảm giá</label>
                                    <form action="#" method="post" class="input-group">
                                        <input type="text" class="form-control" name="coupon" placeholder="Nhập mã...">
                                        <button class="btn btn-outline-success" disabled>
                                            Áp dụng
                                        </button>
                                    </form>
                                </div>

                                <!-- Thanh toán -->
                                <c:choose>
                                    <c:when test="${sessionScope.cart == null || empty sessionScope.cart}">
                                        <button class="btn btn-secondary w-100 mt-4 rounded-pill" disabled>
                                            Giỏ hàng trống
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/checkout"
                                            class="btn btn-success w-100 mt-4 fw-semibold rounded-pill">
                                            <i class="bi bi-credit-card me-1"></i> Thanh Toán Ngay
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                        <section id="checkout" class="container my-5">
                            <h3 class="text-center text-success fw-bold mb-4">Thông Tin Thanh Toán</h3>
                            <div class="row justify-content-center">
                                <div class="col-md-8">
                                    <form class="p-4 shadow rounded bg-white">
                                        <div class="mb-3">
                                            <label class="form-label fw-semibold">Họ và Tên</label>
                                            <input type="text" class="form-control" placeholder="Nguyễn Văn A" required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label fw-semibold">Địa chỉ giao hàng</label>
                                            <input type="text" class="form-control"
                                                placeholder="Số nhà, đường, phường, quận..." required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label fw-semibold">Số điện thoại</label>
                                            <input type="tel" class="form-control" placeholder="0123456789" required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label fw-semibold">Phương thức thanh toán</label>
                                            <select class="form-select" required>
                                                <option value="">-- Chọn phương thức --</option>
                                                <option>Thanh toán khi nhận hàng (COD)</option>
                                                <option>Chuyển khoản ngân hàng</option>
                                                <option>Ví điện tử (Momo, ZaloPay...)</option>
                                            </select>
                                        </div>
                                        <button type="submit" class="btn btn-success w-100 rounded-pill fw-semibold">
                                            <i class="bi bi-check-circle me-1"></i> Xác Nhận Đặt Hàng
                                        </button>
                                        <p id="orderSuccess" class="text-success fw-semibold mt-3"
                                            style="display:none;">
                                            Cảm ơn bạn! Đơn hàng đã được ghi nhận.
                                        </p>
                                    </form>
                                </div>
                            </div>
                        </section>

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
                                            <a href="products.html" class="text-white text-decoration-none">Trái Cây Văn
                                                Phòng</a>
                                        </p>
                                        <p>
                                            <a href="promotions.html" class="text-white text-decoration-none">Khuyến
                                                Mãi</a>
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
            </body>

            </html>