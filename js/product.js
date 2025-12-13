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

// Hàm kiểm tra trạng thái khi tải trang
export function checkLoginStatus() {
    // Sử dụng localStorage để giữ trạng thái qua các lần tải trang
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    updateAuthUI(isLoggedIn);
}

//LOGOUT LOGIC
export function handleLogout() {
    if (confirm("Bạn có chắc chắn muốn đăng xuất không?")) {
        // 1. Xóa trạng thái đăng nhập
        localStorage.setItem('isLoggedIn', 'false');

        // 2. Cập nhật giao diện tt -> đn
        updateAuthUI(false);

        // 3. Thông báo và chuyển hướng
        alert("Đăng xuất thành công! Bạn sẽ được chuyển về Trang Chủ.");
        window.location.href = "index.html";
    }
}

// ĐĂNG NHẬP / ĐĂNG KÝ LOGIC
export function initAuthForms() {
    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");
    if (loginForm) {
        loginForm.addEventListener("submit", function (e) {
            e.preventDefault();
            const username = document.getElementById("loginEmail").value.trim();
            const password = document.getElementById("loginPassword").value.trim();

            if (username === "admin" && password === "123") {
                // LƯU TRẠNG THÁI ĐÃ ĐĂNG NHẬP thành công
                localStorage.setItem('isLoggedIn', 'true');
                updateAuthUI(true);
                alert("Đăng nhập thành công!");
                window.location.href = "admin-dashboard.html";
            } else {
                alert("Sai thông tin đăng nhập!");
            }
        });
    }

    if (registerForm) {
        registerForm.addEventListener("submit", function (e) {
            e.preventDefault();
            localStorage.setItem('isLoggedIn', 'true');
            updateAuthUI(true);

            alert("Đăng ký thành công!");

            // Chuyển hướng về trang chủ hoặc trang đăng nhập sau khi đăng ký thành công
            window.location.href = "index.html";

        });
    }
}
//them moi