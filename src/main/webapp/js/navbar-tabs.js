// NAVBAR TOGGLE LOGIC
export function initNavbarToggle() {
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
export function initTabs() {
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