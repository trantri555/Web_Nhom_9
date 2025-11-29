// NAVBAR TOGGLE LOGIC
function initNavbarToggle() {
    var navbarToggler = document.querySelector('.navbar-toggler');
    var navCollapse = document.querySelector('#navbarNav');

    if (navbarToggler && navCollapse) {
        navbarToggler.onclick = function() {
            //chuyển đổi class show (ẩn/hiện)
            navCollapse.classList.toggle('show');
            //chuyển đổi class collapse (cho hiệu ứng)
            navCollapse.classList.toggle('collapse');
        };
    }
}


// TABS LOGIC
function initTabs() {
    var tabContainers = document.querySelectorAll('[role="tablist"]');

    // Lặp qua tất cả các container chứa tab
    for (var i = 0; i < tabContainers.length; i++) {
        var container = tabContainers[i];

        container.onclick = function(event) {
            var button = event.target.closest('[role="tab"]');
            if (!button) {
                return;
            }

            event.preventDefault();

            // 1. Tắt tab và panel đang hoạt động
            var activeTabs = container.querySelectorAll('.nav-link.active');
            for (var j = 0; j < activeTabs.length; j++) {
                activeTabs[j].classList.remove('active');
            }

            var activePanels = document.querySelectorAll('.tab-pane.show.active');
            for (var k = 0; k < activePanels.length; k++) {
                activePanels[k].classList.remove('show', 'active');
            }

            // 2. Kích hoạt tab mới
            button.classList.add('active');

            // 3. Kích hoạt panel mới
            var targetId = button.getAttribute('data-bs-target');
            if (targetId) {
                var targetPanel = document.querySelector(targetId);
                if (targetPanel) {
                    targetPanel.classList.add('show', 'active');
                }
            }
        };
    }
}


//CAROUSEL LOGIC
function initCarousel(id, interval) {
    var carousel = document.getElementById(id);
    if (!carousel) return;

    var items = carousel.querySelectorAll('.carousel-item');
    if (items.length === 0) return;
    var currentIndex = 0;
    var slideInterval;

    function showItem(index) {
        for (var i = 0; i < items.length; i++) {
            items[i].classList.remove('active');
        }
        items[index].classList.add('active');
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % items.length;
        showItem(currentIndex);
    }

    function prevSlide() {
        currentIndex = (currentIndex - 1 + items.length) % items.length;
        showItem(currentIndex);
    }

    // 1. Tự động chuyển slide
    function startAutoSlide() {
        // Xóa interval cũ trước khi tạo mới
        clearInterval(slideInterval);
        slideInterval = setInterval(nextSlide, interval);
    }

    // 2. Thiết lập nút điều khiển
    var nextButton = carousel.querySelector('.carousel-control-next');
    var prevButton = carousel.querySelector('.carousel-control-prev');

    if (nextButton) {
        nextButton.onclick = function() {
            startAutoSlide();
            nextSlide();
        };
    }

    if (prevButton) {
        prevButton.onclick = function() {
            startAutoSlide();
            prevSlide();
        };
    }

    showItem(currentIndex);
    startAutoSlide();
}

//TOGGLE LOGIN/USER INFO BUTTONS

// Hàm ẩn/hiện nút Đăng Nhập và Thông Tin
function updateAuthUI(isLoggedIn) {
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
function checkLoginStatus() {
    // Sử dụng localStorage để giữ trạng thái qua các lần tải trang
    const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    updateAuthUI(isLoggedIn);
}

//LOGOUT LOGIC
function handleLogout() {
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


// Chạy tất cả các chức năng sau khi DOM đã tải
document.addEventListener('DOMContentLoaded', function() {
    initNavbarToggle();
    initTabs();

    // Chỉ khởi tạo carousel nếu phần tử tồn tại trên trang
    if (document.getElementById('hero-carousel')) {
        initCarousel('hero-carousel', 3000);
    }

    // Kiểm tra và thiết lập trạng thái nút ngay khi tải trang
    checkLoginStatus();

    // ĐĂNG NHẬP / ĐĂNG KÝ LOGIC
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
});
//Tạo biểu đồ doanh thu
document.addEventListener("DOMContentLoaded", function () {
    const ctx = document.getElementById('revenueChart');

    if (!ctx) {
        console.error("Không tìm thấy canvas revenueChart");
        return;
    }

    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['1','5','10','15','20','25','30'],
            datasets: [{
                label: 'Doanh thu (VNĐ)',
                data: [200, 350, 500, 420, 680, 900, 1200],
                borderWidth: 3
            }]
        }
    });
});

   