// admin-chart.js

document.addEventListener('DOMContentLoaded', function () {
    // 1. Lấy dữ liệu từ các thẻ input hidden hoặc biến global (được JSP render ra)
    // Cách này đơn giản: JSP render ra thẻ <input type="hidden" id="..."/> chứa JSON
    // hoặc gán thẳng vào biến JS trong thẻ <script> ở JSP.
    // Ở đây ta giả sử JSP sẽ gán vào biến global: window.revenueLabels và window.revenueData

    const labels = window.revenueLabels || [];
    const data = window.revenueData || [];

    const ctx = document.getElementById('revenueChart');
    if (ctx) {
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Doanh thu (VNĐ)',
                    data: data,
                    borderColor: '#28a745', // Màu xanh lá success
                    backgroundColor: 'rgba(40, 167, 69, 0.1)',
                    borderWidth: 2,
                    pointBackgroundColor: '#28a745',
                    pointRadius: 4,
                    tension: 0.3, // Bo cong nhẹ
                    fill: true
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false, // Để chart tự co giãn theo div cha
                plugins: {
                    legend: {
                        display: false // Ẩn chú thích nếu chỉ có 1 dòng
                    },
                    tooltip: {
                        callbacks: {
                            label: function (context) {
                                let label = context.dataset.label || '';
                                if (label) {
                                    label += ': ';
                                }
                                if (context.parsed.y !== null) {
                                    label += new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(context.parsed.y);
                                }
                                return label;
                            }
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            callback: function (value, index, values) {
                                return new Intl.NumberFormat('vi-VN', { notation: "compact", compactDisplay: "short" }).format(value);
                            }
                        }
                    }
                }
            }
        });
    }
});
