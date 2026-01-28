<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
            <!DOCTYPE html>
            <html lang="vi">

            <head>
                <meta charset="UTF-8">
                <title>Admin Dashboard | Juicy</title>

                <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/logo/logo-juicy.png">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
                <link rel="stylesheet"
                    href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
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
                                        <a class="nav-link fw-semibold"
                                            href="${pageContext.request.contextPath}/admin/dashboard">
                                            Dashboard
                                        </a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link active fw-semibold"
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


                <!-- Main Content -->
                <div class="container my-5">
                    <div
                        class="d-flex flex-column flex-md-row justify-content-between align-items-center mb-4 animate__animated animate__fadeInDown">
                        <div>
                            <h2 class="fw-bold text-success mb-1">Quản Lý Sản Phẩm</h2>
                            <p class="text-muted mb-0">Xem và quản lý tất cả sản phẩm hiện có</p>
                        </div>

                        <div class="d-flex gap-3 mt-3 mt-md-0 align-items-center">
                            <div class="search-container d-none d-md-block">
                                <form action="products" method="get">
                                    <i class="bi bi-search search-icon"></i>
                                    <input type="text" name="search" class="search-input"
                                        placeholder="Tìm kiếm sản phẩm..." value="${param.search}">
                                </form>
                            </div>
                            <button class="btn btn-premium" data-bs-toggle="modal" data-bs-target="#addProductModal">
                                <i class="bi bi-plus-lg me-2"></i>Thêm Mới
                            </button>
                        </div>
                    </div>

                    <div class="card card-custom animate__animated animate__fadeInUp">
                        <div class="card-body p-0">
                            <div class="table-responsive">
                                <table class="table table-custom mb-0">
                                    <thead>
                                        <tr>
                                            <th class="ps-4">Sản Phẩm</th>
                                            <th>Giá Bán</th>
                                            <th>Thể Tích</th>
                                            <th>Kho</th>
                                            <th class="text-center">Hành Động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="p" items="${products}">
                                            <tr>
                                                <td class="ps-4">
                                                    <div class="d-flex align-items-center">
                                                        <div class="position-relative">
                                                            <c:choose>
                                                                <c:when
                                                                    test="${p.img != null && p.img.contains('http')}">
                                                                    <img src="${p.img}" class="product-img"
                                                                        alt="${p.name}" title="Source: ${p.img}"
                                                                        style="width: 60px; height: 60px; object-fit: cover;"
                                                                        onerror="this.src='${pageContext.request.contextPath}/images/logo/logo-juicy.png'" />
                                                                </c:when>
                                                                <c:when
                                                                    test="${p.img != null && (p.img.contains('/') || p.img.contains('\\\\'))}">
                                                                    <img src="${pageContext.request.contextPath}/${p.img}"
                                                                        class="product-img" alt="${p.name}"
                                                                        title="Source: ${p.img}"
                                                                        style="width: 60px; height: 60px; object-fit: cover;"
                                                                        onerror="this.src='${pageContext.request.contextPath}/images/logo/logo-juicy.png'" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <img src="${pageContext.request.contextPath}/images/product/${p.img}"
                                                                        class="product-img" alt="${p.name}"
                                                                        title="Source: ${p.img}"
                                                                        style="width: 60px; height: 60px; object-fit: cover;"
                                                                        onerror="this.src='${pageContext.request.contextPath}/images/logo/logo-juicy.png'" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <div class="ms-3">
                                                            <h6 class="mb-0 fw-bold text-dark">${p.name}</h6>
                                                            <small class="text-muted">ID: #${p.id}</small>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="fw-semibold text-success fs-5">${p.price}₫</td>
                                                <td><span class="badge bg-light text-dark border">${p.volume}ml</span>
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${p.quantity == -1}">
                                                            <span class="badge bg-secondary rounded-pill px-3 mb-2">
                                                                <i class="bi bi-eye-slash-fill me-1"></i> Đã ẩn
                                                            </span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="d-flex align-items-center gap-2">
                                                                <form action="products" method="post"
                                                                    class="d-flex gap-1 align-items-center">
                                                                    <input type="hidden" name="action"
                                                                        value="update_quantity">
                                                                    <input type="hidden" name="id" value="${p.id}">
                                                                    <input type="number" name="quantity"
                                                                        class="form-control form-control-sm text-center px-1"
                                                                        style="width: 70px;" value="${p.quantity}"
                                                                        min="0" required>
                                                                    <button type="submit"
                                                                        class="btn btn-sm btn-outline-success border-0"
                                                                        title="Cập nhật">
                                                                        <i class="bi bi-check-lg fw-bold"></i>
                                                                    </button>
                                                                </form>
                                                                <c:if test="${p.quantity <= 10}">
                                                                    <i class="bi bi-exclamation-circle-fill text-warning"
                                                                        title="Sắp hết hàng"></i>
                                                                </c:if>
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="text-center">
                                                    <form method="post" action="products" style="display:inline">
                                                        <input type="hidden" name="id" value="${p.id}">

                                                        <c:choose>
                                                            <c:when test="${p.quantity == -1}">
                                                                <input type="hidden" name="action" value="show">
                                                                <button class="btn-action-delete text-primary"
                                                                    title="Hiện sản phẩm"
                                                                    onclick="return confirm('Hiện lại sản phẩm này?')">
                                                                    <i class="bi bi-eye-fill"></i>
                                                                </button>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="hidden" name="action" value="hidden">
                                                                <button class="btn-action-delete" title="Ẩn sản phẩm"
                                                                    onclick="return confirm('Bạn có chắc muốn ẩn sản phẩm này?')">
                                                                    <i class="bi bi-eye-slash"></i>
                                                                </button>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal thêm sản phẩm -->
                <div class="modal fade" id="addProductModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content shadow-lg">
                            <!-- Header -->
                            <div class="modal-header">
                                <h5 class="modal-title">
                                    <i class="bi bi-box-seam-fill me-2"></i>Thêm Sản Phẩm Mới
                                </h5>
                                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>

                            <!-- Body -->
                            <div class="modal-body">
                                <form method="post" action="${pageContext.request.contextPath}/add-product"
                                    enctype="multipart/form-data">
                                    <input type="hidden" name="action" value="add">

                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label class="form-label fw-semibold text-secondary">Tên sản phẩm</label>
                                            <input type="text" name="name" class="form-control"
                                                placeholder="Nhập tên sản phẩm..." required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label class="form-label fw-semibold text-secondary">Giá bán (VNĐ)</label>
                                            <div class="input-group">
                                                <input type="number" name="price" class="form-control" placeholder="0"
                                                    required>
                                                <span class="input-group-text bg-light">₫</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label class="form-label fw-semibold text-secondary">Thể tích (ml)</label>
                                            <input type="number" name="volume" class="form-control"
                                                placeholder="Ví dụ: 500" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label class="form-label fw-semibold text-secondary">Số lượng nhập</label>
                                            <input type="number" name="quantity" class="form-control" placeholder="0"
                                                required>
                                        </div>
                                        <div class="col-md-12 mb-3">
                                            <label class="form-label fw-semibold text-secondary">Nhà Cung Cấp</label>
                                            <input type="text" name="supplier_name" class="form-control"
                                                placeholder="Nhập tên nhà cung cấp..." required>
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label fw-semibold text-secondary">Mô tả sản phẩm</label>
                                        <textarea name="description" class="form-control" rows="3"
                                            placeholder="Nhập mô tả chi tiết sản phẩm..."></textarea>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label fw-semibold text-secondary">Link ảnh (URL)</label>
                                        <input type="url" name="image_url" class="form-control"
                                            placeholder="https://example.com/image.png">
                                        <div class="form-text">Dùng link ảnh trực tiếp (Cloudinary, Imgur...) để không
                                            bị mất ảnh.</div>
                                    </div>

                                    <div class="text-center text-muted my-2 fw-bold">- HOẶC -</div>

                                    <div class="mb-4">
                                        <label class="form-label fw-semibold text-secondary">Upload ảnh từ máy</label>
                                        <input type="file" id="imageInput" name="images" class="form-control"
                                            accept="image/*">
                                        <div class="form-text">Hỗ trợ định dạng: .jpg, .png. Kích thước tối đa 5MB.
                                        </div>

                                        <!-- Image Preview Container -->
                                        <div class="mt-3 text-center">
                                            <img id="previewImage" src="#" alt="Preview"
                                                style="display:none; max-width: 200px; max-height: 200px; object-fit: cover; border-radius: 10px; border: 1px solid #ddd; box-shadow: 0 4px 6px rgba(0,0,0,0.1);">
                                        </div>

                                        <c:if test="${param.error == 'no_image'}">
                                            <div class="alert alert-danger mt-2 d-flex align-items-center">
                                                <i class="bi bi-exclamation-triangle-fill me-2"></i>
                                                Vui lòng chọn ảnh hoặc nhập URL!
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="d-flex justify-content-end gap-2 pt-3 border-top">
                                        <button type="button" class="btn btn-light px-4 border"
                                            data-bs-dismiss="modal">Hủy bỏ</button>
                                        <button type="submit" class="btn btn-premium px-4" id="btnSaveProduct">
                                            <i class="bi bi-check-lg me-1"></i> Lưu Sản Phẩm
                                        </button>
                                    </div>
                                </form>
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
                                    <a href="products.html" class="text-white text-decoration-none">Trái Cây Văn
                                        Phòng</a>
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

                <script>
                    // 1. Preview Image Logic
                    const input = document.getElementById("imageInput");
                    const preview = document.getElementById("previewImage");

                    if (input) {
                        input.addEventListener("change", () => {
                            const file = input.files[0];
                            if (!file) {
                                preview.style.display = "none";
                                return;
                            }

                            const reader = new FileReader();
                            reader.onload = e => {
                                preview.src = e.target.result;
                                preview.style.display = "block";
                            };
                            reader.readAsDataURL(file);
                        });
                    }

                    // 2. Ajax Submit Logic
                    const form = document.querySelector("#addProductModal form");
                    if (form) {
                        form.addEventListener("submit", async function (e) {
                            e.preventDefault();

                            const btn = document.getElementById("btnSaveProduct");
                            const originalText = btn.innerHTML;
                            btn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Đang xử lý...';
                            btn.disabled = true;

                            const formData = new FormData(form);

                            try {
                                const response = await fetch(form.action, {
                                    method: 'POST',
                                    body: formData
                                });

                                const result = await response.json();

                                if (result.success) {
                                    alert("🎉 Lưu sản phẩm thành công!");
                                    window.location.reload(); // Reload to show new product
                                } else {
                                    alert("❌ Có lỗi xảy ra: " + (result.message || "Không xác định"));
                                }
                            } catch (error) {
                                console.error('Error:', error);
                                alert("❌ Lỗi kết nối đến server!");
                            } finally {
                                btn.innerHTML = originalText;
                                btn.disabled = false;
                            }
                        });
                    }
                </script>
            </body>

            </html>