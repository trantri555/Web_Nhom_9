<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard | Juicy</title>

        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/logo/logo-juicy.png">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>

    <body>

        <!-- HEADER -->
        <header class="sticky-top shadow-sm">
            <nav class="navbar navbar-expand-lg navbar-light bg-white py-3">
                <div class="container">
                    <a class="navbar-brand fw-bold text-success fs-3"
                        href="${pageContext.request.contextPath}/admin/dashboard">
                        <img src="${pageContext.request.contextPath}/images/logo/logo-juicy.png" height="40"
                            class="me-2">
                        JUICY
                    </a>

                    <div class="collapse navbar-collapse show">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item">
                                <a class="nav-link active fw-semibold"
                                    href="${pageContext.request.contextPath}/admin/dashboard">
                                    Dashboard
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link fw-semibold"
                                    href="${pageContext.request.contextPath}/admin/products">
                                    Sản phẩm
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link fw-semibold"
                                    href="${pageContext.request.contextPath}/admin/manage-orders">
                                    Đơn hàng
                                </a>
                            </li>
                        </ul>

                        <a href="${pageContext.request.contextPath}/logout"
                            class="btn btn-outline-success rounded-pill ms-3">
                            Đăng xuất
                        </a>
                    </div>
                </div>
            </nav>
        </header>

        <!-- TỔNG QUAN -->
        <div class="container my-5">
            <div class="row g-4">

                <div class="col-md-4">
                    <div class="p-4 shadow rounded bg-white text-center">
                        <h6>Đơn hôm nay</h6>
                        <h2 class="text-success fw-bold">
                            ${todayOrders}
                        </h2>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="p-4 shadow rounded bg-white text-center">
                        <h6>Đơn tuần này</h6>
                        <h2 class="text-primary fw-bold">
                            ${weekOrders}
                        </h2>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="p-4 shadow rounded bg-white text-center">
                        <h6>Đơn tháng này</h6>
                        <h2 class="text-warning fw-bold">
                            ${monthOrders}
                        </h2>
                    </div>
                </div>

            </div>

            <!-- BIỂU ĐỒ + TOP SP -->
            <div class="row mt-5">
                <div class="col-md-8">
                    <div class="shadow p-4 bg-white rounded">
                        <h5 class="mb-3">Biểu đồ doanh thu</h5>
                        <canvas id="revenueChart"></canvas>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="shadow p-4 bg-white rounded">
                        <h5 class="mb-3">Sản phẩm bán chạy</h5>

                        <ul class="list-group">
                            <c:forEach items="${topProducts}" var="p">
                                <li class="list-group-item d-flex justify-content-between">
                                    <span>${p.name}</span>
                                    <span class="fw-bold text-success">
                                        ${p.sold} đã bán
                                    </span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    div>
                </div>
            </div>
        </div>

        <!-- FOOTER -->
        <footer class="bg-dark text-white text-center py-3">
            © 2024 Juicy. All Rights Reserved.
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="${pageContext.request.contextPath}/js/admin-chart.js"></script>
    </body>

    </html>