/* ==========================
   NAVBAR TOGGLE LOGIC (Đơn giản)
   ========================== */
function initNavbarToggle() {
    var navbarToggler = document.querySelector('.navbar-toggler');
    var navCollapse = document.querySelector('#navbarNav');

    // Kiểm tra nếu cả nút toggle và menu đều tồn tại
    if (navbarToggler && navCollapse) {
        navbarToggler.onclick = function() {
            // Dùng classList.toggle() để chuyển đổi class 'show' (ẩn/hiện)
            navCollapse.classList.toggle('show');
            // Dùng classList.toggle() để chuyển đổi class 'collapse' (cho hiệu ứng)
            navCollapse.classList.toggle('collapse');
        };
    }
}


/* ==========================
   TABS LOGIC (Đơn giản)
   ========================== */
function initTabs() {
    var tabContainers = document.querySelectorAll('[role="tablist"]');

    // Lặp qua tất cả các container chứa tab (có thể có ở login, products)
    for (var i = 0; i < tabContainers.length; i++) {
        var container = tabContainers[i];

        container.onclick = function(event) {
            var button = event.target.closest('[role="tab"]');
            if (!button) {
                return; // Không phải là nút tab, thoát
            }

            event.preventDefault();

            // 1. Tắt tab và panel đang hoạt động (active)
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


/* ==========================
   CAROUSEL LOGIC (Đơn giản)
   ========================== */
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
        // Xóa interval cũ trước khi tạo mới (phòng trường hợp đã có)
        clearInterval(slideInterval);
        slideInterval = setInterval(nextSlide, interval);
    }

    // 2. Thiết lập nút điều khiển
    var nextButton = carousel.querySelector('.carousel-control-next');
    var prevButton = carousel.querySelector('.carousel-control-prev');

    if (nextButton) {
        nextButton.onclick = function() {
            startAutoSlide(); // Khởi động lại timer sau khi ấn
            nextSlide();
        };
    }

    if (prevButton) {
        prevButton.onclick = function() {
            startAutoSlide(); // Khởi động lại timer sau khi ấn
            prevSlide();
        };
    }

    // Bắt đầu
    showItem(currentIndex);
    startAutoSlide();
}


// Chạy tất cả các chức năng sau khi DOM đã tải
document.addEventListener('DOMContentLoaded', function() {
    initNavbarToggle();
    initTabs();

    // Chỉ khởi tạo carousel nếu phần tử tồn tại trên trang
    if (document.getElementById('hero-carousel')) {
        initCarousel('hero-carousel', 3000);
    }
});
//Đăng nhập với tên là admin và mật khẩu là 123
document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("loginForm");

    if (loginForm) {
        loginForm.addEventListener("submit", function (e) {
            e.preventDefault();
            const username = document.getElementById("loginEmail").value.trim();
            const password = document.getElementById("loginPassword").value.trim();

            if (username === "admin" && password === "123") {
                alert("Đăng nhập thành công!");
                window.location.href = "ad_index.html"; 
            } else {
                alert("Sai thông tin đăng nhập!");
            }
        });
    }
});

