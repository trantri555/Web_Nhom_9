// Hàm ẩn/hiện nút Đăng Nhập và Thông Tin
export function updateAuthUI(isLoggedIn) {
    const loginBtnContainer = document.getElementById('loginButtonContainer');
    const userBtnContainer = document.getElementById('userInfoContainer');

    if (loginBtnContainer && userBtnContainer) {
        if (isLoggedIn) {
            loginBtnContainer.classList.add('d-none');
            userBtnContainer.classList.remove('d-none');
        } else {
            loginBtnContainer.classList.remove('d-none');
            userBtnContainer.classList.add('d-none');
        }
    }
}

export function handleLogout() {
    if (confirm("Bạn có chắc chắn muốn đăng xuất không?")) {
        // Chuyển hướng đến LogoutServlet để xóa sạch Session
        window.location.href = "${pageContext.request.contextPath}/logout";
    }
}