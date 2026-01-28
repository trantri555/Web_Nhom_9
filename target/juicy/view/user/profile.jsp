<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<jsp:include page="/view/user/include/header.jsp">
    <jsp:param name="title" value="Trang Chủ" />
</jsp:include>
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
<%@include file="/view/user/include/footer.jsp" %>

