import { initNavbarToggle, initTabs } from './navbar-tabs.js';
import { initCarousel } from './carousel.js';
import { checkLoginStatus, initAuthForms, handleLogout } from './auth.js';
import { initRevenueChart } from './chart.js';

// Hàm chính để khởi tạo tất cả chức năng
function initApp() {
    initNavbarToggle();
    initTabs();

    // Chỉ khởi tạo carousel
    if (document.getElementById('hero-carousel')) {
        // Tên id và interval (ms)
        initCarousel('hero-carousel', 3000);
    }

    // Kiểm tra và thiết lập trạng thái nút ngay khi tải trang
    checkLoginStatus();

    // Thiết lập xử lý form đăng nhập
    initAuthForms();

    // Khởi tạo biểu đồ
    initRevenueChart();

    //Gán Event Listener cho nút Đăng Xuất
    const logoutButton = document.getElementById('logoutButton'); // Giả sử nút Đăng Xuất có id="logoutButton"
    if (logoutButton) {
        logoutButton.addEventListener('click', handleLogout);
    }
}

document.addEventListener('DOMContentLoaded', initApp);

