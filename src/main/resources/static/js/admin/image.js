const avatarPreview = document.getElementById('avatarPreview');
const avatarInput = document.getElementById('avatarInput');
const temporaryMessage = document.getElementById('temporaryMessage');

const certificateReview = document.getElementById('certificateReview');
const certificateImage = document.getElementById('certificateImage');
const temporaryMessage2 = document.getElementById('temporaryMessage2');

// Xử lý khi người dùng chọn ảnh đại diện (avatar)
avatarPreview.addEventListener('click', () => {
    avatarInput.click();  // Mở cửa sổ chọn tệp
});

certificateReview.addEventListener('click', () => {
    certificateImage.click();  // Mở cửa sổ chọn tệp chứng chỉ
});

// Xử lý khi người dùng thay đổi tệp ảnh đại diện
avatarInput.addEventListener('change', (event) => {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            avatarPreview.src = e.target.result;  // Cập nhật ảnh preview
            temporaryMessage.style.display = 'block';  // Hiển thị thông báo
        };
        reader.readAsDataURL(file);
    }
});

// Xử lý khi người dùng thay đổi tệp chứng chỉ
certificateImage.addEventListener('change', (event) => {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            certificateReview.src = e.target.result;  // Cập nhật ảnh preview
            temporaryMessage2.style.display = 'block';  // Hiển thị thông báo
        };
        reader.readAsDataURL(file);
    }
});