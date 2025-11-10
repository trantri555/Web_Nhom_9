/* ==========================
   NAVBAR TOGGLE LOGIC
   Chức năng: Thay thế data-bs-toggle="collapse" cho Navbar
   ========================== */
document.addEventListener('DOMContentLoaded', () => {
    const navbarToggler = document.querySelector('.navbar-toggler');
    const navCollapse = document.querySelector('#navbarNav');

    if (navbarToggler && navCollapse) {
        navbarToggler.addEventListener('click', () => {
            // Toggle Bootstrap's 'collapse' class for animation
            navCollapse.classList.toggle('collapse');
            navCollapse.classList.toggle('show'); // 'show' makes the content visible
        });
    }
});

/* ==========================
   TABS LOGIC (Cho login.html, products.html)
   Chức năng: Thay thế data-bs-toggle="tab"
   ========================== */
document.addEventListener('DOMContentLoaded', () => {
    const tabContainers = document.querySelectorAll('[role="tablist"]');

    tabContainers.forEach(container => {
        container.addEventListener('click', (e) => {
            const button = e.target.closest('[role="tab"]');
            if (!button) return;

            e.preventDefault();

            // 1. Deactivate current active tab and panel
            container.querySelectorAll('.nav-link.active').forEach(activeTab => {
                activeTab.classList.remove('active');
            });
            // Find and deactivate the active tab panel (must be within the same parent or document)
            document.querySelectorAll('.tab-pane.show.active').forEach(activePanel => {
                activePanel.classList.remove('show', 'active');
            });

            // 2. Activate new tab
            button.classList.add('active');

            // 3. Activate new panel
            const targetId = button.getAttribute('data-bs-target');
            if (targetId) {
                const targetPanel = document.querySelector(targetId);
                if (targetPanel) {
                    targetPanel.classList.add('show', 'active');
                }
            }
        });
    });
});


/* ==========================
   CAROUSEL LOGIC (Cho index.html)
   Chức năng: Thay thế data-bs-ride="carousel" và controls
   ========================== */
const initCarousel = (id, interval = 3000) => {
    const carousel = document.getElementById(id);
    if (!carousel) return;

    const items = carousel.querySelectorAll('.carousel-item');
    if (items.length === 0) return;
    let currentIndex = 0;

    const showItem = (index) => {
        items.forEach((item, i) => {
            item.classList.remove('active');
            if (i === index) {
                item.classList.add('active');
            }
        });
    };

    const nextSlide = () => {
        currentIndex = (currentIndex + 1) % items.length;
        showItem(currentIndex);
    };

    const prevSlide = () => {
        currentIndex = (currentIndex - 1 + items.length) % items.length;
        showItem(currentIndex);
    };

    // Auto slide
    let slideInterval = setInterval(nextSlide, interval);

    // Controls
    const nextButton = carousel.querySelector('.carousel-control-next');
    const prevButton = carousel.querySelector('.carousel-control-prev');

    if (nextButton) {
        nextButton.addEventListener('click', () => {
            clearInterval(slideInterval);
            nextSlide();
            slideInterval = setInterval(nextSlide, interval);
        });
    }

    if (prevButton) {
        prevButton.addEventListener('click', () => {
            clearInterval(slideInterval);
            prevSlide();
            slideInterval = setInterval(nextSlide, interval);
        });
    }

    showItem(currentIndex);
};

// Khởi tạo Carousel khi DOM đã load
document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('hero-carousel')) {
        initCarousel('hero-carousel', 3000);
    }
});