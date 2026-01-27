<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                                <a class="nav-link"
                                    href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/admin/products">Sản
                                    Phẩm</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active"
                                    href="${pageContext.request.contextPath}/admin/manage-orders">Đơn hàng</a>
                            </li>
                        </ul>
                        <a href="login.html"
                            class="btn btn-outline-success rounded-pill fw-semibold ms-lg-3 my-2 my-lg-0 me-lg-2">
                            <i class="bi bi-person-circle me-1"></i> Đăng Xuất
                        </a>
                        <a href="profile.html"
                            class="btn btn-outline-success rounded-pill fw-semibold my-2 my-lg-0 me-lg-2">
                            <i class="bi bi-person-circle me-1"></i> Thông Tin
                        </a>
                    </div>
                </div>
            </nav>
        </header>
        <div class="container my-4">

            <h3>Danh sách đơn hàng</h3>

            <table class="table table-bordered bg-white">
                <thead>
                    <tr>
                        <th>Mã đơn</th>
                        <th>Khách hàng</th>
                        <th>Tổng tiền</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${orders}" var="o">
                        <tr>
                            <td>#DH${o.id}</td>
                            <td>${o.customerName}</td>
                            <td>${o.totalPrice}</td>
                            <td>
                                <form method="post" action="manage-orders">
                                    <input type="hidden" name="action" value="updateStatus" />
                                    <input type="hidden" name="orderId" value="${o.id}" />

                                    <select name="status" onchange="this.form.submit()">
                                        <option ${o.status=='Chờ xác nhận' ? 'selected' : '' }>
                                            Chờ xác nhận
                                        </option>
                                        <option ${o.status=='Đã chuẩn bị' ? 'selected' : '' }>
                                            Đã chuẩn bị
                                        </option>
                                        <option ${o.status=='Đang vận chuyển' ? 'selected' : '' }>
                                            Đang vận chuyển
                                        </option>
                                    </select>
                                </form>
                            </td>

                            <td>
                                <form method="post" action="manage-orders" onsubmit="return confirm('Xóa đơn hàng?')">
                                    <input type="hidden" name="action" value="delete" />
                                    <input type="hidden" name="orderId" value="${o.id}" />
                                    <button class="btn btn-danger btn-sm">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script type="module" src="js/init.js"></script>
    </body>

    </html>