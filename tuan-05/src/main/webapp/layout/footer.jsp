    </div> <!-- End of container -->
    
    <!-- Footer -->
    <footer style="background-color: #343a40; color: white; text-align: center; padding: 2rem 0; margin-top: 3rem;">
        <div class="container">
            <p>&copy; 2024 Quản Lý Điện Thoại - Được phát triển bởi IUH Student</p>
            <p>Ứng dụng quản lý thông tin sản phẩm điện thoại cho công ty mua bán điện thoại trực tuyến</p>
        </div>
    </footer>
    
    <!-- JavaScript for enhanced functionality -->
    <script>
        // Form validation
        function validateForm(formId) {
            const form = document.getElementById(formId);
            if (!form) return true;
            
            const requiredFields = form.querySelectorAll('[required]');
            let isValid = true;
            
            requiredFields.forEach(field => {
                if (!field.value.trim()) {
                    field.classList.add('is-invalid');
                    isValid = false;
                } else {
                    field.classList.remove('is-invalid');
                }
            });
            
            // Validation for year field
            const yearField = form.querySelector('input[name="namSanXuat"]');
            if (yearField && yearField.value) {
                const year = parseInt(yearField.value);
                if (year < 1900 || year > 2100) {
                    yearField.classList.add('is-invalid');
                    isValid = false;
                }
            }
            
            // Validation for configuration field
            const configField = form.querySelector('input[name="cauHinh"]');
            if (configField && configField.value && configField.value.length > 255) {
                configField.classList.add('is-invalid');
                isValid = false;
            }
            
            return isValid;
        }
        
        // File upload validation
        function validateFileUpload(input) {
            const file = input.files[0];
            if (!file) return true;
            
            const allowedTypes = ['image/png', 'image/jpg', 'image/jpeg'];
            const maxSize = 5 * 1024 * 1024; // 5MB
            
            if (!allowedTypes.includes(file.type)) {
                alert('Chỉ chấp nhận file ảnh PNG, JPG, JPEG!');
                input.value = '';
                return false;
            }
            
            if (file.size > maxSize) {
                alert('Kích thước file không được vượt quá 5MB!');
                input.value = '';
                return false;
            }
            
            return true;
        }
        
        // Auto refresh alerts after 5 seconds
        setTimeout(function() {
            const alerts = document.querySelectorAll('.alert');
            alerts.forEach(function(alert) {
                if (alert.classList.contains('alert-success') || alert.classList.contains('alert-info')) {
                    alert.style.opacity = '0.5';
                    setTimeout(function() {
                        alert.remove();
                    }, 1000);
                }
            });
        }, 5000);
        
        // Confirm delete action
        function confirmDelete(message = 'Bạn có chắc chắn muốn xóa không?') {
            return confirm(message);
        }
        
        // Auto-hide forms after successful submission
        function autoHideForm() {
            setTimeout(function() {
                const form = document.querySelector('form');
                if (form && form.querySelector('.alert-success')) {
                    form.style.display = 'none';
                }
            }, 2000);
        }
    </script>
</body>
</html>
