// 관리자 계정 정보 (실제 운영 시에는 서버에서 관리해야 함)
const ADMIN_CREDENTIALS = {
    username: 'admin',
    password: 'admin123'
};

// DOM 요소들
const loginForm = document.getElementById('loginForm');
const loginMessage = document.getElementById('loginMessage');

// 페이지 로드 시 로그인 상태 확인
document.addEventListener('DOMContentLoaded', function() {
    // 이미 로그인되어 있는지 확인
    if (sessionStorage.getItem('adminLoggedIn') === 'true') {
        window.location.href = 'admin.html';
        return;
    }
    
    // 폼 제출 이벤트
    loginForm.addEventListener('submit', handleLogin);
});

// 로그인 처리
async function handleLogin(event) {
    event.preventDefault();
    
    const formData = new FormData(loginForm);
    const username = formData.get('username');
    const password = formData.get('password');
    
    // 로그인 메시지 초기화
    loginMessage.textContent = '';
    loginMessage.className = 'login-message';
    
    // 간단한 인증 (실제 운영 시에는 서버에서 처리해야 함)
    if (username === ADMIN_CREDENTIALS.username && password === ADMIN_CREDENTIALS.password) {
        // 로그인 성공
        sessionStorage.setItem('adminLoggedIn', 'true');
        sessionStorage.setItem('adminUsername', username);
        
        loginMessage.textContent = '로그인 성공! 관리자 페이지로 이동합니다...';
        loginMessage.className = 'login-message success';
        
        // 잠시 후 관리자 페이지로 이동
        setTimeout(() => {
            window.location.href = 'admin.html';
        }, 1000);
    } else {
        // 로그인 실패
        loginMessage.textContent = '아이디 또는 비밀번호가 올바르지 않습니다.';
        loginMessage.className = 'login-message error';
        
        // 비밀번호 필드 초기화
        document.getElementById('password').value = '';
    }
}

// 로그아웃 함수 (다른 페이지에서 사용)
function logout() {
    sessionStorage.removeItem('adminLoggedIn');
    sessionStorage.removeItem('adminUsername');
    window.location.href = 'login.html';
} 